package github.com.kebbbnnn.lightgallery.presenter;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import github.com.kebbbnnn.lightgallery.R;
import github.com.kebbbnnn.lightgallery.utils.ColorUtils;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by kevinladan on 4/13/17.
 */

public class ActionbarPresenter extends BasePresenter {

  public static final String AB_TITLE = "ab-title";

  private Activity activity;

  private ActionBar actionBar;

  private LayoutParams params;

  private int colorId = 0x0;

  public static ActionbarPresenter create(Activity activity) {
    return new ActionbarPresenter(activity);
  }

  public ActionbarPresenter(Activity activity) {
    this.activity = activity;
  }

  public ActionbarPresenter fill() {
    params = new LayoutParams(MATCH_PARENT, MATCH_PARENT);
    return this;
  }

  public ActionbarPresenter color(int colorId) {
    this.colorId = colorId;
    return this;
  }

  public ActionbarPresenter attach(int resId) {
    actionBar = activity.getActionBar();
    View actionBarView = View.inflate(activity, resId, null);

    if (colorId != 0x0) {
      actionBarView.setBackgroundColor(ColorUtils.getColor(activity, colorId));
    }
    if (activity.getIntent() != null) {
      String title = activity.getIntent().getStringExtra(AB_TITLE);
      if (!TextUtils.isEmpty(title)) {
        TextView textTitle = actionBarView.findViewById(R.id.ab_title);
        textTitle.setText(title);
      }
    }
    actionBar.setDisplayShowCustomEnabled(true);
    if (params == null)
      actionBar.setCustomView(actionBarView);
    else
      actionBar.setCustomView(actionBarView, params);

    //make the root view background same with the activity's
    final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);

    if (viewGroup == null) return this;

    Drawable activityDrawable = viewGroup.getBackground();

    if (activityDrawable instanceof ColorDrawable) {
      ViewGroup actionbarContainer = (ViewGroup) actionBar.getCustomView().getParent().getParent().getParent();
      actionbarContainer.setBackgroundColor(((ColorDrawable) activityDrawable).getColor());
    }
    return this;
  }

  public ActionbarPresenter defaultConfig() {
    return config(false, false);
  }

  public ActionbarPresenter config(boolean showHomeEnabled, boolean showTitleEnabled) {
    if (actionBar == null) throw new NullPointerException("ActionBar cannot be null.");
    actionBar.setDisplayShowHomeEnabled(showHomeEnabled);
    actionBar.setDisplayShowTitleEnabled(showTitleEnabled);
    return this;
  }

  public View view() {
    return actionBar.getCustomView();
  }

  public ViewGroup actionBarView() {
    return (ViewGroup) actionBar.getCustomView().getParent().getParent();
  }

  private Drawable foreground;

  public void show(boolean flag) {
    if (!flag)
      actionBar.hide();
    else
      actionBar.show();
  }

  public void disableActionbarShadow(boolean flag) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
      View v = activity.findViewById(android.R.id.content);
      if (v instanceof FrameLayout) {
        if (foreground == null) foreground = ((FrameLayout) v).getForeground();
        ((FrameLayout) v).setForeground(flag ? null : foreground);
      }
    } else {
      // kitkat.
      // v is ActionBarOverlayLayout. unfortunately this is internal class.
      // if u want to check v is desired class, try this
      //   if(v.getClass().getSimpleName.equals("ActionBarOverlayLayout"))
      // (we cant use instanceof caz ActionBarOverlayLayout is internal package)
      View v = ((ViewGroup) activity.getWindow().getDecorView()).getChildAt(0);
      v.setWillNotDraw(flag);
    }
  }

}
