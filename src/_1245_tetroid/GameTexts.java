/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid;



/*******************************************************************************
 * Třída {@code GameInfo} sloužíjako úschovna základních informací hry - Verze,
 * Název, atd.
 *
 * @author  André HELLER
 * @version 1.00 — 06/2013
 */
public class GameTexts
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    /** Názvy položek hlavního menu */
    public final static String
        MENU_GAME = "Hra",
        MENU_HELP = "Nápověda";

    /** Mnemonici názvů položek hlavního menu */
    public final static char
        MENU_GAME_MNE = 'H',
        MENU_HELP_MNE = 'N';

    public final static String
        MENU_GAME_NEW_GAME = "Nová hra",
        MENU_GAME_EXIT = "Ukončit";

    public final static char
        MENU_GAME_NEW_GAME_MNE = 'N',
        MENU_GAME_EXIT_MNE = 'U';

    public final static String
        MENU_HELP_ABOUT = "O aplikaci";

    public final static char
        MENU_HELP_ABOUT_MNE = 'O';

    public final static String
        TETRIS_WIN = "Hráč tetrisu vítězí!\nArkanoid byla vždycky hra pro lůzry",
        ARKANOID_WIN = "Hráč arkanoidu vítězí!\nTetris byla vždycky hra pro lůzry",
        END_OF_GAME = "Konec hry!";

//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     *
     */
    private GameTexts()
    {
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        GameTexts inst = new GameTexts();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
