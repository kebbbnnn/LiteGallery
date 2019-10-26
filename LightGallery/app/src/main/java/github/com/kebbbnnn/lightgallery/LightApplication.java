package github.com.kebbbnnn.lightgallery;

import android.app.Application;
import android.content.Context;

/**
 * Created by kevinladan on 4/15/17.
 */

//@ReportsCrashes(formUri = "https://collector.tracepot.com/49265b6c")
public class LightApplication extends Application {

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    //ACRA.init(this);
  }

}
