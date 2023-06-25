package Screens;

import Sprites.tank;
import Sprites.missile;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
//import com.badlogic.gdx.Screen.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.Gdx;

import java.util.Iterator;
//import



public class play_screen implements Screen {
    final MyGdxGame game;
//    private final Body body;
    Texture texture;
    Skin touchpadSkin;
    missile m1;

    Touchpad.TouchpadStyle touchpadStyle;
    Touchpad touchpad;
    ProgressBar healthBar;
    ProgressBar healthBar2;
    ProgressBar healthbar_fuel;
    ProgressBar healthbar_fuel2;
    private Body body;
    private Texture back_button;
    private OrthographicCamera cam;
    private Viewport port;
    private Pixmap pixmap;
    private TextureRegionDrawable drawable;
    Stage stage;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    Vector2 postion_velocity_pad=new Vector2();
    Vector2 position_pad=new Vector2();
    float angl;

    private ImageButton b_button;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private tank player;
    private Array<Body> tempbodies;
    private tank player2;
    private Skin skin;
    private float t;
    private float t1;

    private static PolygonShape getPolygon(PolygonMapObject polygonObject) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];
        System.out.println(vertices.length);

        for (int i = 0; i < vertices.length; i++) {
            System.out.println(vertices[i]);
            worldVertices[i] = vertices[i] ;
        }

        polygon.set(worldVertices);


        return polygon;
    }
    public void initiate_touchpad(){
        touchpadSkin = new Skin();
        touchpadSkin.add("touchBackground", new Texture("big_circle.png"));
        touchpadSkin.add("touchKnob", new Texture("smallcircle.png"));

        touchpadStyle = new Touchpad.TouchpadStyle();
        Drawable D_background = touchpadSkin.getDrawable("touchBackground");
        Drawable D_knob = touchpadSkin.getDrawable("touchKnob");

        D_background.setMinHeight(40);
        D_background.setMinWidth(40);

        D_knob.setMinHeight(100);
        D_knob.setMinWidth(102);

        touchpadStyle.background = D_background;
        touchpadStyle.knob = D_knob;

        touchpad = new Touchpad(5, touchpadStyle);
        touchpad.setBounds(1600, 100, 200, 200);

        touchpad.addListener(new ChangeListener(){

            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });
    }

    public ProgressBar getHealthBar(float x, float y, float width, float height) {
        Pixmap pixmap;
        pixmap = new Pixmap(0, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        TextureRegionDrawable drawable1 = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = drawable1;

//        ProgressBar.ProgressBarStyle progressBarStyle;
        progressBarStyle.knob = drawable1;

        pixmap = new Pixmap(400, 50, Pixmap.Format.RGBA8888);
//        pixmap = new Pixmap()
        pixmap.setColor(Color.YELLOW);
        pixmap.fill();
        drawable1 = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle.knobBefore = drawable1;
//        stage = new Stage();

        ProgressBar healthBar1 = new ProgressBar(0.0f, 1.0f, 0.01f, false, progressBarStyle);
        healthBar1.setValue(1.0f);
        healthBar1.setAnimateDuration(0.25f);
        healthBar1.setBounds(x, y, width, height);

//        stage.addActor(healthBar);
        return healthBar1;
    }

    public play_screen(final MyGdxGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        cam = new OrthographicCamera();
//        stage = new Stage();
        port = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, cam);
        stage = new Stage(port);
//    stage = new Stage();


        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Final.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        cam.position.set(port.getWorldWidth()/2, port.getWorldHeight()/2, 0);
        tempbodies = new Array<Body>();
        GameConstants GameConstants=new GameConstants() ;
        back_button = new Texture(Gdx.files.internal("pngtree.png"));
        TextureRegion back_button_r = new TextureRegion(back_button);
        TextureRegionDrawable back_button_r_dr = new TextureRegionDrawable(back_button_r);
        b_button = new ImageButton(back_button_r_dr);
        b_button.setSize(200, 100);
        b_button.setPosition(0, 950);
        b_button.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotopause_menu();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });


        Pixmap pixmap;
        pixmap = new Pixmap(0, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = drawable;

//        ProgressBar.ProgressBarStyle progressBarStyle;
        progressBarStyle.knob = drawable;

        pixmap = new Pixmap(600, 50, Pixmap.Format.RGBA8888);
//        pixmap = new Pixmap()
        pixmap.setColor(Color.BLUE);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle.knobBefore = drawable;
//        stage = new Stage();

        healthBar = new ProgressBar(0.0f, 1.0f, 0.01f, false, progressBarStyle);
        healthBar.setValue(1.0f);
        healthBar.setAnimateDuration(0.25f);
        healthBar.setBounds(200, 1000, 600, 50);

//        healthBar2 = healthBar;
        healthBar2 = new ProgressBar(0.0f, 1.0f, 0.01f, false, progressBarStyle);
        healthBar2.setValue(1.0f);
        healthBar2.setAnimateDuration(0.25f);
        healthBar2.setBounds(1200,1000, 600, 50);
        stage.addActor(b_button);

        stage.addActor(healthBar);
        stage.addActor(healthBar2);
        stage.addActor(healthbar_fuel = getHealthBar(100, 30, 400, 50));
        stage.addActor(healthbar_fuel2 = getHealthBar(1400, 30, 400, 50));
        world = new World(new Vector2(0,-200), true);
        b2dr = new Box2DDebugRenderer();
        player = new tank(this.world, 300, 500, 0, game.flag1);
        player2 = new tank(this.world, 900, 500, 1, game.flag2);
        m1 = new missile(this.world);

//        player2.thrown_flag=1;
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
//        PolygonShape shape;

        FixtureDef fdef = new FixtureDef();

//        for(MapObject object: map.getLayers().get(2).getObjects().getByType(PolygonMapObject.class)){
//            Polygon pl = ((PolygonMapObject) object).getPolygon();
//
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set(pl.getBoundingRectangle().getWidth()/2, pl.getBoundingRectangle().getHeight()/2);
////            bdef.position.set(500.0f,500.0f);
////            System.out.println(pl.getX()+"\n"+pl.getY());
//            System.out.println("yes");
//
//            body = world.createBody(bdef);
////            shape.setAsBox(pl.getBoundingRectangle().getWidth()/2, pl.getBoundingRectangle().getHeight()/2);
////            shape.setAsBox(pl.getBoundingRectangle().getWidth()/2, pl.getBoundingRectangle().getHeight()/2);
////            shape.set(pl.getTransformedVertices());
//            shape = getPolygon(((PolygonMapObject) object));
//        Button men_Button = new TextButton("MENU",skin,"small");
//        men_Button.setSize(GameConstants.col_width ,GameConstants.row_height);
//        men_Button.setPosition(GameConstants.centerX - men_Button.getWidth()/2 -200  ,GameConstants.centerY+100);

        

        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle pl = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(pl.getX()+ pl.getWidth()/2, pl.getY() +pl.getHeight()/2);



            body = world.createBody(bdef);
            shape.setAsBox(pl.getWidth()/2, pl.getHeight()/2);

            fdef.shape=shape;
            fdef.friction = 0.4f;

            body.createFixture(fdef);



        }

        Button start_Button = new TextButton("FIRE", skin, "small");
