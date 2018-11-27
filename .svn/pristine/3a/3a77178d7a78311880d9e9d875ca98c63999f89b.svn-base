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
 * REEEEEEEE
 */
public class GameStartScreen implements Screen{

    private BattleShipBob game;
    OrthographicCamera camera;
    private Vector3 touchPosV;

    private Texture rundestartbuttonIMG_Pressed;
    private Texture rundestartbuttonIMG_not_Pressed;
    private Texture backbuttonIMG_pressed;
    private Texture backbuttonIMG_not_pressed;
    private Texture shopIMG_pressed;
    private Texture shopIMG_not_pressed;

    private Rectangle button_back;
    private Rectangle button_shop;
    private Rectangle button_start;


    public GameStartScreen(BattleShipBob gam){
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 1280);

        touchPosV = new Vector3();
        game.touchPos.x = -1;
        game.touchPos.y = -1;

        rundestartbuttonIMG_Pressed = new Texture("Bilder/Buttons/rundeStarten_pressed.png");
        rundestartbuttonIMG_not_Pressed = new Texture("Bilder/Buttons/rundeStarten_not_pressed.png");
        backbuttonIMG_pressed = new Texture("Bilder/Buttons/back_pressed.png");
        backbuttonIMG_not_pressed = new Texture("Bilder/Buttons/back_not_pressed.png");
        shopIMG_pressed = new Texture("Bilder/Buttons/shop_pressed.png");
        shopIMG_not_pressed = new Texture("Bilder/Buttons/shop_not_pressed.png");

        button_back = new Rectangle(10,1150,250,120);
        button_shop = new Rectangle(110,550,500,120);
        button_start = new Rectangle(110,700,500,120);
    }

    @Override
    public void render(float delta) {

        //TODO schwierigkeitsgrade?
        //back button konfigurieren
        if(!Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.lastButtonPressed = 0;
        }
        else if (game.lastButtonPressed != 4 && Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.lastButtonPressed = 4;
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

        //startbutton
        if(Gdx.input.isTouched() && game.touchPos.overlaps(button_start)) {
            game.batch.draw(rundestartbuttonIMG_Pressed, button_start.x, button_start.y);
        }
        else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_start)) {
            game.setScreen(new GameScreen(game));
        }
        else game.batch.draw(rundestartbuttonIMG_not_Pressed, button_start.x, button_start.y);

        //backbutton
        if(Gdx.input.isTouched() && game.touchPos.overlaps(button_back)) {
            game.batch.draw(backbuttonIMG_pressed, button_back.x, button_back.y);
        }
        else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_back)) {
            game.setScreen(new MainMenuScreen(game));
        }
        else game.batch.draw(backbuttonIMG_not_pressed, button_back.x, button_back.y);


        //Shopbutton
        if(Gdx.input.isTouched() && game.touchPos.overlaps(button_shop)) {
            game.batch.draw(shopIMG_pressed, button_shop.x, button_shop.y);
        }
        else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_shop)) {
            game.setScreen(new ShopScreen(game));
        }
        else game.batch.draw(shopIMG_not_pressed, button_shop.x, button_shop.y);


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

    }
}
