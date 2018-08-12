package org.rafalesoft.com.jeuquentin;

import android.content.Context;
import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Environment;

import java.io.File;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class GLRenderer implements GLSurfaceView.Renderer
{
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private Context _ctx;
    private Triangle mTriangle;
    private Square   mSquare;

    GLRenderer(Context ctx) { _ctx = ctx; }

    private String findFilePath(String filename)
    {
        String xmlPath = File.separator + filename;

        //File dataDir = new File(_ctx.getDataDir().getPath() + xmlPath);
        //if (!dataDir.exists())
        //{
            File dataDir = new File(_ctx.getExternalFilesDir(null) + xmlPath);
            if (!dataDir.exists())
            {
                dataDir = new File(Environment.getDataDirectory() + xmlPath);
                if (!dataDir.exists())
                {
                    dataDir = new File(_ctx.getFilesDir() + xmlPath);
                    if (!dataDir.exists())
                        return "";
                }
            }
        //}

        return dataDir.getPath();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        mTriangle = new Triangle();
        mSquare = new Square(findFilePath("M81_1024.jpg"));
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;
        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        //mTriangle.draw();
        mSquare.draw();

        /*
        GLES10.glMatrixMode(GLES10.GL_MODELVIEW);
        GLES10.glPushMatrix();
        GLES10.glTranslatef(0.0f,0.0f, -5.0f);
        GLES10.glPopMatrix();
        */
    }

    public static int loadShader(int type, String shaderCode)
    {
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        int[] compiled = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            String info = GLES20.glGetShaderInfoLog(shader);
            GLES20.glDeleteShader(shader);
            shader = 0;
            throw new RuntimeException("Could not compile shader " +
                    type + ":" + info);
        }

        return shader;
    }
}
