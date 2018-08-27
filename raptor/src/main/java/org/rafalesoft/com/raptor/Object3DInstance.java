package org.rafalesoft.com.raptor;

import android.opengl.GLES10;
import android.util.Log;
import org.rafalesoft.com.raptor.CGLTypes.*;

import static android.opengl.GLES10.glPopMatrix;


public class Object3DInstance extends Object3D
{
    private Object3D m_pReference = null;
    private Shader m_pShader = null;
    private GL_MATRIX m_transform = new GL_MATRIX();

    public Object3DInstance(Object3D instance, String name)
    {
        super.Object3D(name);

        m_pReference = instance;
        if (instance == this)
            m_pReference = null;

        // instance shouldn't be NULL
        if (m_pReference == null)
            Log.d("Object3DInstance","instance is null !");

        resetTransform();
    }

    public void setShader(Shader shader)
    {
        m_pShader = shader;
    }
    public Shader getShader()
    {
        if (m_pShader == null)
            m_pShader = new Shader(getName()+"_Shader");

        return m_pShader;
    }

    public void instantiate(Object3D instance)
    {
        if (instance == null)
            Log.d("Object3DInstance","instance is null !");
        else
            m_pReference = instance;
    }

    public void resetTransform()
    {
        m_transform.identity();
    }

    public GL_MATRIX getTransform()
    {
        return m_transform;
    }



    @Override
    public void glRender()
    {
        if (m_pShader != null)
            m_pShader.glRender();

        if (m_pReference != null)
        {
            GLES10.glPushMatrix();
            GLES10.glMultMatrixf(m_transform.asFloatArray(),0);
            m_pReference.glRender();
            glPopMatrix();
        }
    }

    public void translate(float alpha, float beta, float gamma)
    {
        m_transform.rowh.x += alpha;
        m_transform.rowh.y += beta;
        m_transform.rowh.z += gamma;
    }

    public void translateAbsolute(float alpha, float beta, float gamma)
    {
        m_transform.rowh.x = alpha;
        m_transform.rowh.y = beta;
        m_transform.rowh.z = gamma;
    }

