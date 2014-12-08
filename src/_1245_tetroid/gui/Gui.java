/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.gui;

import _1245_tetroid.IGUI;
import _1245_tetroid.IGame;
import _1245_tetroid.game.Game;
import _1245_tetroid.graphic.Graphic;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import static _1245_tetroid.Constants.*;
import static _1245_tetroid.GameTexts.*;
import javax.swing.JOptionPane;


/*******************************************************************************
 * instance třídy {@code Gui} představuje uživatelské rozhraní aplikace.
 *
 * @author  André HELLER
 * @version 3.00 — 06/2013
 */
public class Gui extends JFrame implements IGUI
{
//== CONSTANT CLASS ATTRIBUTES =================================================

//    /** Jediná vytvoření instance jedináčka */
//    private static final Gui SINGLETON = new Gui();

    /** Šířka framu */
    private static final int FRAME_WIDTH = 570;


    /** Výška framu */
    private static final int FRAME_HEIGHT = ((yBricks+2)*yBrickSize) + 74;

    /** URL ikony hlavního okna */
    private static final URL iconTetroidUrl = Graphic.class.getResource("icon.png");


//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    /** Inicializace hry */
    private final IGame game = Game.getInstance();


    private final JMenuBar menuBar = new JMenuBar();

    private final MenuGame menuGame = new MenuGame(this);

    private final MenuHelp menuHelp = new MenuHelp(this);



    private final JPanel logoPanel = new JPanel();

    private final JPanel mapPanel = game.getMap();

    private final JPanel centralPanel = new JPanel();

    private final JPanel infoPanel = new JPanel();



    private final JPanel tetrisPanel = new JPanel();

    private final JLabel tetrisTitle = new JLabel("Tetris");

    private final JPanel tetrisContentPanel = new JPanel();


    private final JPanel tetrisSpeedPanel = new JPanel();

    private final JLabel tetrisSpeedText = new JLabel("Speed: ");

    private final JLabel tetrisSpeed = new JLabel();


    private final JPanel tetrisNextShapePanel = new JPanel();

    private final JLabel tetrisNextShape = new JLabel();



    private final JPanel arkanoidPanel = new JPanel();

    private final JLabel arkanoidTitle = new JLabel("Arkanoid");

    private final JPanel arkanoidContentPanel = new JPanel();


    private final JPanel arkanoidSpeedPanel = new JPanel();

    private final JLabel arkanoidSpeedText = new JLabel("Speed: ");

    private final JLabel arkanoidSpeed = new JLabel();


    private final JPanel arkanoidLivesPanel = new JPanel();

    private final JLabel arkanoidLivesText = new JLabel("Lives: ");

    private final JLabel arkanoidLives = new JLabel();



    private final JPanel gameInfoContentPanel = new JPanel();

    private final JPanel gameInfoPanel = new JPanel();


    private final JPanel gameInfoMapTimerPanel = new JPanel();

    private final JLabel mapTimerText = new JLabel("Rychlost hry: ");

    private final JLabel mapTimer = new JLabel();


    private final JPanel gameInfoCleanPercentPanel = new JPanel();

    private final JLabel cleanPercentText = new JLabel("Vyčištěno: ");

    private final JLabel cleanPercent = new JLabel();


//== VARIABLE INSTANCE ATTRIBUTES ==============================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

//    public static Gui getInstance(){
//        return SINGLETON;
//    }


    /***************************************************************************
     * Konstruktor volá rodičovský konstuktor JFramu
     */
    public Gui()
    {
        super();

        setFrame();

        createMenu();

        createTetrisPanel();

        createArkanoidPanel();

        createGameInfoPanel();

        buildMainPanels();

        this.pack();
        this.setVisible(true);

        addKeyListener(game.getGameControl());
    }

//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrací URL ikony aplikace pro případné dalsí použití JoptionPanu apod.
     *
     * @return Url ikony okna
     */
    public URL getIconUrl(){
        return iconTetroidUrl;
    }


