package org.rafalesoft.com.jeuquentin;

import android.opengl.GLES20;

import org.rafalesoft.com.raptor.ShadedGeometry;
import org.rafalesoft.com.raptor.TextureObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;


class Square extends ShadedGeometry
{
    private FloatBuffer texelBuffer;
    private ShortBuffer drawListBuffer;

    private int mPositionHandle;
    private int mTexCoordHandle;
    private int mColorHandle;
    private int mDiffuseHandle;
    private TextureObject m_texture = null;


    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
            "attribute vec2 tCoords;" +
            "varying vec2 v_TexCoordinate;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "  v_TexCoordinate = tCoords;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "uniform sampler2D diffuseMap;" +
                    "varying vec2 v_TexCoordinate;" +
                    "void main() {" +
                    "gl_FragColor = (vColor * texture2D(diffuseMap, v_TexCoordinate));" +
                    "}";

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static float squareCoords[] = {
            -0.5f,  -0.5f, 0.0f,   // top left
            -0.5f, 0.5f, 0.0f,   // bottom left
            0.5f, 0.5f, 0.0f,   // bottom right
            0.5f,  -0.5f, 0.0f }; // top right
    static final int TEXCOORDS_PER_VERTEX = 2;
    static float squareTexCoords[] = {
            0.0f, 1.0f,   // top left
            0.0f, 0.0f,   // bottom left
            1.0f, 0.0f,   // bottom right
            1.0f, 1.0f }; // top right


    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices
    //float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };
    float color[] = { 1.0f, 1.0f, 1.0f, 1.0f };
    private final int vertexCount = squareCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;
    private final int texelStride = TEXCOORDS_PER_VERTEX * 4;

    public Square(TextureObject t)
    {
        setVertices(squareCoords);

        // initialize vertex byte buffer for shape coordinates
        ByteBuffer tb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                squareTexCoords.length * 4);
        tb.order(ByteOrder.nativeOrder());
        texelBuffer = tb.asFloatBuffer();
        texelBuffer.put(squareTexCoords);
        texelBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        getShader().loadShader(vertexShaderCode, fragmentShaderCode);
        m_texture = t;
    }

    @Override
    public void glRender()
    {
        // Add program to OpenGL ES environment
        GLES20.glUseProgram(getShader().getProgram());
        m_texture.glRender();

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(getShader().getProgram(), "vPosition");
        mTexCoordHandle = GLES20.glGetAttribLocation(getShader().getProgram(), "tCoords");

        // Enable a handle to the square vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        // Enable a handle to the square texture coords
        GLES20.glEnableVertexAttribArray(mTexCoordHandle);

        // Prepare the square coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, getVertices());
        GLES20.glVertexAttribPointer(mTexCoordHandle, TEXCOORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                texelStride, texelBuffer);

        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(getShader().getProgram(), "vColor");

        // Set color for drawing the square
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        mDiffuseHandle = GLES20.glGetUniformLocation(getShader().getProgram(), "diffuseMap");

        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES20.glUniform1i(mDiffuseHandle, 0);

        // Draw the square
        GLES20.glDrawElements(GLES20.GL_TRIANGLES,6, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mTexCoordHandle);
    }
}
