/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.tetris;

import _1245_tetroid.tetris.ShapeI;
import _1245_tetroid.tetris.ShapeJ;
import _1245_tetroid.tetris.ShapeL;
import _1245_tetroid.tetris.ShapeO;
import _1245_tetroid.tetris.ShapeS;
import _1245_tetroid.tetris.Shape;
import _1245_tetroid.tetris.ShapeT;
import _1245_tetroid.tetris.ShapeZ;
import java.awt.Point;
import java.util.Random;





/*******************************************************************************
 * Instances of class {@code RandomShapeGenerator} represent ...
 *
 * @author  Jan DOLEŽAL
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class RandomShapeGenerator
{
//== CONSTANT CLASS ATTRIBUTES =================================================
    private Point startPoint;
    private Random rand;

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
    public RandomShapeGenerator(Point startPoint)
    {
        this.startPoint = startPoint;
        rand = new Random();
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
      public Shape GenerateShape()
      {
          switch(rand.nextInt(7)) {

              case 0: return new ShapeZ(startPoint);

              case 1: return new ShapeS(startPoint);

              case 2: return new ShapeT(startPoint);

              case 3: return new ShapeL(startPoint);

              case 4: return new ShapeI(startPoint);

              case 5: return new ShapeO(startPoint);

              default: return new ShapeJ(startPoint);



          }


      }

//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        RandomShapeGenerator inst = new RandomShapeGenerator();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