    /***************************************************************************
     * Vrací odkaz na spuštenou hru
     *
     * @return spuštená hra
     */
    public IGame getGame(){
        return game;
    }


//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Inicializační metoda. Odstartuje hru a zvidtieln hlavní panely.
     */
    public void initialize(){

        game.setGui(this);
        //Odstartuje hru
        game.start();

        //Zviditelní oba dva hlavní panely
        centralPanel.setVisible(true);
        infoPanel.setVisible(true);


    }


    /***************************************************************************
     * Informace že někdo vyhrál a hra končí.
     *
     * @param winner
     */
    @Override
    public void infoGameEnd(String winner)
    {
        switch(winner){
            case "tetris":
                JOptionPane.showMessageDialog(this, TETRIS_WIN, END_OF_GAME, -1);
                break;

            case "arkanoid" :
                JOptionPane.showMessageDialog(this, ARKANOID_WIN, END_OF_GAME, -1);
                break;

            default :
        }
        System.exit(0);
    }


    /***************************************************************************
     * Upozorní GUI, že se změnila rychlost Arkanoidu.
     *
     */
    @Override
    public void infoArkSpeedChanged()
    {
        arkanoidSpeed.setText(game.getArkSpeed()+"");
    }


    /***************************************************************************
     * Upozorní GUI, že se změnila rychlost tetrisu.
     *
     */
    @Override
    public void infoTetSpeedChanged()
    {
        tetrisSpeed.setText(game.getTetSpeed()+"");
    }


    /***************************************************************************
     * Upozorní GUI, že se změnil počet životů Arkanoidu.
     *
     */
    @Override
    public void infoArkLivesChanged()
    {
        arkanoidLives.setText(game.getArkLives()+"");
    }


    /***************************************************************************
     * Upozorní GUI, že se změnil následující tvar.
     *
     */
    @Override
    public void infoNextShapeChanged()
    {

        switch(game.getNextShape()){
            case "ShapeI" : tetrisNextShape.setIcon(new ImageIcon(Graphic.class.getResource(game.getNextShape()+ ".png"))); break;
            case "ShapeJ" : tetrisNextShape.setIcon(new ImageIcon(Graphic.class.getResource(game.getNextShape()+ ".png"))); break;
            case "ShapeL" : tetrisNextShape.setIcon(new ImageIcon(Graphic.class.getResource(game.getNextShape()+ ".png"))); break;
            case "ShapeO" : tetrisNextShape.setIcon(new ImageIcon(Graphic.class.getResource(game.getNextShape()+ ".png"))); break;
            case "ShapeS" : tetrisNextShape.setIcon(new ImageIcon(Graphic.class.getResource(game.getNextShape()+ ".png"))); break;
            case "ShapeT" : tetrisNextShape.setIcon(new ImageIcon(Graphic.class.getResource(game.getNextShape()+ ".png"))); break;
            case "ShapeZ" : tetrisNextShape.setIcon(new ImageIcon(Graphic.class.getResource(game.getNextShape()+ ".png"))); break;
            default : tetrisNextShape.setText(game.getNextShape());
        }
    }


    /***************************************************************************
     * Upozorní GUI, že se změnil čas do dalšího posunu mapy.
     *
     */
    @Override
    public void infoMapTimerChanged()
    {
        mapTimer.setText(game.getMapTimer()+"");
    }


    /***************************************************************************
     * Upozorní GUI, že se změnila procentuální hodnota vyčištěných políček
     *
     */
    @Override
    public void infoCleanPercentChanged()
    {
        cleanPercent.setText(game.getCleanPercent()+"%");
    }


//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================

