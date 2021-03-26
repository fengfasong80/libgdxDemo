package com.ffs.box2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * 主程序类
 * @author fengfasong
 * @date 2020/9/11
 */
public class Box2dGame01 extends ApplicationAdapter {

    private SpriteBatch batch;
    private Sprite sprite;
    private Texture texture;
    private World world;
    private Body body;



    @Override
    public void create() {
        super.create();
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("sprite01.png"));
        sprite = new Sprite(texture);
        sprite.setPosition(Gdx.graphics.getWidth() / 2,Gdx.graphics.getHeight() / 2);

        world = new World(new Vector2(0,-98f),true);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(sprite.getX(),sprite.getY());
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth()/2,sprite.getHeight()/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();
    }

    @Override
    public void render() {
        super.render();
        world.step(Gdx.graphics.getDeltaTime(),6,2);
        sprite.setPosition(body.getPosition().x,body.getPosition().y);
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(sprite,sprite.getX(),sprite.getY());
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
        world.dispose();
    }
}
