package github.com.kebbbnnn.lightgallery.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by kevinladan on 4/13/17.
 */

public abstract class BaseActivity extends Activity {

  protected void printlog(String s) {
    Log.i(this.getClass().getName(), s);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    onPreCreateUI(savedInstanceState);
    onCreateUI(savedInstanceState);
    onPostCreateUI(savedInstanceState);
  }

  public abstract void onPreCreateUI(Bundle savedInstanceState);

  public abstract void onCreateUI(Bundle savedInstanceState);

  public abstract void onPostCreateUI(Bundle savedInstanceState);
}
