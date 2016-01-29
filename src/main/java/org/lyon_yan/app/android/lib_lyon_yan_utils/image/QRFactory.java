package org.lyon_yan.app.android.lib_lyon_yan_utils.image;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Hashtable;

/**
 * Created by Lyon_Yan on 2015/4/1 0001.
 */
public class QRFactory {
    private static final int BLACK = 0xff000000;

    public static Bitmap createQRCode(String str, int widthAndHeight)
            throws WriterException {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix matrix = new MultiFormatWriter().encode(str,
                BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = BLACK;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
//
//	public static String getQRValue(Bitmap bitmap) {
//		Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
//		hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
//		RGBLuminanceSource source = new RGBLuminanceSource(bitmap);
//		BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
//		QRCodeReader reader = new QRCodeReader();
//		Result result;
//		try {
//			result = reader.decode(bitmap1, hints);
//			// 得到解析后的文字
//			return result.getText();
//		} catch (NotFoundException e) {
//			e.printStackTrace();
//		} catch (ChecksumException e) {
//			e.printStackTrace();
//		} catch (FormatException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
}
