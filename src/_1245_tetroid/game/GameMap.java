package _1245_tetroid.game;

import _1245_tetroid.IGameMap;
import _1245_tetroid.IPositioned;
import _1245_tetroid.IReactable;
import _1245_tetroid.IShape;
import _1245_tetroid.ReactEvents;

import _1245_tetroid.arkanoid.Ship;
import _1245_tetroid.tetris.ShapeI;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JPanel;

import static _1245_tetroid.Constants.*;
import _1245_tetroid.IGameTimer;
import _1245_tetroid.reaction.SuperTimer;




/*******************************************************************************
 * Instance třídy představují herní mapu
 *
 * @author  Martin KOZÁK
 * @version 0.00 — mm/20yy
 */
public class GameMap implements IGameMap, IReactable
{
//== CONSTANT CLASS ATTRIBUTES =================================================



//== VARIABLE CLASS ATTRIBUTES =================================================
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
    private final int brickSum = (xBricks) * (yBricks);


//== VARIABLE INSTANCE ATTRIBUTES ==============================================
    /***
     * Mapa kostek
     */
    private Brick[][] brickMap = new Brick[xBricks+2][yBricks+2];

    private IShape oldShape = new ShapeI(new Point(8,12));

    private int oneXPos   = oldShape.getBrick1().x;
    private int oneYPos   = oldShape.getBrick1().y;
    private int twoXPos   = oldShape.getBrick2().x;
    private int twoYPos   = oldShape.getBrick2().y;
    private int threeXPos = oldShape.getBrick3().x;
    private int threeYPos = oldShape.getBrick3().y;
    private int fourXPos  = oldShape.getBrick4().x;
    private int fourYPos  = oldShape.getBrick4().y;


    private int percentage;


    private Collection<IPositioned> balls = new ArrayList<>();

    private Collection<IPositioned> powerUps = new ArrayList<>();

    private IPositioned ship;

    private int shipLenght = 64;


    private final MapPanel mapPanel = new MapPanel(this);


    private final IGameTimer mapTimer;

//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Privátní konstruktor zabraňující vytvoření instance
     */
    GameMap()
    {
        this.mapTimer = new SuperTimer(this, ReactEvents.MapEvent ,20000, 3000);
    }




//== INSTANCE GETTERS AND SETTERS ==============================================

    //Metody pro práci s grafickou mapou

    Brick[][] getMap()
    {
        return brickMap;
    }

    Collection<IPositioned> getBalls()
    {
        return balls;
    }

    Collection<IPositioned> getPowerUps()
    {
        return powerUps;
    }

    IPositioned getShip()
    {
        return ship;
    }

    int getShipSize()
    {
        return shipLenght;
    }

    //////////////////////////




    /**************************************************************************
     * Vrátí obrázek mapy.
     *
     * @return obrázek mapy.
     *
     */
    JPanel getMapImage()
    {
        return mapPanel;
    }


    /***************************************************************************
     * Vrátí procento čistého území
     *
     * @return procento čistého území
     */
    int getPercentage()
    {
        this.percentage = calculatePercentage();
        return percentage;
    }


    /***************************************************************************
     *  Zjistí aktuální periodu mapy
     *
     * @return perioda mapy
     */
    @Override
    public int getMapPeriod()
    {
        return mapTimer.getPeriod();
    }


    /***************************************************************************
     * Zeptá se instance třídy implementující IGameMap, jestli je cihla
     * na souřadnicích volná.
     *
     * @param brickX x-ová souřadnice
     * @param brickY y-ová souřadnice
     *
     * @return true - pozice je volná, false - pozice není volná
     */
    @Override
    public boolean isFree(int brickX, int brickY)
    {
        if (brickMap[brickX][brickY].isSolid())
        {
            return false;
        }
        return true;
    }




  /***************************************************************************
     * Zeptá se instance třídy implementující IGameMap, jestli je pozice volná.
     *
     * @param position dotazovaná pozice
     *
     * @return true - pozice je volná, false - pozice není volná
     */
    @Override
    public boolean isFree(Point position)
    {
        int brickX = position.x / xBrickSize;
        int brickY = position.y / yBrickSize;
        return isFree(brickX, brickY);
    }


