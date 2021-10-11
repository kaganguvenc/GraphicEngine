/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hkg.GraphicEngine;

import com.hkg.GraphicEngine.core.EngineManager;
import com.hkg.GraphicEngine.core.WindowManager;
import com.hkg.GraphicEngine.core.utils.Consts;
import com.hkg.GraphicEngine.test.Testgame;

/**
 *
 * @author kagan.guvenc
 */
public class Launcher {

    private static WindowManager window;
    private static Testgame game;

    public static void main(String[] args) {
        window = new WindowManager(Consts.TITLE, 1600, 900, false);
        game = new Testgame();
        EngineManager engine = new EngineManager();
        try {
            engine.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static WindowManager getWindow() {
        return window;
    }

    public static Testgame getGame() {
        return game;
    }
    
    

}
