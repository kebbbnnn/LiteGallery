package github.com.kebbbnnn.lightgallery.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ImagePreview extends ImageView {

    private static TextPaint textPaint = new TextPaint();//The Paint that will draw the text

    private CharSequence text;

    static {
        textPaint.setColor(Color.WHITE);//Change the color if your background is white!
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(24);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setLinearText(true);
    }

    public void setText(CharSequence text) {
        this.text = text;
    }

    public ImagePreview(Context context) {
        this(context, null);
    }

    public ImagePreview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImagePreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Rect b = getClipBounds(); //The dimensions of your canvas
        int x0 = 5;           //add some space on the left. You may use 0
        int y0 = getMeasuredHeight() - 20;          //At least 20 to see your text
        int width = getMeasuredWidth() - 10; //10 to keep some space on the right for the "..."
        CharSequence txt = TextUtils.ellipsize(text, textPaint, width, TextUtils.TruncateAt.END);
        canvas.drawText(txt, 0, txt.length(), x0, y0, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size;

        //If the layout_width or layout_width of this view is set as match_parent or any exact dimension, then this view will use that dimension
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY
                ^ MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
                size = width;
            } else {
                size = height;
            }
        }
        //If the layout_width and layout_width of this view are not set or both set as match_parent or any exact dimension,
        // then this view will use the minimum dimension
        else {
            size = Math.min(width, height);
        }
        setMeasuredDimension(size, size);
    }
}
