/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package _1245_tetroid.arkanoid;



import static _1245_tetroid.Constants.*;
import _1245_tetroid.IArkMaster;
import _1245_tetroid.IGame;
import _1245_tetroid.IGameControl;
import _1245_tetroid.IGameMap;
import _1245_tetroid.IGameTimer;
import _1245_tetroid.IPositioned;
import _1245_tetroid.IReactable;
import _1245_tetroid.ReactEvents;
import _1245_tetroid.game.Game;
import _1245_tetroid.reaction.GameControl;
import _1245_tetroid.reaction.SuperTimer;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;


/*******************************************************************************
 * Instances of class {@code ArkMaster} represent ...
 *
 * @author  Tomáš Kumsta
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class ArkMaster implements IArkMaster, IReactable
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================

    private Ship ship = Ship.getInstance();

    private static Collection<IPositioned> balls = new ArrayList<>();

    private static Collection<IPositioned> powerUps = new ArrayList<>();

    private static Collection<IPositioned> powerUpsAway = new ArrayList<>();

    private Ball ball;


    private final IGame   game;
    private final IGameMap gameMap;

    private final IGameTimer arkTimer;

    private final IGameControl gameControl = GameControl.getInstance();


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
    public ArkMaster(IGame game)
    {
        this.game = game;
        this.gameMap = game.getGameMap();
        this.arkTimer = new SuperTimer(this, ReactEvents.TikArk ,40);
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

/*******************************************************************************
 * Vrací pozici lodi.
 */
    public Point getShipPosition()
    {
        return ship.getPosition();
    }

/*******************************************************************************
 * Vrací délku lodi.
 */
    public int getShipLength()
    {
        return ship.getLength();
    }


/*******************************************************************************
 * Vrací kolekci míčků.
 */

    public Collection<IPositioned> getBallSet()
    {
        return balls;
    }

/*******************************************************************************
 * Vrací kolekci powerupů.
 */
    public Collection<IPositioned> getPowerUpSet()
    {
        return powerUps;
    }


    /***************************************************************************
     * Zjistí aktuální periodu arkanoidu
     */
    @Override
    public int getArkPeriod()
    {
        return arkTimer.getPeriod();
    }


/*******************************************************************************
 * Mapa informuje o zničení cihličky a ptá se jestli vznikne na její pozici
 * powerup.
 *
 */
    @Override
    public void infoBrickDestroy(Point position)
    {
        PowerUp powerUp = PowerUp.generatePowerUp(position);
        if (powerUp != null) {
        powerUps.add(powerUp);
        gameMap.infoPowerUpCreate(powerUp);
        }
    }


/*******************************************************************************
 * Vrací počet životů lodi.
 */
    @Override
    public int getLives()
    {
        return ship.getLives();
    }


//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

