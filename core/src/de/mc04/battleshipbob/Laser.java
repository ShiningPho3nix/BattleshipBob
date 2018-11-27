package de.mc04.battleshipbob;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by 3dworsky on 26.02.2016.
 */
public class Laser extends Rectangle{

    private int typ;
    private int schaden;
    private int geschwindigkeit;
    private Vector2 richtung;

    public Laser(int typ_input){
        richtung = new Vector2();
        typ = typ_input;
        switch (typ) {
            case 0:
                schaden = 1;
                geschwindigkeit = 800;
                richtung.set(0f, -1f);
                this.width = 4;
                this.height = 29;
                break;
            case 1:
                schaden = 1;
                geschwindigkeit = 400;
                richtung.set(0f, -1f);
                this.width = 16;
                this.height = 16;
                break;
            case 2:
                schaden = 2;
                geschwindigkeit = 300;
                richtung.set(0f, -1f);
                this.width = 16;
                this.height = 16;
                break;
            case 3:
                schaden = 1;
                geschwindigkeit = 0;
                richtung.set(0f, -1f);
                this.width = 4;
                this.height = 29;
                break;
            default:
                break;
            }
        }

    public int getSchaden() {
        return schaden;
    }

    public int getGeschwindigkeit() {
        return geschwindigkeit;
    }

    public int getTyp() { return typ; }

    public Vector2 getRichtung() { return richtung; }

    public void setRichtung(float x, float y) {
        richtung.set(x - (this.x - (width / 2)), y - (this.y - (height / 2)));
    }
}
