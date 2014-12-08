/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package _1245_tetroid.game;

import _1245_tetroid.IPositioned;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Collection;
import javax.swing.JPanel;

import static _1245_tetroid.Constants.*;

/**
 * Pomocná třída pro vykreslování mapy.
 *
 * @author Martin
 */
public class MapPanel extends JPanel
{
    /** Počáteční políčková šířka aktivní plochy plátna. */
    private static final int ŠÍŘKA = xBrickSize*(xBricks+2);
    /** Počáteční políčková výška aktivní plochy plátna. */
    private static final int VÝŠKA = yBrickSize*(yBricks+2);   

    Image img;

    Graphics graphics;

    //Odkaz na rodičovskou třídu
    private final GameMap gameMap;


    /***************************************************************************
     * Klasická metoda getInstance()
     */
    MapPanel(GameMap gameMap)
    {
        setLayout(null);
        this.gameMap = gameMap;
        this.setPreferredSize(new Dimension(ŠÍŘKA,VÝŠKA));
    }  

    /***************************************************************************
     * Aby třída fungovala, je nutné překrýt tuto metodu od předka.
     *
     */
    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(img, 0, 0, null);
    }


    /***************************************************************************
     * Metoda která překreslí plátno. Veškeré potřebné údají si získá od GameMap.
     * Nejdřív nakreslí hrubou mapu, pak Míče, PowerUpy a nakonec Loď
     */
    public void refresh()
    {        
        repaintMap(gameMap.getMap());
        repaintBalls(gameMap.getBalls());
        repaintPowerUps(gameMap.getPowerUps());

        repaintShip(gameMap.getShip(), gameMap.getShipSize());
        this.repaint();
    }


    /***************************************************************************
     * Inicializační metoda
     */
    public void inicialize()
    {
        img = this.createImage(ŠÍŘKA, VÝŠKA);
        graphics = img.getGraphics();
    }


    ////////////////////////////
    //SOUKROMÉ KRESLÍCÍ METODY//
    ////////////////////////////

    private void repaintMap(Brick[][] brickmap)
    {
        synchronized(this)
        {
            for (int j = 0; j<yBricks+2; j++)
            {
                for (int i = 0; i<xBricks+2; i++)
                {
                    graphics.setColor(brickmap[i][j].getColor());
                    graphics.fillRect(xBrickSize*i, yBrickSize*j,
                                            xBrickSize, yBrickSize);
                    if (!brickmap[i][j].getColor().equals(BACKGROUND))
                    {
                        graphics.setColor(Color.BLACK);
                        graphics.drawRect(xBrickSize*i, yBrickSize*j,
                                            xBrickSize, yBrickSize);
                    }                    
                }
            }
        }               
    }
        
    private void repaintBalls(Collection<IPositioned> balls)
    {
        for (IPositioned ball: balls)
        {
            graphics.setColor(ball.getColor());
            graphics.fillOval(ball.getPosition().x, ball.getPosition().y, 8, 8);
            graphics.setColor(Color.BLACK);
            graphics.drawOval(ball.getPosition().x, ball.getPosition().y, 8, 8);
        }
    }

    private void repaintPowerUps(Collection<IPositioned> powerUps)
    {
        for (IPositioned powerUp: powerUps)
        {            
            graphics.setColor(powerUp.getColor());
            graphics.fillRect(powerUp.getPosition().x, 
                              powerUp.getPosition().y, 16, 8);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(powerUp.getPosition().x,
                              powerUp.getPosition().y, 16, 8);
        }
    }


    private void repaintShip(IPositioned ship, int shipSize)
    {
        graphics.setColor(ship.getColor());
        graphics.fillRect(ship.getPosition().x,
                          ship.getPosition().y, shipSize, 8);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(ship.getPosition().x, 
                          ship.getPosition().y, shipSize, 8);        
    }



}