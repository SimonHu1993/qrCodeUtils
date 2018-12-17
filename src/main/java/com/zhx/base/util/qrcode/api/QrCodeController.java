package com.zhx.base.util.qrcode.api;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zhx.base.util.qrcode.base.NumUtil;
import com.zhx.base.util.qrcode.wrapper.QrCodeGenWrapper;
import com.zhx.base.util.qrcode.wrapper.QrCodeOptions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
  * @Description:api调用方式
  * @Author:SimonHu
  * @Date: 2018/12/17 15:04
  */
@Controller
@RequestMapping("/qrcode")
public class QrCodeController {

    private static final String QR_CONTENT = "https://api.zihexin.com/scanner?type=1&code=####&action=&url=";
    private static final Logger log = LoggerFactory.getLogger(QrCodeController.class);

    /**
     * 跳转二维码页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping("/showQrcodeIndex")
    public ModelAndView showQrcodeIndex(ModelAndView modelAndView,
                                        @RequestParam(value = "id", required = false, defaultValue = "") String id,
                                        @RequestParam(value = "enterpriseId", required = false, defaultValue = "") String enterpriseId) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("enterpriseId", enterpriseId);
        StringBuffer sb = new StringBuffer();
        if (enterpriseId.contains(",")) {
            String[] enterpriseIds = enterpriseId.split(",");
            for (String eid : enterpriseIds) {
                sb = sb.append(StringUtils.isNotEmpty(eid) ? QR_CONTENT.replace("####", eid) : "");
                sb.append(",");
            }
            map.put("content", sb.toString().substring(0, sb.lastIndexOf(",")));
            map.put("list", enterpriseId);
            map.put("number", enterpriseIds.length);
        } else {
            map.put("content", StringUtils.isNotEmpty(enterpriseId) ? QR_CONTENT.replace("####", enterpriseId) : "");
        }
        modelAndView.addObject("data", map);
        modelAndView.setViewName("/qrcode/qrcode");
        return modelAndView;
    }


    /**
     * 生成二维码
     * 测试case:  http://localhost:8080/qrcode/gen?content=https://my.oschina.net/u/566591/blog/1359432
     * <p>
     * 返回base64 编码的二维码图片， 前端可以直接使用  <img src="data:image/png;base64,="/>
     *
     * @param httpServletRequest
     * @param qrCodeRequest
     * @return
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public Map create(HttpServletRequest httpServletRequest,
                      QrCodeRequest qrCodeRequest) {
        String content = qrCodeRequest.getContent();
        if (content.contains(",")) {
            content = content.substring(0, content.indexOf(","));
            qrCodeRequest.setContent(content);
        }
        try {
            String qr = this.build(qrCodeRequest).asString();
            qr = "data:image/png;base64," + qr;
            final String finalQr = qr;
            return new HashMap() {{
                put(000, finalQr);
            }};
        } catch (Exception e) {
            log.error("create qrcode error! request:{}, e: {}", qrCodeRequest, e);
            return new HashMap() {{
                put(5001, "gen qrcode error!");
            }};
        }
    }


    /**
     * 构造二维码参数
     *
     * @param request
     * @return
     * @throws IOException
     */
    private QrCodeGenWrapper.Builder build(QrCodeRequest request) throws IOException {
        QrCodeGenWrapper.Builder builder = QrCodeGenWrapper.of(request.getContent())
                .setW(request.getSize())
                .setH(request.getSize())
                .setPadding(request.getPadding())
                .setCode(request.getCharset());
        QrCodeOptions.DrawStyle drawStyle = QrCodeOptions.DrawStyle.getDrawStyle(request.getDrawStyle());
        builder.setDrawStyle(drawStyle);
        if (drawStyle == QrCodeOptions.DrawStyle.IMAGE) {
            builder.setDrawImg(request.getDrawImg())
                    .setDrawSize4Img(request.getDrawSize4Img())
                    .setDrawRow2Img(request.getDrawRow2Img())
                    .setDrawCol2Img(request.getDrawCol2Img());
        } else {
            builder.setDrawBgColor(NumUtil.decode2int(request.getBgColor(), 0xFFFFFFFF))
                    .setDrawPreColor(NumUtil.decode2int(request.getPreColor(), 0xFF000000));
        }
        buildErrorStyle(builder, request);
        buildLogoConfig(builder, request);
        buildDetectConfig(builder, request);
        buildBgConfig(builder, request);
        return builder;
    }

    private void buildLogoConfig(QrCodeGenWrapper.Builder builder, QrCodeRequest request) throws IOException {
        if (StringUtils.isBlank(request.getLogo())) {
            return;
        }
        builder.setLogo(request.getLogo())
                .setLogoRate(request.getLogoRate() == null ? 12 : request.getLogoRate())
                .setLogoBorder(request.isLogoBorder())
                .setLogoStyle(QrCodeOptions.LogoStyle.getStyle(request.getLogo()))
                .setLogoBgColor(NumUtil.decode2int(request.getLogoBorderColor(), null));
    }

    private void buildDetectConfig(QrCodeGenWrapper.Builder builder, QrCodeRequest request) throws IOException {
        if (StringUtils.isNotBlank(request.getDetectImg())) {
            builder.setDetectImg(request.getDetectImg());
            return;
        }
        if (StringUtils.isNotBlank(request.getDetectInColor())) {
            builder.setDetectInColor(NumUtil.decode2int(request.getDetectInColor(), null));
        }
        if (StringUtils.isNotBlank(request.getDetectOutColor())) {
            builder.setDetectOutColor(NumUtil.decode2int(request.getDetectOutColor(), null));
        }
    }

    private void buildBgConfig(QrCodeGenWrapper.Builder builder, QrCodeRequest request) throws IOException {
        if (StringUtils.isBlank(request.getBgImg())) {
            return;
        }
        builder.setBgImg(request.getBgImg())
                .setBgW(request.getBgW() == null ? 0 : request.getBgW())
                .setBgH(request.getBgH() == null ? 0 : request.getBgH());
        QrCodeOptions.BgImgStyle style = QrCodeOptions.BgImgStyle.getStyle(request.getBgStyle());
        if (style == QrCodeOptions.BgImgStyle.FILL) {
            builder.setBgStartY(request.getBgY() == null ? 0 : request.getBgY())
                    .setBgStartX(request.getBgX() == null ? 0 : request.getBgX());
            return;
        }
        builder.setBgOpacity(request.getBgOpacity() == null ? 0.85f : request.getBgOpacity());
    }

    private void buildErrorStyle(QrCodeGenWrapper.Builder builder, QrCodeRequest request) {
        if ("l".equalsIgnoreCase(request.getErrorStyle())) {
            builder.setErrorCorrection(ErrorCorrectionLevel.L);
        } else if ("m".equalsIgnoreCase(request.getErrorStyle())) {
            builder.setErrorCorrection(ErrorCorrectionLevel.M);
        } else if ("q".equalsIgnoreCase(request.getErrorStyle())) {
            builder.setErrorCorrection(ErrorCorrectionLevel.Q);
        } else {
            builder.setErrorCorrection(ErrorCorrectionLevel.H);
        }
    }
}
