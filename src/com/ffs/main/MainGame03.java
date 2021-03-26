package com.ffs.main;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ffs.actor.MyActor;
import com.ffs.listener.MyClickListener;
import com.ffs.listener.MyInputListener;

/**
 * 主程序类
 * @author fengfasong
 * @date 2020/9/3
 */
public class MainGame03 extends ApplicationAdapter {
    private Texture texture;

    private MyActor actor;

    private Stage stage;

    @Override
    public void create() {
        super.create();
        Gdx.app.setLogLevel(Application.LOG_DEBUG);


        //texture = new Texture(Gdx.files.internal("hcr.png"));

        actor = new MyActor(MyInputListener.getHcr(0,0));

        actor.setPosition(20,20);

        stage = new Stage();
        stage.addActor(actor);
        Gdx.input.setInputProcessor(stage);
        stage.addListener(new MyInputListener(actor));
        //actor.addListener(new MyClickListener());

    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        if (texture != null) {
            texture.dispose();
        }
        if (stage != null) {
            stage.dispose();
        }
    }
}
