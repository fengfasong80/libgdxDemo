package com.ffs.music.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * 演员类，即运动区域
 * @author fengfasong
 * @date 2020/9/8
 */
public class MusicActor extends Actor {

    private TextureRegion region;

    public MusicActor(TextureRegion region) {
        this.region = region;
    }
}
