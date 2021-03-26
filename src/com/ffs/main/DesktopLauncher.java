package com.ffs.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * 启动类
 * @author fengfasong
 * @date 2020/9/3
 */
public class DesktopLauncher {

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1088;//34
        config.height = 896;//28
        config.resizable = false;
        new LwjglApplication(new MainGame06(),config);
    }
}
