package de.mc04.battleshipbob;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import javax.print.DocFlavor;

/**
 * @author mc04
 */
public class GameScreen implements Screen {

    final BattleShipBob game;

    private final Texture enemyIMG_light;
    private final Texture enemyIMG_medium;
    private final Texture enemyIMG_heavy;
    private final Texture playerIMG;
    private final Texture laserIMG_player;
    private final Texture laserIMG_green;
    private final Texture laserIMG_yellow;
    private final Texture laserIMG_orange;
    private final Texture laserIMG_blue;
    private final Texture backgroundGame1;
    private final Texture backgroundGame2;
    private final Texture backgroundGame_stars1;
    private final Texture backgroundGame_stars2;
    private final Texture planetIMG_blue;
    private final Texture planetIMG_orange;
    private final Texture resetIMG_not_pressed;
    private final Texture resetIMG_pressed;
    private final Texture homeIMG;
    private final Texture life_redIMG;
    private final Texture life_greyIMG;
    private final Texture lifeIMG;
    private final Texture pauseIMG;
    private final Texture playIMG;
    private final Texture drop_bombIMG;
    private final Texture drop_coinIMG;
    private final Texture drop_scoreIMG;


    private final Sound laserSound_player_fire;
    private final Sound laserSound_player_hit;
    private final Sound laserSound_enemy_fire;
    private final Sound laserSound_enemy_hit;
    private final Sound laserSound_enemy2_fire;
    private final Sound laserSound_enemy2_hit;

    private final Sound destroySound_player;
    private final Sound destroySound_enemy;

    private BitmapFont font32_score;
    private BitmapFont font32_bomb;
    private BitmapFont font32_money;
    private long score;

    private boolean lost;
    private boolean playDeathSound;

    private OrthographicCamera camera;
    private Player playerShip;
    private Array<Enemy> enemies;
    private Array<Laser> lasers;
    private Array<Laser> enemy_lasers;
    private Array<Drop> drops;

    private Rectangle button_pause;
    private Rectangle button_reset;
    private Rectangle button_play;
    private Rectangle button_home;

    private Rectangle planet;
    private int planet_typ;

    private Vector3 touchPosV;
    private Vector3 aktuellePos;

    private float planetTime;
    private float laserTime;
    private float enemyTime;

    private int verschiebung1;
    private int verschiebung2;

    private Array<ParticleEffect> particles;


    private State state;

