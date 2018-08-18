package org.rafalesoft.com.raptor;

import android.content.Context;
import android.content.res.Resources;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Environment;
import android.view.View;

import java.io.File;

public class Raptor
{
    private static Raptor _raptor = null;
    private Context _context = null;
    private GLContext _gl_context = null;

    public static Raptor getInstance()
    {
        if (_raptor == null)
            _raptor = new Raptor();
        return _raptor;
    }

    static Resources getResources()
    {
        if (_raptor == null)
            return null;
        else if (_raptor._context == null)
            return null;
        else
            return _raptor._context.getResources();
    }

    public static String findFilePath(String filename)
    {
        String xmlPath = File.separator + filename;

        File dataDir = new File(getInstance()._context.getExternalFilesDir(null) + xmlPath);
        if (!dataDir.exists())
        {
            dataDir = new File(Environment.getDataDirectory() + xmlPath);
            if (!dataDir.exists())
            {
                dataDir = new File(getInstance()._context.getFilesDir() + xmlPath);
                if (!dataDir.exists())
                    return "";
            }
        }

        return dataDir.getPath();
    }

    public static View glCreateWindow(Context ctx, GLContext renderer)
    {
        if ((ctx != null) && (getInstance()._context == null))
            getInstance()._context = ctx;

        RaptorDisplay display = new RaptorDisplay(ctx);
        display.setEGLContextClientVersion(2);
        if (renderer == null)
            getInstance()._gl_context = glCreateContext();
        else
            getInstance()._gl_context = renderer;
        display.setRenderer(renderer);
        display.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        return display;
    }

    public static GLContext glCreateContext()
    {
        GLContext ctx = new GLContext()
        {
            @Override
            public void glInitContext()
            {
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            }
        };
        return ctx;
    }
}
