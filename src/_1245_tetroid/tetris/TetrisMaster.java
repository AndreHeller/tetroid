/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.tetris;

import _1245_tetroid.IGame;
import _1245_tetroid.IGameControl;
import _1245_tetroid.IGameMap;
import _1245_tetroid.IGameTimer;
import _1245_tetroid.IReactable;
import _1245_tetroid.ITetMaster;
import _1245_tetroid.ReactEvents;
import _1245_tetroid.game.GameMap;
import _1245_tetroid.reaction.GameControl;
import _1245_tetroid.reaction.SuperTimer;
import _1245_tetroid.Constants;
import java.awt.Point;





/*******************************************************************************
 * Instances of class {@code TetrisMaster} represent ...
 *
 * @author  Jan DOLEŽAL
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class TetrisMaster implements ITetMaster,IReactable
{
//== CONSTANT CLASS ATTRIBUTES =================================================
 /** The only instance of this class. */
    private Point START_POSITION;

    private final IGameMap gameMap;

    private IGameControl gameControl = GameControl.getInstance();

    private IGameTimer tetTimer;

    private Shape currentShape;

    private Shape nextShape;

    private RandomShapeGenerator generator;

//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS AND SETTERS =================================================

   public Shape getCurrentShape()
   {
        return currentShape;
   }



//== OTHER NON-PRIVATE CLASS METHODS ===========================================


   //##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    public TetrisMaster(IGame game)
    {
        gameMap = game.getGameMap();
        tetTimer = new SuperTimer(this, ReactEvents.MoveDown ,500 , 3000);
    }

    @Override
   public void initialize()
   {
       gameControl.addReactant(this);

       START_POSITION = new Point(6, 2);

       generator = new RandomShapeGenerator(START_POSITION);

       nextShape = generator.GenerateShape();

       GenerateNextShape();
       gameMap.refreshTetPosition(currentShape);

       tetTimer.initialize();

   }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrací rychlost tetrisu
     *
     * @return rychlost tetrisu
     */
    @Override
    public int getTetPeriod()
    {
        return tetTimer.getPeriod();
    }

//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    private void GenerateNextShape()
    {
           currentShape = nextShape;

           nextShape = generator.GenerateShape();


    }

    public boolean onTick()
    {
        currentShape.moveDown();
        if(! gameMap.isFree(currentShape.getBrick1().x,currentShape.getBrick1().y) ){
            currentShape.moveUp(); // vygeneruje novy shape poslat zpravu ze tento uz se nemuze hybat
            GenerateNextShape();
            gameMap.infoImpact();
            gameMap.refreshTetPosition(currentShape);
            return false;
        }

        if(! gameMap.isFree(currentShape.getBrick2().x,currentShape.getBrick2().y)){
            currentShape.moveUp(); // vygeneruje novy shape poslat zpravu ze tento uz se nemuze hybat
            GenerateNextShape();
            gameMap.infoImpact();
            gameMap.refreshTetPosition(currentShape);
            return false;
        }

        if(! gameMap.isFree(currentShape.getBrick3().x,currentShape.getBrick3().y)){
            currentShape.moveUp(); // vygeneruje novy shape poslat zpravu ze tento uz se nemuze hybat
            GenerateNextShape();
            gameMap.infoImpact();
            gameMap.refreshTetPosition(currentShape);
            return false;
        }

        if(! gameMap.isFree(currentShape.getBrick4().x,currentShape.getBrick4().y)){
            currentShape.moveUp(); // vygeneruje novy shape poslat zpravu ze tento uz se nemuze hybat
            GenerateNextShape();
            gameMap.infoImpact();
            gameMap.refreshTetPosition(currentShape);
            return false;
        }

        gameMap.refreshTetPosition(currentShape);
        return true;

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
//        TetrisMaster inst = new TetrisMaster();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }


    @Override
    public void react(ReactEvents reactEvent)
    {
       switch(reactEvent){
      case MoveLeft: moveLeft();

        break;
      case MoveRight: moveRight();

        break;
      case Rotate: rotate();

        break;
      case MoveDown: FastMoveDown();

          break;
        }
    }




    @Override
    public String getNextShape()
    {
        if(nextShape instanceof ShapeI ){
            return "ShapeI";
        }

        if(nextShape instanceof ShapeL){
            return "ShapeL";
        }

        if(nextShape instanceof ShapeJ){
            return "ShapeJ";
        }

        if(nextShape instanceof ShapeO){
            return "ShapeO";
        }

        if(nextShape instanceof ShapeS){
            return "ShapeS";
        }

        if(nextShape instanceof ShapeT){
            return "ShapeT";
        }

        if(nextShape instanceof ShapeZ){
            return "ShapeZ";
        }
        return "Chyba nevygeneroval se nasledujici tvar";
    }


    private void FastMoveDown()
    {
        onTick();
    }


    private void moveLeft()
    {
       currentShape.moveLeft();

       if(! gameMap.isFree(currentShape.getBrick1().x,currentShape.getBrick1().y)){
            currentShape.moveRight();
            return ;
        }

        if(! gameMap.isFree(currentShape.getBrick2().x,currentShape.getBrick2().y)){
            currentShape.moveRight();
            return ;
        }

        if(! gameMap.isFree(currentShape.getBrick3().x,currentShape.getBrick3().y)){
            currentShape.moveRight();
            return ;
        }

        if(! gameMap.isFree(currentShape.getBrick4().x,currentShape.getBrick4().y)){
            currentShape.moveRight();
            return ;
        }
         gameMap.refreshTetPosition(currentShape);
    }


    private void moveRight()
    {
        currentShape.moveRight();

       if(! gameMap.isFree(currentShape.getBrick1().x,currentShape.getBrick1().y)){
            currentShape.moveLeft();
            return ;
        }

        if(! gameMap.isFree(currentShape.getBrick2().x,currentShape.getBrick2().y)){
            currentShape.moveLeft();
            return ;
        }

        if(! gameMap.isFree(currentShape.getBrick3().x,currentShape.getBrick3().y)){
            currentShape.moveLeft();
            return ;
        }

        if(! gameMap.isFree(currentShape.getBrick4().x,currentShape.getBrick4().y)){
            currentShape.moveLeft();
            return ;
        }
         gameMap.refreshTetPosition(currentShape);
    }


    private void rotate()
    {
          currentShape.rotate();

       if(! gameMap.isFree(currentShape.getBrick1().x,currentShape.getBrick1().y)){
            currentShape.rotate();
            currentShape.rotate();
            currentShape.rotate();
            return ;
        }

        if(! gameMap.isFree(currentShape.getBrick2().x,currentShape.getBrick2().y)){
            currentShape.rotate();
            currentShape.rotate();
            currentShape.rotate();
            return ;
        }

        if(! gameMap.isFree(currentShape.getBrick3().x,currentShape.getBrick3().y)){
            currentShape.rotate();
            currentShape.rotate();
            currentShape.rotate();
            return ;
        }

        if(! gameMap.isFree(currentShape.getBrick4().x,currentShape.getBrick4().y)){
             currentShape.rotate();
             currentShape.rotate();
             currentShape.rotate();
            return ;
        }
         gameMap.refreshTetPosition(currentShape);
    }





}
