package Screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
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

public class game_menu implements Screen {
    final MyGdxGame game;
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    private Texture img;
    private Sprite sprite;




    public game_menu(final MyGdxGame game) {
        this.game = game;
//        batch = game.batch;
        batch = new SpriteBatch();

        img = new Texture("2nd_image.jpg");
        sprite = new Sprite(img);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        stage = new Stage();
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        GameConstants GameConstants=new GameConstants() ;



        Label gamemenu = new Label("GAME MENU",skin,"big");
        gamemenu.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        gamemenu.setPosition(GameConstants.centerX - gamemenu.getWidth()/2,GameConstants.centerY + GameConstants.row_height);
        gamemenu.setAlignment(Align.center);

        Button start_Button = new TextButton("START GAME",skin,"small");
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


        Button saved_games = new TextButton("SAVED GAMES",skin,"small");
        saved_games.setSize(GameConstants.col_width*2,GameConstants.row_height);
        saved_games.setPosition(GameConstants.centerX - saved_games.getWidth()/2,start_Button.getY() - GameConstants.row_height -15);

        Button exit = new TextButton("EXIT",skin,"small");
        exit.setSize(GameConstants.col_width*2,GameConstants.row_height);
        exit.setPosition(GameConstants.centerX - exit.getWidth()/2,saved_games.getY() - GameConstants.row_height -15);

//		TextButton newGame = new TextButton("New Game", skin);
//		TextButton preferences = new TextButton("Preferences", skin);
//		TextButton exit = new TextButton("Exit",skin);

        stage.addActor(gamemenu);
        stage.addActor(start_Button);
        stage.addActor(saved_games);
        stage.addActor(exit);
//		table.add(startBtn).fillX().uniformX();
//
//		table.row().pad(0, 0, 0, 0);
//		table.add(settingsBtn).fillX().uniformX();
//		table.row();
//		table.add(exit).fillX().uniformX();
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


        batch.begin();
		batch.draw(img, 100, 100);
        sprite.draw(batch);
        batch.end();
        stage.act();

        stage.draw();
//		batch.end();
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