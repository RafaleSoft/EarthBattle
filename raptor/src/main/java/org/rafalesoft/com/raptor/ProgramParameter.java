package org.rafalesoft.com.raptor;

import java.util.ArrayList;


public class ProgramParameter
{
    public enum GL_VERTEX_ATTRIB
    {
        POSITION,
        WEIGHTS,
        NORMAL,
        PRIMARY_COLOR,
        SECONDARY_COLOR,
        FOG_COORDINATE,
        ADDITIONAL_PARAM1,
        ADDITIONAL_PARAM2,
        TEXCOORD0,
        TEXCOORD1,
        TEXCOORD2,
        TEXCOORD3,
        TEXCOORD4,
        TEXCOORD5,
        TEXCOORD6,
        TEXCOORD7
    }


    public class ParameterBase
    {
        public void setName(String n) { m_name = n; }
        public String getName() { return m_name; }
/*
        virtual uint64_t size(void) const { return 0; };
        virtual const void* addr(void) const { return NULL; };
        virtual CParameterBase* clone(void) const { return NULL; };
        virtual bool copy(const CParameterBase&) { return false; };

        virtual CParameterBase& operator=(const CParameterBase& p)
        {
            m_name = p.m_name;
            locationIndex = p.locationIndex;
            locationType = p.locationType;
            return *this;
        }

        virtual size_t getTypeId(void) const
        {
            static size_t ti = typeid(void*).hash_code();
            return ti;
        }

        template <class T> bool isA(const T &t) const
        {
            return getTypeId() == CParameter<T>::TypeId();
        }

        //! Parameter locations can be retrieved once per link and reused.
        int				locationIndex;
        unsigned int	locationType;

        protected:
        CParameterBase() :m_name(""), locationIndex(-1), locationType(0) {};
        CParameterBase(const CParameterBase& p)
			:m_name(p.m_name),
        locationIndex(p.locationIndex),
        locationType(p.locationType) {};
*/
        //! The name of the value: it is matched to variables of the program ( @see GLSL )
        String				m_name;
    }


    public class Parameter extends ParameterBase
    {
        public Parameter() {}
        /*
        static size_t TypeId(void)
        {
            static size_t ti = typeid(P).hash_code();
            return ti;
		};
        virtual size_t getTypeId(void) const { return CParameter<P>::TypeId(); };

        virtual uint64_t size(void) const { return sizeof(p); };
        virtual const void* addr(void) const { return &p; };
        virtual CParameterBase* clone(void) const
        {
            CParameter<P> *param = new CParameter<P>(p);
			*param = *this;
            return param;
        };
        virtual bool copy(const CParameterBase& param)
        {
            // Should a safe 'isA' be used ? only if fast enough.
            if (param.size() == sizeof(p))
            {
                p = ((const CParameter<P>&)param).p;
                return true;
            }
            else
                return false;
        };

        CParameter<P>& operator=(const P &_p)
        {
            p = _p;
            return *this;
        };

        CParameter<P>& operator=(const CParameter<P> &_p)
        {
            CParameterBase::operator=(_p);
            p = _p.p;
            return *this;
        };

        virtual CParameterBase& operator=(const CParameterBase& param)
        {
            //	Safe assignment
            if (param.isA(p))
                this->operator=((const CParameter<P>&)param);
            return *this;
        }

        public:
        P	p;
        */
    }


    //! Default constructor. The parameter set is uninitialised, user
    //! must call the proper init method from the relevant shader.
    public void ProgramParameters() {}

    //!	Remove all parameters
    void clear() { m_parameters.clear(); }

    //! Number of paremeters
    long getNbParameters() { return m_parameters.size(); }


    //! Add a generic parameter
    //template <class param> bool addParameter(const std::string& name, const param& param);

    //! Add a base parameter
    boolean addParameter(ParameterBase param)
    {
        m_parameters.add(param);
        return false;
    }

    //! Parameters assignment: updates typed values, based on identical parameter names
    //bool updateParameters(const CProgramParameters& params);

    private ArrayList<ParameterBase> m_parameters = new ArrayList<>();
}


/*
template <class P>
        bool CProgramParameters::addParameter(const std::string& name, const P& param)
        {
            CProgramParameters::CParameter<P> *p = new CProgramParameters::CParameter<P>(param);
            p->name(name);
            m_parameters.push_back(p);

            return false;
        }
}*/
