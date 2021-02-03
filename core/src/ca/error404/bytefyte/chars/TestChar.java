package ca.error404.bytefyte.chars;

import ca.error404.bytefyte.Main;
import ca.error404.bytefyte.constants.Keys;
import ca.error404.bytefyte.scene.TestScene;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class TestChar extends Sprite {
    public enum State {IDLE, WALK, RUN, JUMP, FALL}

    public State currentState;
    public State prevState;

    public Vector2 pos = new Vector2();
    public Vector2 vel = new Vector2();
    public Vector2 prevPos = new Vector2();
    public Vector2 prevVel = new Vector2();

    public World world;
    public Body b2body;

    public int walkSpeed = 2;

    public TestChar(TestScene screen) {
        this.world = screen.getWorld();
        currentState = State.IDLE;
        prevState = State.IDLE;
        
        defineChar();
        b2body.setLinearDamping(5);
    }
    
    public void defineChar() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(0, 0);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(10 / Main.PPM,10 / Main.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-10 / Main.PPM, -12 / Main.PPM), new Vector2(10 / Main.PPM, -12 / Main.PPM));
        fdef.isSensor = true;

        fdef.shape = feet;
        b2body.createFixture(fdef).setUserData("feet");
    }

    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Keys.MOVE_RIGHT) && b2body.getLinearVelocity().x <= walkSpeed) {
            b2body.applyLinearImpulse(new Vector2(0.1f, 0), b2body.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyPressed(Keys.MOVE_LEFT) && b2body.getLinearVelocity().x >= -walkSpeed) {
            b2body.applyLinearImpulse(new Vector2(-0.1f, 0), b2body.getWorldCenter(), true);
        }

        if ((Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))) {
            b2body.applyLinearImpulse(new Vector2(0, 5f), b2body.getWorldCenter(), true);
        }
    }
}
