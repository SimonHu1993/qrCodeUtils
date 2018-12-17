package com.zhx.base.util.qrcode;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

public class QrcodeUtil {

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int WIDTH = 60;
    // LOGO高度
    private static final int HEIGHT = 60;

    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\admin\\Desktop\\github.jpg");
            InputStream inputStream = new FileInputStream(file);
            BufferedImage img = createImage("https://www.cnblogs.com/SimonHu1993/",inputStream,true);
//			insertImage(img,inputStream,true);
            String b64 = bfimgToBase64(img);
            System.out.println("data:image/png;base64,"+b64);
            base64ToFile("C:\\Users\\admin\\Desktop",b64,"code.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成二维码
     * @param content 二维码内容
     * @param imgPath 二维码logo图片流文件
     * @param needCompress logo是否压缩
     * @return
     * @throws Exception
     */
    public static BufferedImage createImage(String content, InputStream imgPath, boolean needCompress) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        if(imgPath == null || "".equals(imgPath)){
            return image;
        }
        QrcodeUtil.insertImage(image,imgPath,needCompress);
        return image;
    }

    public static String bfimgToBase64(BufferedImage bufferedImage){
        String imgBase64 = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpeg", outputStream);
            BASE64Encoder encoder = new BASE64Encoder();
            imgBase64 = encoder.encode(outputStream.toByteArray()).trim().replaceAll("\\s", "").replaceAll("\r", "").replaceAll("\n", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgBase64;
    }

    /**
     *
     * @param source 原图片文件
     * @param inputStream 加载logo输入流
     * @param needCompress 是否缩放
     * @throws Exception
     */
    public static void insertImage(BufferedImage source, InputStream inputStream,
                                   boolean needCompress) throws Exception {
        Image logoImage = ImageIO.read(inputStream);
        int width = logoImage.getWidth(null);
        int height = logoImage.getHeight(null);
        // 压缩LOGO
        if (needCompress) {
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = logoImage.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null);
            g.dispose();
            logoImage = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(logoImage, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }
    /**
     *BASE64解码成File文件
     */
    public static void base64ToFile(String destPath,String base64, String fileName) {
        File file = null;
        //创建文件目录
        String filePath=destPath;
        File  dir=new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.decode(base64);
            file=new File(filePath+File.separator+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
