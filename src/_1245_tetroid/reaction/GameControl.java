/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.reaction;

import java.util.ArrayList;
import java.util.Collection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import _1245_tetroid.IGameControl;
import _1245_tetroid.IReactable;
import _1245_tetroid.ReactEvents;


/*******************************************************************************
 * Instance třídy {@code GameControl} představujeherní ovládání.
 * Třída je klávesovým posluchačem aplikace.
 *s
 * @author  David KRONEISL
 * @version 1.00 — 06/2013
 */
public class GameControl implements IGameControl, KeyListener
{
//== CONSTANT CLASS ATTRIBUTES =================================================

     private static final GameControl INSTANCE = new GameControl();

//== VARIABLE CLASS ATTRIBUTES =================================================

     private final Collection<IReactable> reactants = new ArrayList<>();

//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

     /**************************************************************************
      * Tovární metoda vrací odkaz na jedináčka
      *
      * @return odkaz na jedináčka
      */
     public static GameControl getInstance(){
        return INSTANCE;
     }


     /***************************************************************************
     * Privátní konstruktor zabranující vytvoření instance
     */
      private GameControl() {}


//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================


    /***************************************************************************
     * Přidá reactant do kolekce
     *
     * @param reactant reactant
     */
    @Override
    public void addReactant(IReactable reactant)
    {
        reactants.add(reactant);
    }


    /***************************************************************************
     * Odebere reactanc z kolekce
     *
     * @param reactant reactant
     */
     @Override
    public void removeReactant(IReactable reactant)
    {
        reactants.remove(reactant);
    }


    /***************************************************************************
     * Událost pri stiknu klávesy
     *
     * @param e klávesa
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_UP: notice(ReactEvents.UpEvent); //up
                        break; //nebo break
            case KeyEvent.VK_DOWN: ; //down
                        break;
            case KeyEvent.VK_LEFT: notice(ReactEvents.LeftEvent); //left
                        break;
            case KeyEvent.VK_RIGHT: notice(ReactEvents.RightEvent); //right
                        break;
            case KeyEvent.VK_W: notice(ReactEvents.Rotate); //w
                        break;
            case KeyEvent.VK_S: notice(ReactEvents.MoveDown); //s
                        break;
            case KeyEvent.VK_A: notice(ReactEvents.MoveLeft); //a
                        break;
            case KeyEvent.VK_D: notice(ReactEvents.MoveRight); //d
        }
    }


    /***************************************************************************
     * Oznámí posluchačům, že došlo k události
     *
     * @param reactEvent
     */
    public void notice(ReactEvents reactEvent) {
        for (IReactable reactable : reactants) {
            reactable.react(reactEvent);
        }
    }

    @Override
    public void keyReleased(KeyEvent e){/*Unsupported*/}

    @Override
    public void keyTyped(KeyEvent e) {/*Unsupported*/}


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
//        GameControl inst = new GameControl();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}







