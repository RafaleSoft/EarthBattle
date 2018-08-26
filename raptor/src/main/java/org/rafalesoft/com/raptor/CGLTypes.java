package org.rafalesoft.com.raptor;

import android.support.annotation.NonNull;

public final class CGLTypes
{
// #ifndef __RAPTOR_VERSION_H__
//	#include "System/Version.h"
// #endif

    /**
     *	Generic opaque pointers.
     *
     * A valid handle must not be NULL, i.e.
     * both the handle and the hClass attributes
     * are strictly positive. hClass of handles are
     * divided in two parts:
     * - Raptor private handles, created and managed by Raptor
     *	- User handles, that Raptor can use but that identify client classes.
     */
    public static class RAPTOR_HANDLE implements Comparable<RAPTOR_HANDLE>
    {
        public long hClass = 0;
        public long handle = 0;

        RAPTOR_HANDLE(long c,long p)
        {
            hClass = c;
            handle = p;
        }

        @Override
        public int compareTo(@NonNull RAPTOR_HANDLE h)
        {
            if (h.hClass==hClass)
            {
                if (h.handle == handle)
                    return 0;
                else if (handle < h.handle)
                    return -1;
                else
                    return 1;
            }
            else if (hClass < h.hClass)
                return -1;
            else
                return 1;
        }
    }

    /** Classes of handles are in 2 categories : Raptor handles and client handles. */
    public static long RAPTOR_HANDLE_CLASS = 0x00000000;
    public static long CLIENT_HANDLE_CLASS = 0x00010000;
    public static long DEVICE_CONTEXT_CLASS = 0x00010000;
    public static long WINDOW_CLASS = 0x00010001;
    public static long DIB_CLASS = 0x00010002;

    /**
     *	Base window buffers configuration
     *
     *	These constants are passed to the graphic context factory
     *	using CGL_CRATE_STRUCT.display_mode. The display mode is
     * 	set by combining these constants with a bitwise OR.
     * 	Do not use them in the frame_mode parameter.
     */
    public static long CGL_NULL	= 0x00000000;
    public static long CGL_RGB = 0x00000001;
    public static long CGL_RGBA = 0x00000002;
    public static long CGL_DEPTH_16 = 0x00000010;
    public static long CGL_DEPTH_24	= 0x00000020;
    public static long CGL_DEPTH_32	= 0x00000030; // CGL_DEPTH_16 | CGL_DEPTH_24
    public static long CGL_DEPTH = CGL_DEPTH_24;
    public static long CGL_STENCIL = 0x00000040;
    public static long CGL_RENDER_CUBETEXTURE = 0x02001000;
    public static long CGL_ACCUM = 0x00002000;
    public static long CGL_FLOAT = 0x00010002; // float format is only used with rgba
    public static long CGL_FLOAT_16 = 0x00030002;
    public static long CGL_FLOAT_32 = 0x00050002;


    //////////////////////////////////////////////////////////////////////
    //	Mathematical defines
    //
    //
    //#if !defined(RANDOM)	// returns a random value between -0.5 and 0.5
	//    #define RANDOM (((float)(rand()-(RAND_MAX/2)))/RAND_MAX)
    //    #endif
    //#if !defined(PI)
	//    #define PI 3.1415926535897932384626433832795
    //#endif
    //#if !defined(TWO_PI)
	//  #define TWO_PI 6.283185307179586476925286766559
    //#endif
    //#if !defined(ABS)
	//  #define ABS(x) (x)>0?(x):-(x)
    //#endif
    //#if !defined(RAD)
	//    #define TO_RADIAN(x) ((x) * 0.017453292519943295769236907684886)	//x*PI/180.0f
    //#endif
    //#if !defined(DEG)
	//  #define TO_DEGREE(x) ((x) * 57.295779513082320876798154814105)	//x*180.0f/PI
    //#endif
    //#if !defined(SGN)
    //  #define SGN(x) (x)>=0?(1):(-1)
    //#endif
    //#if !defined(MIN)
	//  #define MIN(a,b) (((a)<(b))?(a):(b))
    //#endif
    //#if !defined(MAX)
	//  #define MAX(a,b) (((a)>(b))?(a):(b))
    //#endif
    //#if !defined(ONE_OVER_256)
	//  #define ONE_OVER_256 0.00390625
    //  #define ONE_OVER_256_F 0.00390625f
    //#endif
    //#if !defined(ONE_OVER_255)
	//  #define ONE_OVER_255 0.00392156
    //  #define ONE_OVER_255_F 0.00392156f
    //#endif

