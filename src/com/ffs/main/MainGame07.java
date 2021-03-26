package com.ffs.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * 主程序类
 * @author fengfasong
 * @date 2020/9/9
 */
public class MainGame07 extends ApplicationAdapter {

    private BitmapFont bitmapFont;

    private FreeTypeFontGenerator generator;

    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    private SpriteBatch batch;

    private Stage stage;

    private Label.LabelStyle style;

    private Button button;

    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("九遇我梦.ttf"));

        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //设置字体大小
        //parameter.size = 24;
        parameter.characters = "九遇我梦";
        bitmapFont = generator.generateFont(parameter);
        style = new Label.LabelStyle(bitmapFont,bitmapFont.getColor());
        stage = new Stage(new StretchViewport(600,600));
        Label label = new Label("梦我遇九",style);
        label.setPosition(200,200);
        label.setScale(1);
        stage.addActor(label);
        generator.dispose();


    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        /*batch.begin();
        bitmapFont.draw(batch,"九遇我梦",200,200);
        batch.end();*/
    }

    @Override
    public void dispose() {
        super.dispose();
        if(batch != null){
            batch.dispose();
        }
    }
}
