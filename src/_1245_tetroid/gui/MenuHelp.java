/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


import static _1245_tetroid.GameTexts.*;


/*******************************************************************************
 * Instance třídy {@code MenuHelp} představuje Nápovědní menu aplikace.
 * Menu má tyto položky:
 * <ul>
 * <li>O aplikaci</li>
 * </ul>
 *
 * @author  André HELLER
 * @version 2.00 — 06/2013
 */
public class MenuHelp extends JMenu
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================

    /** Položka o aplikaci */
    private JMenuItem about;

    /** Rodičovské gui */
    private Gui gui;


//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Konstuktor
     */
    public MenuHelp(Gui gui)
    {
        super(MENU_HELP);
        setMnemonic(MENU_HELP_MNE);

        this.gui = gui;

        createAboutItem();
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================

    /***************************************************************************
     * Vytvoří položku, která zobrazí info o aplikaci
     */
    private void createAboutItem()
    {
        about = new JMenuItem(MENU_HELP_ABOUT);
        about.setMnemonic(MENU_HELP_ABOUT_MNE);
        about.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(gui.getParent(), gui.getGame().getGameName().toUpperCase() + "\nVerze hry: " + gui.getGame().getGameVersion()+ "\n\nTvůrci hry:\n" + gui.getGame().getGameAuthors(), gui.getGame().getGameName(), -1, new ImageIcon(gui.getIconUrl()));
            }
        });
        add(about);
    }


//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        MenuHelp inst = new MenuHelp();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
