package github.com.kebbbnnn.lightgallery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.lucasr.smoothie.AsyncGridView;

import github.com.kebbbnnn.lightgallery.R;
import github.com.kebbbnnn.lightgallery.presenter.ActionbarPresenter;
import github.com.kebbbnnn.lightgallery.presenter.GalleryPresenter;

import static github.com.kebbbnnn.lightgallery.activity.ImageDisplayActivity.IMAGE_URI_ID;
import static github.com.kebbbnnn.lightgallery.presenter.ActionbarPresenter.AB_TITLE;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

  @Override
  public void onPreCreateUI(Bundle instanceState) {
    ActionbarPresenter.create(this).fill().color(R.color.layout_top).attach(R.layout.layout_toolbar).defaultConfig();
  }

  @Override
  public void onCreateUI(Bundle savedInstanceState) {
    setContentView(getView());

    new GalleryPresenter(this).attach(this).load();
  }

  private View getView() {
    AsyncGridView grid = new AsyncGridView(this);
    grid.setId(R.id.grid);
    grid.setColumnWidth((int) getResources().getDimension(R.dimen.image_width));
    grid.setHorizontalSpacing(1);
    grid.setNumColumns(GridView.AUTO_FIT);
    grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
    grid.setVerticalSpacing(1);

    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT);
    grid.setLayoutParams(params);

    return grid;
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
