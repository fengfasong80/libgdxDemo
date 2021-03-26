package com.ffs.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.ffs.actor.MyActor;
import com.ffs.main.MainGame;
import com.ffs.main.MainGame03;

import java.lang.annotation.Target;

/**
 * 输入监听
 * @author fengfasong
 * @date 2020/9/3
 */
public class MyInputListener extends InputListener {
    private static final String TAG = MainGame03.class.getSimpleName();

    private MyActor actor;

    private Boolean flag;


    public MyInputListener(MyActor actor) {
        this.actor = actor;
        this.flag = true;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.log(TAG,"touchDown");
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.log(TAG,"touchUp");
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        Gdx.app.log(TAG,"touchDragged");
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {

        switch (keycode){
            case Input.Keys.W:
                Gdx.app.log(TAG,"按下了W！");
                actor.clear();
                actor.setRegion(getHcr(2,2));
                break;
            case Input.Keys.A:
                Gdx.app.log(TAG,"按下了A！");
                Animation animation = new Animation(2.0f,getHcrs());
                animation.setPlayMode(Animation.PlayMode.LOOP);
                break;
            case Input.Keys.D:
                Gdx.app.log(TAG,"按下了D！");
                break;
            case Input.Keys.S:
                Gdx.app.log(TAG,"按下了S！");
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        switch (keycode){
            case Input.Keys.W:
                Gdx.app.log(TAG,"抬起了W！");
                actor.setRegion(getHcr(0,0));
                break;
            case Input.Keys.A:
                Gdx.app.log(TAG,"按下了A！");
                break;
            case Input.Keys.D:
                Gdx.app.log(TAG,"按下了D！");
                break;
            case Input.Keys.S:
                Gdx.app.log(TAG,"按下了S！");
                break;
            default:
                break;
        }
        return false;
    }

    public static TextureRegion getHcr(int row,int col){
        Texture texture = new Texture(Gdx.files.internal("hcr.png"));
        int pRow = 4;
        int pCol = 5;

        int spriteRow = texture.getHeight() / pRow;
        int spriteCol = texture.getWidth() / pCol;
        TextureRegion[][] regions = TextureRegion.split(texture,spriteRow,spriteCol);
        return regions[row][col];
    }

    public static TextureRegion[] getHcrs(){
        Texture texture = new Texture(Gdx.files.internal("hcr.png"));
        int pRow = 4;
        int pCol = 5;

        int spriteRow = texture.getHeight() / pRow;
        int spriteCol = texture.getWidth() / pCol;
        TextureRegion[][] regions = TextureRegion.split(texture,spriteRow,spriteCol);
        int i = 0;
        TextureRegion[] textureRegions = new TextureRegion[20];
        for(int row = 0;row<3;row++){
            for(int col = 0;col<5;col++){
                textureRegions[i++] = regions[row][col];
            }
        }
        return textureRegions;
    }


}
