package _1245_tetroid;


/**
 *Třída implementující {@code IGameTimer} realizuje
 * zasílání zpráv v závislosti na tikání časovače.
 *
 * @author Tetroids
 */
public interface IGameTimer
{
    /***************************************************************************
     * Inicializační metoda
     */
    public abstract void initialize();

    /***************************************************************************
     * Nastaví periodu pro Arkanoid.
     *
     */
    public abstract void setPeriod(int period);

    /***************************************************************************
     * Vratí velikost periody pro Arkanoid.
     *
     */
    public abstract int getPeriod();

//    /***************************************************************************
//     * Nastaví periodu pro Tetris.
//     *
//     */
//    public abstract void setTetPeriod(int period);
//
//    /***************************************************************************
//     * Vratí velikost periody pro Tetris.
//     *
//     */
//    public abstract int getTetPeriod();
//
//    /***************************************************************************
//     * Nastaví periodu pro Mapu.
//     *
//     */
//    public abstract void setMapPeriod(int period);
//
//    /***************************************************************************
//     * Vratí velikost periody pro Mapu.
//     *
//     */
//    public abstract int getMapPeriod();
}
