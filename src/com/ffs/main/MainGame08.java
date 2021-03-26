package com.ffs.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ffs.actor.FrameActor;

/**
 * 游戏主程序类
 * @author fengfasong
 * @date 2020/9/9
 */
public class MainGame08 extends ApplicationAdapter {

    private Stage stage;

    private FrameActor actor;

    private OrthographicCamera camera;

    private TiledMap tiledMap;

    private TiledMapRenderer renderer;



    @Override
    public void create() {
        super.create();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1088,896);
        camera.update();

        tiledMap = new TmxMapLoader().load("map/hy.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        stage = new Stage(new StretchViewport(1100,900));
        actor = new FrameActor(100f,100f,stage);
        stage.addActor(actor);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.setView(camera);
        renderer.render();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
