package engine;

package org.lwjglb.engine;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    // class variables
    private final String title;
    private int width;
    private int height;
    private long windowHandle;
    private boolean resized;
    private boolean vSync;

    // constructor
    public Window(String title, int width, int height, boolean vSync) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.vSync = vSync;
        this.resized = false;
    }

    // initialization
    public void init() {
        // setup error callback; will print error msg in System.err
        GLFWErrorCallback.createPrint(System.err).set();

        // init GLFW. Necessary for most GLFW functions
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints(); // optional; current window hints are just default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // window hint is hidden
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // window is resizeable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

        // create window
        windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);
        if (windowHandle == NULL) {
            throw new RuntimeException("failed to create GLFW window");
        }

        )
    }

    public boolean isKeyPressed(int keyCode) {
        return glfwGetKey(windowHandle, keyCode) == GLFW_PRESS;
    }

}
