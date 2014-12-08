/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package _1245_tetroid;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Martin
 */
public interface IShape
{
     public abstract Point getBrick1();

     public abstract Point getBrick2();

     public abstract Point getBrick3();

     public abstract Point getBrick4();

     public abstract Color getColor();

}
