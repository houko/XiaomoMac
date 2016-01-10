package com.xiaomo.TimeMachine.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

/**
 * 二维码处理类
 * @author L.cm
 * email: 596392912@qq.com
 * site:http://www.dreamlu.net
 * @date 2014-3-28 5:11:43
 */
public class QRCodeUtil {

	/**
	 * 二维码编码
	 * @throws WriterException
	 * @throws IOException 
	 */
	public static void encode(OutputStream out, String code, int width) throws WriterException, IOException {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // 文字编码。
		hints.put(EncodeHintType.MARGIN, 1); // 二维码白边，默认是4 “1,2,3,4”
		BitMatrix bmx = new QRCodeWriter().encode(code, BarcodeFormat.QR_CODE, width, width, hints);
		MatrixToImageWriter.writeToStream(bmx, "jpg", out);
	}

	/**
	 * 二维码解码
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FormatException
	 * @throws ChecksumException
	 * @throws NotFoundException
	 */
	public static String decode(String path) throws MalformedURLException, IOException, NotFoundException, ChecksumException, FormatException {
		BufferedImage image = null;
		if (path.startsWith("http://")) {
			image = ImageIO.read(new URL(path));
		} else {
			image = ImageIO.read(new File(path));
		}
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result = new QRCodeReader().decode(bitmap);
		// 注意解密的时候可能有乱码问题和编码时的格式有关系
		// new String(result.getText().getBytes("ISO-8859-1"), "gbk") 可以解决
		return result.getText();
	}
}
