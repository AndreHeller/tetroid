/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.arkanoid;

import static _1245_tetroid.Constants.*;
import _1245_tetroid.IPositioned;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;





/*******************************************************************************
 * Instances of class {@code Ship} represent ...
 *
 * @author  Tomáš KUMSTA
 * @version 0.00.0000 — 20yy-mm-dd
 */
public final class Ship implements IPositioned{

//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private int lives=3;

    private Point position;

    private byte speed;

    private int size;





//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    private static final Ship SINGLETON = new Ship();

    /***************************************************************************
     * Vrátí instanci jedináčka realizujícího Arkmastera.
     *
     * @return Instance jedináčka
     */
    public final static Ship getInstance()
    {
        return SINGLETON;
    }
    /***************************************************************************
     *
     */
    private Ship()
    {
        this.size = MEDIUM_SHIP;
        this.position = new Point(((xBricks/2)*xBrickSize),
                                                    (yBricks*yBrickSize));
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
    void setSize(int length)
    {
        this.size = length;
    }

    void setLive(int lives)
    {
        this.lives = lives;
    }

    int getLives()
    {
        return lives;
    }

    int getLength()
    {
        return size;
    }

    @Override
    public Point getPosition()
    {
        return position;
    }
    int getSpeed()
    {
        return speed;
    }

    void setStartPosition()
    {
        this.position = new Point(((xBricks/2)*xBrickSize),
                                                    (yBricks*yBrickSize));
    }
    @Override
     public Color getColor()
    {
        return Color.GREEN;
    }
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
    public void move(byte speed)
    {
            this.speed = speed;
            switch(speed){
                case 0:
                  break;
                case 1:
                  this.position = new Point(position.x + SHIP_SPEED,position.y);
                  break;
                case -1:
                  this.position = new Point(position.x - SHIP_SPEED,position.y);
                  break;
                default:
                     break;
        }
    }
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        NewClass inst = new NewClass();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
