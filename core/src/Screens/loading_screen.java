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
//import com.mygdx.game.GameConstants;
import com.mygdx.game.GameConstants;
import com.mygdx.game.MyGdxGame;

import com.mygdx.game.MyGdxGame;

public class loading_screen implements Screen{
    final MyGdxGame game;

//    private Animation<TextureRegion> animation;
    public SpriteBatch batch;
    private SpriteBatch spriteBatch;

    private Skin skin;

    private Skin skin1;
    private Stage stage;
    private Texture img;
    private Sprite sprite;
    private Animation<TextureRegion> animation;
    private float stateTime =0f;
    private float stateTime1 =0f;

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

    public loading_screen(final MyGdxGame game){
        this.game = game;
        batch = game.batch;
        img = new Texture("3rd_image.png");

        sprite = new Sprite(img);
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

//        com.mygdx.game.GameConstants GameConstants=new com.mygdx.game.GameConstants() ;

        animation = crnt_frame("loading.png", 5, 2);
        Gdx.input.setInputProcessor(stage);



    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        TextureRegion currentFrame = animation.getKeyFrame(stateTime1, true);
        stateTime1 += (Gdx.graphics.getDeltaTime());

        batch.begin();
        sprite.draw(batch);
        batch.end();
        batch.begin();
        batch.draw(currentFrame, 60, -40, currentFrame.getRegionWidth()/2, currentFrame.getRegionHeight()/4);
        batch.end();


        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));

        stage.draw();
        if(animation.isAnimationFinished(stateTime1)){

            game.gotogame_menu();
        }

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