    /**
     *	Own data structures for fast access
     *	( faster than classes, data can also
     * 	be tightly packed into registers )
     * 	These structure types match opengl data storage
     * 	for vertex and matrix and can be safely cast
     * 	in function calls such as glMultMatrixf(...)
     */
    public static class GL_COORD_VERTEX implements Comparable<GL_COORD_VERTEX>
    {
        public float x = 0.0f;	//	3D coordinates
        public float y = 0.0f;
        public float z = 0.0f;
        public float h = 0.0f;	// homogenous component

        public GL_COORD_VERTEX(float _x,float _y,float _z,float _h)
        {
            x = _x;
            y = _y;
            z = _z;
            h = _h;
        }

        public GL_COORD_VERTEX set(GL_COORD_VERTEX v)
        {
            x = v.x;
            y = v.y;
            z = v.z;
            h = v.h;
            return this;
        }

        public GL_COORD_VERTEX set(float v_x,float v_y,float v_z,float v_h)
        {
            x = v_x;
            y = v_y;
            z = v_z;
            h = v_h;
            return this;
        }

        public float [] asFloatArray()
        {
            float [] res = new float[]{x, y, z, h};
            return res;
        }

        @Override
        public int compareTo(@NonNull GL_COORD_VERTEX o)
        {
            if (x == o.x)
            {
                if (y == o.y)
                {
                    if (z == o.z)
                        return 0;
                    else if (z < o.y)
                        return -1;
                    else return 1;
                }
                else if (y < o.y)
                    return -1;
                else
                    return 1;
            }
            else if (x < o.x)
                return -1;
            else
                return 1;
        }
    }
/*
    public static class GL_HIRES_COORD_VERTEX;
    {
        double x;	//	3D coordinates
        double y;
        double z;
        double h;	// homogenous component

	const GL_HIRES_COORD_VERTEX_TAG& operator=(const GL_HIRES_COORD_VERTEX_TAG& r_v)
        {
            x = r_v.x;
            y = r_v.y;
            z = r_v.z;
            h = r_v.h;
            return *this;
        }

        operator double*() { return (double*)(this); }
        operator const double*() const { return (const double*)this; };

        GL_HIRES_COORD_VERTEX_TAG(double _x = 0.0f,double _y = 0.0f,double _z = 0.0f,double _h = 1.0f)
		:x(_x),y(_y),z(_z),h(_h)
        {
        }
    }

    public static class GL_TEX_VERTEX_TAG
    {
        float u;	// texture coordinates
        float v;

        bool operator==(const GL_TEX_VERTEX_TAG &r_v)
        {
            return (*((int*)&u) == *((int*)&r_v.u) &&
				*((int*)&v) == *((int*)&r_v.v));
        }
        bool operator==(float val)
        {
            return (*((int*)&u) == *((int*)&val) &&
				*((int*)&v) == *((int*)&val));
        }

        operator float*() { return (float*)(this); }
        operator const float*() const { return (const float*)this; };

        GL_TEX_VERTEX_TAG(float _u = 0.0f,float _v = 0.0f)
		:u(_u),v(_v)
        {
        }
    }
*/
    /**
     *	t => translation vector
     *	R => orientation matrix
     *	H => homogenous vector
     *	x' = M*x => x' =  ( R*x + x.h*t ) / H*x
     */
    public static class GL_MATRIX_TAG
    {
        public GL_COORD_VERTEX rowx;//	rx.x rx.y rx.z t.x
        public GL_COORD_VERTEX rowy;//	ry.x ry.y ry.z t.y
        public GL_COORD_VERTEX rowz;//	rz.x rz.y rz.z t.z
        public GL_COORD_VERTEX rowh;//	h.x h.y h.z h.h

