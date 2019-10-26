package github.com.kebbbnnn.lightgallery.presenter;

import android.app.Activity;
import android.net.Uri;
import android.support.media.ExifInterface;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import github.com.kebbbnnn.lightgallery.R;

public class DetailsPresenter extends BasePresenter {

  private Activity activity;

  private TextView note;

  private Uri imageUri;

  public static DetailsPresenter create(Activity activity) {
    return new DetailsPresenter(activity);
  }

  public DetailsPresenter(Activity activity) {
    this.activity = activity;
  }

  public DetailsPresenter attach(int resId) {
    note = (TextView) activity.findViewById(resId);
    return this;
  }

  public DetailsPresenter setUri(Uri imageUri) {
    this.imageUri = imageUri;
    init();
    return this;
  }

  private void init() {
    if (imageUri != null) {
      InputStream in = null;
      try {
        in = activity.getContentResolver().openInputStream(imageUri);
        ExifInterface exif = new ExifInterface(in);
        // Now you can extract any Exif tag you want
        // Assuming the image is a JPEG or supported raw format
        int width = exif.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 0);
        int height = exif.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, 0);
        //TODO: use resource and format the string.
        note.setText("This image is " + width + " x " + height + " pixels. On most devices it will be subsampled, and higher quality tiles are loaded as you zoom in.");
      } catch (IOException e) {
        // Handle any errors
      } finally {
        if (in != null) {
          try {
            in.close();
          } catch (IOException ignored) {
          }
        }
      }
    }
  }
}
