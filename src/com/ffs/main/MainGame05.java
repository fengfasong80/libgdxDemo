package com.ffs.main;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ffs.actor.ActionActor;

/**
 * 主程序类
 * @author fengfasong
 * @date 2020/9/3
 */
public class MainGame05 extends ApplicationAdapter {
    private static final String TAG = MainGame05.class.getSimpleName();

    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 800;

    private Texture texture;

    private Stage stage;

    private ActionActor actor;

    @Override
    public void create() {
        super.create();
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        texture = new Texture(Gdx.files.internal("sprite01.png"));
        stage = new Stage(new StretchViewport(MainGame05.WORLD_WIDTH,MainGame05.WORLD_HEIGHT));
        actor = new ActionActor(new TextureRegion(texture));
        stage.addActor(actor);

        testRotateByAction();
    }

    private void testMoveToAction(){
        actor.setPosition(0,0);
        MoveToAction action = Actions.moveTo(200,200,3.0f);
        actor.addAction(action);
    }

    private void testMoveByAction(){
        actor.setPosition(actor.getStage().getWidth()/ 2 - actor.getWidth(),
                              actor.getStage().getHeight()/2 - actor.getHeight()/2 );
        MoveByAction action = Actions.moveBy(50,70,1.0f);
        actor.addAction(action);
    }

