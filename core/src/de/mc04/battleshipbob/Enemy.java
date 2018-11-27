package de.mc04.battleshipbob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * @author mineöööö
 */
public class Enemy extends Rectangle {
    private float life;
    private int geschwindigkeit;
    private int typ;
    private int score;
    private int feuerrate;
    private int laser_counter;
    private float rotation;
    private int anzahl_punkte;
    private Array<Laser> laserArray;
    private Array<Drop> dropArray;
    private ArrayList<Float> punkte_x;
    private ArrayList<Float> punkte_y;
    private Vector2 richtung;
    private static float DROPCHANCE_GELD = 10.0f;
    private static float DROPCHANCE_BOMBE = 3.0f;
    private static float DROPCHANCE_SCORE = 15.0f;

    /**
     * @param typ_input der typ des gegners. 1: normal, 2: heavy, 3: light
     */
    public Enemy(int typ_input, float x, float y) {
        super();
        anzahl_punkte = 0;

        laserArray = new Array<Laser>();
        punkte_x = new ArrayList<Float>();
        punkte_y = new ArrayList<Float>();
        dropArray = new Array<Drop>();
        richtung = new Vector2();

        typ = typ_input;
        switch (typ) {
            case 1:
                geschwindigkeit = 200;
                life = 6;
                score = 100;
                feuerrate = 120;
                rotation = 0f;
                this.x = MathUtils.random(0, 720 - 128);
                this.y = 1280;
                richtung.add(0, -1);
                this.width = 75;
                this.height = 75;
                break;
            case 2:
                geschwindigkeit = 70;
                life = 12;
                score = 225;
                feuerrate = 200;
                rotation = 0f;
                this.x = MathUtils.random(0, 720 - 128);
                this.y = 1280;
                richtung.add(0, -1);
                this.width = 110;
                this.height = 110;
                break;
            case 3:
                geschwindigkeit = 550;
                life = 3;
                score = 50;
                this.width = 50;
                this.height = 50;
                int i = MathUtils.random(1,3);
                switch (i) {
                    case 1:
                        this.x = MathUtils.random(0, 720 - 128);
                        this.y = 1280;
                        richtung.add(x - (this.x + (this.width / 2)), y - (this.y + (this.height / 2)));
                        break;
                    case 2:
                        this.x = 0 - 128;
                        this.y = MathUtils.random(640, 1280 - 128);
                        richtung.add(x - (this.x + (this.width / 2)), y - (this.y + (this.height / 2)));
                        break;
                    case 3:
                        this.x = 720;
                        this.y = MathUtils.random(640, 1280 - 128);
                        richtung.add(x - (this.x + (this.width / 2)), y - (this.y + (this.height / 2)));
                        break;
                    default:
                        break;
                }
                break;
            default:
                Gdx.app.log("Gegnertyp", "ungültiger gegnertyp eingegeben");
                break;
        }

        if (richtung.y < 0) {

            if (Math.abs(richtung.x) > Math.abs(richtung.y)) {
                rotation = 90 - (float) Math.abs(Math.toDegrees(Math.tanh(richtung.y / richtung.x)));
            }
            else {
                rotation = (float) Math.abs(Math.toDegrees(Math.tanh(richtung.x / richtung.y)));
            }

            if (richtung.x < 0) {
                rotation = rotation * (-1f);
            }
            else { }
        }

        else if (richtung.y > 0) {

            if (Math.abs(richtung.x) > Math.abs(richtung.y)) {
                rotation = (float) Math.abs(Math.toDegrees(Math.tanh(richtung.y / richtung.x)));
            }
            else {
                rotation = 90 - (float) Math.abs(Math.toDegrees(Math.tanh(richtung.x / richtung.y)));
            }

            if (richtung.x < 0) {
                rotation = rotation * (-1f);
                rotation -= 90f;
            }
            else if (richtung.x > 0) {
                rotation += 90f;
            }
            else { }
        }

        else {
            if (richtung.x < 0) {
                rotation = -90f;
            }
            else if (richtung.x > 0) {
                rotation = +90f;
            }
            else { }
        }
        Gdx.app.log("rotation:", String.valueOf(rotation));
        Gdx.app.log("x", String.valueOf(richtung.x));
        Gdx.app.log("y", String.valueOf(richtung.y));

    }

    public Array<Laser> spawnLaser(int lasers, float x, float y){
        Array<Laser> temp_laser = new Array<Laser>();

        Laser laser = new Laser(getTyp());

        switch (lasers) {
            case 1:
                laser.x = this.x + (this.width / 2) - (laser.width / 2);
                laser.y = this.y - laser.height;
                if (typ == 2) {
                    laser.setRichtung(x, y);
                }
                temp_laser.add(laser);
                break;
            default:
                break;
        }

        return temp_laser;
    }

    /**
     *  dropchance wird beeinflusst durch das player upgradelevel und nach typ des gegeners.
     *  heavies haben eine 20% erhöhte chance auf drops.
     * @param bonus_chance das upgradelevel vom spielerschiff bzgl der dropchance
     * @return Array mit drops, zwischen 0 und viel.
     */
    public Array<Drop> spawnDrop(float bonus_chance){
        int i1 = MathUtils.random(1,100);
        int i2 = MathUtils.random(1,100);
        int i3 = MathUtils.random(1,100);

        float heavybonus = 1.0f;
        if(typ == 2){
            heavybonus = 1.2f;
        }

        Vector2 center = getCenter(new Vector2());
        if(i1 <= (DROPCHANCE_GELD * bonus_chance * heavybonus)){
            Drop geld = new Drop(1,center.x + MathUtils.random(-80f, 80f), center.y + MathUtils.random(-80f, 80f));
            dropArray.add(geld);
        }
        if(i2 <= (DROPCHANCE_BOMBE * bonus_chance * heavybonus)){
            Drop bomb = new Drop(2,center.x + MathUtils.random(-80f, 80f), center.y + MathUtils.random(-80f, 80f));
            dropArray.add(bomb);
        }
        if(i3 <= (DROPCHANCE_SCORE * bonus_chance * heavybonus)){
            Drop score = new Drop(3,center.x + MathUtils.random(-80f, 80f), center.y + MathUtils.random(-80f, 80f));
            dropArray.add(score);
        }
        return dropArray;
    }

    public boolean shoot() {
        boolean b;
        if (getTyp() != 3) {
            if (laser_counter == feuerrate) {
                b = true;
                laser_counter = 0;
            }
            else {
                b = false;
                laser_counter++;
            }
        }
        else {
            b = false;
        }
        return b;
    }

    public void hit(float damage){
        this.life -= damage;
        if (life < 0) life = 0;
    }

    public Array<Laser> getLaserArray() {

        return laserArray;
    }

    public float getLife() {

        return life;
    }

    public int getGeschwindigkeit() {

        return geschwindigkeit;
    }

    public int getScore() {

        return score;
    }

    public int getTyp(){

        return typ;
    }

    public float getRotation() {
        return rotation;
    }

    public void move() {
        float betrag = (float) Math.sqrt(richtung.x * richtung.x + richtung.y * richtung.y);
        this.y += (richtung.y / betrag * geschwindigkeit) * Gdx.graphics.getDeltaTime();
        this.x += (richtung.x / betrag * geschwindigkeit)* Gdx.graphics.getDeltaTime();
    }
}