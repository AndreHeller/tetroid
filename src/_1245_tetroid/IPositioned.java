/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package _1245_tetroid;

import java.awt.Color;
import java.awt.Point;

/**
 * Třídy implementující toto rozhraní umí vrátit svou pozici
 *
 * @author Martin
 */
public interface IPositioned 
{     
    
    /***************************************************************************
     * Vratí pozici instance třídy implementující IPositioned.
     *
     * return pozice
     */
     public abstract Point getPosition();
     
     /**************************************************************************
      * Vrátí barvu instance třídy implementující IPositioned.
      * 
      * @return barva
      */
     public abstract Color getColor();
}