/*******************************************************************************
 * Vyvolá reakci implementujících tříd na tiknutí Timeru.
 * Posune všechny míčky v kolekci a všechny powerupy v kolekci.
 */
    private void tik()
    {
        if (balls.isEmpty()) {
            ship.setLive(ship.getLives()-1);
            Game.getInstance().infoLifeChange();
            inicializeStart();
        }
        else{    for (IPositioned positionedBall : balls) {
                    Ball ball = (Ball)positionedBall;
                    Point nextPoint = ball.getNextPosition();

                if (ball.isReleased()) {
                   if (nextPoint.y<(yBricks*yBrickSize)+SHIP_HEIGHT+1) {
                        if (shipNotHit(nextPoint)) {
                            if (gameMap.isFree(nextPoint)) {
                                ball.move(nextPoint);
                                gameMap.infoMove(ball);
                            }
                            else{int side = gameMap.getHitDirection
                                                    (ball.getNextPosition(), ball);
                        ball.brickBounce(side);}
                        }
                        else{shipBounce(ball);
                    }}
                   else{this.ball= ball;

                }}



        }}
        if (!(powerUps.isEmpty()) ) {

            for (IPositioned positionedPowerUp : powerUps) {
                PowerUp powerUp = (PowerUp)positionedPowerUp;
                Point position = powerUp.getNextPosition();
                    if (position.y>0) {
                        if (powerUpTaken(position)) {
                            powerUpReact(powerUp.getType());
                            powerUpsAway.add(powerUp);
                        }
                        else{
                            powerUp.move(position);
                            gameMap.infoMove(powerUp);}
                    }
                    else{
                     if (position.y>(yBricks*yBrickSize)+SHIP_HEIGHT+1) {
                         powerUpsAway.add(powerUp);
                     }
                     else{powerUp.move(position);
                        gameMap.infoMove(powerUp);}}
            }}
        powerUps.removeAll(powerUpsAway);
        for (IPositioned powerUp : powerUpsAway) {
        gameMap.infoLost(powerUp);
        }
        powerUpsAway.clear();
        balls.remove(this.ball);
        gameMap.infoLost(this.ball);

    }

    private boolean shipNotHit(Point position)
    {
       Point ballPosition = position;
       int shipX = ship.getPosition().x;
       int shipY = ship.getPosition().y;
     if(((ballPosition.x + 4>=shipX) &&
                (ballPosition.x + 4<=shipX+ship.getLength())) &&
         ((ballPosition.y + 7>=shipY) &&
                    (ballPosition.y + 8<=shipY+SHIP_HEIGHT))){
         return false;
     }
     else{return true;}
    }

    private boolean powerUpTaken(Point position)
    {
       Point powerUpPosition = position;
       int shipX = ship.getPosition().x;
       int shipY = ship.getPosition().y;
     if((powerUpPosition.x>=shipX) &&
                    (powerUpPosition.x<=shipX+ship.getLength()) &&
         (powerUpPosition.y>=shipY) &&
                        (powerUpPosition.y<=shipY+SHIP_HEIGHT))
//        || (((powerUpPosition.x+16>=shipX) &&
//                (powerUpPosition.x+16<=shipX+ship.getLength())) &&
//         ((powerUpPosition.y+16<=shipY) &&
//                (powerUpPosition.y+16>=shipY+SHIP_HEIGHT))))
     {
         return true;
     }
     else{return false;}
    }

    private void powerUpReact(PowerUpType type)
    {
       switch(type){
            case ShortShip:
                if (ship.getLength()<=MEDIUM_SHIP) {
                    ship.setSize(SHORT_SHIP);
                    gameMap.infoShipLengthChanges(SHORT_SHIP);
                }
                else{ship.setSize(MEDIUM_SHIP);
                    gameMap.infoShipLengthChanges(MEDIUM_SHIP);
                }
                break;
            case LongShip:
                if (ship.getLength()>=MEDIUM_SHIP) {
                    ship.setSize(LONG_SHIP);
                    gameMap.infoShipLengthChanges(LONG_SHIP);
                }
                else{
                    ship.setSize(MEDIUM_SHIP);
                    gameMap.infoShipLengthChanges(MEDIUM_SHIP);
                }
                break;
            case NewBall:
                Point point = ship.getPosition();
                Ball ball = new Ball(
                                new Point(point.x+ship.getLength()/2, point.y-9));
                balls.add(ball);
                gameMap.infoBallCreate(ball);
                break;
            default:
                 break;
       }
    }
    void shipBounce(Ball ball)
    {
        final double influenceX = 0.75;
        double speedX ;
        double speedY ;
        double shipHalfLenght = (ship.getLength()/2);
        double posX = ((ball.getPosition().x + 4) -
                      (ship.getPosition().x+shipHalfLenght)) / (shipHalfLenght/2);
        speedX = (MAX_BALL_SPEED * posX * influenceX );
        if (speedX>=MAX_BALL_SPEED) { speedX = MAX_BALL_SPEED-1;}
        if (speedX<=-MAX_BALL_SPEED) { speedX = 1-MAX_BALL_SPEED;}
        ball.setSpeedX((int)speedX);
        speedY = (Math.sqrt(MAX_BALL_SPEED*MAX_BALL_SPEED - speedX*speedX));
        ball.setSpeedY((int)-speedY);
        ball.move(ball.getNextPosition());
        gameMap.infoMove(ball);
    }


    @Override
    public void react(ReactEvents reactevent)
    {
    switch(reactevent){
      case UpEvent:
          for (IPositioned positionedBall : balls) {
              Ball ball = (Ball)positionedBall;
              if (ball.isReleased()) {}
              else{
                  ball.setReleased(true);
                  ball.setSpeedY(-MAX_BALL_SPEED);
              }

          }
        break;
      case LeftEvent:
        if ((32<ship.getPosition().x)){
          byte leftSpeed = (-1);
          ship.move(leftSpeed);
             gameMap.infoMove(ship);
          for (IPositioned positionedBall : balls) {
            Ball ball = (Ball)positionedBall;
            if (ball.isReleased()) {}
            else{
                ball.setSpeedX(-SHIP_SPEED);
                ball.move(ball.getNextPosition());
                gameMap.infoMove(ball);
                ball.setSpeedX(0);
            }}
          }
        break;
      case RightEvent:
        if(ship.getPosition().x < 352-ship.getLength()){
          byte rightSpeed = (1);
          ship.move(rightSpeed);
          gameMap.infoMove(ship);
          for (IPositioned positionedBall : balls) {
            Ball ball = (Ball)positionedBall;
            if (ball.isReleased()) {}
            else{ball.setSpeedX(SHIP_SPEED);
            ball.move(ball.getNextPosition());
            gameMap.infoMove(ball);
            ball.setSpeedX(0);
            }}
          }
        break;
      case TikArk:
          tik();
          break;
       default:
           break;
        }
    }

    public void inicializeStart()
    {
        ship.setSize(MEDIUM_SHIP);
        ship.setStartPosition();
        gameMap.infoMove(ship);
        gameMap.infoShipLengthChanges(MEDIUM_SHIP);
        gameMap.createShip(ship);
        Point point = ship.getPosition();
        Ball ball = new Ball(new Point(point.x+ship.getLength()/2, point.y-9));
        balls.add(ball);    
        gameMap.infoBallCreate(ball);
    }

        @Override
    public void initialize()
    {
        gameControl.addReactant(this);
        inicializeStart();
        arkTimer.initialize();
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
//    }
//    /** @param args Command line arguments - not used. */
//    public static void main(String[] args)  {  test();  }












}
