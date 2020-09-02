package main.game;

import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

import main.engine.IGameLogic;
import main.engine.Window;
import main.engine.graph.Mesh;
import main.engine.GameItem;

public class DummyGame implements IGameLogic {

    // class variables
    private int displxInc = 0;
    private int displyInc = 0;
    private int displzInc = 0;
    private int scaleInc = 0;
    private final Renderer renderer;
    private GameItem[] gameItems;

    public DummyGame() {
        renderer = new Renderer();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        float[] positions = new float[] {
                -0.5f,  0.5f,  0.0f,
                -0.5f, -0.5f,  0.0f,
                 0.5f, -0.5f,  0.0f,
                 0.5f,  0.5f,  0.0f,
        };
        float[] colors = new float[] {
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        int[] indices = new int[] {
                0, 1, 3, 3, 1, 2,
        };
        Mesh mesh = new Mesh(positions, colors, indices);
        GameItem gameItem = new GameItem(mesh);
        gameItem.setPosition(0,0,-2);
        gameItems = new GameItem[] { gameItem };
    }

    @Override
    public void input(Window window) {
        displxInc = 0;
        displyInc = 0;
        displzInc = 0;
        scaleInc = 0;
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            displyInc = 1;
        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            displyInc = -1;
        } else if (window.isKeyPressed(GLFW_KEY_A)){
            displxInc = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            displxInc = 1;
        } else if (window.isKeyPressed(GLFW_KEY_W)) {
            displzInc = 1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            displzInc = -1;
        } else if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            scaleInc = -1;
        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            scaleInc = 1;
        }
    }

    @Override
    public void update(float interval) {
        for (GameItem gameItem : gameItems) {
            // Update position
            Vector3f itemPos = gameItem.getPosition();
            float posx = itemPos.x + displxInc * 0.01f;
            float posy = itemPos.y + displyInc * 0.01f;
            float posz = itemPos.z + displzInc * 0.01f;
            gameItem.setPosition(posx, posy, posz);

            // Update scale
            float scale = gameItem.getScale();
            scale += scaleInc * 0.05f;
            if (scale < 0) {
                scale = 0;
            }
            gameItem.setScale(scale);

            // Update rotation angle
            float rotation = gameItem.getRotation().z + 1.5f;
            if (rotation > 360) {
                rotation = 0;
            }
            gameItem.setRotation(0,0,rotation);
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, gameItems);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanup();
        }
    }
}