    public GameScreen(final BattleShipBob gam) {

        this.game = gam;


        touchPosV = new Vector3();
        aktuellePos = new Vector3(720 / 2 - 128 / 2, 90, 0);
        game.touchPos.x = -1;
        game.touchPos.y = -1;

        // Images
        //TODO fertig refactoren
        enemyIMG_heavy = new Texture(Gdx.files.internal("Bilder/Objekte/enemy_heavy.png"));
        enemyIMG_medium = new Texture(Gdx.files.internal("Bilder/Objekte/enemy_medium.png"));
        enemyIMG_light = new Texture(Gdx.files.internal("Bilder/Objekte/enemy_light.png"));
        playerIMG = new Texture(Gdx.files.internal("Bilder/Objekte/playerIMG.png"));
        laserIMG_player = new Texture(Gdx.files.internal("Bilder/Objekte/laserIMG_player.png"));
        laserIMG_green = new Texture(Gdx.files.internal("Bilder/Objekte/laser_gruen.png"));
        laserIMG_yellow = new Texture(Gdx.files.internal("Bilder/Objekte/laser_gelb.png"));
        laserIMG_orange = new Texture(Gdx.files.internal("Bilder/Objekte/laser_orange.png"));
        laserIMG_blue = new Texture(Gdx.files.internal("Bilder/Objekte/laser_blau.png"));
        backgroundGame1 = new Texture(Gdx.files.internal("Bilder/BG/backgroundGame.png"));
        backgroundGame2 = backgroundGame1;
        backgroundGame_stars1 = new Texture(Gdx.files.internal("Bilder/BG/backgroundGame_stars.png"));
        backgroundGame_stars2 = backgroundGame_stars1;
        planetIMG_blue = new Texture(Gdx.files.internal("Bilder/BG/planet1.png"));
        planetIMG_orange = new Texture(Gdx.files.internal("Bilder/BG/planet2.png"));
        resetIMG_not_pressed = new Texture(Gdx.files.internal("Bilder/Buttons/resetbutton_not_pressed.png"));
        resetIMG_pressed = new Texture(Gdx.files.internal("Bilder/Buttons/resetbutton_pressed.png"));
        homeIMG = new Texture(Gdx.files.internal("Bilder/Buttons/home_button.png"));
        life_redIMG = new Texture(Gdx.files.internal("Bilder/GUI/lebensleiste_rot.png"));
        life_greyIMG = new Texture(Gdx.files.internal("Bilder/GUI/lebensleiste_grau.png"));
        lifeIMG = new Texture(Gdx.files.internal("Bilder/GUI/lebensleiste_deckel.png"));
        pauseIMG = new Texture(Gdx.files.internal("Bilder/Buttons/pauseButton.png"));
        playIMG = new Texture(Gdx.files.internal("Bilder/Buttons/playbutton.png"));
        drop_bombIMG = new Texture(Gdx.files.internal("Bilder/Objekte/drop_bombIMG.png"));
        drop_coinIMG = new Texture(Gdx.files.internal("Bilder/Objekte/drop_coinIMG.png"));
        drop_scoreIMG = new Texture(Gdx.files.internal("Bilder/Objekte/drop_scoreIMG.png"));

        // Sounds
        laserSound_player_fire = Gdx.audio.newSound(Gdx.files.internal("Sounds/laserSound_player_fire.mp3"));
        laserSound_player_hit = Gdx.audio.newSound(Gdx.files.internal("Sounds/laserSound_player_hit.wav"));
        laserSound_enemy_fire = Gdx.audio.newSound(Gdx.files.internal("Sounds/laserSound_enemy_fire.mp3"));
        laserSound_enemy_hit = Gdx.audio.newSound(Gdx.files.internal("Sounds/laserSound_player_hit.wav"));
        laserSound_enemy2_fire = Gdx.audio.newSound(Gdx.files.internal("Sounds/laserSound_enemy2_fire.mp3"));
        laserSound_enemy2_hit = Gdx.audio.newSound(Gdx.files.internal("Sounds/laserSound_enemy2_hit.wav"));
        destroySound_player = Gdx.audio.newSound(Gdx.files.internal("Sounds/destroySound_player.mp3"));
        destroySound_enemy = Gdx.audio.newSound(Gdx.files.internal("Sounds/destroySound_enemy.wav"));

        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 1280);

        // Spieler-Ship Position
        playerShip = new Player(game.player_damage, game.player_life,
                game.player_dropchance, game.player_firerate, game.player_cannons);

        // alle Arrays
        enemies = new Array<Enemy>();
        lasers = new Array<Laser>();
        enemy_lasers = new Array<Laser>();
        drops = new Array<Drop>();
        particles = new Array<ParticleEffect>();

        // Buttons
        button_pause = new Rectangle(0, 0, 50, 50);
        button_reset = new Rectangle(60, 20, 500, 120);
        button_play = new Rectangle(360, 600, 356, 243);
        button_home = new Rectangle(40, 600, 300, 200);

        planet = new Rectangle();

        // erster Planet am Anfang
        spawnPlanet();

        //globale Zeiten
        planetTime = 0f;
        laserTime = 0f;
        enemyTime = 0f;

        // Score
        score = 0;

        // Booleans
        lost = false;
        playDeathSound = true;

        // Fonts
        font32_score = new BitmapFont(Gdx.files.internal("Fonts/Space32.fnt"));
        font32_bomb = new BitmapFont(Gdx.files.internal("Fonts/Space32.fnt"));
        font32_money = new BitmapFont(Gdx.files.internal("Fonts/Space32.fnt"));

