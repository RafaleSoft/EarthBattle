package org.rafalesoft.com.jeuquentin;

import android.opengl.GLES20;

import org.rafalesoft.com.raptor.Raptor;
import org.rafalesoft.com.raptor.RenderEntryPoint;
import org.rafalesoft.com.raptor.TextureObject;
import org.rafalesoft.com.raptor._3DScene;



class GLRenderer implements RenderEntryPoint
{
    @Override
    public void glInitContext()
    {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        TextureObject txt = new TextureObject();
        txt.loadImage(R.drawable.earth_battle);

        Square mSquare = new Square(txt);
        _3DScene scene = Raptor.getCurrentDisplay().getRootScene();
        scene.addObject(mSquare);
    }

    @Override
    public void glRender()
    {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }
}
