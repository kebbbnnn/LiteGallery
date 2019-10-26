package github.com.kebbbnnn.lightgallery.presenter;

import android.util.Log;

/**
 * Created by kevinladan on 4/14/17.
 */

public class BasePresenter {
  protected void printlog(String s) {
    Log.i(this.getClass().getName(), s);
  }
}
