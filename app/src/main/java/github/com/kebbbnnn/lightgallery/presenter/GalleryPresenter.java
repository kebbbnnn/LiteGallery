package github.com.kebbbnnn.lightgallery.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.ImageColumns;
import android.widget.AdapterView;
import github.com.kebbbnnn.lightgallery.R;
import github.com.kebbbnnn.lightgallery.adapter.GalleryAdapter;
import github.com.kebbbnnn.lightgallery.utils.GalleryLoader;
import org.lucasr.smoothie.AsyncGridView;
import org.lucasr.smoothie.ItemManager;

/**
 * Created by kevinladan on 4/13/17.
 */

public class GalleryPresenter implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int GALLERY_LOADER = 0;

    private Activity activity;

    private AsyncGridView gridView;

    private GalleryAdapter adapter;

    public GalleryPresenter(Activity activity) {
        this.activity = activity;
        init();
    }

    private void init() {
        gridView = activity.findViewById(R.id.grid);
    }

    public GalleryPresenter attach(AdapterView.OnItemClickListener onItemClickListener) {
        adapter = new GalleryAdapter(activity);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(onItemClickListener);
        activity.getLoaderManager().initLoader(GALLERY_LOADER, null, this);
        return this;
    }

    public GalleryPresenter load() {
        GalleryLoader loader = new GalleryLoader(activity);
        ItemManager.Builder builder = new ItemManager.Builder(loader);
        builder.setPreloadItemsEnabled(true).setPreloadItemsCount(12);
        builder.setThreadPoolSize(4);
        gridView.setItemManager(builder.build());
        return this;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderID, Bundle bundle) {
        return new CursorLoader(
                activity,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{ImageColumns._ID, ImageColumns.DATE_TAKEN},
                null,
                null,
                ImageColumns.DATE_TAKEN + " DESC"
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.changeCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.changeCursor(null);
    }
}
