package github.com.kebbbnnn.lightgallery.adapter;

/*
 * Copyright (C) 2012 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Images.ImageColumns;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.SimpleCursorAdapter;
import github.com.kebbbnnn.lightgallery.view.ImagePreview;

public class GalleryAdapter extends SimpleCursorAdapter {

    private Context context;

    public GalleryAdapter(Context context) {
        super(context, -1, null, new String[]{}, new int[]{}, 0);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cursor c = (Cursor) getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            //convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);

            holder = new ViewHolder();
            //holder.image = convertView.findViewById(R.id.image);
            //holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.image = new ImagePreview(context);
            holder.image.setScaleType(ScaleType.CENTER_CROP);
            holder.image.setLayoutParams(new LayoutParams(MATCH_PARENT, MATCH_PARENT));

            convertView = holder.image;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //holder.title.setText(c.getString(c.getColumnIndex(ImageColumns.DATE_TAKEN)));
        holder.image.setText(c.getString(c.getColumnIndex(ImageColumns.DATE_TAKEN)));
        holder.image.setImageDrawable(null);

        return convertView;
    }

    public static class ViewHolder {

        public ImagePreview image;
        //public TextView title;
    }
}