package ca.error404.bytefyte.chars;

import ca.error404.bytefyte.constants.Tags;
import ca.error404.bytefyte.scene.TMap;
import ca.error404.bytefyte.scene.TestScene;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;

public class DeathWall extends Wall {

    public DeathWall(int x, int y, float w, float h, TMap screen) {
        super(x, y, w, h, screen);
        Filter filter = new Filter();
        filter.categoryBits = Tags.DEATH_BARRIER_BIT;
        fix.setFilterData(filter);
    }

    public void contact(Character chara) {
        chara.die();
        chara.vel = new Vector2(0, 0);
        chara.prevVel = new Vector2(0, 0);
    }
}
