package org.rafalesoft.com.raptor;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class RaptorDisplay extends GLSurfaceView
{
    public RaptorDisplay(Context context)
    {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }
}
