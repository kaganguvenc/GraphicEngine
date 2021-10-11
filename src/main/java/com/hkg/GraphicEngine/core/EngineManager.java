/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hkg.GraphicEngine.core;

import com.hkg.GraphicEngine.Launcher;
import com.hkg.GraphicEngine.core.utils.Consts;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

/**
 *
 * @author kagan.guvenc
 */
public class EngineManager {

    public static final long NANOSECOND = 1000000000L;
    public static final float FRAMERATE = 144.0f;

    private static int fps;
    private static float frameTime = 1.0f / FRAMERATE;

    private boolean isRunning;

    private WindowManager window;
    private GLFWErrorCallback errorCallback;

    private ILogic gameLogic;

    private void init() throws Exception {
        GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        window = Launcher.getWindow();
        gameLogic = Launcher.getGame();
        window.init();
        gameLogic.init();
    }

    public void start() throws Exception {
        init();
        if (isRunning) {
            return;
        }
        run();
    }

    public void run() {
        this.isRunning = true;
        int frames = 0;
        long frameCounter = 0;
        long lastTime = System.nanoTime();
        double unprocessedTime = 0;
        while (isRunning) {
            boolean render = false;
            long now = System.nanoTime();
            long passedTime = now - lastTime;
            lastTime = now;
            unprocessedTime += (double) passedTime / (double) NANOSECOND;
            frameCounter += passedTime;
            input();
            while (unprocessedTime >= frameTime) {
                render = true;
                unprocessedTime -= frameTime;

                if (window.windowShouldClose()) {
                    stop();
                }
                if (frameCounter >= NANOSECOND) {
                    setFps(frames);
                    window.setTitle(Consts.TITLE + " FPS: " + getFps());
                    frames = 0;
                    frameCounter = 0;
                }
            }
            if (render) {
                update();
                render();
                frames++;
            }
        }
        window.cleanup();

    }

    private void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
    }

    private void input() {
        gameLogic.input();
    }

    private void render() {
        gameLogic.render();
        window.update();
    }

    private void update() {
        gameLogic.update();
    }

    private void cleanup() {
        window.cleanup();
        gameLogic.cleanup();
        errorCallback.free();
        GLFW.glfwTerminate();
    }

    public static int getFps() {
        return fps;
    }

    public static void setFps(int fps) {
        EngineManager.fps = fps;
    }

}
