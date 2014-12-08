/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.arkanoid;

import _1245_tetroid.IPositioned;
import java.awt.Color;
import java.awt.Point;


/*******************************************************************************
 * Instances of class {@code Ball} represent ...
 *
 * @author  Tomáš KUMSTA
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class Ball implements IPositioned{

//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================
    private int speedX;

    private int speedY;

    private Point position;

    private boolean released;
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public Ball(Point point)
    {
        this.position = point;
        this.speedX   = 0;
        this.speedY   = 0;
        this.released = false;
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

/*******************************************************************************
 * Vrací pozici zadaného míčku.
 */
    @Override
    public Point getPosition()
    {
        return position;
    }

/*******************************************************************************
 * Vrací pozici zadaného míčku.
 */
     Point getNextPosition()
    {
        return new Point(position.x + speedX, position.y + speedY);
    }

/*******************************************************************************
 * Nastaví rychlost po X určitému míčku.
 */
    void setSpeedX(int speedX)
    {
        this.speedX = speedX;
    }

/*******************************************************************************
 * Vrátí rychlost po X určitému míčku.
 */
    int getSpeedX()
    {
        return speedX;
    }

/*******************************************************************************
 * Vrátí rychlost po Y určitému míčku.
 */
    int getSpeedY()
    {
        return speedY;
    }

/*******************************************************************************
 * Nastaví rychlost po Y určitému míčku.
 */
     void setSpeedY(int speedY)
    {
        this.speedY = speedY;
    }

    boolean isReleased()
    {
        return this.released;
    }
    void setReleased(boolean released)
    {
        this.released= released;
    }
    @Override
     public Color getColor()
    {
        return Color.BLUE;
    }
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
/*******************************************************************************
 * Změní směr pohybu míčku pomocí speedX a speedY při nárazu do kostky.
 */
    void brickBounce(int side)
    {
       switch(side){
              case 0:
                  break;
              case 1:
                  this.speedY = - speedY;
                  break;
              case 2:
                  this.speedX = -speedX;
                  break;
              case 3:
                  this.speedY = - speedY;
                  break;
              case 4:
                  this.speedX = - speedX;
                  break;
              case 5:
                  this.speedX = - speedX;
                  this.speedY = - speedY;
                  break;
               default:
                   break;
       }}

    void move(Point nextPosition)
    {
        this.position= nextPosition;
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