    /***************************************************************************
     * Zeptá se instance třídy implementující IGameMap, z jaké stany
     * došlo k zásahu.
     *
     * @param position pozice zásahu
     *
     * @return číslo dle strany zásahu
     */
    @Override
    public int getHitDirection(Point position, IPositioned ball)
    {

        int trueX = position.x / xBrickSize;
        int trueY = position.y / yBrickSize;

        if (trueX > xBricks)
        {
            return 4;
        }
        if (trueX < 1)
        {
            return 2;
        }
        if (trueY < 1)
        {
            return 3;
        }


        int hitDirection = evaluateHitDirection(position.x,position.y,
                ball.getPosition().x, ball.getPosition().y);



        Brick target = brickMap[trueX][trueY];
        if (target.hitKill())
        {
            target.setColor(BACKGROUND);
            target.setSolid(false);
            int targetX = (trueX*xBrickSize)+xBrickSize/2;    //výpočet středu kostky
            int targetY = (trueY*xBrickSize)+xBrickSize/2;
            Game.getInstance().getArkMaster().infoBrickDestroy(new Point(targetX,targetY));
        }

        Game.getInstance().getGameGui().infoCleanPercentChanged();

        return hitDirection;
    }




//== OTHER NON-PRIVATE INSTANCE METHODS ========================================


    /***************************************************************************
     *  Reakce na MapMove
     *
     * @param reactEvent
     */
    @Override
    public void react(ReactEvents reactEvent)
    {
        switch (reactEvent)
        {
            case MapEvent:
            {
                for (int j = yBricks; j > 2; j--)
                {
                    for (int i = 1; i<=xBricks; i++)
                    {
                        brickMap[i][j].changeToBrick(brickMap[i][j-1]);
                    }
                }
                for (int i = 1; i<=xBricks; i++)
                {
                    brickMap[i][1].setSolid(false);
                    brickMap[i][1].setColor(BACKGROUND);
                    brickMap[i][1].setEndurance(0);
                    if (brickMap[i][yBricks].isSolid())
                    {
                        Game.getInstance().getGameGui().infoGameEnd("tetris");
                    }
                }
                refreshTetPosition(oldShape);
                mapPanel.refresh();
                break;
            }
            default:
                break;
        }
    }


    /***************************************************************************
     * Inicializace po spuštění
     */
    public void inicialize()
    {
        //Nejdříve všechny cihličky nastaví prázdné
        for (int i = 1; i<=xBricks; i++)
        {
            for (int j = 1; j<=yBricks; j++)
            {
                brickMap[i][j] = new Brick(false, 0, BACKGROUND);
            }
        }

        //Pak nastaví okraje
        for (int i = 0;i<xBricks+2;i++)
        {
            brickMap[i][0]         = new Brick(true, -1, BORDER);
            brickMap[i][yBricks+1] = new Brick(true, -1, BORDER);
        }

        for (int i = 0; i<yBricks+2;i++)
        {
            brickMap[0][i]           = new Brick(true, -1, BORDER);
            brickMap[xBricks+1][i]   = new Brick(true, -1, BORDER);
        }



        for (int j = (yBricks/2);j<=(3*yBricks/4);j++)
        {
        for (int i = 1;i<=xBricks;i++)
        {
            brickMap[i][j] = new Brick(true, 1, Color.RED);
        }
        }

        createShip(Ship.getInstance());

        mapPanel.inicialize();
        mapPanel.refresh();
        mapTimer.initialize();
    }




