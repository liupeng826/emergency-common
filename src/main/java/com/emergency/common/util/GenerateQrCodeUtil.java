package com.emergency.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static com.google.zxing.client.j2se.MatrixToImageWriter.toBufferedImage;

/**
 * 二维码生成工具类
 *
 * @author yujl
 */
public class GenerateQrCodeUtil {

    /**
     * 生成二维码图片 输出到流
     * @param content
     * @param outputStream
     */
    public static void encodeQrcode(String content, OutputStream outputStream) {
        if (StringUtils.isBlank(content))
            return;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //设置字符集编码类型
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
            BufferedImage image = toBufferedImage(bitMatrix);
            //输出二维码图片流
            try {
                ImageIO.write(image, "png", outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WriterException e1) {
            e1.printStackTrace();
        }
    }
}
