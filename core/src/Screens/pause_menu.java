package Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.mygdx.game.MyGdxGame;


public class pause_menu implements Screen {




    final MyGdxGame game;
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    private Texture img;
    private Sprite sprite;




    public pause_menu(final MyGdxGame game){
        this.game = game;
        batch = new SpriteBatch();
        img = new Texture("2nd_image.jpg");
        sprite = new Sprite(img);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        OrthographicCamera camera = new OrthographicCamera();
//        camera.setToOrtho(false);
//        ScreenViewport screenPort = new ScreenViewport();


        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        stage = new Stage();
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        Screens.GameConstants GameConstants=new GameConstants() ;

        Button start_Button = new TextButton("SAVE GAME",skin,"small");
        start_Button.setSize(GameConstants.col_width*2,GameConstants.row_height);
        start_Button.setPosition(GameConstants.centerX - start_Button.getWidth()/2,GameConstants.centerY);

        Label gamemenu = new Label("SETTINGS",skin,"big");
        gamemenu.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        gamemenu.setPosition(GameConstants.centerX - gamemenu.getWidth()/2,GameConstants.centerY + GameConstants.row_height);
        gamemenu.setAlignment(Align.center);




        Button saved_games = new TextButton("RESUME",skin,"small");
        saved_games.setSize(GameConstants.col_width*2,GameConstants.row_height);
        saved_games.setPosition(GameConstants.centerX - saved_games.getWidth()/2,start_Button.getY() - GameConstants.row_height -15);
        saved_games.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotobattlefield_screen();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        Button exit = new TextButton("MAIN MENU",skin,"small");
        exit.setSize(GameConstants.col_width*2,GameConstants.row_height);
        exit.setPosition(GameConstants.centerX - exit.getWidth()/2,saved_games.getY() - GameConstants.row_height -15);
        exit.addListener(new InputListener(){
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

//		TextButton newGame = new TextButton("New Game", skin);
//		TextButton preferences = new TextButton("Preferences", skin);
//		TextButton exit = new TextButton("Exit",skin);

        stage.addActor(gamemenu);
        stage.addActor(start_Button);
        stage.addActor(saved_games);
        stage.addActor(exit);
        Gdx.input.setInputProcessor(stage);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



//		TextureRegion currentFrame = animation.getKeyFrame(stateTime1, false);
//		stateTime1 += (Gdx.graphics.getDeltaTime());

        batch.begin();
        batch.draw(img, 100, 100);
        sprite.draw(batch);
        batch.end();
//		batch.begin();
////		batch.draw(currentFrame, 200, 320, currentFrame.getRegionWidth()/2, currentFrame.getRegionHeight()/2);
//		batch.end();

        stage.act();

        stage.draw();
//		group.draw();

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
        stage.dispose();

    }
}
