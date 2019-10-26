package github.com.kebbbnnn.lightgallery.utils;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by kevinladan on 4/14/17.
 */

public class ColorUtils {

  private ColorUtils() {

  }

  public static int getColor(Context context, int colorId) {
    return context.getResources().getColor(colorId);
  }

  public static int getDominantColor(Bitmap bitmap) {
    Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);
    final int color = newBitmap.getPixel(0, 0);
    newBitmap.recycle();
    return color;
  }
}
