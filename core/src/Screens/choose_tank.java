package Screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
//import com.mygdx.template.GameConstants;
//import com.mygdx.template.Main;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameConstants;
import com.mygdx.game.MyGdxGame;


//class GameConstants {
//    public static final String skin = "skin/glassy-ui.json";
//
//    public static final int screenWidth = Gdx.graphics.getWidth();
//    public static final int screenHeight = Gdx.graphics.getHeight();
//    public static final int centerX = screenWidth/2;
//    public static final int centerY = screenHeight/2;
//    public static final int col_width = screenWidth/7;
//    public static final int row_height = screenHeight/7;
//}

public class choose_tank implements Screen {

    final MyGdxGame game;
    private SpriteBatch batch;
    private Skin skin1;

    private Skin skin;
    private Stage stage;
    private Texture img;
    private Sprite sprite;
    private Animation<TextureRegion> animation;
    private Animation<TextureRegion> animation1;
    private Animation<TextureRegion> animation2;
    private float stateTime =0f;
    private float stateTime1 =0f;
    private float stateTime2 =0f;
    private float stateTime3 =0f;


    public Animation<TextureRegion> crnt_frame(String path, int col, int row){

        Texture walksheet = new Texture(Gdx.files.internal(path));
        TextureRegion[][] tmp = TextureRegion.split(walksheet, walksheet.getWidth()/col, walksheet.getHeight()/row);
        TextureRegion[] walkFrames = new TextureRegion[col * row];
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        // Initialize the Animation with the frame interval and array of frames
        Animation<TextureRegion> crnt_animation = new Animation<TextureRegion>(0.25f, walkFrames);
//		TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        stateTime =0f;

        return crnt_animation;



    }


    public choose_tank(final MyGdxGame game) {
        this.game = game;
        batch = new SpriteBatch();
//        batch = game.batch;
        img = new Texture("2nd_image.jpg");
        sprite = new Sprite(img);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        skin= new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        skin1 = new Skin(Gdx.files.internal("skin1/uiskin.json"));
        stage = new Stage();
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

//        GameConstants GameConstants= com.mygdx.game.MyGdxGame. ;

        Label gamemenu = new Label("Choose Tank",skin1,"title");
        gamemenu.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        gamemenu.setPosition(GameConstants.centerX - gamemenu.getWidth()/2,GameConstants.centerY + GameConstants.row_height+82);
        gamemenu.setAlignment(Align.center);

        Label gamemenu1 = new Label("Player 1",skin1,"title");
        gamemenu1.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        gamemenu1.setPosition(GameConstants.centerX - gamemenu1.getWidth()/2 -200,GameConstants.centerY + GameConstants.row_height+33);
        gamemenu1.setAlignment(Align.center);

        Label gamemenu2 = new Label("Player 2",skin1, "title");
        gamemenu1.setSize(GameConstants.col_width*2,GameConstants.row_height*2);

        gamemenu2.setPosition(GameConstants.centerX - gamemenu2.getWidth()/2 -200,GameConstants.centerY + GameConstants.row_height-150+33);
        gamemenu2.setAlignment(Align.center);

//		group = new Group();
//		group.addActor(gamemenu2);
//		group.setScale(2f, 2f);

        Button frost = new TextButton("FROST",skin,"small");

        frost.setSize(GameConstants.col_width*1.2f,GameConstants.row_height/1.6f);
        frost.setPosition(60, 235+33);
        frost.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.flag1 =1;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        Button abrams = new TextButton("ABRAMS",skin,"small");

        abrams.setSize(GameConstants.col_width*1.2f,GameConstants.row_height/1.6f);
        abrams.setPosition(265, 235+33);
        abrams.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.flag1 =2;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });
        Button buratino = new TextButton("BURATINO",skin,"small");

        buratino.setSize(GameConstants.col_width*1.2f,GameConstants.row_height/1.6f);
        buratino.setPosition(465, 235+33);
        buratino.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.flag1 =3;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });


        Button frost1 = new TextButton("FROST",skin,"small");

        frost1.setSize(GameConstants.col_width*1.2f,GameConstants.row_height/1.6f);
        frost1.setPosition(60, 45+33);
        frost1.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.flag2 =4;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        Button abrams1 = new TextButton("ABRAMS",skin,"small");

        abrams1.setSize(GameConstants.col_width*1.2f,GameConstants.row_height/1.6f);
        abrams1.setPosition(265, 45+33);
        abrams1.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.flag2 =5;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        Button buratino1 = new TextButton("BURATINO",skin,"small");

        buratino1.setSize(GameConstants.col_width*1.2f,GameConstants.row_height/1.6f);
        buratino1.setPosition(465, 45+33);
        buratino1.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.flag2 =6;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        Button playbtn = new TextButton("LETS FIGHT!!!!",skin, "small");

        playbtn.setSize(GameConstants.col_width *3 ,GameConstants.row_height);
        playbtn.setPosition(185, 5);
        playbtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotoplay_screen();
//                game.gotochoose_tank();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        animation = crnt_frame("output-onlinegiftools.png", 7, 10);
        animation1 = crnt_frame("tank_2.png", 7, 6);
        animation2 = crnt_frame("tank_3.png", 7, 6);





        stage.addActor(gamemenu);
        stage.addActor(gamemenu1);
        stage.addActor(gamemenu2);
        stage.addActor(abrams);
        stage.addActor(frost);
        stage.addActor(buratino);

        stage.addActor(abrams1);
        stage.addActor(frost1);
        stage.addActor(buratino1);
        stage.addActor(playbtn);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        skin.dispose();
        stage.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        TextureRegion currentFrame = animation.getKeyFrame(stateTime1, true);
        stateTime1 += (Gdx.graphics.getDeltaTime())+0.125;

        batch.begin();
        sprite.draw(batch);
        batch.end();


        batch.begin();
        batch.draw(currentFrame, 15, 270+33, currentFrame.getRegionWidth()/4, currentFrame.getRegionHeight()/4);
        batch.draw(currentFrame, 15, 80+33, currentFrame.getRegionWidth()/4, currentFrame.getRegionHeight()/4);


        TextureRegion currentFrame1= animation1.getKeyFrame(stateTime2, true);
        stateTime2 += (Gdx.graphics.getDeltaTime())+1;
        batch.draw(currentFrame1, 200, 270+33, currentFrame1.getRegionWidth()/4, currentFrame1.getRegionHeight()/4);
        batch.draw(currentFrame1, 200, 80+33, currentFrame1.getRegionWidth()/4, currentFrame1.getRegionHeight()/4);

        TextureRegion currentFrame2= animation2.getKeyFrame(stateTime3, true);
        stateTime3 += (Gdx.graphics.getDeltaTime())+1;
        batch.draw(currentFrame2, 400, 270+33, currentFrame2.getRegionWidth()/4, currentFrame2.getRegionHeight()/4);
        batch.draw(currentFrame2, 400, 80+33, currentFrame2.getRegionWidth()/4, currentFrame2.getRegionHeight()/4);

        batch.end();



        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
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
}
