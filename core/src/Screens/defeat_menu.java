package Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
//import Screens.GameConstants;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
//import com.mygdx.template.GameConstants;
//import com.mygdx.template.Main;
import com.mygdx.game.GameConstants;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
public class defeat_menu implements Screen {
    public SpriteBatch batch;
    private SpriteBatch spriteBatch;

    private Skin skin;
    final MyGdxGame game;

    private Skin skin1;
    private Stage stage;
    private Texture img;
    private Sprite sprite;
    private Animation<TextureRegion> animation;
    private Animation<TextureRegion> animation1;
    private Animation<TextureRegion> animation2;
    private Animation<TextureRegion> animation3;
    private Animation<TextureRegion> animation4;
    private Animation<TextureRegion> animation5;
    private Animation<TextureRegion> animation6;
    private Animation<TextureRegion> animation7;
    private Animation<TextureRegion> animation8;
    private Animation<TextureRegion> animation9;
    private Animation<TextureRegion> animation10;
    private float stateTime =0f;

    private float stateTime1 =0f;
    private float stateTime2 =0f;
    private float stateTime3 =0f;
    private float stateTime4 =0f;
    private float stateTime5 =0f;
    private float stateTime6 =0f;
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

    public defeat_menu(final MyGdxGame game) {
        this.game = game;
        batch = game.batch;

        img = new Texture("2nd_image.jpg");
        sprite = new Sprite(img);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        stage = new Stage();
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        GameConstants GameConstants= new GameConstants();

        animation = crnt_frame("def.png", 8, 10);


        Button start_Button = new TextButton("RESTART GAME",skin,"small");
        start_Button.setSize(GameConstants.col_width*2,GameConstants.row_height);
        start_Button.setPosition(GameConstants.centerX - start_Button.getWidth()/2,GameConstants.centerY);
        start_Button.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotomenu_choose();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });




        Button saved_games = new TextButton("MAIN MENU",skin,"small");
        saved_games.setSize(GameConstants.col_width*2,GameConstants.row_height);
        saved_games.setPosition(GameConstants.centerX - saved_games.getWidth()/2,start_Button.getY() - GameConstants.row_height -15);
        saved_games.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotogame_menu();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

//		Button exit = new TextButton("EXIT",skin,"small");
//		exit.setSize(GameConstants.col_width*2,GameConstants.row_height);
//		exit.setPosition(GameConstants.centerX - exit.getWidth()/2,saved_games.getY() - GameConstants.row_height -15);

//		TextButton newGame = new TextButton("New Game", skin);
//		TextButton preferences = new TextButton("Preferences", skin);
//		TextButton exit = new TextButton("Exit",skin);

//		stage.addActor(gamemenu);
        stage.addActor(start_Button);
        stage.addActor(saved_games);
//		stage.addActor(exit);
//		table.add(startBtn).fillX().uniformX();
//
//		table.row().pad(0, 0, 0, 0);
//		table.add(settingsBtn).fillX().uniformX();
//		table.row();
//		table.add(exit).fillX().uniformX();

        Gdx.input.setInputProcessor(stage);



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        TextureRegion currentFrame = animation.getKeyFrame(stateTime1, false);
        stateTime1 += (Gdx.graphics.getDeltaTime());

        batch.begin();
        sprite.draw(batch);
        batch.end();
        batch.begin();
        batch.draw(currentFrame, 200, 320, currentFrame.getRegionWidth()/2, currentFrame.getRegionHeight()/2);
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

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        skin.dispose();
        skin1.dispose();
        stage.dispose();


    }
}
