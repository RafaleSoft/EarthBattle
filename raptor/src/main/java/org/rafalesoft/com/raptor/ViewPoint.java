package org.rafalesoft.com.raptor;

import org.rafalesoft.com.raptor.CGLTypes.*;


public class ViewPoint
{
    public enum VIEW_POINT_MODEL
    {
        ORTHOGRAPHIC,
        PERSPECTIVE
    }

    public enum VIEW_POINT_POSITION
    {
        EYE,
        TARGET
    }


    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private final float[] viewVolume = new float[6];
    private VIEW_POINT_MODEL model = VIEW_POINT_MODEL.PERSPECTIVE;

    GL_COORD_VERTEX Origin = new GL_COORD_VERTEX(0.0f,0.0f,0.0f,1.0f);
    GL_COORD_VERTEX Target = new GL_COORD_VERTEX(0.0f,0.0f,-1.0f,1.0f);
    GL_COORD_VERTEX Scale = new GL_COORD_VERTEX(1.0f,1.0f,1.0f,1.0f);

    private double		m_lfAlpha = 0.0;
    private double		m_lfBeta = 0.0;
    private double		m_lfGamma = 0.0;
    private double		m_lfLength = 0.0;
    //private boolean		m_bContinus = true;
    //private boolean		m_bLoop = true;
    //private int			currentPath = -1;
    //private float		timePos = 0;	// current position between starttime & endtime
    //private float		startTime = 0;	// first time position
    //private float		endTime = 1;



    public ViewPoint()
    {
        //  Initialise view
        viewVolume[0] = -1.33f;
        viewVolume[1] = 1.33f;
        viewVolume[2] = -1.0f;
        viewVolume[3] = 1.0f;
        viewVolume[4] = 1.0f;
        viewVolume[5] = 200.0f;

        // initialise trackers.
        //pEyeTrack = null;
        //pEyeTrack2 = null;
        //pTargetTrack = null;
        //pTargetTrack2 = null;
    }

/*
    public void setPosition(float x,float y,float z,VIEW_POINT_POSITION p)
    {
        switch(p)
        {
            case EYE:
                Origin.set(x,y,z,1.0f);
                break;
            case TARGET:
                Target.set(x,y,z,1.0f);
                break;
        }

        recomputeViewPoint();
    }
*/

