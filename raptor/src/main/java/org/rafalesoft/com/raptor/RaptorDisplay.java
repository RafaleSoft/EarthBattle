package org.rafalesoft.com.raptor;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class RaptorDisplay extends GLSurfaceView
{
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;


    public RaptorDisplay(Context context)
    {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //return super.onTouchEvent(event);

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:

                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                // reverse direction of rotation above the mid-line
                if (y > getHeight() / 2) {
                    dx = dx * -1 ;
                }

                // reverse direction of rotation to left of the mid-line
                if (x < getWidth() / 2) {
                    dy = dy * -1 ;
                }

                Raptor.getContext().setAngle(Raptor.getContext().getAngle() + ((dx + dy) * TOUCH_SCALE_FACTOR));
                requestRender();

                break;
        }

        mPreviousX = x;
        mPreviousY = y;

        return true;
    }
}
