package com.zhx.base.util.qrcode.base;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * Created by yihui on 2017/7/17.
 */
public class Base64Util {

    public static String encode(BufferedImage bufferedImage, String imgType) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, imgType, outputStream);
        return encode(outputStream);
    }

    public static String encode(ByteArrayOutputStream outputStream) {
        byte[] lens = outputStream.toByteArray();
        String result  = Base64.encodeBase64String(lens);
        //jdk1.8写法
        //return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        return result;
    }


    public static BufferedImage decode2Img(String base64) throws IOException {
        byte[] bytes = new byte[0];
        try {
            bytes = Base64.decodeBase64(base64);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //jdk1.8写法
        //byte[] bytes = Base64.getDecoder().decode(base64.getBytes("utf-8"));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return ImageIO.read(inputStream);
    }
}
