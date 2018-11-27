package de.mc04.battleshipbob;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BattleShipBob extends Game {

	public SpriteBatch batch;
	public BitmapFont font60_titel;
	public BitmapFont font60_gameover;
	public BitmapFont font32;
	public Texture backgroundMenu;
	public Rectangle touchPos;
	public long geld;
	public long highscore;
	public boolean music_enabled;
	public boolean sound_enabled;
	public boolean vibro_enabled;
	public float player_damage;
	public float player_life;
	public float player_dropchance;
	public float player_firerate;
	public int player_cannons;
	public static Preferences PREF_GELD;
	public static Preferences PREF_SCORE;
	public static Preferences PREF_MUSIC_ENABLED;
	public static Preferences PREF_SOUND_ENABLED;
	public static Preferences PREF_VIBRO_ENABLED;
	public static Preferences PREF_PLAYER_DAMAGE;
	public static Preferences PREF_PLAYER_LIFE;
	public static Preferences PREF_PLAYER_DROPCHANCE;
	public static Preferences PREF_PLAYER_FIRERATE;
	public static Preferences PREF_PLAYER_CANNONS;
	public int lastButtonPressed;
	public Music test_music;

	
	@Override
	public void create () {

		lastButtonPressed = 0;
		Gdx.input.setCatchBackKey(true);

		initPrefs();

		touchPos = new Rectangle(-1,-1,1,1);
		batch = new SpriteBatch();
		font60_titel = new BitmapFont(Gdx.files.internal("Fonts/Space60.fnt"));
		font60_gameover = new BitmapFont(Gdx.files.internal("Fonts/Space60.fnt"));
		font60_gameover.getData().setScale(0.7f, 1f);
		font32 = new BitmapFont(Gdx.files.internal("Fonts/Space32.fnt"));

		test_music = Gdx.audio.newMusic(Gdx.files.internal("Music/test_music.mp3"));
		test_music.setLooping(true);
		if(music_enabled)test_music.play();
		backgroundMenu = new Texture(Gdx.files.internal("Bilder/BG/backgroundMenu.jpg"));
		this.setScreen(new MainMenuScreen(this));
	}

	private void initPrefs(){
		//init geld
		PREF_GELD = Gdx.app.getPreferences("Geld");
		if (!PREF_GELD.contains("Geld")) {
			PREF_GELD.putLong("Geld", 0).flush();

		}
		geld = PREF_GELD.getLong("Geld");

		// init Highscore
		PREF_SCORE = Gdx.app.getPreferences("Highscore");
		if (!PREF_SCORE.contains("Highscore")) {
			PREF_SCORE.putLong("Highscore", 0).flush();
		}
		highscore = PREF_SCORE.getLong("Highscore");

		// init Musik
		PREF_MUSIC_ENABLED = Gdx.app.getPreferences("music");
		if (!PREF_MUSIC_ENABLED.contains("music")) {
			PREF_MUSIC_ENABLED.putBoolean("music", true).flush();
		}
		music_enabled = PREF_MUSIC_ENABLED.getBoolean("music");

		// init Sound
		PREF_SOUND_ENABLED = Gdx.app.getPreferences("sound");
		if (!PREF_SOUND_ENABLED.contains("sound")) {
			PREF_SOUND_ENABLED.putBoolean("sound", true).flush();
		}
		sound_enabled = PREF_SOUND_ENABLED.getBoolean("sound");

		// init Vibration
		PREF_VIBRO_ENABLED = Gdx.app.getPreferences("vibro");
		if (!PREF_VIBRO_ENABLED.contains("vibro")) {
			PREF_VIBRO_ENABLED.putBoolean("vibro", true).flush();
		}
		vibro_enabled = PREF_VIBRO_ENABLED.getBoolean("vibro");

		//init playerdamage
		PREF_PLAYER_DAMAGE = Gdx.app.getPreferences("player_damage");
		if (!PREF_PLAYER_DAMAGE.contains("player_damage")) {
			PREF_PLAYER_DAMAGE.putFloat("player_damage", 1f).flush();
		}
		player_damage = PREF_PLAYER_DAMAGE.getFloat("player_damage");

		//init playerlife
		PREF_PLAYER_LIFE = Gdx.app.getPreferences("player_life");
		if (!PREF_PLAYER_LIFE.contains("player_life")) {
			PREF_PLAYER_LIFE.putFloat("player_life", 7f).flush();
		}
		player_life = PREF_PLAYER_LIFE.getFloat("player_life");

		//init player dropchance
		PREF_PLAYER_DROPCHANCE = Gdx.app.getPreferences("player_dropchance");
		if (!PREF_PLAYER_DROPCHANCE.contains("player_dropchance")) {
			PREF_PLAYER_DROPCHANCE.putFloat("player_dropchance", 1f).flush();
		}
		player_dropchance = PREF_PLAYER_DROPCHANCE.getFloat("player_dropchance");

		//init firerate
		PREF_PLAYER_FIRERATE = Gdx.app.getPreferences("player_firerate");
		if (!PREF_PLAYER_FIRERATE.contains("player_firerate")) {
			PREF_PLAYER_FIRERATE.putFloat("player_firerate", 0.3f).flush();
		}
		player_firerate = PREF_PLAYER_FIRERATE.getFloat("player_firerate");

		//init cannons
		PREF_PLAYER_CANNONS = Gdx.app.getPreferences("player_cannons");
		if (!PREF_PLAYER_CANNONS.contains("player_cannons")) {
			PREF_PLAYER_CANNONS.putInteger("player_cannons", 1).flush();
		}
		player_cannons = PREF_PLAYER_CANNONS.getInteger("player_cannons");
	}

	public void render() {

		super.render();
	}

	public void dispose() {

		font60_titel.dispose();
		font60_gameover.dispose();
		font32.dispose();
		batch.dispose();
	}
}