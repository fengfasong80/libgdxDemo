package com.ffs.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


/**
 * 主程序类
 * @author fengfasong
 * @date 2020/9/9
 */
public class MainGame09 extends ApplicationAdapter implements InputProcessor {

    private TiledMap tiledMap;

    private Texture texture;

    private OrthographicCamera camera;

    private TiledMapRenderer tiledMapRenderer;

    private TextureRegion region;

    private SpriteBatch batch;




    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("bg01.png"));
        region = new TextureRegion(texture);
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1088,896);
        camera.update();
        tiledMap = new TmxMapLoader().load("map/hy.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(this);


    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(region,0,0);
        batch.end();
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        /**
         * 相机移动
         */
        camera.translate(-32,0);
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
