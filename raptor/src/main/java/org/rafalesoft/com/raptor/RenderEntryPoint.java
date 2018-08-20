package org.rafalesoft.com.raptor;

public interface RenderEntryPoint
{
    /**
     * Main entry point for placing OpenGL context initialisation
     * (creation of all OpenGL objects and states)
     */
    void glInitContext();

    /**
     * Main entry point for user rendering
     */
    void glRender();
}
