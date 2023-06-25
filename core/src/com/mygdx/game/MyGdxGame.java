package com.mygdx.game;

//import Screens.GameConstants;
import Screens.*;
import Screens.GameConstants;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//import com.mygdx.template.managers.MyAssetManager;
//import com.mygdx.template.screens.GameScreen;
//import com.mygdx.template.screens.MenuScreen;
//import com.mygdx.template.screens.SettingsScreen;

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
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
//import com.mygdx.template.GameConstants;
//import com.mygdx.template.Main;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MyGdxGame extends Game implements ApplicationListener{
	public SpriteBatch batch;
	private SpriteBatch spriteBatch;
	private game_menu game_menu;
	private game_menu ps;
	private loading_screen loading_screen;
	private battlefield_screen battlefield_screen;
	private play_screen play_screen;
	private  defeat_menu defeat_menu;
	public int flag1;
	public int flag2;

	private Skin skin;

	private Skin skin1;
	private Stage stage;

	private Sprite sprite;
	public Viewport screenPort;
	public static final int V_WIDTH = 1920;
	public static final int V_HEIGHT = 1080;

//	public MyAssetManager myAssetManager = new MyAssetManager();
public game_menu gme;
	public game_menu gm;



public game_menu getInstancegame_menu(){
	if(gme== null){
		gme = new game_menu(this);
		return gme;
	}
	else{
		return gme;
	}


}


	@Override
	public void create() {

		batch = new SpriteBatch();
//		batch = new SpriteBatch();
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false);
		screenPort = new ScreenViewport();
		this.setScreen(new loading_screen(this));

//		this.setScreen(new play_screen(this));


	}
	public void gotogame_menu(){
//		game_menu gm = new game_menu(this);
		gm = getInstancegame_menu();
		setScreen(gm);
	}
	//
	public void gotomenu_choose(){
		menu_choose mc = new menu_choose(this);
		setScreen(mc);
	}

	public void gotochoose_tank(){
		choose_tank ct = new choose_tank(this);
		setScreen((ct));
	}
	public void gotobattlefield_screen(){
		battlefield_screen bs = new battlefield_screen(this);
		setScreen((bs));
	}

	public void gotovictory_menu(){
		victory_menu vm = new victory_menu(this);
		setScreen((vm));
	}

	public void gotoplay_screen(){
		play_screen ps = new play_screen(this);
		setScreen((ps));
	}


	public void gotopause_menu(){
		pause_menu pm = new pause_menu(this);
		setScreen((pm));
	}

	public void gotodefeat_menu(){
		defeat_menu dm = new defeat_menu(this);
		setScreen((dm));
	}


	@Override
	public void dispose() {
		super.dispose();

	}

	@Override
	public void render() {
		super.render();



	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume(){
	}
}