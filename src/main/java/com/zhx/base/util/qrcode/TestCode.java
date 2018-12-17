package com.zhx.base.util.qrcode;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zhx.base.util.qrcode.base.Base64Util;
import com.zhx.base.util.qrcode.wrapper.QrCodeGenWrapper;
import com.zhx.base.util.qrcode.wrapper.QrCodeOptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @Author: SimonHu
 * @Date: 2018/12/17 14:01
 * @Description:测试工具类
 */
public class TestCode {

    public static void main(String[] args) {
        String msg = "http://blog.zbang.online:8080";
        // 根据本地文件生成待logo的二维码， 重新着色位置探测图像
        try {
            String logo = "C:\\Users\\admin\\Desktop\\github.jpg";
            String bg = "C:\\Users\\admin\\Desktop\\github.jpg";
            BufferedImage img = QrCodeGenWrapper.of(msg)
                    .setW(500)
                    .setDrawPreColor(0xff002fa7) // 宝石蓝
                    .setDetectOutColor(0xff00ff00)
                    .setDetectInColor(0xffff0000)
                    .setPadding(1)
                    .setErrorCorrection(ErrorCorrectionLevel.H)
                    .setLogo(logo)
                    .setLogoStyle(QrCodeOptions.LogoStyle.ROUND)
                    .setLogoBgColor(0xff00cc00)
                    .setBgImg(bg)
                    .setDrawStyle(QrCodeOptions.DrawStyle.IMAGE.name())
                    .setDrawImg(bg)
                    .asBufferedImage();


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(img, "png", outputStream);
            String img64 = Base64Util.encode(outputStream);
            System.out.println(img64);
            System.out.println("\r\n");
            QrcodeUtil.base64ToFile("C:\\Users\\admin\\Desktop",img64,"code.jpg");
//            System.out.println("<img src=\"data:image/png;base64," + img64 + "\" />");
        } catch (Exception e) {
            System.out.println("create qrcode error! e: " + e);
        }
    }
    public void testGenColorCode() {

    }
}
