package com.ffs.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ffs.actor.MyActor;

public class MainGame02 extends ApplicationAdapter {

    private SpriteBatch batch;

    private Texture texture;

    private MyActor myActor;

    private Stage stage;

    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("sprite01.png"));

        myActor = new MyActor(new TextureRegion(texture));

        myActor.setPosition(50,100);
        myActor.setOrigin(0,0);
        myActor.setScale(1.0F,1.0F);
        myActor.setRotation(0);
        stage = new Stage();
        stage.addActor(myActor);
    }

    @Override
    public void render() {
        super.render();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        myActor.act(Gdx.graphics.getDeltaTime());

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();

        if (batch != null) {
            batch.dispose();
        }
        // 应用退出, 纹理不在需要用到, 释放纹理资源
        if (texture != null) {
            texture.dispose();
        }
    }
}
