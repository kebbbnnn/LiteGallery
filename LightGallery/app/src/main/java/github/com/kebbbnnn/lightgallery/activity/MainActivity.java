package github.com.kebbbnnn.lightgallery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import github.com.kebbbnnn.lightgallery.R;
import github.com.kebbbnnn.lightgallery.presenter.ActionbarPresenter;
import github.com.kebbbnnn.lightgallery.presenter.GalleryPresenter;

import static github.com.kebbbnnn.lightgallery.activity.ImageDisplayActivity.IMAGE_URI_ID;
import static github.com.kebbbnnn.lightgallery.presenter.ActionbarPresenter.AB_TITLE;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

  @Override
  public void onPreCreateUI(Bundle instanceState) {
    ActionbarPresenter.create(this).fill().color(R.color.layout_top).attach(R.layout.layout_toolbar).defaultConfig();
  }

  @Override
  public void onCreateUI(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main);
    new GalleryPresenter(this).attach(this).load();
  }

  @Override
  public void onPostCreateUI(Bundle instanceState) {

  }

  @Override
  public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
    Intent intent = new Intent(this, ImageDisplayActivity.class);
    intent.putExtra(IMAGE_URI_ID, id);
    intent.putExtra(AB_TITLE, String.format("IMG %1$02d", id));
    startActivity(intent);
  }
}