//        start_Button.

        start_Button.setPosition(GameConstants.centerX - start_Button.getWidth()/2-100,GameConstants.centerY-200);
        start_Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                impulse.x=20;
////                impulse.y=100;
                float speed, angle;
                float y1=position_pad.y;
                if(y1<0){
                    y1=-1*y1;
                }
                if(angl>100 && angl<180){
                    angl=180-angl;
                    angl=-angl;


                }
                else if(angl>180){
                    angl=360-angl;
                    angl=-angl;


                }

//                for(int i=0;i<5;i++) {
//                world.setGravity(new Vector2(0, -15));
//                Vector2 startingVelocity = new Vector2((-1)*1000000*position_pad.x , 0);
////                world.setGravity(new Vector2(0, -200));
//                startingVelocity.rotateDeg((float)angl );
//                    System.out.println("x" + position_pad.x);
//                    System.out.println("y" + position_pad.y);

//                body.setLinearVelocity(startingVelocity);
//                m1.b2body.setLinearVelocity(startingVelocity);
//                m1.b2body.setGravityScale(-150);
//                m1.b2body.setLinearVelocity(startingVelocity);
//                m1.b2body.setLinearVelocity(startingVelocity);
//                m1.b2body.setLinearVelocity(startingVelocity);
//                impulse.x=20;
//                impulse.y=100;
//                m1.b2body.setGravityScale(-2);
//                MassData massData = new MassData();
//                massData.mass = 0.0001f;
//                m1.b2body.setMassData(massData);
                m1.b2body.applyForceToCenter(new Vector2(1000, 100000000), true);
                m1.b2body.applyLinearImpulse(new Vector2(1000000000000f, 1000000000000f), new Vector2(500, 10000), true);