        public float [] asFloatArray()
        {
            float [] res = new float[]{ rowx.x, rowx.y, rowx.z, rowx.h,
                                        rowy.x, rowy.y, rowy.z, rowy.h,
                                        rowz.x, rowz.y, rowz.z, rowz.h,
                                        rowh.x, rowh.y, rowh.z, rowh.h };
            return res;
        }
    }

/*
#define ZERO_COORD_VERTEX(v) { v.x=0.0f;v.y=0.0f;v.z=0.0f;v.h=1.0f; }
#define NULL_COORD_VERTEX(v) { v.x=0.0f;v.y=0.0f;v.z=0.0f;v.h=0.0f; }
#define NULL_TEX_VERTEX(t) { t.u=0.0f;t.v=0.0f; }

#define NULL_VERTEX(v) \
    { NULL_COORD_VERTEX(v.coords) \
        NULL_TEX_VERTEX(v.texcoords) \
        v.w = 0.0f; \
        v.f = 0.0f; }

#define ZERO_VERTEX(v) \
    { ZERO_COORD_VERTEX(v.coords) \
        NULL_TEX_VERTEX(v.texcoords) \
        v.w = 0.0f; \
        v.f = 0.0f; }

#define NULL_MATRIX(m) \
    { NULL_COORD_VERTEX(m.rowx)\
        NULL_COORD_VERTEX(m.rowy)\
        NULL_COORD_VERTEX(m.rowz)\
        NULL_COORD_VERTEX(m.rowh) }

#define ZERO_MATRIX(m) \
    { NULL_COORD_VERTEX(m.rowx)\
        NULL_COORD_VERTEX(m.rowy)\
        NULL_COORD_VERTEX(m.rowz)\
        ZERO_COORD_VERTEX(m.rowh) }

#define IDENT_MATRIX(m) \
    { ZERO_MATRIX(m)\
        m.rowx.x = m.rowy.y = m.rowz.z = 1.0; }

#define RX_MATRIX(m,a)\
    { IDENT_MATRIX(m)\
        m.rowz.z = m.rowy.y = (float)cos(TO_RADIAN(a));\
        m.rowy.z = -(float)sin(TO_RADIAN(a));\
        m.rowz.y = (float)sin(TO_RADIAN(a)); }

#define RY_MATRIX(m,a)\
    { IDENT_MATRIX(m)\
        m.rowz.z = m.rowx.x = (float)cos(TO_RADIAN(a));\
        m.rowx.z = -(float)sin(TO_RADIAN(a));\
        m.rowz.x = (float)sin(TO_RADIAN(a)); }

#define RZ_MATRIX(m,a)\
    { IDENT_MATRIX(m)\
        m.rowy.y = m.rowx.x = (float)cos(TO_RADIAN(a));\
        m.rowx.y = -(float)sin(TO_RADIAN(a));\
        m.rowy.x = (float)sin(TO_RADIAN(a)); }


    typedef struct GL_VERTEX_DATA_TAG
    {
        GL_COORD_VERTEX	vertex;
        GL_COORD_VERTEX	normal;
        GL_TEX_VERTEX	texCoord0;
        CColor::RGBA	color;
        CColor::RGBA	secondaryColor;
        GL_COORD_VERTEX	tangent;
        GL_COORD_VERTEX	binormal;
        float			fog;
        float			weight;
    } GL_VERTEX_DATA;
    typedef GL_VERTEX_DATA *LP_GL_VERTEX_DATA;
*/
}
