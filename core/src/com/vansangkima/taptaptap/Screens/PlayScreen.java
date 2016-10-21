package com.vansangkima.taptaptap.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vansangkima.taptaptap.Scenes.Hud;
import com.vansangkima.taptaptap.Sprites.Character;
import com.vansangkima.taptaptap.Taptaptap;
import com.vansangkima.taptaptap.Tools.B2WorldCreator;

/**
 * Created by Dyce on 10/17/2016.
 */

public class PlayScreen implements Screen {
    private Taptaptap game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box 2D variables
    private World world;
    private Box2DDebugRenderer b2dr;


 //   private Controller controller;


    private Character player;


    public PlayScreen(Taptaptap game){
        this.game = game;
        //create cam used to follow character through cam world
        gamecam = new OrthographicCamera();

        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(Taptaptap.V_WIDTH / Taptaptap.PPM, Taptaptap.V_HEIGHT / Taptaptap.PPM, gamecam);


        //create our game HUD for scores/timers/level info
        hud = new Hud(game.batch);

        maploader = new TmxMapLoader();
        map = maploader.load("lvl2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Taptaptap.PPM);

        //initially set our gamecam to be centered correctly at the start of map
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -7), true);
        b2dr = new Box2DDebugRenderer();
        player = new Character(world);

     //   controller = new Controller();

        new B2WorldCreator(world, map);

    }
    @Override
    public void show() {
        
    }



    public void handleInput(float dt){

     /*   if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
     */


        if (Gdx.input.justTouched())
        {
            if (Gdx.input.getX() > Gdx.graphics.getWidth() / 2)
            {
                player.b2body.setLinearVelocity((1.5f),( 1.7f));

            }
            else
            {
                //        player.b2body.applyLinearImpulse(new Vector2(-0.7f, 1.9f), player.b2body.getWorldCenter(), true);
                player.b2body.setLinearVelocity((-1.5f),( 1.7f));
            }
        }





    }

    public void update(float dt){

        handleInput(dt);

        world.step(1/60f, 6, 2);

        gamecam.position.x = player.b2body.getPosition().x;
     //   gamecam.position.y = player.b2body.getPosition().y;


        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float v) {
        update(v);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


       //render our game map
        renderer.render();

        //render our Box2DDebugLines
        b2dr.render(world, gamecam.combined);



        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int i, int i1) {

        gamePort.update(i, i1);


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}
