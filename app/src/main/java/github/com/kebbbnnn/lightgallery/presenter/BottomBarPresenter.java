package github.com.kebbbnnn.lightgallery.presenter;

import android.app.Activity;
import android.view.View;

/**
 * Created by kevinladan on 4/15/17.
 */

public class BottomBarPresenter {

  private Activity activity;

  private View bottomBar;

  public static BottomBarPresenter create(Activity activity) {
    return new BottomBarPresenter(activity);
  }

  public BottomBarPresenter(Activity activity) {
    this.activity = activity;
  }

  public BottomBarPresenter attach(int resId) {
    bottomBar = activity.findViewById(resId);
    return this;
  }

  public void show(boolean flag) {
    float n_h = bottomBar.getMeasuredHeight();
    float n_y = bottomBar.getY();
    bottomBar.animate().y(n_y + (flag ? -n_h : n_h));
  }

  public View view() {
    return bottomBar;
  }

}
