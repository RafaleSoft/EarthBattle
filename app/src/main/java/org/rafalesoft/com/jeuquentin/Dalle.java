package org.rafalesoft.com.jeuquentin;

import android.opengl.GLES20;

import org.rafalesoft.com.raptor.RaptorDisplay;
import org.rafalesoft.com.raptor.ShadedGeometry;
import org.rafalesoft.com.raptor.TextureObject;


class Dalle extends ShadedGeometry
{
    private int mPositionHandle;
    private int mTexCoordHandle;
    //private int mColorHandle;
    private int mDiffuseHandle;
    private int mMVPMatrixHandle;


    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "attribute vec2 tCoords;" +
            "varying vec2 v_TexCoordinate;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "  v_TexCoordinate = tCoords;" +
                    "}";

    private final String fragmentShaderCode =
                    "precision mediump float;" +
                    "uniform sampler2D diffuseMap;" +
                    "varying vec2 v_TexCoordinate;" +
                    "void main() {" +
                    "gl_FragColor = texture2D(diffuseMap, v_TexCoordinate);" +
                    "}";

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static float squareCoords[] = {
            -0.5f,  -1.0f, -0.5f,   // top left
            -0.5f, -1.0f, 0.5f,   // bottom left
            0.5f, -1.0f, 0.5f,   // bottom right
            0.5f, -1.0f, -0.5f }; // top right
    static final int TEXCOORDS_PER_VERTEX = 2;
    static float squareTexCoords[] = {
            0.0f, 1.0f,   // top left
            0.0f, 0.0f,   // bottom left
            1.0f, 0.0f,   // bottom right
            1.0f, 1.0f }; // top right

    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices


    public Dalle(TextureObject t)
    {
        setVertices(squareCoords);
        setTextureCoords(squareTexCoords);
        setPolygons(drawOrder);

        getShader().loadShader(vertexShaderCode, fragmentShaderCode);
        getShader().setTexture(t);
    }

    @Override
    public void glRender()
    {
        super.glRender();

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(getShader().getProgram(), "vPosition");
        mTexCoordHandle = GLES20.glGetAttribLocation(getShader().getProgram(), "tCoords");
        mMVPMatrixHandle = GLES20.glGetUniformLocation(getShader().getProgram(), "uMVPMatrix");

        // Enable a handle to the square vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        // Enable a handle to the square texture coords
        GLES20.glEnableVertexAttribArray(mTexCoordHandle);

        // Prepare the square coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                COORDS_PER_VERTEX * 4, getVertices());
        GLES20.glVertexAttribPointer(mTexCoordHandle, TEXCOORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                TEXCOORDS_PER_VERTEX * 4, getTextureCoords());
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, RaptorDisplay.getmMVPMatrix(), 0);


        // get handle to fragment shader's vColor member
        //mColorHandle = GLES20.glGetUniformLocation(getShader().getProgram(), "vColor");
        // Set color for drawing the square
        //GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        mDiffuseHandle = GLES20.glGetUniformLocation(getShader().getProgram(), "diffuseMap");

        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES20.glUniform1i(mDiffuseHandle, 0);

        // Draw the square
        GLES20.glDrawElements(GLES20.GL_TRIANGLES,6, GLES20.GL_UNSIGNED_SHORT, getPolygons());

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mTexCoordHandle);
    }
}
