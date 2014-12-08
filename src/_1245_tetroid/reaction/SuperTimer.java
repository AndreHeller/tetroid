/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.reaction;

import _1245_tetroid.IGameTimer;
import _1245_tetroid.IReactable;
import _1245_tetroid.ReactEvents;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;



/*******************************************************************************
 * Intance třídy {@code SuperTimer} představují jednotlivé herní timery
 *
 * @author  André HELLER & Tomáš KUMSTA
 * @version 3.00 — 06/2013
 */
public class SuperTimer extends Timer implements IGameTimer
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    public static final Collection<IReactable> reactants = new ArrayList<>();

//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    private final MyTask task;

//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private int period;

    private int delay;

//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Konstruktor bez parametru delay. Bez zpoždění
     *
     * @param reactant
     * @param event
     * @param period
     */
    public SuperTimer(IReactable reactant, ReactEvents event, int period)
    {
        this(reactant, event, period, 0);
    }

    /***************************************************************************
     * Konstruktor s nastavitelným zpožděním --> delay
     *
     * @param reactant reagující objekt
     * @param event herní událost
     * @param period časová perioda
     * @param delay zpoždení
     */
    public SuperTimer(IReactable reactant, ReactEvents event, int period, int delay)
    {
        super();
        reactants.add(reactant);
        this.task = new MyTask(reactant, event);
        this.period = period;
        this.delay = delay;
    }


//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    @Override
    public void setPeriod(int period)
    {
        this.period = period;
    }


    @Override
    public int getPeriod()
    {
        return period;
    }


//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================

//    /***************************************************************************
//     *
//     *
//     * @return
//     */
//    public IReactable getReactant()
//    {
//        return reactable;
//    }


    /***************************************************************************
     *
     */
    @Override
    public void initialize() {
        this.schedule(task, delay, period);
    }


//== EMBEDDED TYPES AND INNER CLASSES ==========================================

    private class MyTask extends TimerTask {

        private IReactable reactant;
        private ReactEvents event;

        private MyTask(IReactable reactant, ReactEvents event)
        {
            this.reactant = reactant;
            this.event = event;
        }

        @Override
        public void run() {
            reactant.react(event);
        }
    }

//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        ArcTimer inst = new ArcTimer();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
