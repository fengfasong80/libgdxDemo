package com.ffs.music;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ffs.main.MainGame08;
import com.ffs.main.SdGame;
import com.ffs.music.game.MusicGame;

/**
 * 程序启动类
 * @author fengfasong
 * @date 2020/9/8
 */
public class MainLuncher {
    public static void main(String[] args) {
        LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.width = 1100;
        configuration.height = 700;
        configuration.resizable = false;

        new LwjglApplication(new SdGame(),configuration);
    }
}
