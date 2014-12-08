package _1245_tetroid;

import java.awt.event.KeyListener;



/**
 * Třída implementující {@code IGameControl} realizuje
 * zasílání zpráv v závislosti na stiskách kláves.
 *
 * @author Tetroids
 */
public interface IGameControl extends KeyListener
{
   /***************************************************************************
    * Přidá třídu implementující IReactable jako posluchače třídy implementující
    * rozhraní IGameControl.
    *
    * @param reactant přidávaný posluchač
    */
    public abstract void addReactant(IReactable reactant);


    /***************************************************************************
     * Odebere třídu implementující IReactable z posluchačů třídy implementující
     * rozhraní IGameControl.
     *
     * @param reactant odebíraný posluchač
     */
    public abstract void removeReactant(IReactable reactant);


}
