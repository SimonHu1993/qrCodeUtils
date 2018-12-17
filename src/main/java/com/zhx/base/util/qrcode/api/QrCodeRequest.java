package com.zhx.base.util.qrcode.api;


import com.zhx.base.util.qrcode.wrapper.QrCodeOptions;

public class QrCodeRequest {
    /**
     * 二维码编号
     */
    private String ids="";

    private String content="";

    private Integer size = 200;

    /**
     * 绘制二维码的背景色
     */
    private String bgColor = "0xffffffff";

    /**
     * 绘制二维码的前置色
     */
    private String preColor = "0xff000000";

    /**
     * 绘制二维码的图片
     */
    private String drawImg="";

    private String drawRow2Img="";

    private String drawCol2Img="";

    private String drawSize4Img="";

    /**
     * 绘制二维码的样式
     */
    private String drawStyle = QrCodeOptions.DrawStyle.RECT.name();

    /**
     * 二维码边框留白， 取值 [0, 4]
     */
    private Integer padding = 1;


    /**
     * 二维码文本信息的编码格式
     */
    private String charset = "UTF-8";


    // 探测图形

    /**
     * 探测图形外边框颜色
     */
    private String detectOutColor="";


    /**
     * 探测图形内边框颜色
     */
    private String detectInColor="";


    /**
     * 位置探测图形图片
     */
    private String detectImg="";


    // logo 相关

    /**
     * logo的http格式地址
     */
    private String logo="";

    /**
     * logo 占二维码大小的比例
     */
    private Integer logoRate=12;


    /**
     * logo的样式: ROUND & NORMAL
     */
    private String logoStyle = QrCodeOptions.LogoStyle.NORMAL.name();

    /**
     * logo边框是否存在
     */
    private Boolean logoBorder = true;

    /**
     * logo边框颜色
     */
    private String logoBorderColor="";


    // 背景相关

    /**
     * 背景图
     */
    private String bgImg="";

    /**
     * 背景图宽
     */
    private Integer bgW=0;

    /**
     * 背景图高
     */
    private Integer bgH=0;

    /**
     * 填充模式时，二维码在背景上的起始x坐标
     */
    private Integer bgX=0;

    /**
     * 填充模式时，二维码在背景上的起始y坐标
     */
    private Integer bgY=0;

    /**
     * 全覆盖模式时，二维码的透明度
     */
    private Float bgOpacity=0.85f;


    /**
     * 背景样式，默认为全覆盖模式
     */
    private String bgStyle = QrCodeOptions.BgImgStyle.OVERRIDE.name();


