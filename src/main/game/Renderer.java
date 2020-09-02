package main.game;

import org.lwjgl.system.MemoryUtil;
import main.engine.Utils;
import main.engine.Window;
import main.engine.graph.ShaderProgram;
import main.engine.graph.Mesh;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Renderer {

    private int vboId;
    private int vaoId;
    private ShaderProgram shaderProgram;

    public Renderer() {

    }

    public void init() throws Exception {
        shaderProgram = new ShaderProgram();
        // have to use relative path from Utils.java to main.resources files
        // up 1 gets you to src, then into main.resources then the filename
        shaderProgram.createVertexShader(Utils.loadResource("../resources/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("../resources/fragment.fs"));
        shaderProgram.link();
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Window window, Mesh mesh) {
        clear();

        if (window.isResized()) {
            glViewport(0,0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        shaderProgram.bind();

        // Draw the mesh
        glBindVertexArray(mesh.getVaoId());
        glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

        // Restore state
        glBindVertexArray(0);

        shaderProgram.unbind();
    }

    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }

}