    private void testRotateToAction(){
        actor.setPosition(actor.getStage().getWidth()/ 2 - actor.getWidth(),
                actor.getStage().getHeight()/2 - actor.getHeight()/2 );
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);
        actor.setRotation(-90);
        RotateToAction action = Actions.rotateTo(-270,2.0f);
        actor.addAction(action);
    }
    private void testRotateByAction(){
        actor.setPosition(actor.getStage().getWidth()/ 2 - actor.getWidth(),
                actor.getStage().getHeight()/2 - actor.getHeight()/2 );
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);
        actor.setRotation(-90);
        RotateByAction action = Actions.rotateBy(45,1.0f);
        actor.addAction(action);
    }
    private void testScaleToAction(){
        actor.setPosition(actor.getStage().getWidth()/ 2 - actor.getWidth(),
                actor.getStage().getHeight()/2 - actor.getHeight()/2 );
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);
        actor.setScale(0.5f,2.0f);
        ScaleToAction action = Actions.scaleTo(1.0f,1.0f,2.0f);
        actor.addAction(action);
    }

    private void testScaleByAction(){
        actor.setPosition(actor.getStage().getWidth()/ 2 - actor.getWidth(),
                actor.getStage().getHeight()/2 - actor.getHeight()/2 );
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);
        actor.setScale(0.5f,2.0f);
        ScaleByAction action = Actions.scaleBy(0.5f,0.5f,1.0f);
        actor.addAction(action);
    }
    private void testSizeToAction(){
        actor.setPosition(0,0);
        SizeToAction action = Actions.sizeTo(100,100,1.0f);
        actor.addAction(action);
    }
    private void testSizeByAction(){
        actor.setPosition(0,0);
        SizeByAction action = Actions.sizeBy(70,70,1.0f);
        actor.addAction(action);
    }
    private void testAlphaAction(){
        actor.setPosition(0,0);
        actor.getColor().a = 1.0f;
        AlphaAction action = Actions.alpha(0.0f,5.0f);
        actor.addAction(action);
    }
    private void testParallelAction(){
        actor.setPosition(0,0);
        actor.setScale(0.5f,0.5f);
        actor.setRotation(0);
        actor.setOrigin(actor.getWidth()/2,actor.getHeight()/2);
        MoveToAction action = Actions.moveTo(100,100,1.0f);
        ScaleToAction action1 = Actions.scaleTo(1.0f,1.0f,3.0f);
        RotateByAction action2 = Actions.rotateBy(90,1.0f);
        ParallelAction parallelAction = Actions.parallel(action,action1,action2);
        actor.addAction(parallelAction);
    }
    private void testSequenceAction(){
        actor.setPosition(0, 0);
        actor.setScale(1.0F, 1.0F);
        actor.setRotation(0);
        // 缩放和旋转支点设置到演员中心
        actor.setOrigin(actor.getWidth() / 2, actor.getHeight() / 2);

        DelayAction action = Actions.delay(3.0f);
        MoveToAction action1 = Actions.moveTo(100,100,1.0f);
        ParallelAction action2 = Actions.parallel(Actions.scaleTo(0.5f,0.5f,2.0f),
                Actions.rotateBy(360.f,3.0f));
        SequenceAction sequenceAction = Actions.sequence(action,action1,action2);
        actor.addAction(sequenceAction);
    }
    /**
     * 12. 重复动作: 重复 缩小, 放大
     */
    private void testRepeatAction() {
        // 设置演员显示到舞台中心
        actor.setPosition(
                actor.getStage().getWidth() / 2 - actor.getWidth() / 2,
                actor.getStage().getHeight() / 2 - actor.getHeight() / 2
        );

        // 缩放和旋转支点设置到演员中心
        actor.setOrigin(actor.getWidth() / 2, actor.getHeight() / 2);

        // 设置演员初始缩放比
        actor.setScale(1.0F, 1.0F);

        // 顺序动作: 先缩小到 0.5, 再放大到 1.0
        SequenceAction sequence = Actions.sequence(
                Actions.scaleTo(0.5F, 0.5F, 2.0F),
                Actions.scaleTo(1.0F, 1.0F, 2.0F)
        );

        // 重复动作: 重复执行 sequence
        RepeatAction repeatAction = Actions.forever(sequence);

        // 执行重复动作
        actor.addAction(repeatAction);
    }

    /**
     * 13. Runnable 动作: 适当时机执行自己的代码, 与顺序动作一起使用可用于监听某个动作的完成
     */
    private void testRunnableAction() {
        // 设置演员初始化状态
        actor.setPosition(0, 0);

        // 移动动作
        MoveToAction moveTo = Actions.moveTo(150, 300, 3.0F);

        // Runnable 动作
        RunnableAction runnable = Actions.run(new Runnable() {
            @Override
            public void run() {
                // 打印一句 log 表示动作已执行
                Gdx.app.log(TAG, "The runnable action has been running.");
            }
        });

        // 顺序动作: 在 moveTo 动作执行完后执行 runnable 动作
        SequenceAction sequence = Actions.sequence(moveTo, runnable);

        // 执行顺序动作
        actor.addAction(sequence);
    }

    /**
     * 14. After 动作: 可用于监听演员动作的执行完成
     */
    private void testAfterAction() {
        // 设置演员初始化状态
        actor.setPosition(0, 0);
        actor.setRotation(0);

        // 缩放和旋转支点设置到演员中心
        actor.setOrigin(actor.getWidth() / 2, actor.getHeight() / 2);

        // 移动动作, 移动 3 秒
        MoveToAction moveTo = Actions.moveTo(150, 300, 3.0F);

        // 旋转动作, 旋转 2 秒
        RotateByAction rotateBy = Actions.rotateBy(360.0F, 2.0F);

        // Runnable 动作
        RunnableAction runnable = Actions.run(new Runnable() {
            @Override
            public void run() {
                // 打印一句 log 表示动作已执行
                Gdx.app.log(TAG, "演员的其他所有动作都已经执行完了");
            }
        });

        // After 动作, 包含一个 runnable 动作
        AfterAction afterAction = Actions.after(runnable);

        // 同时添加多个动作到演员: 将立即执行 moveTo 和 rotateBy, 都执行完后执行 afterAction
        actor.addAction(moveTo);
        actor.addAction(rotateBy);
        actor.addAction(afterAction);
    }
    @Override
    public void render() {
        // 使用淡蓝色清屏
        Gdx.gl.glClearColor(0.75F, 1, 0.98F, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // 更新舞台逻辑
        stage.act();
        // 绘制舞台
        stage.draw();
    }

    @Override
    public void dispose() {
        // 释放资源
        if (stage != null) {
            stage.dispose();
        }
        if (texture != null) {
            texture.dispose();
        }
    }
}