    /***************************************************************************
     * Nastaví základní vlastnosti hlavního okna aplikce
     */
    private void setFrame()
    {
        this.setTitle(game.getGameName());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        // Vystředění oken
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int widthCenter = (int) (width/2)-(FRAME_WIDTH/2);
        int heightCenter = (int) (height/2)-(FRAME_HEIGHT/2);

        this.setLocation(widthCenter, heightCenter);
        this.setResizable(false);
//        setUndecorated(true);

        //Nastaví oknu ikonu
        if(iconTetroidUrl != null){
            this.setIconImage(new ImageIcon(iconTetroidUrl).getImage());
        }
    }


    /***************************************************************************
     * Přidá do hlavního aplikačního okna Menu
     */
    private void createMenu()
    {
        setJMenuBar(menuBar);
        menuBar.add(menuGame);
        menuBar.add(menuHelp);
    }


    /***************************************************************************
     * Vytvoří panel informací pro tetris
     */
    private void createTetrisPanel()
    {
        tetrisSpeedPanel.add(tetrisSpeedText);
        tetrisSpeedPanel.add(tetrisSpeed);

        tetrisNextShapePanel.add(tetrisNextShape);

        tetrisPanel.setLayout(new BoxLayout(tetrisPanel, BoxLayout.PAGE_AXIS));
        tetrisPanel.add(tetrisTitle);
        tetrisPanel.add(tetrisSpeedPanel);
        tetrisPanel.add(tetrisNextShapePanel);

        tetrisContentPanel.add(tetrisPanel);
    }


    /***************************************************************************
     * Vytvoří panel informací pro arkanoid
     */
    private void createArkanoidPanel()
    {
        arkanoidSpeedPanel.add(arkanoidSpeedText);
        arkanoidSpeedPanel.add(arkanoidSpeed);

        arkanoidLivesPanel.add(arkanoidLivesText);
        arkanoidLivesPanel.add(arkanoidLives);

        arkanoidPanel.setLayout(new BoxLayout(arkanoidPanel, BoxLayout.PAGE_AXIS));
        arkanoidPanel.add(arkanoidTitle);
        arkanoidPanel.add(arkanoidSpeedPanel);
        arkanoidPanel.add(arkanoidLivesPanel);

        arkanoidContentPanel.add(arkanoidPanel);
    }


    /***************************************************************************
     * Vytvoří panel informací pro hru
     */
    private void createGameInfoPanel()
    {
        gameInfoMapTimerPanel.add(mapTimerText);
        gameInfoMapTimerPanel.add(mapTimer);

        gameInfoContentPanel.add(cleanPercentText);
        gameInfoContentPanel.add(cleanPercent);

        gameInfoPanel.setLayout(new BoxLayout(gameInfoPanel, BoxLayout.PAGE_AXIS));
        gameInfoPanel.add(gameInfoMapTimerPanel);
        gameInfoPanel.add(gameInfoCleanPercentPanel);

        gameInfoContentPanel.add(gameInfoPanel);
    }


    /***************************************************************************
     * Seskládá panely dohromady a přidá je do hlavního framu
     */
    private void buildMainPanels()
    {
        infoPanel.setLayout(new GridLayout(3, 1));
        infoPanel.add(tetrisContentPanel);
        infoPanel.add(arkanoidContentPanel);
        infoPanel.add(gameInfoContentPanel);

        centralPanel.add(logoPanel, BorderLayout.NORTH);
        centralPanel.add(mapPanel, BorderLayout.CENTER);

        this.add(centralPanel,BorderLayout.WEST);
        this.add(infoPanel, BorderLayout.CENTER);

        //Zneviditelní hlavní panely, dokud nebude gui inicializováno pomocí metody initialize()
        infoPanel.setVisible(false);
        centralPanel.setVisible(false);
    }


//== EMBEDDED TYPES AND INNER CLASSES ==========================================
//== TESTING CLASSES AND METHODS ===============================================
//
//    /*************************************************************************
//     * Testing method.
//     */
//    public static void test()
//    {
//        Gui inst = new Gui();
//        inst.setVisible(true);
//
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }
}