//                    System.out.println("kgmjb");
//                }
            }
        });
        stage.addActor(start_Button);
        Gdx.input.setInputProcessor(stage);




    }

    @Override
    public void show() {
        initiate_touchpad();
        stage.addActor(touchpad);
        Gdx.input.setInputProcessor(stage);

    }
    int f=0;

    public void handle(float dt){
//        if(Gdx.input.isTouched()){
////            cam.position.x += 100*dt;
//        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x<=1000 && healthbar_fuel.getValue()>0f && player.thrown_flag==0){
            player.b2body.applyLinearImpulse(new Vector2(100f,-2f), player.b2body.getWorldCenter(), true);
            healthbar_fuel.setValue(healthbar_fuel.getValue()-0.05f);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x>=-1000 && healthbar_fuel.getValue()>0f &&player.thrown_flag==0){
            player.b2body.applyLinearImpulse(new Vector2(-100f,-2f), player.b2body.getWorldCenter(), true);
            healthbar_fuel.setValue(healthbar_fuel.getValue()-0.05f);

        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)&& player.thrown_flag==0){
            System.out.println("thrown");
            player.thrown_flag=1;
            player2.thrown_flag=0;
            healthbar_fuel2.setValue(1.0f);





//            System.out.println(player2.thrown_flag);
//            System.out.println(player.thrown_flag);

        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.UP)&& player2.thrown_flag==0 ){
            player2.thrown_flag=1;
            player.thrown_flag=0;
            healthbar_fuel.setValue(1.0f);

        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && player2.b2body.getLinearVelocity().x<=1000 && healthbar_fuel2.getValue()>0f && player2.thrown_flag==0){
            player2.b2body.applyLinearImpulse(new Vector2(100f,-2f), player2.b2body.getWorldCenter(), true);
            healthbar_fuel2.setValue(healthbar_fuel2.getValue()-0.05f);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && player2.b2body.getLinearVelocity().x>=-1000 && healthbar_fuel2.getValue()>0f && player2.thrown_flag==0){
            player2.b2body.applyLinearImpulse(new Vector2(-100f,-2f), player2.b2body.getWorldCenter(), true);
            healthbar_fuel2.setValue(healthbar_fuel2.getValue()-0.05f);

        }



    }
    public void update(float dt){
        handle(dt);
        world.step(1/60f, 6, 2);
//        cam.position.x=player.b2body.getPosition().x;
        cam.update();
        renderer.setView(cam);

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        b2dr.render(world, cam.combined);

        game.batch.setProjectionMatrix(cam.combined);
        t +=delta;
        t1=t1+delta;

//            if(postion_velocity_pad.angleDeg()!=0) {
        postion_velocity_pad.x = 50 * touchpad.getKnobPercentX() * 5;
        postion_velocity_pad.y = 50 * touchpad.getKnobPercentY() * 5;
//            System.out.println(postion_velocity_pad.x);

        float ang = postion_velocity_pad.angleDeg();
//        System.out.println("angle "+postion_velocity_pad.angleDeg());
//                System.out.println("angle " + postion_velocity_pad.x);
        ang = 60;
//            }
        if(postion_velocity_pad.angleDeg()!=0) {
            position_pad.x=postion_velocity_pad.x;
            position_pad.y=postion_velocity_pad.y;
            angl=postion_velocity_pad.angleDeg();
            System.out.println(angl);

        }
        game.batch.begin();
//        game.batch.draw(texture,0,0);
        game.batch.draw(new Texture("vs2.png"), 950, 950, 100, 100);
        world.getBodies(tempbodies);
        Iterator<Body> iter = tempbodies.iterator();
        

//        for(Body b: tempbodies) {
//            if (b.getUserData() != null && b.getUserData() instanceof Sprite) {
//                Sprite sprite = (Sprite) b.getUserData();
//                sprite.setPosition(b.getPosition().x - sprite.getWidth() / 2, b.getPosition().y - sprite.getHeight() / 2);
//                sprite.setRotation(b.getAngle() * MathUtils.radiansToDegrees);
//                sprite.draw(game.batch);
//            }
        Body b = null;
        while(iter.hasNext()) {
            b = (Body) iter.next();

            if (b.getUserData() != null && b.getUserData() instanceof Sprite) {
                Sprite sprite = (Sprite) b.getUserData();
                sprite.setPosition(b.getPosition().x - sprite.getWidth() / 2, b.getPosition().y - sprite.getHeight() / 2);
                sprite.setRotation(b.getAngle() * MathUtils.radiansToDegrees);
                sprite.draw(game.batch);

            }
        }
        game.batch.end();
//        stage.act();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));

        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);

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
        stage.dispose();




    }
}
