package com.ffs.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector3;
import com.ffs.render.OrthogonalTiledMapRendererWithSprites;

/**
 * 主程序类
 * @author fengfasong
 * @date 2020/9/10
 */
public class MainGame10 extends ApplicationAdapter implements InputProcessor {

    private TiledMap tiledMap;

    private Texture texture;

    private OrthographicCamera camera;

    private Sprite sprite;

    private OrthogonalTiledMapRendererWithSprites renderer;


    @Override
    public void create() {
        super.create();
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,width,height);
        camera.update();

        texture = new Texture(Gdx.files.internal("sprite01.png"));
        sprite = new Sprite(texture);

        tiledMap =  new TmxMapLoader().load("map/hy.tmx");
        renderer = new OrthogonalTiledMapRendererWithSprites(tiledMap);
        renderer.addSprite(sprite);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setView(camera);
        renderer.render();
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
    public boolean keyUp(int keyCode) {
        switch (keyCode){
            case Input.Keys.W:
                camera.translate(0,32);
                break;
            case Input.Keys.S:
                camera.translate(0,-32);
                break;
            case Input.Keys.A:
                camera.translate(-32,0);
                break;
            case Input.Keys.D:
                camera.translate(32,0);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
        Vector3 position = camera.unproject(clickCoordinates);
        sprite.setPosition(position.x-64,position.y-64);
        return true;
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
