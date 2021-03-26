package com.ffs.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 动作演员
 * @author fengfasong
 * @date 2020/9/3
 */
public class ActionActor extends Actor {
    private TextureRegion region;

    public ActionActor(TextureRegion region) {
        this.region = region;
        setSize(region.getRegionWidth(),region.getRegionHeight());
    }

    public TextureRegion getRegion() {
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

        Color tempBatchColor = batch.getColor();

        Color color = getColor();

        batch.setColor(color.r,color.g,color.b,color.a * parentAlpha);

        batch.draw(
                region,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation()
        );
        batch.setColor(tempBatchColor);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
