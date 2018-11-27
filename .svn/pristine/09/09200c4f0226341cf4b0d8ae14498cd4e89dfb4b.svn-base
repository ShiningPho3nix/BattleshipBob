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
 * ...
 */
public class MainMenuScreen implements Screen{

    final BattleShipBob game;
    OrthographicCamera camera;
    private Vector3 touchPosV;
    private Rectangle button_start;
    private Rectangle button_option;
    private final Texture startbuttonIMG_not_pressed;
    private final Texture startbuttonIMG_pressed;
    private final Texture optionbuttonIMG_pressed;
    private final Texture optionbuttonIMG_not_pressed;


    public MainMenuScreen(final BattleShipBob gam) {


        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 1280);

        touchPosV = new Vector3();
        game.touchPos.x = -1;
        game.touchPos.y = -1;

        startbuttonIMG_not_pressed = new Texture(Gdx.files.internal("Bilder/Buttons/startbutton_not_pressed.png"));
        startbuttonIMG_pressed = new Texture(Gdx.files.internal("Bilder/Buttons/startbutton_pressed.png"));
        optionbuttonIMG_not_pressed = new Texture(Gdx.files.internal("Bilder/Buttons/Optionen_not_pressed.png"));
        optionbuttonIMG_pressed = new Texture(Gdx.files.internal("Bilder/Buttons/Optionen_pressed.png"));

        button_start = new Rectangle(110,700,500,120);
        button_option = new Rectangle(110,550,500,120);
    }


    @Override
    public void render(float delta) {

        //back button konfigurieren
        if(!Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.lastButtonPressed = 0;
        }
        else if (game.lastButtonPressed != 4 && Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.lastButtonPressed = 4;
            Gdx.app.exit();
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

        game.font60_titel.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        game.font60_titel.getData().setScale(1.45f);

        game.font60_titel.draw(game.batch, "Battleship", 10, 1100);
        game.font60_titel.draw(game.batch, "Bob", (720 / 2.0f) - 105, 1000);

        //startbutton
        if(Gdx.input.isTouched() && game.touchPos.overlaps(button_start)) {
            game.batch.draw(startbuttonIMG_pressed, button_start.x, button_start.y);
        }
        else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_start)) {
            game.setScreen(new GameStartScreen(game));
        }
        else game.batch.draw(startbuttonIMG_not_pressed, button_start.x, button_start.y);

        //optionbutton
        if(Gdx.input.isTouched() && game.touchPos.overlaps(button_option)) {
            game.batch.draw(optionbuttonIMG_pressed, button_option.x, button_option.y);
        }
        else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_option)) {
            game.setScreen(new OptionScreen(game));
        }
        else game.batch.draw(optionbuttonIMG_not_pressed, button_option.x, button_option.y);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        startbuttonIMG_not_pressed.dispose();
        startbuttonIMG_pressed.dispose();
        optionbuttonIMG_pressed.dispose();
        optionbuttonIMG_not_pressed.dispose();
    }

}
