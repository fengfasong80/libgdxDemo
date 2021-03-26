package com.ffs.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ffs.listener.MyClickListener;
/**
 * 数独
 * @author fengfasong
 * @date 2020/9/7
 */
public class SdGame extends ApplicationAdapter {
    /**
     * 文本框宽高
     */
    public static final Integer TEXT_WIDTH = 100;
    public static final Integer TEXT_HEIGHT = 100;

    /**
     * 舞台
     */
    private Stage stage;
    /**
     * 文本框背景
     */
    private Texture bgTexture;
    /**
     * 文本框光标
     */
    private Texture cursorTexture;
    /**
     * 字体
     */
    private BitmapFont bitmapFont;
    /**
     * 文本框
     */
    private TextField[][] textField;

    private Pixmap pixmapLine;

    private Texture texture;

    private SpriteBatch spriteBatch;


    private char[][] ysd = {{'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}};

    @Override
    public void create() {
        super.create();
        spriteBatch = new SpriteBatch();

        stage = new Stage(new StretchViewport(1100,900));
        Gdx.input.setInputProcessor(stage);
        bgTexture = createBackgroundTexture();
        cursorTexture = createCursorTexture();
        bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(2.0f);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.background = new TextureRegionDrawable(new TextureRegion(bgTexture));
        style.cursor = new TextureRegionDrawable(new TextureRegion(cursorTexture));
        style.font = bitmapFont;
        style.fontColor = new Color(0,0,0,1);
        textField = new TextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField numTextfield = new TextField("",style);
                numTextfield.setSize(TEXT_WIDTH,TEXT_HEIGHT);
                numTextfield.setPosition(i*100,800-j*100);
                numTextfield.setAlignment(Align.center);
                if('.' != (ysd[i][j])){
                    numTextfield.setText(String.valueOf(ysd[i][j]));
                }
                if('.' == ysd[i][j]){
                    numTextfield.setText("");
                }
                textField[i][j] = numTextfield;
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textField[i][j].addListener(new MyClickListener(i,j,textField));
                stage.addActor(textField[i][j]);
            }
        }

        TextField butTextField = new TextField("",style);
        butTextField.setSize(TEXT_WIDTH,TEXT_HEIGHT);
        butTextField.setPosition(900,500);
        butTextField.setAlignment(Align.center);
        butTextField.setText("answer");
        butTextField.addListener(new MyClickListener(9,9,textField));
        stage.addActor(butTextField);
        stage.addActor(butTextField);

        pixmapLine = new Pixmap(900,900,Pixmap.Format.RGBA8888);
        pixmapLine.setColor(Color.BLACK);
        pixmapLine.drawLine(300,0,300,900);
        pixmapLine.drawLine(600,0,600,900);
        pixmapLine.drawLine(0,300,900,300);
        pixmapLine.drawLine(0,600,900,600);
        texture = new Texture(pixmapLine);


    }

    @Override
    public void render() {
        super.render();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // 更新舞台逻辑
        stage.act();
        // 绘制舞台
        stage.draw();
        spriteBatch.begin();
        spriteBatch.draw(texture,0,0);
        spriteBatch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
        if (bgTexture != null) {
            bgTexture.dispose();
        }
        if (cursorTexture != null) {
            cursorTexture.dispose();
        }
        if (bitmapFont != null) {
            bitmapFont.dispose();
        }
        if (stage != null) {
            stage.dispose();
        }
        if(pixmapLine != null){
            pixmapLine.dispose();
        }
    }

    /**
     * 创建文本框的背景纹理
     */
    private Texture createBackgroundTexture() {
        Pixmap pixmap = new Pixmap(TEXT_WIDTH, TEXT_HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0.8f, 0.8f, 1);
        pixmap.drawRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    /**0,206,209
     * 创建文本框中的光标纹理
     */
    private Texture createCursorTexture() {
        Pixmap pixmap = new Pixmap(1, TEXT_HEIGHT - 4, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.7f, 0.7f, 0.7f, 1);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
}
