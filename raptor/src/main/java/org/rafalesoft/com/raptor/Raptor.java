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
    /** Raptor single instance */
    private static Raptor _raptor = null;
    /** Store owner activity context */
    private Context _context = null;

    private static RaptorScreenDisplay _currentDisplay = null;

    static
    {
        // Used to load the 'native-lib' library on application startup.
        System.loadLibrary("native-lib");
    }

    Raptor()
    {

    }

    public static Raptor getInstance()
    {
        if (_raptor == null)
            _raptor = new Raptor();
        return _raptor;
    }

    public static RaptorScreenDisplay getCurrentDisplay()
    {
        return _currentDisplay;
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

    public static View glCreateWindow(Context ctx, RenderEntryPoint entryPoint)
    {
        if ((ctx != null) && (getInstance()._context == null))
            getInstance()._context = ctx;

        RaptorScreenDisplay display = new RaptorScreenDisplay(ctx);
        display.getRaptorDisplay().setEntryPoint(entryPoint);

        /** TODO: reproduce Raptor display binding  */
        _currentDisplay = display;

        return display;
    }
}
