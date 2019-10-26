package github.com.kebbbnnn.lightgallery.activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.concurrent.ExecutionException;

import github.com.kebbbnnn.lightgallery.R;
import github.com.kebbbnnn.lightgallery.presenter.ActionbarPresenter;
import github.com.kebbbnnn.lightgallery.presenter.BackgroundPresenter;
import github.com.kebbbnnn.lightgallery.presenter.BottomBarPresenter;
import github.com.kebbbnnn.lightgallery.presenter.DetailsPresenter;
import github.com.kebbbnnn.lightgallery.utils.ImageDetail;

import static github.com.kebbbnnn.lightgallery.presenter.ActionbarPresenter.AB_TITLE;

/**
 * Created by kevinladan on 4/14/17.
 */

public class ImageDisplayActivity extends BaseActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks {

  public static final String IMAGE_URI_ID = "image-uri-id";

  private BottomBarPresenter bottombar;

  private ActionbarPresenter toolbar;

  private DetailsPresenter details;

  private BackgroundPresenter background;

  private Uri imageUri;

  @Override
  public void onPreCreateUI(Bundle savedInstanceState) {
    getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
    long id = getIntent().getLongExtra(IMAGE_URI_ID, 0L);
    imageUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(id));

    try {
      String title = new ImageDetail(this, imageUri).name();
      getIntent().putExtra(AB_TITLE, String.format("IMG %s", title));
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onCreateUI(Bundle savedInstanceState) {
    setContentView(R.layout.activity_image_display);
    toolbar = ActionbarPresenter.create(this).fill().color(R.color.layout_top).attach(R.layout.layout_toolbar).defaultConfig();
    final SubsamplingScaleImageView imageView = findViewById(R.id.imageView);
    bottombar = BottomBarPresenter.create(this).attach(R.id.note);
    background = BackgroundPresenter.create(this).attach(R.id.root);

    imageView.setImage(ImageSource.uri(imageUri));
    imageView.setOnClickListener(this);
    imageView.setOnImageEventListener(new SubsamplingScaleImageView.DefaultOnImageEventListener() {

      @Override
      public void onReady() {
        //Bitmap imageBitmap = ViewUtils.createBitmapFromView(imageView);
        //TODO: check for null bitmap.
        //int dominantColor = ColorUtils.getDominantColor(imageBitmap);
        //background.changeBackground(dominantColor);
      }

      @Override
      public void onImageLoadError(Exception e) {
        super.onImageLoadError(e);
        printlog("Encountered some error while loading image.");
      }
    });

    details = DetailsPresenter.create(this).attach(R.id.note);
    details.setUri(imageUri);
  }

  @Override
  public void onPostCreateUI(Bundle savedInstanceState) {

  }

  @Override
  public void onClick(View view) {
    final boolean flag = view.getTag() != null && !(boolean) view.getTag();
    bottombar.show(flag);
    toolbar.show(flag);
    view.setTag(flag);
  }

  @Override
  public Loader onCreateLoader(int i, Bundle bundle) {
    return null;
  }

  @Override
  public void onLoadFinished(Loader loader, Object o) {

  }

  @Override
  public void onLoaderReset(Loader loader) {

  }
}
