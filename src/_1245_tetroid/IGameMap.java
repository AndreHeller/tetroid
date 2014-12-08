package _1245_tetroid;

import java.awt.Point;

/*******************************************************************************
 * Třída implementující {@code IGameMap} realizuje zobrazování mapy
 * a práci s mapou.
 *
 * @author Tetroids
 */
public interface IGameMap
{
// Globální --------------------------------------------------------------------

    /***************************************************************************
     * Master se zeptá se instance třídy implementující IGameMap, jestli je
     * pozice volná.
     *
     * @param position dotazovaná pozice
     *
     * @return true - pozice je volná, false - pozice není volná
     */
    public abstract boolean isFree(Point position);

    /***************************************************************************
     * Master se zeptá se instance třídy implementující IGameMap, jestli je
     * cihla na dané pozici volná.
     *
     * @param brickX x-ová souřadnice dotazované cihly
     * @param brickY y-ová souřadnice dotazované cihly
     *
     *
     * @return true - cihla je volná, false - cihla není volná
     */
    public abstract boolean isFree(int brickX, int brickY);

    /*
    Důležitý rozdíl obou dvou metod: První petoda je otázkou na přímou pomocí,
    pixelové pozice, dotaz je směřován na přesnou souřadnici,
    ze které je určena cihla.

    Druhá metoda je dotaz na konkrétní cihlu.

    Jinými slovy: První metoda pracuje s reálnou velikostí cihly,
    zatímco druhá s její pozicí oproti ostatním sihlám.
    */

    /***************************************************************************
     *  Zjistí aktuální periodu mapy
     * 
     * @return perioda mapy
     */
    public abstract int getMapPeriod();
    
// Arkanoid --------------------------------------------------------------------

    /***************************************************************************
     * Zeptá se instance třídy implementující IGameMap, z jaké stany
     * došlo k zásahu.
     *
     * @param position pozice zásahu
     *
     * @return číslo dle strany zásahu
     */
    public abstract int getHitDirection(Point position, IPositioned ball);


    /***************************************************************************
     * Informuje instanci třídy implementující IGameMap, že došlo k pohybu
     * instance třídy implementující IPositioned.
     *
     * @param object objekt který se pohnul a je jej třeba překreslit
     */
    public abstract void infoMove(IPositioned object);


    /***************************************************************************
     * Informuje instanci třídy implementující IGameMap,že se změnila délka lodi
     *
     * @param newLenght nová délka lodi
     */
    public abstract void infoShipLengthChanges(int newLenght);


    /***************************************************************************
     * Informuje instanci třídy implementující IGameMap, že má vymazat míček
     * nebo power up
     *
     * @param object mazaný objekt
     */
    public abstract void infoLost(IPositioned object);


    /***************************************************************************
     * Informuje instanci třídy implementující IGameMap, že má vytvořit míček
     *
     * @param object vytvářený objekt
     */
    public abstract void infoBallCreate(IPositioned ball);


    /***************************************************************************
     * Informuje instanci třídy implementující IGameMap, že má vytvořit power up
     *
     * @param object vytvářený objekt
     */
    public abstract void infoPowerUpCreate(IPositioned powerUp);


    /***************************************************************************
     * Vytvoří loď
     *
     * @param ship
     */
    public abstract void createShip(IPositioned ship);


// Tetris --------------------------------------------------------------------

    /***************************************************************************
     * Informuje instanci třídy implementující IGameMap,
     * že došlo k dopadu tetris bloku.
     *
     */
    public abstract void infoImpact();

     /***************************************************************************
     * Překrreslí blok tetrisu na nové pozice
     *
     * @param Shape blok tetrisu
     */
    public abstract void refreshTetPosition(IShape shape);

}
