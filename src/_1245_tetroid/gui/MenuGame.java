/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import static _1245_tetroid.GameTexts.*;


/*******************************************************************************
 * Instance třídy {@code MenuGame} představuje GameMenu aplikace.
 * Menu má tyto položky:
 * <ul>
 * <li>Nová hra</li>
 * <li>Ukončit</li>
 * </ul>
 *
 * @author  André HELLER
 * @version 2.00 — 06/2013
 */
public class MenuGame extends JMenu
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    /** Položka nová hra  */
    private JMenuItem newGame;

    /** Položka ukončit */
    private JMenuItem exit;

    /** rodičovské gui */
    private Gui gui;


//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Konstruktor používá gui parametr, aby mohl volat inicializační metodu
     *
     * @param gui rodičovské gui
     */
    public MenuGame(Gui gui)
    {
        super(MENU_GAME);
        setMnemonic('H');

        this.gui = gui;

        createNewGameItem();
        createExitItem();
    }


//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================

    /***************************************************************************
     * Vytvoří položku, která zahájí novou hru.
     */
    private void createNewGameItem()
    {
        newGame = new JMenuItem(MENU_GAME_NEW_GAME);
        newGame.setMnemonic(MENU_GAME_NEW_GAME_MNE);
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                gui.initialize();
                newGame.setEnabled(false);
            }
        });
        add(newGame);
    }


    /***************************************************************************
     * Vytvoří položku, které ukončí aplikaci
     */
    private void createExitItem()
    {
        exit = new JMenuItem(MENU_GAME_EXIT);
        exit.setMnemonic(MENU_GAME_EXIT_MNE);
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        add(exit);
    }


//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        MenuGame inst = new MenuGame();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
