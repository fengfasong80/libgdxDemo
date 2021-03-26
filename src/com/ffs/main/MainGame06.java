package com.ffs.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * 主程序类
 * @author fengfasong
 * @date 2020/94
 */
public class MainGame06 extends ApplicationAdapter {

    private SpriteBatch batch;

    private Texture texture;

    private Animation animation;

    private TextureRegion region;

   // private Texture texture2;

    private float stateTime;



    @Override
    public void create() {
        super.create();

        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal("hcr.png"));
       // texture2 = new Texture(Gdx.files.internal("background.png"));

        int row = 4;
        int col = 5;

        int spriteRow = texture.getHeight() / row;
        int spriteCol = texture.getWidth() / col;

        TextureRegion[][] textureRegions = TextureRegion.split(texture,spriteCol,spriteRow);
        TextureRegion[] regions = new TextureRegion[15];
        int temp = 0;
        for(int i = 0;i<3;i++){
            for(int j = 0;j<5;j++) {
                regions[temp++] = textureRegions[i][j];
            }
        }
        animation = new Animation(0.1f,regions);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();
        region = animation.getKeyFrame(stateTime);
        batch.begin();
        //batch.draw(texture2,0,0);
        batch.draw(region,100,200);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        if (texture != null) {
            texture.dispose();
        }
        if (batch != null) {
            batch.dispose();
        }
    }
}
