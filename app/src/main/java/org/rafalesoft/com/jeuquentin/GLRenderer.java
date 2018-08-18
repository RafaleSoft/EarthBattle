package org.rafalesoft.com.jeuquentin;

import org.rafalesoft.com.raptor.GLContext;
import org.rafalesoft.com.raptor.TextureObject;
import org.rafalesoft.com.raptor._3DScene;



class GLRenderer extends GLContext
{
    public void glInitContext()
    {
        TextureObject txt = new TextureObject();
        txt.loadImage(R.drawable.earth_battle);

        Square mSquare = new Square(txt);
        _3DScene scene = getScene();
        scene.addObject(mSquare);
    }
}
