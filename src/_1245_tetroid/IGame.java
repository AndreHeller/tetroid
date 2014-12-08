package _1245_tetroid;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.JPanel;

/**
 * Třída implementující {@code IGame} se stará o hlavní chod hry.
 *
 * @author Tetroids
 */
public interface IGame
{
    /***************************************************************************
     * Spustí hru
     */
    public abstract void start();

    /***************************************************************************
     * Vypne hru
     */
    public abstract void stop();

    /***************************************************************************
     * Zjistí, zda hra probíhá.
     */
    public abstract boolean isAlive();


    /***************************************************************************
     * Vrátí verzi hry
     *
     * @return Aktuální verzi hry
     */
    public abstract String getGameVersion();


    /***************************************************************************
     * Vrátí název hry
     *
     * @return Název hry
     */
    public abstract String getGameName();


    /***************************************************************************
     * Vrátí autory hry
     *
     * @return Autoři hry
     */
    public abstract String getGameAuthors();


    /***************************************************************************
     * Vrátí odkaz an gui, se kterým hra spolupracuje
     *
     * @return odkaz na gui
     */
    public abstract IGUI getGameGui();


    /***************************************************************************
     * Vrátí obrázek mapy hry
     *
     * @return mapa hry
     */
    public abstract JPanel getMap();

    /***************************************************************************
     * Vrátí odkaz na třídu zajištující ovládání hry pomocí kláves
     *
     * @return odkaz na třídu
     */
    public abstract IGameControl getGameControl();
    
    
    /***************************************************************************
     * Vrátí odkaz na třídu zajištující ovládání hry pomocí kláves
     *
     * @return odkaz na třídu
     */
    public abstract IGameMap getGameMap();
    

    public abstract IArkMaster getArkMaster();
    
    public abstract ITetMaster getTetMaster();
    
    
    /***************************************************************************
     * Vrátí rychlost arkanoid části.
     *
     * @return rychlost arkanoid části
     */
    public abstract int getArkSpeed();

    /***************************************************************************
     * Vrátí rychlost tetris části.
     *
     * @return tetris části
     */
    public abstract int getTetSpeed();

    /***************************************************************************
     * Vrátí počet životů arkanoid části
     *
     * @return počet životů arkanoid části
     */
    public abstract int getArkLives();

    /***************************************************************************
     * Vrátí obrázek následujícího tvaru
     *
     * @return obrázek následujícího tvaru tetrisu
     */
    public abstract String getNextShape();


    /***************************************************************************
     * Vrátí čas do dalšího posunu mapy.
     *
     * @return čas do posunu mapy.
     */
    public abstract int getMapTimer();


    /***************************************************************************
     * Vrátí procento čistého území.
     *
     * @return Procento čistého území
     */
    public abstract int getCleanPercent();

    /***************************************************************************
     * Informace o tom, že se změnil počet životů.
     */
    public abstract void infoLifeChange();


    /***************************************************************************
     * Nastaví herní gui
     */
    public abstract void setGui(IGUI gui);

}