    private String errorStyle="";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor.startsWith("#") ? bgColor.replace("#", "0xFF") : bgColor;
    }

    public String getPreColor() {
        return preColor;
    }

    public void setPreColor(String preColor) {
        this.preColor = preColor.startsWith("#") ? preColor.replace("#", "0xFF") : preColor;
    }

    public String getDrawImg() {
        return drawImg;
    }

    public void setDrawImg(String drawImg) {
        this.drawImg = drawImg;
    }

    public String getDrawRow2Img() {
        return drawRow2Img;
    }

    public void setDrawRow2Img(String drawRow2Img) {
        this.drawRow2Img = drawRow2Img;
    }

    public String getDrawCol2Img() {
        return drawCol2Img;
    }

    public void setDrawCol2Img(String drawCol2Img) {
        this.drawCol2Img = drawCol2Img;
    }

    public String getDrawSize4Img() {
        return drawSize4Img;
    }

    public void setDrawSize4Img(String drawSize4Img) {
        this.drawSize4Img = drawSize4Img;
    }

    public String getDrawStyle() {
        return drawStyle;
    }

    public void setDrawStyle(String drawStyle) {
        this.drawStyle = drawStyle;
    }

    public Integer getPadding() {
        return padding;
    }

    public void setPadding(Integer padding) {
        this.padding = padding;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getDetectOutColor() {
        return detectOutColor;
    }

    public void setDetectOutColor(String detectOutColor) {
        this.detectOutColor = detectOutColor.startsWith("#") ? detectOutColor.replace("#", "0xFF") : detectOutColor;
    }

    public String getDetectInColor() {
        return detectInColor;
    }

    public void setDetectInColor(String detectInColor) {
        this.detectInColor = detectInColor.startsWith("#") ? detectInColor.replace("#", "0xFF") : detectInColor;
    }

    public String getDetectImg() {
        return detectImg;
    }

    public void setDetectImg(String detectImg) {
        this.detectImg = detectImg;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getLogoRate() {
        return logoRate;
    }

    public void setLogoRate(Integer logoRate) {
        this.logoRate = logoRate;
    }

    public String getLogoStyle() {
        return logoStyle;
    }

    public void setLogoStyle(String logoStyle) {
        this.logoStyle = logoStyle;
    }

    public boolean isLogoBorder() {
        return logoBorder;
    }

    public void setLogoBorder(boolean logoBorder) {
        this.logoBorder = logoBorder;
    }

    public String getLogoBorderColor() {
        return logoBorderColor;
    }

    public void setLogoBorderColor(String logoBorderColor) {
        this.logoBorderColor = logoBorderColor.startsWith("#") ? logoBorderColor.replace("#", "0xFF") : logoBorderColor;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public Integer getBgW() {
        return bgW;
    }

    public void setBgW(Integer bgW) {
        this.bgW = bgW;
    }

    public Integer getBgH() {
        return bgH;
    }

    public void setBgH(Integer bgH) {
        this.bgH = bgH;
    }

    public Integer getBgX() {
        return bgX;
    }

    public void setBgX(Integer bgX) {
        this.bgX = bgX;
    }

    public Integer getBgY() {
        return bgY;
    }

    public void setBgY(Integer bgY) {
        this.bgY = bgY;
    }

    public Float getBgOpacity() {
        return bgOpacity;
    }

    public void setBgOpacity(Float bgOpacity) {
        this.bgOpacity = bgOpacity;
    }

    public String getBgStyle() {
        return bgStyle;
    }

    public void setBgStyle(String bgStyle) {
        this.bgStyle = bgStyle;
    }

    public String getErrorStyle() {
        return errorStyle;
    }

    public void setErrorStyle(String errorStyle) {
        this.errorStyle = errorStyle;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "QrCodeRequest{" +
                "ids='" + ids + '\'' +
                ", content='" + content + '\'' +
                ", size=" + size +
                ", bgColor='" + bgColor + '\'' +
                ", preColor='" + preColor + '\'' +
                ", drawImg='" + drawImg + '\'' +
                ", drawRow2Img='" + drawRow2Img + '\'' +
                ", drawCol2Img='" + drawCol2Img + '\'' +
                ", drawSize4Img='" + drawSize4Img + '\'' +
                ", drawStyle='" + drawStyle + '\'' +
                ", padding=" + padding +
                ", charset='" + charset + '\'' +
                ", detectOutColor='" + detectOutColor + '\'' +
                ", detectInColor='" + detectInColor + '\'' +
                ", detectImg='" + detectImg + '\'' +
                ", logo='" + logo + '\'' +
                ", logoRate=" + logoRate +
                ", logoStyle='" + logoStyle + '\'' +
                ", logoBorder=" + logoBorder +
                ", logoBorderColor='" + logoBorderColor + '\'' +
                ", bgImg='" + bgImg + '\'' +
                ", bgW=" + bgW +
                ", bgH=" + bgH +
                ", bgX=" + bgX +
                ", bgY=" + bgY +
                ", bgOpacity=" + bgOpacity +
                ", bgStyle='" + bgStyle + '\'' +
                ", errorStyle='" + errorStyle + '\'' +
                '}';
    }
}
