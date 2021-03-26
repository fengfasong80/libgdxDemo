package com.ffs.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ffs.actor.MyActor;
import com.ffs.main.MainGame04;

public class StartScreen implements Screen {

    private MainGame04 mainGame04;

    private Texture startTexture;

    private Stage stage;

    private MyActor startActor;

    private float deltaSum;

    public StartScreen(MainGame04 mainGame04) {
        this.mainGame04 = mainGame04;
        startTexture = new Texture(Gdx.files.internal("background.png"));
        stage = new Stage(new StretchViewport(MainGame04.WORLD_WIDTH,MainGame04.WORLD_HEIGHT));
        startActor = new MyActor(new TextureRegion(startTexture));

        startActor.setPosition(stage.getWidth()/2 - startActor.getWidth()/2,
                                    stage.getHeight()/2 - startActor.getWidth()/2);
        stage.addActor(startActor);
    }

    @Override
    public void show() {
        deltaSum = 0;
    }

    @Override
    public void render(float delta) {
        deltaSum += delta;
        if(deltaSum >= 3.0f){
            if(mainGame04 != null){
                mainGame04.showGameScreen();
            }
        }
        Gdx.gl.glClearColor(0.75F, 1, 0.98F, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        if (stage != null) {
            stage.dispose();
        }
        if (startTexture != null) {
            startTexture.dispose();
        }
    }
}
