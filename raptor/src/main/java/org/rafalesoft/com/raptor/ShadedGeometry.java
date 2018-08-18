package org.rafalesoft.com.raptor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class ShadedGeometry extends Object3D
{
    int nbVertex() { return vertexBuffer.array().length; }
    int nbFace() { return 0; }
    public Shader getShader() { return m_shader; }

    @Override
    public void glRender()
    {
        if (m_shader != null)
            m_shader.glRender();
    }

    public void setVertices(float[] coords)
    {
        // initialize vertex byte buffer for shape coordinates
        // (# of coordinate values * 4 bytes per float)
        ByteBuffer bb = ByteBuffer.allocateDirect(coords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(coords);
        vertexBuffer.position(0);
    }

    public FloatBuffer getVertices() { return vertexBuffer; }


    public enum RenderingModel
    {
        CGL_FRONT_GEOMETRY(1),
        CGL_BACK_GEOMETRY(2),
        CGL_NORMALS(4),
        CGL_TANGENTS(8),
        CGL_TEXTURE(16),
        CGL_WEIGHT(32),
        CGL_COLORS(64),
        CGL_FOG(128),
        CGL_FULL_RENDER(255);

        RenderingModel(long m) { m_renderingModel = m; }

        //! Returns true if the model feature is set
        //bool RAPTOR_FASTCALL hasModel(MODEL model) const { return ((m_renderingModel & model) == model); };

        //!	appends a rendering model feature
        //void addModel(MODEL);

        //!	removes a rendering model feature
        //void removeModel(MODEL);

        private long	m_renderingModel;
    }


    private FloatBuffer vertexBuffer = null;
    private Shader m_shader = new Shader();
}
