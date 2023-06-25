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
public class battlefield_screen implements Screen {
    public SpriteBatch batch;
    private SpriteBatch spriteBatch;

    private Skin skin;
    final MyGdxGame game;

    private Skin skin1;
    private Stage stage;
    private Texture img;
    private Sprite sprite;
    private Sprite sprite2;
    private Sprite sprite3;
    private Sprite sprite4;
    private Sprite sprite5;
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

    public battlefield_screen(final MyGdxGame game) {
        this.game = game;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
        batch = game.batch;
//        GameConstants GameConstants= new GameConstants();
        GameConstants GameConstants= new GameConstants();

        img = new Texture("battleground.jpg");
        sprite = new Sprite(img);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Texture left = new Texture("both2.png");
        sprite2 = new Sprite(left);
        sprite2.setSize(Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/10);
        sprite2.setPosition(50,50);

//        Texture right = new Texture("moving_right.jpg");
//        sprite3 = new Sprite(right);
//        sprite3.setSize(Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/10);
//        sprite3.setPosition(50+30,50);
        Texture angle = new Texture("vec.png");
        sprite3 = new Sprite(angle);
        sprite3.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
        sprite3.setPosition(50+100+300 ,50);
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        stage = new Stage();
        Button start_Button = new TextButton("FIRE",skin,"small");
        start_Button.setSize(GameConstants.col_width*2,GameConstants.row_height);
        start_Button.setPosition(GameConstants.centerX - start_Button.getWidth()/2,GameConstants.centerY-200);
        start_Button.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotodefeat_menu();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });


        Button men_Button = new TextButton("MENU",skin,"small");
        men_Button.setSize(GameConstants.col_width,GameConstants.row_height/1);
        men_Button.setPosition(GameConstants.centerX - men_Button.getWidth()/2 -280,GameConstants.centerY+170);

        men_Button.addListener(new InputListener(){
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

        stage.addActor(men_Button);
        stage.addActor(start_Button);




        Gdx.input.setInputProcessor(stage);



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



//        TextureRegion currentFrame = animation.getKeyFrame(stateTime1, false);
//        stateTime1 += (Gdx.graphics.getDeltaTime());

        batch.begin();
        sprite.draw(batch);
//        batch.begin();
//        batch.draw(currentFrame, 260, 350, currentFrame.getRegionWidth()/4, currentFrame.getRegionHeight()/4);
//        batch.end();
        sprite2.draw(batch);
        sprite3.draw(batch);
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
