package _1245_tetroid;

import java.awt.Color;

/**
 * Třída uchovávající všechny konstanty
 *
 * @author Martin
 */
public class Constants
{

    /***
     * Barva pozadí
     */
    public static final Color BACKGROUND = new Color(200, 180, 50, 255);

    /***
     * Barva okrajů
     */
    public static final Color BORDER = new Color(0, 0, 0, 255);

    /***
     * Šířka (bez okrajů)
     */
    public static final int xBricks = 10;

    /***
     * Výška (bez okrajů)
     */
    public static final int yBricks = 20;

    /***
     * Počet pixelů na kostku - x
     */
    public static final int xBrickSize = 32;

    /***
     * Počet pixelů na kostku - y
     */
    public static final int yBrickSize = 32;

    /***
     * Arkanoid konstanty
     */
    public static final int  SHIP_HEIGHT    = 20;
    public static final byte LONG_SHIP      = 96;
    public static final byte MEDIUM_SHIP    = 64;
    public static final byte SHORT_SHIP     = 32;
    public static final byte MAX_BALL_SPEED = 8;
    public static final byte SHIP_SPEED     = 32;
    public static final int POWERUP_SPEED_Y = 4;



}
