package com.ffs.music.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 主游戏类
 * @author fengfasong
 * @date 2020/9/8
 */
public class MusicGame extends ApplicationAdapter {

    private Texture texture;

    private Texture texture02;

    private SpriteBatch batch;

    private TextureRegion region;

    private Sprite sprite;

    private Sprite sprite02;

    private Pixmap pixmap;


    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();

        batch.setColor(1f,1f,1f,1f);
        texture = new Texture(Gdx.files.internal("bg01.png"));
        pixmap = new Pixmap(Gdx.graphics.getWidth()-200,Gdx.graphics.getHeight()-200,Pixmap.Format.RGB565);
        pixmap.setColor(0.4f,0.3f,0.1f,0.5f);
        //pixmap.fillCircle(300,300,100);
        //pixmap.fill();
        texture02 = new Texture(pixmap);

        region = new TextureRegion(texture);
        TextureRegion[][] split = region.split(64, 64);
        sprite = new Sprite(region);
        sprite02 = new Sprite(texture02);

    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch,0.5f);
        //pixmap.drawCircle(300,300,100);
        sprite02.draw(batch,0.5f);
        //batch.draw(region,0,0);

        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        if(texture != null){
            texture.dispose();
        }
        if(batch != null){
            batch.dispose();
        }
    }
}
