package com.ffs.box2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * 主程序类
 * @author fengfasong
 * @date 2020/9/11
 */
public class Box2dGame02 extends ApplicationAdapter implements InputProcessor {

    private SpriteBatch batch;

    private Sprite sprite;

    private Texture texture;

    private World world;

    private Body body;

    private Box2DDebugRenderer renderer;

    private Matrix4 matrix4;

    private OrthographicCamera camera;

    float torque = 0.0f;
    boolean drawSprite = true;

    final float PIXELS_TO_METERS = 100f;
    private float elapsed = 0;

    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("sprite01.png"));
        sprite = new Sprite(texture);

        sprite.setPosition(-sprite.getWidth()/2,-sprite.getHeight()/2);

        world = new World(new Vector2(0,0f),true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((sprite.getX() + sprite.getWidth()/2) / PIXELS_TO_METERS,(sprite.getY() + sprite.getHeight()) / PIXELS_TO_METERS);
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth()/2/PIXELS_TO_METERS,sprite.getHeight()/2/PIXELS_TO_METERS);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        body.createFixture(fixtureDef);
        shape.dispose();
        Gdx.input.setInputProcessor(this);

        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

    }

    @Override
    public void render() {
        super.render();
        camera.update();
        world.step(1f/60f,6,2);
        body.applyTorque(torque,true);
        sprite.setPosition((body.getPosition().x*PIXELS_TO_METERS)-sprite.getWidth()/2,
                (body.getPosition().y*PIXELS_TO_METERS - sprite.getHeight()/2));

        sprite.setRotation((float) Math.toDegrees(body.getAngle()));
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        matrix4 = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS,PIXELS_TO_METERS,0);

        batch.begin();
        if(drawSprite) {
            batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(),
                    sprite.getOriginY(),
                    sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.
                            getScaleY(), sprite.getRotation());
        }

        batch.end();
        renderer.render(world, matrix4);

    }

    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
        world.dispose();
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if(keycode == Input.Keys.RIGHT){
            body.setLinearVelocity(1f, 0f);}
        if(keycode == Input.Keys.LEFT){
            body.setLinearVelocity(-1f,0f);}
        if(keycode == Input.Keys.UP){
            body.applyForceToCenter(0f,10f,true);}
        if(keycode == Input.Keys.DOWN){
            body.applyForceToCenter(0f, -10f, true);}

        // On brackets ( [ ] ) apply torque, either clock or counterclockwise
        if(keycode == Input.Keys.RIGHT_BRACKET){
            torque += 0.1f;}
        if(keycode == Input.Keys.LEFT_BRACKET) {
            torque -= 0.1f;
        }
        // Remove the torque using backslash /
        if(keycode == Input.Keys.BACKSLASH){
            torque = 0.0f;}

        // If user hits spacebar, reset everything back to normal
        if(keycode == Input.Keys.SPACE) {
            body.setLinearVelocity(0f, 0f);
            body.setAngularVelocity(0f);
            torque = 0f;
            sprite.setPosition(0f,0f);
            body.setTransform(0f,0f,0f);
        }

        if(keycode == Input.Keys.ESCAPE){
            drawSprite = !drawSprite;}
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int i2, int i3) {
        body.applyForce(1f,1f,screenX,screenY,true);
        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
