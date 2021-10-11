/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hkg.GraphicEngine.core;

/**
 *
 * @author kagan.guvenc
 */
public interface ILogic {

    void init() throws Exception;

    void input();

    void update();

    void render();

    void cleanup();

}
