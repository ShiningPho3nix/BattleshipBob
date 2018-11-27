package de.mc04.battleshipbob;

import com.badlogic.gdx.math.Rectangle;

/**
 * C
 */
public class Drop extends Rectangle {

    private int Typ;

    /**
     * fsedrfw
     * @param Type 1:geld, 2:bombe, 3: score
     * @param xPos sedfrsdf
     * @param yPos jfskd
     */
    public Drop(int Type, float xPos, float yPos){
        super(xPos, yPos, 50, 50);
        Typ = Type;
    }

    /**
     *
     * @return 1:geld, 2:bombe, 3: score
     */
    public int getTyp(){

        return Typ;
    }
}
