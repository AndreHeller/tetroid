/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.tetris;

import _1245_tetroid.IShape;
import java.awt.Color;
import java.awt.Point;
import javax.swing.text.Position;





/*******************************************************************************
 * Instances of class {@code ShapeType} represent ...
 *
 * @author  Jan DOLEŽAL
 */
public abstract class Shape implements IShape
{

    ;

//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================

	protected Point centralBrick;
                protected int rotate;

//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================


    public Shape(Point centralBrick)
    {
        centralBrick = new Point(6,2);
        this.centralBrick = centralBrick;
        rotate = 0;
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================

    public void moveDown()
    {
        centralBrick.y++;
    }
    public void moveRight()
    {
        centralBrick.x++;
    }

    public void moveLeft()
    {
        centralBrick.x--;
    }

     public void moveUp()
    {
        centralBrick.y--;
    }

    public void rotate()
    {
        rotate = (rotate + 1) %4;
    }

    @Override
     public abstract Point getBrick1();

    @Override
     public abstract Point getBrick2();

    @Override
     public abstract Point getBrick3();

    @Override
     public abstract Point getBrick4();

    @Override
    public abstract Color getColor();

//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        ShapeType inst = new ShapeType();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
