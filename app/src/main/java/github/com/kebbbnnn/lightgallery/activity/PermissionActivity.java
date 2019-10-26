package github.com.kebbbnnn.lightgallery.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;

import github.com.kebbbnnn.lightgallery.R;

public class PermissionActivity extends Activity {

  private final static int REQUEST_STORAGE_PERMISSION = 1111;

  private Button buttonOkay;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_permission);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {

        buttonOkay = (Button) findViewById(R.id.buttonOk);
        buttonOkay.setOnClickListener(v -> {
          Intent intent = new Intent();
          intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
          Uri uri = Uri.fromParts("package", this.getPackageName(), null);
          intent.setData(uri);
          startActivityForResult(intent, REQUEST_STORAGE_PERMISSION);
        });

      } else {
        openMainActivity();
      }
    } else {
      openMainActivity();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_STORAGE_PERMISSION) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
          openMainActivity();
        }
      }
    }
  }

  private void openMainActivity() {
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }
}
