/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.game;


import _1245_tetroid.IArkMaster;
import _1245_tetroid.IGame;
import _1245_tetroid.IGameControl;
import _1245_tetroid.IGameTimer;
import _1245_tetroid.ITetMaster;
import _1245_tetroid.arkanoid.ArkMaster;
import _1245_tetroid.tetris.TetrisMaster;
import javax.swing.JPanel;


import static _1245_tetroid.GameInfo.*;
import _1245_tetroid.IGUI;
import _1245_tetroid.IGameMap;
import _1245_tetroid.reaction.GameControl;


/*******************************************************************************
 * Hlavní třída hry.
 *
 * @author  Martin KOZÁK
 */
public class Game implements IGame
{
//== CONSTANT CLASS ATTRIBUTES =================================================

    private static final Game INSTANCE = new Game();

//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    private final GameMap gameMap = new GameMap();



    private final IArkMaster arkMaster = new ArkMaster(this);

    private final ITetMaster tetMaster = new TetrisMaster(this);

    private /*final*/ IGameTimer gameTimer;// = new GameTimer();


    private final IGameControl gameControl = GameControl.getInstance();

    //== VARIABLE INSTANCE ATTRIBUTES ==============================================

    private boolean alive;

    private IGUI gui;

//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vrátí herní instanci
     *
     * @return jediná instance hry
     */
    public static Game getInstance(){
        return INSTANCE;
    }


    /***************************************************************************
     * Privátní konstruktor zabraňující vytvoření instance
     */
    private Game(){/* ... */}


//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================


    /***************************************************************************
     * Vrátí název hry
     *
     * @return Aktuální verzi hry
     */
    @Override
    public String getGameVersion(){
        return GAME_VERSION;
    }


    /***************************************************************************
     * Vrátí název hry
     *
     * @return Název hry
     */
    @Override
    public String getGameName(){
        return GAME_NAME;
    }


    /***************************************************************************
     * Vrátí autory hry
     *
     * @return Autoři hry
     */
    @Override
    public String getGameAuthors(){
        return GAME_AUTHORS;
    }


    /***************************************************************************
     * Vrátí odkaz an gui, se kterým hra spolupracuje
     *
     * @return odkaz na gui
     */
    @Override
    public IGUI getGameGui(){
        return gui;
    }

    /***************************************************************************
     * Vrátí obrázek mapy hry
     *
     * @return mapa hry
     */
    @Override
    public JPanel getMap() {
        return gameMap.getMapImage();
    }

    /***************************************************************************
     * Vrátí odkaz na gameControl
     *
     * @return odkaz na GameCobntrol
     */
    @Override
    public IGameControl getGameControl()
    {
        return gameControl;
    }

    /***************************************************************************
     * Vrátí odkaz na správce mapy.
     *
     * @return Správce mapy
     */
    @Override
    public IGameMap getGameMap()
    {
        return gameMap;
    }


    @Override
    public IArkMaster getArkMaster()
    {
        return arkMaster;
    }

    @Override
    public ITetMaster getTetMaster()
    {
        return tetMaster;
    }



    /***************************************************************************
     * Vrátí rychlost arkanoid části.
     *
     * @return rychlost arkanoid části
     */
    @Override
    public int getArkSpeed() {
        return arkMaster.getArkPeriod();
    }


    /***************************************************************************
     * Vrátí rychlost tetris části.
     *
     * @return tetris části
     */
    @Override
    public int getTetSpeed() {
        return tetMaster.getTetPeriod();//gameTimer.getTetPeriod();
    }


    /***************************************************************************
     * Vrátí počet životů arkanoid části
     *
     * @return počet životů arkanoid části
     */
    @Override
    public int getArkLives() {
        return arkMaster.getLives();
    }


    /***************************************************************************
     * Vrátí obrázek následujícího tvaru
     *
     * @return obrázek následujícího tvaru tetrisu
     */
    @Override
    public String getNextShape() {
        return tetMaster.getNextShape();
    }


    /***************************************************************************
     * Vrátí čas do dalšího posunu mapy.
     *
     * @return čas do posunu mapy.
     */
    @Override
    public int getMapTimer() {
        return gameMap.getMapPeriod();
    }


    /***************************************************************************
     * Vrátí procento čistého území.
     *
     * @return Procento čistého území
     */
    @Override
    public int getCleanPercent()
    {
        int percent = gameMap.getPercentage();
        if (percent > 85)
        {
            gui.infoGameEnd("arkanoid");
        }
        return percent;
    }


    /***************************************************************************
     * Zjistí, zda hra probíhá.
     */
    @Override
    public boolean isAlive()
    {
       return alive;
    }


    /***************************************************************************
     * Nastaví herní gui
     *
     * @param gui
     */
    @Override
    public void setGui(IGUI gui){
        this.gui = gui;
    }


//== OTHER NON-PRIVATE INSTANCE METHODS ========================================


    /***************************************************************************
     * Spustí hru
     */
    @Override
    public void start()
    {
        gameMap.inicialize();
        alive = true;

        arkMaster.initialize();
        tetMaster.initialize();

        gui.infoArkLivesChanged();
        gui.infoArkSpeedChanged();
        gui.infoTetSpeedChanged();
        gui.infoMapTimerChanged();
        gui.infoCleanPercentChanged();
        gui.infoNextShapeChanged();
    }


    /***************************************************************************
     * Vypne hru
     */
    @Override
    public void stop() {
        alive = false;
    }

    /***************************************************************************
     * Informuje hru o změne životu arkanoidu
     */
    @Override
    public void infoLifeChange()
    {
        gui.infoArkLivesChanged();
        if (arkMaster.getLives() <= 0)
        {
            gui.infoGameEnd("tetris");
        }
    }


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
//        Game1 inst = new Game1();
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }




}
