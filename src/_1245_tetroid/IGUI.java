/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid;


/*******************************************************************************
 * Instance interejsu {@code IGUI} rpředstavují rámec uživatelského rozhraní aplikace
 *
 * @author  Tetroids
 * @version 2.00 — 06/2013
 */
public interface IGUI
{
    /***************************************************************************
     *
     */
    public abstract void infoGameEnd(String winner);


    /***************************************************************************
     * Upozorní GUI, že se změnila rychlost Arkanoidu.
     *
     */
    public abstract void infoArkSpeedChanged();

    /***************************************************************************
     * Upozorní GUI, že se změnila rychlost tetrisu.
     *
     */
    public abstract void infoTetSpeedChanged();

    /***************************************************************************
     * Upozorní GUI, že se změnil počet životů Arkanoidu.
     *
     */
    public abstract void infoArkLivesChanged();

    /***************************************************************************
     * Upozorní GUI, že se změnil následující tvar.
     *
     */
    public abstract void infoNextShapeChanged();


    /***************************************************************************
     * Upozorní GUI, že se změnil čas do dalšího posunu mapy.
     *
     */
    public abstract void infoMapTimerChanged();


    /***************************************************************************
     * Upozorní GUI, že se změnila procentuální hodnota vyčištěných políček
     *
     */
    public abstract void infoCleanPercentChanged();
}
