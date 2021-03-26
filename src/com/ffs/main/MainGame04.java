package com.ffs.main;

import com.badlogic.gdx.Game;
import com.ffs.screen.GameScreen;
import com.ffs.screen.StartScreen;

/**
 * 主程序类
 * @author fengfasong
 * @date 2020/9/3
 */
public class MainGame04 extends Game {
    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 800;

    private StartScreen startScreen;

    private GameScreen gameScreen;


    @Override
    public void create() {
        startScreen = new StartScreen(this);
        gameScreen = new GameScreen();
        setScreen(startScreen);
    }

    public void showGameScreen(){
        setScreen(gameScreen);
        if(startScreen != null) {
            startScreen.dispose();
            startScreen = null;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (startScreen != null) {
            startScreen.dispose();
            startScreen = null;
        }
        if (gameScreen != null) {
            gameScreen.dispose();
            gameScreen = null;
        }
    }
}
