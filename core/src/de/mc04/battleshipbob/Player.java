package de.mc04.battleshipbob;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 * @author hitlor
 */
public class Player extends Rectangle{

    private float life_current;
    private float startlife;
    private float damage;
    private float dropchance;
    private float firerate;
    private int cannons; //anzahl gleichzeitiger schüsse
    private Array<Laser> laserArray;
    private int bombs;


    public Player(float start_damage, float start_life, float start_dropchance, float start_firerate, int start_cannons){
        super(720 / 2 - 128 / 2, 70, 100, 100);

        life_current = start_life;
        startlife = start_life;
        damage = start_damage;
        cannons = start_cannons;
        dropchance = start_dropchance;
        firerate = start_firerate;
        laserArray = new Array<Laser>();
        bombs = 2;
    }

    public Array<Laser> spawnLaser(){

        Laser laser1 = new Laser(0);
        Laser laser2 = new Laser(0);
        Laser laser3 = new Laser(0);
        Laser laser4 = new Laser(0);

        switch (cannons) {
            case 1:
                laser1.x = this.x + (this.width / 2) - (laser1.width / 2);
                laser1.y = this.y + 128;
                laserArray.add(laser1);
                break;
            case 2:
                laser1.x = this.x + (this.width / 3) - (laser1.width / 2);
                laser1.y = this.y + 128;
                laserArray.add(laser1);
                laser2.x = this.x + (this.width / 3 * 2) - (laser2.width / 2);
                laser2.y = this.y + 128;
                laserArray.add(laser2);
                break;
            case 3:
                laser1.x = this.x + (this.width / 4) - (laser1.width / 2);
                laser1.y = this.y + 128;
                laserArray.add(laser1);
                laser2.x = this.x + (this.width / 4 * 2) - (laser2.width / 2);
                laser2.y = this.y + 150;
                laserArray.add(laser2);
                laser3.x = this.x + (this.width / 4 * 3) - (laser3.width / 2);
                laser3.y = this.y + 128;
                laserArray.add(laser3);
                break;
            case 4:
                laser1.x = this.x + (this.width / 5) - (laser1.width / 2);
                laser1.y = this.y + 128;
                laserArray.add(laser1);
                laser2.x = this.x + (this.width / 5 * 2) - (laser2.width / 2);
                laser2.y = this.y + 150;
                laserArray.add(laser2);
                laser3.x = this.x + (this.width / 5 * 3) - (laser3.width / 2);
                laser3.y = this.y + 150;
                laserArray.add(laser3);
                laser4.x = this.x + (this.width / 5 * 4) - (laser4.width / 2);
                laser4.y = this.y + 128;
                laserArray.add(laser4);
                break;
            default:
                break;
        }

        return laserArray;
    }

    public void hit(int damage){

        this.life_current -= damage;
        if (life_current < 0) life_current = 0;
    }


    public float getLife_current() {

        return life_current;
    }

    public float getStartlife() {
        return startlife;
    }

    public float getDamage(){

        return damage;
    }

    public int getCannons(){

        return cannons;
    }

    public float getFirerate(){
        return firerate;
    }

    public float getDropchance(){
        return dropchance;
    }

    public int getBombs(){
        return bombs;
    }

    public void setBombs(int i){
        bombs = i;
    }

    public float getX_mitte() {
        return this.x + (width / 2);
    }

    public float getY_mitte() {
        return this.y + (height / 2);
    }
}
