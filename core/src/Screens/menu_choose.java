package Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
//import com.mygdx.template.GameConstants;
//import com.mygdx.template.Main;
import com.mygdx.game.MyGdxGame;


public class menu_choose implements Screen {
    final MyGdxGame game;
    private SpriteBatch batch;
    private Skin skin;
    private Stage stage;
    private Texture img;
    private Sprite sprite;


    public menu_choose(final MyGdxGame game) {
        this.game = game;
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

        Button vs = new TextButton("1 vs 1",skin,"small");
        vs.setSize(GameConstants.col_width*2,GameConstants.row_height);
        vs.setPosition(GameConstants.centerX - vs.getWidth()/2,GameConstants.centerY);

        vs.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotochoose_tank();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        Label choose_mode = new Label("Choose Mode",skin,"big");
        choose_mode.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        choose_mode.setPosition(GameConstants.centerX - choose_mode.getWidth()/2,GameConstants.centerY + GameConstants.row_height);
        choose_mode.setAlignment(Align.center);




//        Button saved_games = new TextButton("SAVED GAMES",skin,"small");
//        saved_games.setSize(GameConstants.col_width*2,GameConstants.row_height);
//        saved_games.setPosition(GameConstants.centerX - saved_games.getWidth()/2,start_Button.getY() - GameConstants.row_height -15);

        Button exit = new TextButton("BACK",skin,"small");
        exit.setSize(GameConstants.col_width*2,GameConstants.row_height);
        exit.setPosition(GameConstants.centerX - exit.getWidth()/2,vs.getY() - GameConstants.row_height -15);

//		TextButton newGame = new TextButton("New Game", skin);
//		TextButton preferences = new TextButton("Preferences", skin);
//		TextButton exit = new TextButton("Exit",skin);

        stage.addActor(choose_mode);
        stage.addActor(vs);
//        stage.addActor(saved_games);
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
