package com.ffs.listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ffs.main.SdGame;

/**
 * 按钮监听
 * @author fengfasong
 * @date 2020/9/7
 */
public class BtnListener extends ClickListener {
    private SdGame sdGame;

    public BtnListener(SdGame sdGame){
        this.sdGame = sdGame;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);



    }
}
