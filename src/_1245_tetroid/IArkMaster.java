package _1245_tetroid;

import java.awt.Point;
import java.util.Collection;


/*******************************************************************************
 * Instance rozhraní {@code IArkMaster} slouží ke komunikaci se hrou a reakcím
 * arkanoidu na situace ve hře.
 *
 * @author    Tetroids
 */
public interface IArkMaster
{



    /***************************************************************************
     * Připravá hru do počáteční fáze
     */
    public abstract void initialize();


    /***************************************************************************
     * Vrací počet životů lodi.
     */
    public abstract int getLives();


    /***************************************************************************
    * Informace o úspěšném zničení kostky, je zde kvůli tvorbě powerupů
    */
    public abstract void infoBrickDestroy(Point position);


    /***************************************************************************
     * Zjistí aktuální periodu arkanoidu
     */
    public abstract int getArkPeriod();



///*****************************************************************************
// * Vrací kolekci míčků.
// */
//    public abstract Collection<IPositioned> getBallSet();
//
///*****************************************************************************
// * Vrací kolekci powerupů.
// */
//    public abstract Collection<IPositioned> getPowerUpSet();

///*******************************************************************************
// * Vrací pozici lodi.
// */
//    public abstract Point getShipPosition();








}
