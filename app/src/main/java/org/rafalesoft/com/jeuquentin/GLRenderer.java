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

        TextureObject txt2 = new TextureObject();
        txt2.loadImage(R.drawable.marble);
        Dalle mDalle = new Dalle(txt2);
        scene.addObject(mDalle);

        //	Create sky dome object
        TextureObject txt3 = new TextureObject();
        txt3.loadImage(R.drawable.sky);
        //CShadedGeometry *sky = new CShadedGeometry();
        //CGeometry* skydome = (CGeometry*)CPersistence::FindObject("SKYDOME");
	    //*sky = *skydome;
        //CShader *sh = sky->getShader();
        //CTextureUnitSetup* tus = sh->glGetTextureUnitsSetup();
        //tus->setDiffuseMap(T);
    }

    @Override
    public void glRender()
    {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }
}
