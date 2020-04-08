package cathay.hospital.example.activity;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.journeyapps.barcodescanner.BarcodeView;
import com.journeyapps.barcodescanner.Size;

public class CustomBarcodePreview extends BarcodeView {
    public CustomBarcodePreview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected Rect calculateFramingRect(Rect container, Rect surface) {
        // intersection is the part of the container that is used for the preview
        Rect intersection = new Rect(container);
        boolean intersects = intersection.intersect(surface);

        // [Custom] Get private variables by getter
        Size framingRectSize = getFramingRectSize();
        double marginFraction = getMarginFraction();

        if(framingRectSize != null) {
            // Specific size is specified. Make sure it's not larger than the container or surface.
            int horizontalMargin = Math.max(0, (intersection.width() - framingRectSize.width) / 2);
            int verticalMargin = Math.max(0, (intersection.height() - framingRectSize.height) / 2);
            intersection.inset(horizontalMargin, verticalMargin);

            // [Custom] Move down the framing rectangle
            //調整掃描筐在這裏
            intersection.offset(0, -250);

            return intersection;
        }
        // margin as 10% (default) of the smaller of width, height
        int margin = (int)Math.min(intersection.width() * marginFraction, intersection.height() * marginFraction);
        intersection.inset(margin, margin);
        if (intersection.height() > intersection.width()) {
            // We don't want a frame that is taller than wide.
            intersection.inset(0, (intersection.height() - intersection.width()) / 2);
        }
        return intersection;
    }
}
