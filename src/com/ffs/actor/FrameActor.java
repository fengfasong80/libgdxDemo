package com.ffs.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * 帧动画演员
 * @author fengfasong
 * @date 2020/9/9
 */
public class FrameActor extends Actor {

    private Float x;

    private Float y;

    private Float stateTime;

    private Texture texture;

    private TextureRegion currentFrame;

    private Animation aniRight;

    private Animation aniLeft;

    private Animation aniUp;

    private Animation aniDown;

    private Animation aniIdle;

    private STATE state;

    private Stage stage;

    enum STATE {
        LEFT,RIGHT,UP,DOWN,IDEL
    };


    public FrameActor(Float x, Float y,Stage stage) {
        this.x = x;
        this.y = y;
        this.stage = stage;
        show();
        state = STATE.IDEL;
        stateTime = 0f;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        stateTime += Gdx.graphics.getDeltaTime();
        update();
        check();
        batch.draw(currentFrame,x,y);
    }

    private void update(){
        if(state == STATE.LEFT){
            x -= 1.5f;
            if(x <= 20){
                x = 20f;
            }
        }
        if(state == STATE.RIGHT){
            x += 1.5f;
            if(x >= 800){
                x = 800f;
            }
        }
        if(state == STATE.DOWN){
            y -= 1.5f;
            if(y<=10){
                y = 10f;
            }
        }
        if(state == STATE.UP){
            y += 1.5f;
            if(y>=800){
                y = 800f;
            }
        }
    }

    private void check(){
        if(state == STATE.LEFT){
            currentFrame = aniLeft.getKeyFrame(stateTime,true);
        }
        if(state == STATE.RIGHT){
            currentFrame = aniRight.getKeyFrame(stateTime,true);
        }
        if(state == STATE.UP){
            currentFrame = aniUp.getKeyFrame(stateTime,true);
        }
        if(state == STATE.DOWN){
            currentFrame = aniDown.getKeyFrame(stateTime,true);
        }
        if(state == STATE.IDEL){
            currentFrame = aniIdle.getKeyFrame(stateTime,true);
        }
    }

    private void show(){
        System.out.println("=+++++++++++");
        texture = new Texture(Gdx.files.internal("mr.png"));
        TextureRegion[][] regions = TextureRegion.split(texture,texture.getWidth()/4,texture.getHeight()/4);
        TextureRegion[] regionL = new TextureRegion[4];
        regionL[0] = regions[1][0];
        regionL[1] = regions[1][1];
        regionL[2] = regions[1][2];
        regionL[3] = regions[1][3];
        aniLeft = new Animation(0.1f,regionL);

        TextureRegion[] regionsR = new TextureRegion[4];
        regionsR[0] = regions[2][0];
        regionsR[1] = regions[2][1];
        regionsR[2] = regions[2][2];
        regionsR[3] = regions[2][3];
        aniRight = new Animation(0.1f,regionsR);

        TextureRegion[] regionsU = new TextureRegion[4];
        regionsU[0] = regions[3][0];
        regionsU[1] = regions[3][1];
        regionsU[2] = regions[3][2];
        regionsU[3] = regions[3][3];
        aniUp = new Animation(0.1f,regionsU);

        TextureRegion[] regionsD = new TextureRegion[4];
        regionsD[0] = regions[0][0];
        regionsD[1] = regions[0][1];
        regionsD[2] = regions[0][2];
        regionsD[3] = regions[0][3];
        aniDown = new Animation(0.1f,regionsD);

        TextureRegion[] regionsI = new TextureRegion[1];
        regionsI[0] = regions[0][0];
        aniIdle = new Animation(0.1f,regionsI);

        stage.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {

                switch (keycode){
                    case Input.Keys.D:
                        state = STATE.RIGHT;
                        System.out.println("right");
                        break;
                    case Input.Keys.A:
                        state = STATE.LEFT;
                        System.out.println("left");
                        break;
                    case Input.Keys.S:
                        state = STATE.DOWN;
                        break;
                    case Input.Keys.W:
                        state = STATE.UP;
                        break;
                    default:
                        state = STATE.IDEL;
                        System.out.println("middle");
                        break;
                }
                return false;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                switch (keycode){
                    case Input.Keys.D:
                        state = STATE.IDEL;
                        System.out.println("right");
                        break;
                    case Input.Keys.A:
                        state = STATE.IDEL;
                        System.out.println("left");
                        break;
                    case Input.Keys.S:
                        state = STATE.IDEL;
                        System.out.println("down");
                        break;
                    case Input.Keys.W:
                        state = STATE.IDEL;
                        System.out.println("up");
                        break;
                    default:
                        state = STATE.IDEL;
                        System.out.println("middle");
                        break;
                }
                return false;
            }
        });

    }
}