    public void setViewVolume(float left, float right,
                              float bottom, float up,
                              float n, float f,
                              VIEW_POINT_MODEL m)
    {
        viewVolume[0] = left;
        viewVolume[1] = right;
        viewVolume[2] = bottom;
        viewVolume[3] = up;
        viewVolume[4] = n;
        viewVolume[5] = f;
        model = m;
    }
/*
    public GL_MATRIX getFrustum()
    {
        GL_MATRIX frustum = new GL_MATRIX();

        float l = viewVolume[0];
        float r = viewVolume[1];
        float b = viewVolume[2];
        float t = viewVolume[3];
        float n = viewVolume[4];
        float f = viewVolume[5];

        if (VIEW_POINT_MODEL.PERSPECTIVE == model)
        {
            //	2.n / (r-l)		0		(r+l) / (r-l)		0
            //		0		2.n / (t-b)	(t+b) / (t-b)		0
            //		0			0		-(f+n) / (f-n)	-2.f.n / (f-n)
            //		0			0			-1				0

            frustum[0] = 2 * n / (r - l);
            frustum[2] = (r + l) / (r - l);

            frustum[5] = 2 * n / (t - b);
            frustum[6] = (t + b) / (t - b);

            frustum[10] = -(f + n) / (f - n);
            frustum[11] = -2 * f * n / (f - n);

            frustum[14] = -1;
        }
        else if (VIEW_POINT_MODEL.ORTHOGRAPHIC == model)
        {
            //	2 / (r-l)		0			0			-(r+l) / (r-l)
            //		0		2 / (t-b)		0			-(t+b) / (t-b)
            //		0			0		-2 / (f-n)		-(f+n) / (f-n)
            //		0			0			0				1

            frustum[0] = 2 / (r - l);
            frustum[3] = -(r + l) / (r - l);

            frustum[5] = 2 / (t - b);
            frustum[7] = -(t + b) / (t - b);

            frustum[10] = -2 / (f - n);
            frustum[11] = -(f + n) / (f - n);

            frustum[15] = 1;
        }

        return frustum;
    }

    GL_COORD_VERTEX getPosition(VIEW_POINT_POSITION p)
    {
        switch(p)
        {
            case EYE:
                return Origin;
            case TARGET:
                return Target;
            //! This case is never reached
            default:
                return new GL_COORD_VERTEX(0,0,0,0);
        }
    }

//////////////////////////////////////////////////////////////////////
// Transforms
//////////////////////////////////////////////////////////////////////
    public void Object3DInstance(float tx,float ty,float tz)
    {
        CGenericVector<float>	z_axis = Origin - Target;
        z_axis.Normalize();

        CGenericVector<float> x_axis;
        CGenericVector<float> y_axis;
        C3DEngine::Get3DEngine()->normals(z_axis,x_axis,y_axis);

        //	x_axis y component must be 0.
        //	keep y_axis y component positive ...
        if (y_axis.Y() < 0)
        {
            y_axis = !y_axis;
            x_axis = !x_axis;
        }

        CGenericVector<float>	delta = x_axis*tx + y_axis*ty + z_axis*tz;
        delta.H(0.0f);

        Origin += delta;
        Target += delta;
    }

    public void Object3DInstance(float sx,float sy,float sz)
    {
        Scale.X() *= sx;
        Scale.Y() *= sy;
        Scale.Z() *= sz;
    }

    public void Object3DInstance(float rz)
    {
        m_lfGamma += rz;
    }

    public void Object3DInstance(float rx)
    {
        m_lfBeta -= rx;
        if (m_lfBeta > 360.0)
            m_lfBeta -= 360.0;
        else if (m_lfBeta < 0.0)
            m_lfBeta += 360.0;

        CGenericVector<float> v = Target - Origin;

        v.Y()  = m_lfLength * sin(TO_RADIAN(m_lfBeta));
        float d2 = m_lfLength*m_lfLength - (v.Y()*v.Y());
        float d = -sqrt(d2);

        v.Z() = d * cos(TO_RADIAN(m_lfAlpha));
        v.X() = d * sin(TO_RADIAN(m_lfAlpha));

        Target = v + Origin;
    }

    public void Object3DInstance(float ry)
    {
        m_lfAlpha -= ry;
        if (m_lfAlpha > 360.0)
            m_lfAlpha -= 360.0;
        else if (m_lfAlpha < 0.0)
            m_lfAlpha += 360.0;

        CGenericVector<float> v = Target - Origin;

        float d2 = m_lfLength*m_lfLength - (v.Y()*v.Y());
        float d = -Math.sqrt(d2);

        v.Z() = d * Math.cos(TO_RADIAN(m_lfAlpha));
        v.X() = d * Math.sin(TO_RADIAN(m_lfAlpha));

        Target = v + Origin;
    }


    //////////////////////////////////////////////////////////////////////
    // Rendering
    //////////////////////////////////////////////////////////////////////
    public void recomputeViewPoint()
    {
        CGenericVector<float> v = Target - Origin;

        m_lfLength = v.Normalize();

        m_lfAlpha = TO_DEGREE(Math.atan2(v.X(),v.Z())) + 180.0;
        m_lfBeta = -TO_DEGREE(Math.atan2(Math.sqrt(v.X()*v.X()+v.Z()*v.Z()),v.Y())) + 90.0;
    }


    public void Object3DInstance()
    {
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glScalef(Scale.X(),Scale.Y(),Scale.Z());

        glRotatef(m_lfGamma,0.0f,0.0f,1.0f);
        glRotatef(-m_lfBeta,1.0f,0.0f,0.0f);
        glRotatef(-m_lfAlpha,0.0f,1.0f,0.0f);

        glTranslatef(-Origin.X(),-Origin.Y(),-Origin.Z());

        C3DEngine::Get3DEngine()->glConfigureEngine(this);
    }

    public void glRenderViewPointModel()
    {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        switch(model)
        {
            case ORTHOGRAPHIC:
                glOrtho(viewVolume[0],viewVolume[1],
                        viewVolume[2],viewVolume[3],
                        viewVolume[4],viewVolume[5]);
                break;
            case PERSPECTIVE:
                glFrustum(	viewVolume[0],viewVolume[1],
                            viewVolume[2],viewVolume[3],
                            viewVolume[4],viewVolume[5]);
        }

        glMatrixMode(GL_MODELVIEW);
    }


    public void setTimeInterval(float tMin, float tMax)
    {
        if ((tMin<tMax)&&(tMin>=0))
        {
            startTime = tMin;
            endTime = tMax;
        }
        else
        {
            startTime = 0.0f;
            endTime = 1.0f;
        }
    }

    public void setTimePos(float tpos)
    {
        if ((tpos>endTime)||(tpos<startTime))
            timePos = startTime;
        else
            timePos = tpos;
    }

    public void trackObject(CObject3D *object, VIEW_POINT_POSITION p)
    {
        switch(p)
        {
            case EYE:
                pEyeTrack = object;
                pEyeTrack2 = null;
                break;
            case TARGET:
                pTargetTrack = object;
                pTargetTrack2 = null;
                break;
        }
    }

    public void trackObject(CLight *object, VIEW_POINT_POSITION p)
    {
        switch(p)
        {
            case EYE:
                pEyeTrack2 = object;
                pEyeTrack = null;
                break;
            case TARGET:
                pTargetTrack2 = object;
                pTargetTrack = null;
                break;
        }
    }

    void RAPTOR_FASTCALL deltaTime(float dt)
    {
        timePos += dt;

        if (pEyeTrack != null)
        {
            CGLTypes.GL_COORD_VERTEX center;
            pEyeTrack->getCenter(center);
            setPosition(center.x,center.y,center.z,VIEW_POINT_POSITION.EYE);
        }
        else if (pEyeTrack2 != null)
        {
            CGLTypes.GL_COORD_VERTEX center = pEyeTrack2->getLightPosition();
            setPosition(center.x,center.y,center.z,VIEW_POINT_POSITION.EYE);
        }

        if (pTargetTrack != null)
        {
            CGLTypes.GL_COORD_VERTEX center;
            pTargetTrack->getCenter(center);
            setPosition(center.x,center.y,center.z,VIEW_POINT_POSITION.TARGET);
        }
        else if (pTargetTrack2 != null)
        {
            CGLTypes.GL_COORD_VERTEX center = pTargetTrack2->getLightPosition();
            setPosition(center.x,center.y,center.z,VIEW_POINT_POSITION.TARGET);
        }


        if (currentPath>=0)
        {
            if (timePos>endTime)
            {
                if (m_bLoop)
                    timePos = startTime;
                else
                    timePos = endTime;

                if (m_bContinus)
                {
                    currentPath++;
                    if (m_bLoop)
                    {
                        if (currentPath >= (int)(eyePositionPaths.size()))
                            currentPath = 0;
                    }
                    else
                    {
                        if (currentPath < (int)(eyePositionPaths.size()))
                            timePos = startTime;
                        else
                            currentPath--;
                    }
                }
            }

            float uPos = (timePos-startTime)/(endTime-startTime);

            Origin = eyePositionPaths[currentPath]->eval(uPos);
            Target = targetPaths[currentPath]->eval(uPos);

            recomputeViewPoint();
        }
    }


    public void addPath(C3DPath *eyePositionPath,C3DPath *targetPath)
    {
        if ((eyePositionPath != null)&&(targetPath != null))
        {
            eyePositionPaths.push_back(eyePositionPath);
            targetPaths.push_back(targetPath);
        }
    }

    public void setCurrentPath(int nPath,boolean continus,boolean loop)
    {
        if (nPath < (int)(eyePositionPaths.size()))
            currentPath = nPath;

        m_bContinus = continus;
        m_bLoop = loop;
    }
    */
}
