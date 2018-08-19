package org.rafalesoft.com.raptor;

import android.opengl.GLES20;
import android.util.Log;

import java.util.ArrayList;

public class Shader
{
    public int getProgram() { return m_program; }

    public void setTexture(TextureObject txt)
    {
        m_texture = txt;
    }

    public void glRender()
    {
        if (m_program > 0)
            GLES20.glUseProgram(m_program);
        if (m_texture != null)
            m_texture.glRender();
    }

    public void setParameters(ArrayList<ProgramParameter.Parameter> params)
    {

    }

    public void loadShader(String vertexShaderCode, String fragmentShaderCode)
    {
        int vertexShader = Shader.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = Shader.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        // create empty OpenGL ES Program
        m_program = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(m_program, vertexShader);
        // add the fragment shader to program
        GLES20.glAttachShader(m_program, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(m_program);
        String log = GLES20.glGetProgramInfoLog(m_program);

        if (!log.isEmpty() || m_program <= 0)
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

    private int m_program = 0;
    private float m_color[] = { 1.0f, 1.0f, 1.0f, 1.0f };
    private TextureObject m_texture = null;
    private ProgramParameter m_parameters = new ProgramParameter();
}
