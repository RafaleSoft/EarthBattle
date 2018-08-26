package org.rafalesoft.com.raptor;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class RaptorDisplay implements GLSurfaceView.Renderer
{
    private static float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final _3DScene mScene = new _3DScene();
    private final ViewPoint mViewPoint = new ViewPoint();
    private RenderEntryPoint mEntryPoint = new DefaultEntryPoint();

    private class DefaultEntryPoint implements RenderEntryPoint
    {

        /**
         * Main entry point for placing OpenGL context initialisation
         * (creation of all OpenGL objects and states)
         */
        @Override
        public void glInitContext()
        {
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        }

        /**
         * Main entry point for user rendering
         */
        @Override
        public void glRender()
        {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        }
    }
    void setEntryPoint(RenderEntryPoint entryPoint)
    {
        if (entryPoint != null)
            mEntryPoint = entryPoint;
    }

    public static float [] getmMVPMatrix() { return mMVPMatrix; }

    public _3DScene getScene()
    {
        return mScene;
    }


    public volatile float mAngle = 0;
    public float getAngle()
    {
        return mAngle;
    }
    public void setAngle(float angle)
    {
        mAngle = angle;
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        mEntryPoint.glInitContext();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;
        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 1, 100);
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        mEntryPoint.glRender();

        // Set the camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, 3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        float[] mRotationMatrix = new float[16];
        Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0.0f, 1.0f, 0.0f);

        float[] scratch = new float[16];
        // Combine the rotation matrix with the projection and camera view
        // Note that the mMVPMatrix factor *must be first* in order
        // for the matrix multiplication product to be correct.
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);
        mMVPMatrix = scratch;

        mScene.glRender();
    }

}