    //////////
    //TETRIS//
    //////////
    private boolean impact = false;
    /***************************************************************************
     * Informuje instanci třídy implementující IGameMap,
     * že došlo k dopadu tetris bloku.
     */
    @Override
    public void infoImpact()
    {
        int oldLines = calculateLines();

        if (
           ( brickMap[oneXPos][oneYPos]    .isSolid() )||
           ( brickMap[twoXPos][twoYPos]    .isSolid() )||
           ( brickMap[threeXPos][threeYPos].isSolid() )||
           ( brickMap[fourXPos][fourYPos]  .isSolid() )
           )
        {
            Game.getInstance().getGameGui().infoGameEnd("arkanoid");
        }

        brickMap[oneXPos][oneYPos].setSolid(true);
        brickMap[oneXPos][oneYPos].setEndurance(1);
        brickMap[twoXPos][twoYPos].setSolid(true);
        brickMap[twoXPos][twoYPos].setEndurance(1);
        brickMap[threeXPos][threeYPos].setSolid(true);
        brickMap[threeXPos][threeYPos].setEndurance(1);
        brickMap[fourXPos][fourYPos].setSolid(true);
        brickMap[fourXPos][fourYPos].setEndurance(1);

        impact = true;

        mapPanel.refresh();

        Game.getInstance().getGameGui().infoNextShapeChanged();
        Game.getInstance().getGameGui().infoCleanPercentChanged();

        int newLines = calculateLines();

        if (oldLines < newLines)
        {
            hardenLower();
    }

    }

     /**************************************************************************
     * Překreslí blok tetrisu na nové pozice
     *
     * @param shape blok tetrisu
     */
    @Override
    public void refreshTetPosition(IShape shape)
     {

       if (!impact)
       {
       brickMap[oneXPos][oneYPos].setColor(BACKGROUND);
       brickMap[twoXPos][twoYPos].setColor(BACKGROUND);
       brickMap[threeXPos][threeYPos].setColor(BACKGROUND);
       brickMap[fourXPos][fourYPos].setColor(BACKGROUND);
       }

       impact = false;
        oneXPos   = shape.getBrick1().x;
        oneYPos   = shape.getBrick1().y;
        twoXPos   = shape.getBrick2().x;
        twoYPos   = shape.getBrick2().y;
        threeXPos = shape.getBrick3().x;
        threeYPos = shape.getBrick3().y;
        fourXPos  = shape.getBrick4().x;
        fourYPos  = shape.getBrick4().y;

        Color newColor = shape.getColor();

        brickMap[oneXPos][oneYPos].setColor(newColor);
        brickMap[twoXPos][twoYPos].setColor(newColor);
        brickMap[threeXPos][threeYPos].setColor(newColor);
        brickMap[fourXPos][fourYPos].setColor(newColor);

        oldShape = shape;

        mapPanel.refresh();
    }



    ////////////
    //ARKANOID//
    ////////////


    /***************************************************************************
     * Informuje instanci třídy implementující IGameMap, že došlo k pohybu
     * instance třídy implementující IPositioned.
     * //Nakonec natvrdo překreslí celou mapu//
     *
     * @param object objekt který se pohnul a je jej třeba překreslit
     *
     */
    @Override
    public void infoMove(IPositioned object)
    {
        mapPanel.refresh();
    }

    /***************************************************************************
     * Informuje instanci třídy implementující IGameMap, že má vytvořit míček
     *
     * @param object vytvářený objekt
     */
    @Override
    public void infoBallCreate(IPositioned ball)
    {
        balls.add(ball);
        mapPanel.refresh();
    }

    /***************************************************************************
     * Informuje instanci třídy implementující IGameMap, že má vytvořit power up
     *
     * @param object vytvářený objekt
     */
    @Override
    public void infoPowerUpCreate(IPositioned powerUp)
    {
        powerUps.add(powerUp);
        mapPanel.refresh();
    }

    /***************************************************************************
     * Informuje instanci třídy implementující IGameMap, že má vymazat míček
     * nebo power up
     *
     * @param object mazaný objekt
     */
    @Override
    public void infoLost(IPositioned object)
    {
        if (powerUps.contains(object))
        {
            powerUps.remove(object);
        }
        else if (balls.contains(object))
        {
            balls.remove(object);
        }
        else
        {
            System.out.print("Unknown object.");
        }
        mapPanel.refresh();
    }

    /***************************************************************************
     * Vytvoří loď
     *
     * @param ship
     */
    @Override
    public void createShip(IPositioned ship)
    {
        this.ship = ship;
       // MapPanel.getInstance().refresh();
    }

