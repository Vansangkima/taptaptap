package com.vansangkima.taptaptap.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vansangkima.taptaptap.Taptaptap;

/**
 * Created by Dyce on 10/18/2016.
 */

public class Character extends Sprite {
    public World world;
    public Body b2body;

    public Character(World world){
        this.world = world;
        defineCharacter();
    }

    public void defineCharacter(){

        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Taptaptap.PPM, 32 / Taptaptap.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Taptaptap.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);


    }
}
