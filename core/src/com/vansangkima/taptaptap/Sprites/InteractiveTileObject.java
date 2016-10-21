package com.vansangkima.taptaptap.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vansangkima.taptaptap.Taptaptap;

/**
 * Created by Dyce on 10/21/2016.
 */

public abstract class InteractiveTileObject {

    protected World world;
    protected TiledMap map;
   // protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef= new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / Taptaptap.PPM, (bounds.getY() + bounds.getHeight() / 2)/ Taptaptap.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / Taptaptap.PPM, bounds.getHeight() / 2 / Taptaptap.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
    }
}
