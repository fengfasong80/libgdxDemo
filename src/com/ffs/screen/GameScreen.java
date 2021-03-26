package com.ffs.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ffs.actor.MyActor;
import com.ffs.main.MainGame04;

/**
 * 主游戏场景
 * @author fengfasong
 * @date 2020/9/3
 */
public class GameScreen extends ScreenAdapter {

    private Texture texture;

    private Stage stage;

    private MyActor actor;

    public GameScreen() {
        texture = new Texture(Gdx.files.internal("sprite01.png"));
        stage = new Stage(new StretchViewport(MainGame04.WORLD_WIDTH,MainGame04.WORLD_HEIGHT));

        actor = new MyActor(new TextureRegion(texture));
        stage.addActor(actor);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        if (stage != null) {
            stage.dispose();
        }
        if (texture != null) {
            texture.dispose();
        }
    }
}
