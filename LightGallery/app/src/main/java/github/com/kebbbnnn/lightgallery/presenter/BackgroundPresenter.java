package github.com.kebbbnnn.lightgallery.presenter;

import android.app.Activity;
import android.view.View;

public class BackgroundPresenter {

  private Activity activity;

  private View root;

  public static BackgroundPresenter create(Activity activity) {
    return new BackgroundPresenter(activity);
  }

  public BackgroundPresenter(Activity activity) {
    this.activity = activity;
  }

  public BackgroundPresenter attach(int resId) {
    root = activity.findViewById(resId);
    return this;
  }

  public void changeBackground(int color) {
    root.setBackgroundColor(color);
  }
}
