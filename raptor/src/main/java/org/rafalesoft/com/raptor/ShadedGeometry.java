package org.rafalesoft.com.raptor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

public class ShadedGeometry extends Object3D
{
    int getNbVertex() { return vertexBuffer.array().length; }
    int getNbTextureCoord() { return textureCoordBuffer.array().length; }
    int nbFace() { return 0; }
    public Shader getShader() { return m_shader; }

    @Override
    public void glRender()
    {
        if (m_shader != null)
            m_shader.glRender();
    }

    public FloatBuffer getVertices() { return vertexBuffer; }
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

    public FloatBuffer getTextureCoords() { return textureCoordBuffer; }
    public void setTextureCoords(float [] coords)
    {
        // initialize vertex byte buffer for shape coordinates
        // (# of coordinate values * 4 bytes per float)
        ByteBuffer tb = ByteBuffer.allocateDirect(coords.length * 4);
        tb.order(ByteOrder.nativeOrder());
        textureCoordBuffer = tb.asFloatBuffer();
        textureCoordBuffer.put(coords);
        textureCoordBuffer.position(0);
    }

    public ShortBuffer getPolygons()
    {
        return polygons;
    }
    public void setPolygons(short [] polys)
    {
        // initialize byte buffer for the draw list
        // (# of coordinate values * 2 bytes per short)
        ByteBuffer dlb = ByteBuffer.allocateDirect(polys.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        polygons = dlb.asShortBuffer();
        polygons.put(polys);
        polygons.position(0);
    }

    public static class RenderingModel
    {
        public enum MODEL
        {
            CGL_FRONT_GEOMETRY,
            CGL_BACK_GEOMETRY,
            CGL_NORMALS,
            CGL_TANGENTS,
            CGL_TEXTURE,
            CGL_WEIGHT,
            CGL_COLORS,
            CGL_FOG,
            CGL_FULL_RENDER
        }

        //! Returns true if the model feature is set
        boolean hasModel(MODEL model) { return m_renderingModel.contains(model); }

        //!	appends a rendering model feature
        void addModel(MODEL m) { m_renderingModel.add(m); }

        //!	removes a rendering model feature
        void removeModel(MODEL m) { m_renderingModel.remove(m); }

        private ArrayList<MODEL> m_renderingModel = new ArrayList<>();
    }


    private FloatBuffer vertexBuffer = null;
    private FloatBuffer textureCoordBuffer = null;
    private ShortBuffer polygons = null;
    private Shader m_shader = new Shader("SHADED_GEOMETRY_SHADER");
}
