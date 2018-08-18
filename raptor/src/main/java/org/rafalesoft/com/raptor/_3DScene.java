package org.rafalesoft.com.raptor;

import java.util.ArrayList;

public class _3DScene
{
    public boolean addObject(Object3D obj)
    {
        if (objects.contains(obj))
            return false;
        objects.add(obj);
        return true;
    }

    public void glRender()
    {
        for (Object3D obj:objects)
            obj.glRender();
    }

    private ArrayList<Object3D> objects = new ArrayList<>();
}
