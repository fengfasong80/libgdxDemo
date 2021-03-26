package com.ffs.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 演员类
 * @author fengfasong
 * @date 2020/9/3
 */
public class MyActor extends Actor {

    private TextureRegion region;

    public MyActor(TextureRegion region) {
        this.region = region;
        setSize(region.getRegionWidth(),region.getRegionHeight());
    }

    public TextureRegion getRegion(){
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
        setSize(region.getRegionWidth(),region.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(region == null || !isVisible()){
            return;
        }
        batch.draw(region,
                getX(),getY(),
                getOriginX(),getOriginY(),
                getWidth(),getHeight(),
                getScaleX(),getScaleY(),
                getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
