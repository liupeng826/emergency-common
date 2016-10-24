package com.emergency.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.util.Hashtable;

/**
 * google zxing 生成二维码
 */
public class QrcodeUtil {
    public static void createQrcode(int width, int height, String format, String text) throws Exception {

//        text = "http://www.baidu.com";
//        width = 300;
//        height = 300;
//        //二维码的图片格式
//        format = "gif";
        Hashtable hints = new Hashtable();
        //内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
                BarcodeFormat.QR_CODE, width, height, hints);
        //生成二维码
        File outputFile = new File("/Users/liutao/new.gif");
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
    }

}
