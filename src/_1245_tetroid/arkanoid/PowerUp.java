/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.arkanoid;

import static _1245_tetroid.Constants.*;
import _1245_tetroid.IPositioned;
import java.awt.Color;
import java.awt.Point;
import java.util.Random;


/*******************************************************************************
 * Instances of class {@code PowerUp} represent ...
 *
 * @author  Tomáš KUMSTA
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class PowerUp implements IPositioned{

//== CONSTANT CLASS ATTRIBUTES =================================================

/***************************************************************************
     * Konstantní rychlost pádu powerUpů.
     */
//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private PowerUpType powerUpType;

    private Point position;
    private Color color;

    private static PowerUp powerUp;

//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public PowerUp(Point position, PowerUpType type)
    {
        this.position = position;
        this.powerUpType = type;
               switch(type){
            case ShortShip:
                this.color = Color.RED;
                break;
            case LongShip:
                this.color = Color.PINK;
                break;
            case NewBall:
                this.color = Color.ORANGE;
                break;
            default:
                 break;
       }
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

/*******************************************************************************
 * Vrací pozici zadaného powerUpu.
 */
    @Override
    public Point getPosition()
    {
        return position;
    }

/*******************************************************************************
 * Vrací typ zadaného powerUpu.
 */
    PowerUpType getType()
    {
        return powerUpType;
    }

/*******************************************************************************
 * Vrací budoucí pozici zadaného powerUpu.
 */

    Point getNextPosition()
    {
        return new Point(position.x , position.y+ POWERUP_SPEED_Y);
    }

    @Override
     public Color getColor()
    {
        return color;
    }
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
/*******************************************************************************
 * Pohne powerUpem na nové místo.
 */
    void move(Point position)
    {
        this.position = position;
    }
/*******************************************************************************
 * Může vygenerovat nový powerUp na zadané pozici.
 */
    static PowerUp generatePowerUp(Point position)
    {
        Random random = new Random();
        int numberRandom = random.nextInt(3);
        switch(numberRandom){
              case 0:
                powerUp = new PowerUp(position, PowerUpType.LongShip);
                  break;
              case 1:
                powerUp = new PowerUp(position, PowerUpType.ShortShip);
                  break;
              case 2:
                  powerUp = new PowerUp(position, PowerUpType.NewBall);
              default:
                  break;
        }
        return powerUp;
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
