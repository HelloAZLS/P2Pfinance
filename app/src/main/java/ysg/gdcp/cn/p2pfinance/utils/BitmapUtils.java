package ysg.gdcp.cn.p2pfinance.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 * Created by Administrator on 2017/3/16 07:30.
 *
 * @author ysg
 */

public class BitmapUtils {
    public static Bitmap circleBitMap(Bitmap bitmap) {
        final Paint paint = new Paint();
        int width = bitmap.getWidth();
        paint.setAntiAlias(true);
        Bitmap reslut = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.drawCircle(width / 2, width / 2, width / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return reslut;
    }

    public  static Bitmap zoom(Bitmap bitmap,float w,float h){
        Matrix matrix = new Matrix();
        float sx = w/bitmap.getWidth();
        float sh = h/bitmap.getHeight();
        matrix.postScale(sx,sh);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

    }
}