    /***************************************************************************
     * Informuje instanci třídy implementující IGameMap,že se změnila délka lodi
     *
     * @param newLenght nová délka lodi
     */
    @Override
    public void infoShipLengthChanges(int newLenght)
    {
        shipLenght = newLenght;
        mapPanel.refresh();
    }


//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
    /***
     * Výpočet procenta mapy.
     * @return procento
     */
    private int calculatePercentage()
    {
        int solidSum = 0;
        for (int j = 1; j<=yBricks; j++)
        {
            for (int i = 1; i<=xBricks; i++)
            {
                if (isFree(i,j))
                {
                    solidSum++;
                }
            }
        }

        int percent = (solidSum * 100)/brickSum;
        return percent;

    }

    /***
     * Metoda na výpočet počtu kompletních řádek.
     * @return Počet kompletních řádek
     */
    private int calculateLines()
    {
        int currentLines = 0;
        for (int j = 1; j<yBricks; j++)
        {
            int solidSum = 0;
            for (int i = 1; i<=xBricks; i++)
            {
                if (!isFree(i,j))
                {
                    solidSum++;
                }
            }
            if (solidSum == xBricks)
            {
                currentLines++;
            }
        }
        return currentLines;
    }

    /***************************************************************************
     * Nastaví nejspodnější řádce vyšší odolnost.
     */
    private void hardenLower()
    {
        boolean end = false;
        for (int i = yBricks-1;i>0;i--)
        {
            if (end)
            {
                break;
            }
            for (int j = 1; j<=xBricks;j++)
            {
                if (!isFree(j,i))
                {
                    end = true;
                    brickMap[j][i].harden();
                }
            }
        }
    }

    /***************************************************************************
    * Zjištění strany dopadu
    *
    * @param xTarget cílová souřadnice x
    * @param yTarget cílová souřadnice y
    * @param xBall původní souřadnice x
    * @param yball původní souřadnice y
    * @return číselná reprezentace strany
    */
    private int evaluateHitDirection(int xTarget, int yTarget, int xBall, int yBall)
    {
        int xDiff = (xTarget - xBall);
        int yDiff = (yTarget - yBall);

        float linear;
        float absolut;

        if (Math.abs(xDiff) > Math.abs(yDiff))
        {
            linear = (float)yDiff/(float)xDiff;

            int xFrom  = xBall;
            int xTo    = xTarget;

            if (xFrom > xTo)
            {
                xFrom  = xTarget;
                xTo    = xBall;
            }
            absolut = (float)yBall - (linear * (float)xBall);

            for (int x = xFrom; x <= xTo; x++)
            {
                int y = (int)(linear * x + absolut);
                int edge = returnEdge(x, y);
                if ((edge != 0) && (!isFree(new Point(x,y))) )
                {
                    return edge;
                }
            }
        }
        else
        {
            linear = (float)xDiff/(float)yDiff;
            int yFrom = yBall;
            int yTo   = yTarget;

            if (yFrom > yTo)
            {
                yFrom = yTarget;
                yTo   = yBall;
            }
            absolut = (float)xBall - (linear * (float)yBall);
            for (int y = yFrom; y <= yTo; y++)
            {
                int x = (int)(linear * y + absolut);
                int edge = returnEdge(x, y);
                if ((edge != 0) && (!isFree(new Point(x,y))) )
                {
                    return edge;
                }
            }

        }
        return 0;





     }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    private int returnEdge(int x, int y)
    {
        int trueX = x%xBrickSize;
        int trueY = y%yBrickSize;

        if (((trueX == 0)||(trueX == (xBrickSize-1)))&&((trueY == 0)||(trueY == (yBrickSize-1))))
        {
            return 5;
        }

        if (trueX == 0)
        {
            return 4;
        }
        if (trueX == (xBrickSize-1))
        {
            return 2;
        }
        if (trueY == 0)
        {
            return 1;
        }
        if (trueY == (yBrickSize-1))
        {
            return 3;
        }

        return 0;

    }

    public void helpTest(int type)
    {
        if (type == 1)
        {
        brickMap[6][12] = new Brick(true, 1, Color.WHITE);
        }
        if (type == 2)
        {
            brickMap[6][12] = new Brick(true, 1, Color.WHITE);
            brickMap[7][12] = new Brick(true, 1, Color.WHITE);
        }
    }

    public int testEdge(int x, int y)
    {
        return returnEdge(x,y);
    }

}
