package github.com.kebbbnnn.lightgallery.utils;

import android.graphics.Bitmap;
import android.view.View;

public class ViewUtils {

  /**
   * Returns bitmap from view's drawing cache
   *
   * @param view
   * @return
   */
  public static Bitmap createBitmapFromView(View view) {
    Bitmap bitmap;
    view.setDrawingCacheEnabled(true);
    view.buildDrawingCache();
    Bitmap src = view.getDrawingCache();
    bitmap = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight());
    view.destroyDrawingCache();
    view.setDrawingCacheEnabled(false);
    src.recycle();
    return bitmap;
  }
}
