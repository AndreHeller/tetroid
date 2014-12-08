/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.game;

import java.awt.Color;






/*******************************************************************************
 * Instance třídy {@code Brick} představují jednodtlivé cihličky na herní mapě.
 *
 * @author  Martin KOZÁK
 */
public class Brick
{


    private boolean solid;

    private int endurance;

    private Color color;


//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     * @param solid Průchodnost
     * @param endurance výdrž
     * @param color Barva
     */
    public Brick(boolean solid, int endurance, Color color)
    {
        this.solid = solid;
        this.endurance = endurance;
        this.color = color;
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí průchodnost cihličky
     *
     * @return průchodnost cihličky
     */
    public boolean isSolid(){
        return solid;
    }

    /***************************************************************************
     * Vrátí barvu cihličky
     *
     * @return barva cihličky
     */
    public Color getColor(){
        return color;
    }

    /***************************************************************************
     * Nastaví Průchodnost cihličky
     *
     * @param solid průchodnost cihličky
     */
    public void setSolid(boolean solid){
        this.solid = solid;
    }

    /***************************************************************************
     * Nastaví endurance cihličky
     *
     * @param endurance výdrž cihly
     */
    public void setEndurance(int endurance){
        this.endurance = endurance;        
    }

    /***************************************************************************
     * Zvedne odolnost cihly
     */
    public void harden()
    {
        endurance++;
    }
    
    /***************************************************************************
     * Nastaví barvu cihličky
     *
     * @param color Barva cihličky
     */
    public void setColor(Color color){
        this.color = color;
    }

//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Metoda sníží enduracne cihličky.
     * Pokud je endurance moc nízká cihličku zničí
     * 
     * @return vrátí true, pokud je cohlička zničena, false pokud přežije
     */
    public boolean hitKill()
    {
      if (endurance > 0)
      {
        endurance--;
      }
      if (endurance == 0){
          this.setSolid(false);          
          return true;
      }
      return false;
    }
   
    
    /***************************************************************************
     * Změní kostku na kostku jinou.
     * 
     * @param brick Kostka, ve kterou se změní
     */
    void changeToBrick(Brick brick) 
    {
        this.color = brick.color;
        this.endurance = brick.endurance;
        this.solid = brick.solid;
    }
    
    
}
