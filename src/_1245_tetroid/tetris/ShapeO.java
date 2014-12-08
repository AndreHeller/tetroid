/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.tetris;

import _1245_tetroid.tetris.Shape;
import java.awt.Color;
import java.awt.Point;





/*******************************************************************************
 * Instances of class {@code OShape} represent ...
 *
 * @author  Jan DOLEŽAL
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class ShapeO extends Shape
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public ShapeO(Point centralPoint)
    {
        super(centralPoint);
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
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
//        OShape inst = new OShape();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }


    @Override
    public Point getBrick1()
    {
        return new Point (centralBrick.x - 1, centralBrick.y);
    }


    @Override
    public Point getBrick2()
    {
        return centralBrick;
    }


    @Override
    public Point getBrick3()
    {
        return new Point (centralBrick.x - 1, centralBrick.y - 1);
    }


    @Override
    public Point getBrick4()
    {
        return new Point (centralBrick.x, centralBrick.y - 1);
    }


    @Override
    public Color getColor()
    {
        return new Color(242,226, 5);
    }
}
