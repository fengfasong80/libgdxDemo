package com.ffs.box2d;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * 启动类
 * @author fengfasong
 * @date 2020/9/11
 */
public class BoxMainLuncher {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1088;//34
        config.height = 896;//28
        config.resizable = false;
        new LwjglApplication(new Box2dGame03(),config);
    }
}