    public void scale(float alpha, float beta, float gamma)
    {
        m_transform.rowx.x *= alpha;
        m_transform.rowy.y *= beta;
        m_transform.rowz.z *= gamma;
    }
/*
    void CObject3DInstance::

    Object3DInstance(GL_MATRIX &m)
{
    CGenericMatrix<float> T;
    CGenericMatrix<float> TT;

    T = m_transform;
    TT = m;
    T *= TT.Transpose();
    C3DEngine::Generic_to_MATRIX(m_transform,T);

    GL_COORD_VERTEX min;
    GL_COORD_VERTEX max;

    getBoundingBox(min,max);
    setBoundingBox(min,max);
}

    void CObject3DInstance::

    Object3DInstance(float rx)
{
    float u = (float)cos(TO_RADIAN(rx));
    float v = (float)sin(TO_RADIAN(rx));
    //					*	m_transform
    //	1	0		0
    //	0	cos		-sin
    //	0	sin		cos

    float aux = u*m_transform.rowy.x - v*m_transform.rowz.x;
    m_transform.rowz.x = v*m_transform.rowy.x + u*m_transform.rowz.x;
    m_transform.rowy.x = aux;

    aux = u*m_transform.rowy.y - v*m_transform.rowz.y;
    m_transform.rowz.y = v*m_transform.rowy.y + u*m_transform.rowz.y;
    m_transform.rowy.y = aux;

    aux = u*m_transform.rowy.z - v*m_transform.rowz.z;
    m_transform.rowz.z = v*m_transform.rowy.z + u*m_transform.rowz.z;
    m_transform.rowy.z = aux;

    GL_COORD_VERTEX min;
    GL_COORD_VERTEX max;

    getBoundingBox(min,max);
    setBoundingBox(min,max);
}

    void CObject3DInstance::

    Object3DInstance(float ry)
{
    float u = (float)cos(TO_RADIAN(ry));
    float v = (float)sin(TO_RADIAN(ry));
    //					*	m_transform
    //	cos		0	sin
    //	0		1	0
    //	-sin		0	cos

    float aux = u*m_transform.rowx.x + v*m_transform.rowz.x;
    m_transform.rowz.x = -v*m_transform.rowx.x + u*m_transform.rowz.x;
    m_transform.rowx.x = aux;

    aux = u*m_transform.rowx.y + v*m_transform.rowz.y;
    m_transform.rowz.y = -v*m_transform.rowx.y + u*m_transform.rowz.y;
    m_transform.rowx.y = aux;

    aux = u*m_transform.rowx.z + v*m_transform.rowz.z;
    m_transform.rowz.z = -v*m_transform.rowx.z + u*m_transform.rowz.z;
    m_transform.rowx.z = aux;

    GL_COORD_VERTEX min;
    GL_COORD_VERTEX max;

    getBoundingBox(min,max);
    setBoundingBox(min,max);
}

    void CObject3DInstance::

    Object3DInstance(float rz)
{
    float u = (float)cos(TO_RADIAN(rz));
    float v = (float)sin(TO_RADIAN(rz));
    //					*	m_transform
    // cos	-sin	0
    // sin	cos		0
    //	0	0		1

    float aux = u*m_transform.rowx.x - v*m_transform.rowy.x;
    m_transform.rowy.x = v*m_transform.rowx.x + u*m_transform.rowy.x;
    m_transform.rowx.x = aux;

    aux = u*m_transform.rowx.y - v*m_transform.rowy.y;
    m_transform.rowy.y = v*m_transform.rowx.y + u*m_transform.rowy.y;
    m_transform.rowx.y = aux;

    aux = u*m_transform.rowx.z - v*m_transform.rowy.z;
    m_transform.rowy.z = v*m_transform.rowx.z + u*m_transform.rowy.z;
    m_transform.rowx.z = aux;

    GL_COORD_VERTEX min;
    GL_COORD_VERTEX max;

    getBoundingBox(min,max);
    setBoundingBox(min,max);
}

    void CObject3DInstance::

    Object3DInstance(double angle, float ax, float ay, float az)
{
    CGenericMatrix<float> temp;
    float tx,ty,tz;

    CGenericMatrix<float> T;
    T = m_transform;
    T.Transpose();

    CGenericVector<float> axis(ax,ay,az,1.0f);
    C3DEngine::Get3DEngine()->generateRotation(angle,axis,temp);

    // save translation before it is cleared
    //	to compute Object3DInstance
    tx = T[3];	T[3] = 0;
    ty = T[7];	T[7] = 0;
    tz = T[11];	T[11] = 0;

    temp *= T;

    temp[3] = tx;
    temp[7] = ty;
    temp[11] = tz;

    temp.Transpose();

    C3DEngine::Generic_to_MATRIX(m_transform,temp);

    GL_COORD_VERTEX min;
    GL_COORD_VERTEX max;

    getBoundingBox(min,max);
    setBoundingBox(min,max);
}


    void CObject3DInstance::

    Object3DInstance(float rx, float cy, float cz)
{
    CGenericMatrix<float> T;
    CGenericMatrix<float> M;


    // Compute new position
    CGenericVector<float> t(m_transform.rowh.x,m_transform.rowh.y - cy,m_transform.rowh.z - cz,1);

    float u = (float)cos(TO_RADIAN(rx));
    float v = (float)sin(TO_RADIAN(rx));
    float aux = t.Y();

    t.Y() = aux * u - t.Z() * v + cy;
    t.Z() = aux * v + t.Z() * u + cz;

    T = m_transform;
    T.Transpose();
    T[3] = 0;
    T[7] = 0;
    T[11] = 0;

    M.Ident();
    M[5] = u;
    M[6] = -v;
    M[9] = v;
    M[10] = u;

    T *= M;

    T[3] = t.X();
    T[7] = t.Y();
    T[11] = t.Z();

    T.Transpose();
    C3DEngine::Generic_to_MATRIX(m_transform,T);

    GL_COORD_VERTEX min;
    GL_COORD_VERTEX max;

    getBoundingBox(min,max);
    setBoundingBox(min,max);
}

    void CObject3DInstance::

    Object3DInstance(float ry, float cx, float cz)
{
    CGenericMatrix<float> T;
    CGenericMatrix<float> M;


    // Compute new position
    CGenericVector<float> t(m_transform.rowh.x - cx,m_transform.rowh.y,m_transform.rowh.z - cz,1);

    float u = (float)cos(TO_RADIAN(ry));
    float v = (float)sin(TO_RADIAN(ry));
    float aux = t.X();

    t.X() = aux * u - t.Z() * v + cx;
    t.Z() = aux * v + t.Z() * u + cz;

    T = m_transform;
    T.Transpose();
    T[3] = 0;
    T[7] = 0;
    T[11] = 0;

    M.Ident();
    M[0] = u;
    M[2] = -v;
    M[8] = v;
    M[10] = u;

    T *= M;

    T[3] = t.X();
    T[7] = t.Y();
    T[11] = t.Z();

    T.Transpose();
    C3DEngine::Generic_to_MATRIX(m_transform,T);

    GL_COORD_VERTEX min;
    GL_COORD_VERTEX max;

    getBoundingBox(min,max);
    setBoundingBox(min,max);
}

    void CObject3DInstance::

    Object3DInstance(float rz, float cx, float cy)
{
    CGenericMatrix<float> T;
    CGenericMatrix<float> M;


    // Compute new position
    CGenericVector<float> t(m_transform.rowh.x - cx,m_transform.rowh.y - cy,m_transform.rowh.z,1);

    float u = (float)cos(TO_RADIAN(rz));
    float v = (float)sin(TO_RADIAN(rz));
    float aux = t.X();

    t.X() = aux * u - t.Y() * v + cx;
    t.Y() = aux * v + t.Y() * u + cy;

    T = m_transform;
    T.Transpose();
    T[3] = 0;
    T[7] = 0;
    T[11] = 0;

    M.Ident();
    M[0] = u;
    M[1] = -v;
    M[4] = v;
    M[5] = u;

    T *= M;

    T[3] = t.X();
    T[7] = t.Y();
    T[11] = t.Z();

    T.Transpose();
    C3DEngine::Generic_to_MATRIX(m_transform,T);

    GL_COORD_VERTEX min;
    GL_COORD_VERTEX max;

    getBoundingBox(min,max);
    setBoundingBox(min,max);
}
*/



}
