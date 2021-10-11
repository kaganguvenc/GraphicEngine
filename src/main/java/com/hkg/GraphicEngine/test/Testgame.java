package com.hkg.GraphicEngine.test;

import com.hkg.GraphicEngine.core.ILogic;
import com.hkg.GraphicEngine.core.RenderManager;
import com.hkg.GraphicEngine.core.WindowManager;
import com.hkg.GraphicEngine.Launcher;
import com.hkg.GraphicEngine.core.ObjectLoader;
import com.hkg.GraphicEngine.core.entity.Model;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Testgame implements ILogic {

    private int direction = 0;
    private float colour = 0.0f;

    private RenderManager renderer;
    private ObjectLoader loader;
    private WindowManager window;

    private Model model;

    public Testgame() {
        renderer = new RenderManager();
        window = Launcher.getWindow();
        loader = new ObjectLoader();
    }

    @Override
    public void init() throws Exception {
        renderer.init();
        float[] vertices = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
            -0.5f, 0.5f, 0f
        };

        int[] indices = {
            0,1,3,
            3,1,2

        };

        model = loader.loadModel(vertices,indices);

    }

    @Override
    public void input() {
        if (window.isKeyPressed(GLFW.GLFW_KEY_UP)) {
            direction = 1;
        } else if (window.isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
            direction = -1;
        } else {
            direction = 0;
        }
    }

    @Override
    public void update() {
        colour += (float) direction * 0.01f;
        colour = colour > 1.0 ? 1 : colour < 0 ? colour = 0 : colour;
    }

    @Override
    public void render() {
        if (window.isResize()) {
            GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResize(true);
        }
        //renderer.clear();
        window.setClearColor(colour, colour, colour, 0.0f);
        renderer.render(model);

    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        loader.cleanup();
    }

}
