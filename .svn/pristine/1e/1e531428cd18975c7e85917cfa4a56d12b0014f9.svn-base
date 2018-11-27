package de.mc04.battleshipbob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


/**
 * @author MC04
 */
public class OptionScreen implements Screen{

    private Texture backbuttonIMG_pressed;
    private Texture backbuttonIMG_not_pressed;
    private Texture option_sound_enabled;
    private Texture option_sound_disabled;
    private Texture option_music_enabled;
    private Texture option_music_disabled;
    private Texture option_vibro_enabled;
    private Texture option_vibro_disabled;
    private final BattleShipBob game;

    OrthographicCamera camera;
    private Vector3 touchPosV;

    private Rectangle button_back;
    private Rectangle button_sound_enabled;
    private Rectangle button_sound_disabled;
    private Rectangle button_music_enabled;
    private Rectangle button_music_disabled;
    private Rectangle button_vibro_enabled;
    private Rectangle button_vibro_disabled;

    public OptionScreen(BattleShipBob gam){

        backbuttonIMG_pressed = new Texture("Bilder/Buttons/back_pressed.png");
        backbuttonIMG_not_pressed = new Texture("Bilder/Buttons/back_not_pressed.png");
        option_sound_enabled = new Texture("Bilder/Buttons/option_sound_enabled.png");
        option_sound_disabled = new Texture("Bilder/Buttons/option_sound_disabled.png");
        option_music_enabled = new Texture("Bilder/Buttons/option_music_enabled.png");
        option_music_disabled = new Texture("Bilder/Buttons/option_music_disabled.png");
        option_vibro_enabled = new Texture("Bilder/Buttons/option_vibro_enabled.png");
        option_vibro_disabled = new Texture("Bilder/Buttons/option_vibro_disabled.png");
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 1280);

        touchPosV = new Vector3();
        game.touchPos.x = -1;
        game.touchPos.y = -1;

        button_back = new Rectangle(10,1150,300,120);
        button_sound_enabled = new Rectangle(720/2 - 210,800,120,120);
        button_sound_disabled = new Rectangle(720/2 - 210,800,120,120);
        button_music_enabled = new Rectangle(720/2 + 90,800,120,120);
        button_music_disabled = new Rectangle(720/2 + 90,800,120,120);
        button_vibro_enabled = new Rectangle(720/2 -60,560,120,120);
        button_vibro_disabled = new Rectangle(720/2 -60,560,120,120);

    }

    @Override
    public void render(float delta) {

        //back button konfigurieren
        if(!Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.lastButtonPressed = 0;
        }
        else if (game.lastButtonPressed != 4 && Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.lastButtonPressed = 4;
            game.PREF_MUSIC_ENABLED.putBoolean("music", game.music_enabled).flush();
            game.PREF_SOUND_ENABLED.putBoolean("sound", game.sound_enabled).flush();
            game.PREF_VIBRO_ENABLED.putBoolean("vibro", game.vibro_enabled).flush();
            game.setScreen(new MainMenuScreen(game));
        }


        if (Gdx.input.isTouched()) {
            touchPosV.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosV);
            game.touchPos.x = touchPosV.x;
            game.touchPos.y = touchPosV.y;
        }

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.disableBlending();
        game.batch.draw(game.backgroundMenu, 0, 0);
        game.batch.enableBlending();


        if(Gdx.input.isTouched() && game.touchPos.overlaps(button_back)) {
            game.batch.draw(backbuttonIMG_pressed, button_back.x, button_back.y);
        }
        else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_back)) {
            game.PREF_MUSIC_ENABLED.putBoolean("music", game.music_enabled).flush();
            game.PREF_SOUND_ENABLED.putBoolean("sound", game.sound_enabled).flush();
            game.PREF_VIBRO_ENABLED.putBoolean("vibro", game.vibro_enabled).flush();
            game.setScreen(new MainMenuScreen(game));
        }
        else game.batch.draw(backbuttonIMG_not_pressed, button_back.x, button_back.y);

        //TODO vibration, sound, musik können größere bilder bekommen (240px?)
        //sound enabled, berühren:disable sound
        if (game.sound_enabled) {
            if (Gdx.input.isTouched() && game.touchPos.overlaps(button_sound_enabled)) {
                game.batch.draw(option_sound_enabled, button_sound_enabled.x, button_sound_enabled.y);
            } else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_sound_enabled)) {
                game.sound_enabled = false;
                game.touchPos.x = -1;
                game.touchPos.y = -1;
            } else
                game.batch.draw(option_sound_enabled, button_sound_enabled.x, button_sound_enabled.y);
        }
        //sound disabled, berühren:enable sound
        else if (Gdx.input.isTouched() && game.touchPos.overlaps(button_sound_disabled)) {
            game.batch.draw(option_sound_disabled, button_sound_disabled.x, button_sound_disabled.y);
        } else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_sound_disabled)) {
            game.sound_enabled = true;
            game.touchPos.x = -1;
            game.touchPos.y = -1;
        } else
            game.batch.draw(option_sound_disabled, button_sound_disabled.x, button_sound_disabled.y);


        //music enabled, berühren:disable music
        if (game.music_enabled) {
            if (Gdx.input.isTouched() && game.touchPos.overlaps(button_music_enabled)) {
                game.batch.draw(option_music_enabled, button_music_enabled.x, button_music_enabled.y);
            } else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_music_enabled)) {
                game.music_enabled = false;
                game.test_music.stop();
                game.touchPos.x = -1;
                game.touchPos.y = -1;
            } else
                game.batch.draw(option_music_enabled, button_music_enabled.x, button_music_enabled.y);
        }
        //music disabled, berühren:enable music
        else if (Gdx.input.isTouched() && game.touchPos.overlaps(button_music_disabled)) {
            game.batch.draw(option_music_disabled, button_music_disabled.x, button_music_disabled.y);
        } else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_music_disabled)) {
            game.music_enabled = true;
            game.test_music.play();
            game.touchPos.x = -1;
            game.touchPos.y = -1;
        } else
            game.batch.draw(option_music_disabled, button_music_disabled.x, button_music_disabled.y);


        //vibro enabled, berühren:disable vibro
        if (game.vibro_enabled) {
            if (Gdx.input.isTouched() && game.touchPos.overlaps(button_vibro_enabled)) {
                game.batch.draw(option_vibro_enabled, button_vibro_enabled.x, button_vibro_enabled.y);
            } else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_vibro_enabled)) {
                game.vibro_enabled = false;
                game.touchPos.x = -1;
                game.touchPos.y = -1;
            } else
                game.batch.draw(option_vibro_enabled, button_vibro_enabled.x, button_vibro_enabled.y);
        }
        //vibro disabled, berühren:enable vibro
        else if (Gdx.input.isTouched() && game.touchPos.overlaps(button_vibro_disabled)) {
            game.batch.draw(option_vibro_disabled, button_vibro_disabled.x, button_vibro_disabled.y);
        } else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_vibro_disabled)) {
            game.vibro_enabled = true;
            game.touchPos.x = -1;
            game.touchPos.y = -1;
        } else
            game.batch.draw(option_vibro_disabled, button_vibro_disabled.x, button_vibro_disabled.y);
        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void hide() {

        dispose();
    }

    @Override
    public void show() {

    }


    @Override
    public void dispose() {
        backbuttonIMG_not_pressed.dispose();
        backbuttonIMG_pressed.dispose();

    }
}
