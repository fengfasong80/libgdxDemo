package com.ffs.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * 主程序入口类
 * @author fengfasong
 * @date 2020/9/3
 */
public class MainGame implements ApplicationListener {

    private SpriteBatch spriteBatch;

    private Texture texture;

    private Pixmap pixmap;

    /**
     * 应用被创建时调用一次
     */
    @Override
    public void create() {
        spriteBatch = new SpriteBatch();


        Pixmap pixmap01 = new Pixmap(Gdx.files.internal("sprite01.png"));
        pixmap01.drawCircle(40,40,40);
        pixmap = new Pixmap(10,10,Pixmap.Format.RGBA8888);
        pixmap.drawPixmap(pixmap01,10,10);
        //pixmap.setColor(1,1,1,1);
        //pixmap.fillCircle(100,100,50);

        /*pixmap.setColor(1,0,0,1);
        pixmap.drawCircle(64,64,32);*/

        //pixmap.drawPixel(100,100);

      /*  pixmap.setColor(Color.GREEN);
        pixmap.drawLine(0,0,256,256);

        pixmap.setColor(Color.BLUE);
        pixmap.drawRectangle(128,128,64,64);

        pixmap.setColor(Color.YELLOW);
        pixmap.fillTriangle(0,256,0,128,128,128);*/

        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    /**
     * 游戏屏幕尺寸改变，且不处于暂停状态时调用
     * @param i
     * @param i1
     */
    @Override
    public void resize(int i, int i1) {

    }

    /**
     * 游戏主要逻辑更新，时刻都在进行调用
     */
    @Override
    public void render() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(texture,0,0);
        spriteBatch.end();

    }

    /**
     * 游戏暂停状态
     */
    @Override
    public void pause() {

    }

    /**
     * 暂停后重回界面调用
     */
    @Override
    public void resume() {

    }

    /**
     * 应用销毁时调用
     */
    @Override
    public void dispose() {
        spriteBatch.dispose();
        texture.dispose();
    }
}
