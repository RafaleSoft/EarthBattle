package org.rafalesoft.com.raptor;

import android.opengl.GLES20;
import android.util.Log;

public class Shader
{
    private int mProgram = 0;
    private TextureObject m_texture = null;

    public int getProgram() { return mProgram; }

    public void glRender()
    {
        if (mProgram > 0)
            GLES20.glUseProgram(mProgram);
        if (m_texture != null)
            m_texture.glRender();
    }

    public void loadShader(String vertexShaderCode, String fragmentShaderCode)
    {
        int vertexShader = Shader.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = Shader.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);
        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);
        String log = GLES20.glGetProgramInfoLog(mProgram);

        if (!log.isEmpty() || mProgram <= 0)
            Log.d("Shader", log);
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
        if (compiled[0] == 0)
        {
            String info = GLES20.glGetShaderInfoLog(shader);
            GLES20.glDeleteShader(shader);
            shader = 0;
            Log.d("Shader", info);
        }

        return shader;
    }
}
