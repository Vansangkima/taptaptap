package com.vansangkima.taptaptap.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vansangkima.taptaptap.Sprites.Coin;
import com.vansangkima.taptaptap.Taptaptap;

/**
 * Created by Dyce on 10/21/2016.
 */

public class B2WorldCreator {
    public B2WorldCreator(World world, TiledMap map){
        BodyDef bdef = new BodyDef();
        PolygonShape shape= new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        fdef.friction = 0.9f;
        fdef.restitution = 0.3f;

        Body body;

        //create ground bodies/fixtures
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Taptaptap.PPM, (rect.getY() + rect.getHeight() / 2)/ Taptaptap.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / Taptaptap.PPM, rect.getHeight() / 2 / Taptaptap.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }


     /*   //create pipe bodies/fixtures
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Taptaptap.PPM, (rect.getY() + rect.getHeight() / 2)/ Taptaptap.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / Taptaptap.PPM, rect.getHeight() / 2 / Taptaptap.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create bricks bodies/fixtures
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Taptaptap.PPM, (rect.getY() + rect.getHeight() / 2)/ Taptaptap.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / Taptaptap.PPM, rect.getHeight() / 2 / Taptaptap.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
*/
       // create coins bodies/fixtures
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coin(world, map, rect);

        }

    }
}