        state = State.RUN;
    }

    private void spawnPlanet() {
        planet_typ = MathUtils.random(1,2);
        planet.x = MathUtils.random(0, 720 - 128);
        planet.y = 1280;
    }

    private void spawnEnemy(int typ) {
        Enemy enemy = new Enemy(typ, playerShip.getX_mitte(), playerShip.getY_mitte());
        enemies.add(enemy);
    }

    // Death-Screen
    private void lostGame() {

        //back button konfigurieren nach verloren
        if (!Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.lastButtonPressed = 0;
        } else if (game.lastButtonPressed != 4 && Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.lastButtonPressed = 4;
            game.setScreen(new MainMenuScreen(game));
        }
        if (playDeathSound) {
            if(game.sound_enabled)destroySound_player.play();
            playDeathSound = false;
        }

        game.font60_gameover.draw(game.batch, "Game over!", 80, 680);
        game.font60_gameover.draw(game.batch, "Highscore: " + game.highscore, 80, 600);

        //Resetbutton und neustart des Mainmenüs.
        if (Gdx.input.isTouched() && game.touchPos.overlaps(button_reset)) {
            game.batch.draw(resetIMG_pressed, button_reset.x, button_reset.y);
        } else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_reset)) {
            game.setScreen(new MainMenuScreen(game));
        } else game.batch.draw(resetIMG_not_pressed, button_reset.x, button_reset.y);
    }

    /**
     * render loop waehrend man nicht verloren hat.
     */
    public void runGame() {

        //back button konfigurieren während spiel läuft
        if (!Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.lastButtonPressed = 0;
        } else if (game.lastButtonPressed != 4 && Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.lastButtonPressed = 4;
            game.touchPos.x = -1;
            game.touchPos.y = -1;
            setGameState(State.PAUSE);
        }

        //spawnEnemy
        if (enemyTime > 2f) {
            //TODO richtiges leveldesign spawnverhalten
            int i = MathUtils.random(1, 100);
            if (i <= 20) spawnEnemy(2); //heavy
            else if (i <= 50) spawnEnemy(1);    //medium
            else spawnEnemy(3); //light

            enemyTime = 0f;
        }

        // Zeichnung (Spaceship Spieler)
        game.batch.draw(playerIMG, playerShip.x - 14, playerShip.y - 14);

        // Laser-Spawn
        if (playerShip.getFirerate() < laserTime && Gdx.input.isTouched()) {
            lasers = playerShip.spawnLaser();
            laserTime = 0f;
            if(game.sound_enabled)laserSound_player_fire.play();
        }

        //Zeichnung und Bewegung der Drops
        Iterator<Drop> iter_drop = drops.iterator();
        while (iter_drop.hasNext()) {
            Drop drop = iter_drop.next();

            //Zeichnung
            switch (drop.getTyp()) {
                case 1:
                    game.batch.draw(drop_coinIMG, drop.x, drop.y);
                    break;
                case 2:
                    game.batch.draw(drop_bombIMG, drop.x, drop.y);
                    break;
                case 3:
                    game.batch.draw(drop_scoreIMG, drop.x, drop.y);
                    break;
                default:
                    Gdx.app.log("DropTyp", "Keine Textur für gegebenen DropTyp");
                    break;
            }
            // Bewegung
            drop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (drop.x < 0) drop.x = 0;
            if (drop.x > 720 - 50) drop.x = 720 - 50;
            if (drop.y + 50 > 1280) {
                iter_drop.remove();
            }
            if (playerShip.overlaps(drop)) {
                switch (drop.getTyp()) {
                    case 1:
                        game.geld += 100;
                        break;
                    case 2:
                        playerShip.setBombs(playerShip.getBombs() + 1);
                        break;
                    case 3:
                        score += 150;
                        if (game.highscore < score) game.highscore = score;
                        break;
                    default:
                        Gdx.app.log("Droptyp", "Kollision problem");
                        break;
                }
                iter_drop.remove();
            }
        }

        // Zeichnung + Bewegung (Laser vom Spieler)
        Iterator<Laser> iter_laser = lasers.iterator();
        while (iter_laser.hasNext()) {
            Laser laser = iter_laser.next();

            // Zeichnung
            game.batch.draw(laserIMG_player, laser.x, laser.y);

            // Bewegung
            laser.y += laser.getGeschwindigkeit() * Gdx.graphics.getDeltaTime();
            if (laser.y + 29 > 1280) {
                iter_laser.remove();
            }
        }

        // Zeichnung + Bewegung + Schoot-Laser + Kollision Spieler + Kollision Laser (Spaceships Enemies)
        // Textur wird passened zum unterschied der Hitbox zentriert gezeichnet.
        Iterator<Enemy> iter_enemy = enemies.iterator();
        while (iter_enemy.hasNext()) {
            Enemy enemy = iter_enemy.next();
            //Zeichnung
            switch (enemy.getTyp()) {
                case 1:
                    game.batch.draw(enemyIMG_medium,
                            enemy.x - (enemyIMG_medium.getWidth() - enemy.getWidth()) / 2.0f,
                            enemy.y - (enemyIMG_medium.getHeight() - enemy.getHeight()) / 2.0f,
                            64, 64, 128, 128, 1, 1, enemy.getRotation(), 0, 0, 128, 128, false, false);
                    break;
                case 2:
                    game.batch.draw(enemyIMG_heavy,
                            enemy.x - (enemyIMG_heavy.getWidth() - enemy.getWidth()) / 2.0f,
                            enemy.y - (enemyIMG_heavy.getHeight() - enemy.getHeight()) / 2.0f,
                            64, 64, 128, 128, 1, 1, enemy.getRotation(), 0, 0, 128, 128, false, false);

                    break;
                case 3:
                    game.batch.draw(enemyIMG_light,
                            enemy.x - (enemyIMG_light.getWidth() - enemy.getWidth()) / 2.0f,
                            enemy.y - (enemyIMG_light.getHeight() - enemy.getHeight()) / 2.0f,
                            64, 64, 128, 128, 1, 1, enemy.getRotation(), 0, 0, 128, 128, false, false);
                    break;
                default:
                    Gdx.app.log("Gegnertyp", "Keine Textur für gegebenen Gegner");
                    break;
            }

            // Bewegung gegner
            enemy.move();
            // Shoot-Laser
            if (enemy.shoot()) {
                enemy_lasers.addAll(enemy.spawnLaser(1, playerShip.getX_mitte(), playerShip.getY_mitte()));
                if (enemy.getTyp() == 1) {
                    if(game.sound_enabled)laserSound_enemy_fire.play();
                } else {
                    if(game.sound_enabled)laserSound_enemy2_fire.play();
                }
            }
            // TODO Enemy-Höhe variabel/ anpassen
            if (enemy.y + 128 < 0 || enemy.y > 1280 || enemy.x + 128 < 0 || enemy.x > 720) {
                iter_enemy.remove();
            }
            // Spieler berührt Gegner
            if (playerShip.overlaps(enemy)) {
                playerShip.hit(2);
                if(game.vibro_enabled)Gdx.input.vibrate(300);
                particles.add(spawnPartikel(1, enemy.x, enemy.y));
                iter_enemy.remove();
                if(game.sound_enabled)destroySound_enemy.play();
                continue;
            }
            // Spieler-Laser trifft Gegner
            Iterator<Laser> iter_laser1 = lasers.iterator();
            while (iter_laser1.hasNext()) {
                Laser laser = iter_laser1.next();
                if (enemy.overlaps(laser)) {
                    enemy.hit(playerShip.getDamage());
                    if(game.sound_enabled)laserSound_player_hit.play();
                    particles.add(spawnPartikel(4, laser.x, laser.y));
                    if (enemy.getLife() == 0) {
                        if(game.sound_enabled)destroySound_enemy.play();
                        score += enemy.getScore();
                        particles.add(spawnPartikel(1, enemy.x + 64, enemy.y + 64));
                        drops.addAll(enemy.spawnDrop(playerShip.getDropchance()));
                        iter_enemy.remove();
                        if (score > game.highscore) game.highscore = score;

                    }
                    iter_laser1.remove();
                    break;
                }
            }
        }

        // Zeichnung + Bewegung + Kollision Spieler (Laser Enemies)
        Iterator<Laser> iter_laser_enemy = enemy_lasers.iterator();
        while (iter_laser_enemy.hasNext()) {
            Laser laser = iter_laser_enemy.next();

            // Zeichnung
            switch (laser.getTyp()) {
                case 1:
                    game.batch.draw(laserIMG_green,
                            laser.x - (laserIMG_blue.getWidth() - laser.getWidth()) / 2.0f,
                            laser.y - (laserIMG_blue.getHeight() - laser.getHeight()) / 2.0f);
                    break;
                case 2:
                    game.batch.draw(laserIMG_blue,
                            laser.x - (laserIMG_blue.getWidth() - laser.getWidth()) / 2.0f,
                            laser.y - (laserIMG_blue.getHeight() - laser.getHeight()) / 2.0f);
                    break;
                case 3:
                    game.batch.draw(laserIMG_green,
                            laser.x - (laserIMG_blue.getWidth() - laser.getWidth()) / 2.0f,
                            laser.y - (laserIMG_blue.getHeight() - laser.getHeight()) / 2.0f);
                    break;
                default:
                    break;
            }

            // Bewegung Enemy-Laser
            float betrag = (float) Math.sqrt(laser.getRichtung().x * laser.getRichtung().x + laser.getRichtung().y * laser.getRichtung().y);
            laser.y += laser.getRichtung().y / betrag * laser.getGeschwindigkeit() * Gdx.graphics.getDeltaTime();
            laser.x += laser.getRichtung().x / betrag * laser.getGeschwindigkeit() * Gdx.graphics.getDeltaTime();

            // Spieler wird von Laser getroffen
            if (playerShip.overlaps(laser)) {
                if(game.vibro_enabled)Gdx.input.vibrate(300);
                playerShip.hit(laser.getSchaden());
                if (laser.getTyp() == 2) {
                    particles.add(spawnPartikel(2, laser.x, laser.y));
                    if(game.sound_enabled)laserSound_enemy2_hit.play();
                } else {
                    particles.add(spawnPartikel(3, laser.x, laser.y));
                    if(game.sound_enabled)laserSound_enemy_hit.play();
                }
                iter_laser_enemy.remove();
            }
            if (laser.y - 20 < 0) {
                iter_laser_enemy.remove();
            }
        }

        game.batch.draw(life_greyIMG, 10, -25);
        // 520px breite
        game.batch.draw(life_redIMG, (-340 + (525 * (playerShip.getLife_current() / playerShip.getStartlife()))), 10);
        game.batch.draw(lifeIMG, -10, -10);

        //PauseButton
        if (Gdx.input.isTouched() && game.touchPos.overlaps(button_pause)) {
            game.batch.draw(pauseIMG, button_pause.x, button_pause.y);//TODO grafik pausebutton
        } else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_pause)) {
            pause();
        } else game.batch.draw(pauseIMG, button_pause.x, button_pause.y);
    }

    /**
     * @param typ   1: explosion, 2:laser_green, 3:laser_green_s, 4: laser_purple_s, 5: explosion_big
     * @param x_pos x-Koordinate
     * @param y_pos y-Koordinate
     * @return ein partikel der gedrawed werden kann
     */
    public ParticleEffect spawnPartikel(int typ, float x_pos, float y_pos) {
        ParticleEffect particle = new ParticleEffect();
        switch (typ) {
            case 1:
                particle.load(Gdx.files.internal("Effekte/explosion.p"), Gdx.files.internal("Bilder/particle"));
                particle.setPosition(x_pos, y_pos);
                particle.start();
                break;
            case 2:
                particle.load(Gdx.files.internal("Effekte/laser_hit_ball.p"), Gdx.files.internal("Bilder/particle"));
                particle.setPosition(x_pos, y_pos);
                particle.start();
                break;
            case 3:
                particle.load(Gdx.files.internal("Effekte/laser_hit_green_wenig.p"), Gdx.files.internal("Bilder/particle"));
                particle.setPosition(x_pos, y_pos);
                particle.start();
                break;
            case 4:
                particle.load(Gdx.files.internal("Effekte/laser_hit_purple_wenig.p"), Gdx.files.internal("Bilder/particle"));
                particle.setPosition(x_pos, y_pos);
                particle.start();
                break;
            case 5:
                particle.load(Gdx.files.internal("Effekte/explosion_big.p"), Gdx.files.internal("Bilder/particle"));
                particle.setPosition(x_pos, y_pos);
                particle.start();
                break;
            default:
                Gdx.app.log("spawnParticle", "ungültiger Partikeltyp");
        }
        return particle;
    }

    private void drawPauseScreen() {
        //back button konfigurieren
        if (!Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.lastButtonPressed = 0;
        } else if (game.lastButtonPressed != 4 && Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.lastButtonPressed = 4;
            game.touchPos.x = -1;
            game.touchPos.y = -1;
            setGameState(State.RUN);
        }

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        //PlayButton
        if (Gdx.input.isTouched() && game.touchPos.overlaps(button_play)) {
            game.batch.draw(playIMG, button_play.x, button_play.y);//TODO grafik playbutton
        } else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_play)) {
            resume();
        } else game.batch.draw(playIMG, button_play.x, button_play.y);

        //MainMenuButton
        if (Gdx.input.isTouched() && game.touchPos.overlaps(button_home)) {
            game.batch.draw(homeIMG, button_home.x, button_home.y);//TODO grafik playbutton
        } else if (!Gdx.input.isTouched() && game.touchPos.overlaps(button_home)) {
            game.setScreen(new MainMenuScreen(game));
        } else game.batch.draw(homeIMG, button_home.x, button_home.y);


        game.batch.end();
    }

    private void drawRunScreen() {

        //Initialisierung Bildschirm/Hintergrund
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        // Verschiebung + Zeichnung Hintergrund 1
        if (verschiebung1 < -1280.0f) {
            verschiebung1 = 0;
        }
        game.batch.draw(backgroundGame1, 0, verschiebung1);
        game.batch.draw(backgroundGame2, 0, 1280.0f + verschiebung1);
        verschiebung1 -= 1.0f;

        // Zeichnung + Bewegung (Planeten)
        switch (planet_typ) {
            case 1:
                planet.width = 300;
                planet.height = 300;
                game.batch.draw(planetIMG_blue, planet.x, planet.y);
                break;
            case 2:
                planet.width = 110;
                planet.height = 110;
                game.batch.draw(planetIMG_orange, planet.x, planet.y);
                break;
            default:
                break;
        }

        planet.y -= 200 * Gdx.graphics.getDeltaTime();

        // Planeten-Spawn
        if (planetTime > 10) {
            spawnPlanet();
            planetTime = 0;
        }

        // Verschiebung + Zeichnung Hintergrund 2
        if (verschiebung2 < -1280.0f) {
            verschiebung2 = 0;
        }
        game.batch.draw(backgroundGame_stars1, 0, verschiebung2);
        game.batch.draw(backgroundGame_stars2, 0, 1280.0f + verschiebung2);
        verschiebung2 -= 4.0f;


        // Spiel läuft noch
        if (!lost) {
            runGame();
        }

        //zeichne alle Partikel
        Iterator<ParticleEffect> iter_particle = particles.iterator();
        while (iter_particle.hasNext()) {
            ParticleEffect particle = iter_particle.next();
            if (!particle.isComplete()) {
                particle.draw(game.batch, Gdx.graphics.getDeltaTime());
            } else {
                particle.dispose();
                iter_particle.remove();
            }
        }

        // Schriftart
        font32_score.draw(game.batch, "Score: " + score, 0, 1270);
        font32_money.draw(game.batch, "Geld: " + game.geld, 0, 1220);
        font32_bomb.draw(game.batch, "Bomben: " + playerShip.getBombs(), 0, 1170);

        // Spiel verloren
        if (lost) {
            lostGame();
        }

        game.batch.end();

        // Touch-Input
        if (Gdx.input.isTouched()) {
            playerShip.x += (touchPosV.x - aktuellePos.x) / 8.0f;
            playerShip.y += ((touchPosV.y - aktuellePos.y) / 8.0f);
            aktuellePos.set(playerShip.getX() + 64, playerShip.getY() - 50, 0);
        }

        // Rand-Begrenzung
        if (playerShip.x < 0) playerShip.x = 0;
        if (playerShip.x > 720 - 100) playerShip.x = 720 - 100;
        if (playerShip.y < 0) playerShip.y = 0;
        if (playerShip.y > 1280 - 100) playerShip.y = 1280 - 100;

        // Spieler Leben auf 0 -> lost
        if (playerShip.getLife_current() == 0 && (!lost)) {
            lost = true;
            BattleShipBob.PREF_SCORE.putLong("Highscore", game.highscore);
            BattleShipBob.PREF_GELD.putLong("Geld", game.geld);
            BattleShipBob.PREF_SCORE.flush();
            BattleShipBob.PREF_GELD.flush();
            particles.add(spawnPartikel(5, playerShip.x + 64, playerShip.y + 64));
        }

        // Spawn-Zeit Planeten + Laser (Spieler)
        planetTime += Gdx.graphics.getDeltaTime();
        enemyTime += Gdx.graphics.getDeltaTime();
        if (Gdx.input.isTouched()) {
            laserTime += Gdx.graphics.getDeltaTime();
        }

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.isTouched()) {
            touchPosV.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosV);
            game.touchPos.x = touchPosV.x;
            game.touchPos.y = touchPosV.y;
        }

        switch (state) {

            case RUN:
                drawRunScreen();
                break;
            case PAUSE:
                drawPauseScreen();
                break;
            case RESUME:
                setGameState(GameScreen.State.RUN);
                break;
            default:
                Gdx.app.log("Enum", "Falscher State");
                break;
        }
    }

    public void setGameState(State s) {
        this.state = s;
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {

        dispose();
    }

    @Override
    public void pause() {

        this.state = State.PAUSE;
    }

    @Override
    public void resume() {

        this.state = State.RESUME;
    }

    @Override
    public void dispose() {

        enemyIMG_light.dispose();
        enemyIMG_medium.dispose();
        enemyIMG_heavy.dispose();
        playerIMG.dispose();
        laserIMG_player.dispose();
        laserIMG_green.dispose();
        laserIMG_orange.dispose();
        laserIMG_yellow.dispose();
        backgroundGame1.dispose();
        backgroundGame_stars1.dispose();
        backgroundGame_stars2.dispose();
        planetIMG_blue.dispose();
        planetIMG_orange.dispose();
        resetIMG_not_pressed.dispose();
        resetIMG_pressed.dispose();
        life_redIMG.dispose();
        life_greyIMG.dispose();
        lifeIMG.dispose();
        pauseIMG.dispose();
        playIMG.dispose();
        homeIMG.dispose();
        drop_bombIMG.dispose();
        drop_coinIMG.dispose();
        drop_scoreIMG.dispose();

        laserSound_player_fire.dispose();
        laserSound_player_hit.dispose();
        laserSound_enemy_fire.dispose();
        laserSound_enemy_hit.dispose();
        laserSound_enemy2_fire.dispose();
        laserSound_enemy2_hit.dispose();

        destroySound_player.dispose();
        destroySound_enemy.dispose();

        font32_money.dispose();
        font32_bomb.dispose();
        font32_score.dispose();
    }

    public enum State {
        PAUSE,
        RUN,
        RESUME,
        //STOPPED
    }
}




