package Controller;

import Model.Entities.Ball;
import Model.Entities.Player;
import Model.Entities.Pocket;
import Model.Game.Game;
import Model.Game.GameState;
import Model.Utils.File;
import Model.Utils.GameStatus;
import View.Panels.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;



public class GameController implements MouseListener, MouseMotionListener {
    private GamePanel GP;
    private PhysicsEngine PE;
    private GameState GS;
    private boolean showCue = false;
    private boolean dragging = false;
    private int startx, starty;
    private int powerrange = 0;
    public double angle;

    private ArrayList<Ball> pocketedBalls = new ArrayList<>();
    private ArrayList<Pocket> pocketedPockets = new ArrayList<>();
    private int collideWallCount = 0;
    private boolean breakshotEnded = false;

    private boolean foulState = false;
    private boolean collide = false;

    public GameController(GamePanel GP, PhysicsEngine PE, GameState GS){
        this.GP = GP;
        this.PE = PE;
        this.GS = GS;
        createBalls();
    }

    public void reset(){
        showCue = false;
        dragging = false;
        powerrange = 0;
        pocketedBalls.clear();
        pocketedPockets.clear();
        collideWallCount = 0;
        breakshotEnded = false;
        foulState = false;
        createPockets();
        createBalls();

    }

    public Color getBallColor(int number) {
        return switch (number) {
            case 1, 9 -> Color.YELLOW;
            case 2, 10 -> Color.BLUE;
            case 3, 11 -> Color.RED;
            case 4, 12 -> new Color(128, 0, 128);
            case 5, 13 -> Color.ORANGE;
            case 6, 14 -> new Color(0, 200, 0);
            case 7, 15 -> new Color(128, 0, 0);
            case 8 -> Color.black;
            default -> Color.WHITE;
        };
    }


    public void createPockets(){
        double w = GP.getWidth() * 0.1;
        double r = (GP.getBallR() / w) * 1.4;

        Game.addPocket(new Pocket(1, 0.75, r));
        Game.addPocket(new Pocket(5, 0.75 - 0.05,0.9 * r));
        Game.addPocket(new Pocket(9, 0.75, r));

        Game.addPocket(new Pocket(1, 4 + 0.75, r));
        Game.addPocket(new Pocket(5, 4 + 0.75 + 0.05,0.9 * r));
        Game.addPocket(new Pocket(9, 4 + 0.75, r));
    }


    private void createBalls() {
        double startX = 6.5;
        double startY = 2.6;

        Game.addBall(new Ball(0, Color.WHITE,
                startX - 4, startY, true));

        int[][] rack = {
                {1},
                {10, 2},
                {3, 8, 11},
                {12, 5, 13, 6},
                {7, 14, 4, 15, 9}
        };

        for (int i = 0; i < rack.length; i++) {

            double x = startX + i * 0.1944;
            double y = startY - i * 0.2268;

            for (int j = 0; j < rack[i].length; j++) {
                int number = rack[i][j];
                Game.addBall(new Ball(number, getBallColor(number),
                        x, y + j * 0.4536, true));
            }
        }
    }

    public void updateCue(MouseEvent e){
        Ball cueBall = Game.getCueBall();
        double w = GP.getWidth() * 0.1;
        double dx = e.getX() - GP.getBallR() - cueBall.getX() * w;
        double dy = e.getY() - GP.getBallR() - cueBall.getY() * w;

        angle = Math.atan2(dy, dx);

        Game.getCue().setAngle(angle);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!dragging) return;
        updateCue(e);
        Ball cueBall = Game.getCueBall();
        double w = GP.getWidth() * 0.1;

        double dx = startx - GP.getBallR() - cueBall.getX() * w;
        double dy = starty - GP.getBallR() - cueBall.getY() * w;

        double dxx = e.getX() - GP.getBallR() - cueBall.getX() * w;
        double dyy = e.getY() - GP.getBallR() - cueBall.getY() * w;

        powerrange = (int)(Math.sqrt(dxx * dxx + dyy * dyy) - Math.sqrt(dx * dx + dy * dy)) ;
        powerrange = Math.max(powerrange, 0);

        double angle = Math.atan2(dyy, dxx);

//        if(Math.abs(this.angle - angle) > 1 || distance < (GP.getBallR() * 3)){
//            powerrange = 0;
//        }
    }



    @Override
    public void mouseMoved(MouseEvent e) {
        if(PE.anythingMove()) return;

        Ball cueBall = Game.getCueBall();
        double w = GP.getWidth() * 0.1;
        int r = GP.getBallR();

        if(foulState){
            PE.setCueball((e.getX() - r) / w, (e.getY() - r) / w, w, r);
            return;
        }

        double dx = e.getX() - r - cueBall.getX() * w;
        double dy = e.getY() - r - cueBall.getY() * w;

        angle = Math.atan2(dy, dx);
        double distance = Math.sqrt(dx * dx + dy * dy);

        showCue = (r < distance) && (distance < r * 6);
        Game.getCue().setAngle(angle);
    }

    public boolean isShowCue() {
        return (showCue && !foulState);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(isShowCue()){
            dragging = true;
            startx = e.getX();
            starty = e.getY();
        }else if(foulState && !PE.anythingMove()){
            if(Game.getCueBall().isOntable()){
                if(Game.getCueBall().getX() < 0) return;
                if(GS.getTurn().isBiColor() == null)
                    GS.setStatus(GameStatus.OPEN_TABLE);
                else
                    GS.setStatus(GameStatus.NORMAL_PLAY);
                if(GS.getStatus() != GameStatus.OPEN_TABLE) GP.selectPocket();
                else GP.selectSpin();
                foulState = false;
            }
            else Game.getCueBall().setOntable(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(dragging){
            if(powerrange > 0){
                PE.shoot(angle, getPowerrange(), GP.getWidth() * 0.1);
            }
            else System.out.println("Cancelled");
            dragging = false;
            showCue = false;
            powerrange = 0;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getPowerrange() {
        if(powerrange > 100) return 100;
        return powerrange;
    }

    private void foulOccurred(){
        if(foulState) return;
        foulState = true;
        GS.setStatus(GameStatus.FOUL);
        GS.getTurn().setFoulCount(GS.getTurn().getFoulCount() + 1);
    }

    public void pocketed(Pocket pocket, Ball ball) {
        if(ball.getNumber() == 8 && !breakshotEnded){
            GP.resetGame();
        }
        if(GS.getTurn().getScore() + GS.getNotTurn().getScore() == 0 && ball.getNumber() != 0){
            GS.getTurn().setBiColor(ball.isBicolor());
            GS.getNotTurn().setBiColor(!ball.isBicolor());
            GS.setStatus(GameStatus.NORMAL_PLAY);
        }
        if(ball.getNumber() == 0){
            ball.addVelocity(-ball.getVelocityX(), -ball.getVelocityY());
            foulOccurred();
        }
        else{
            pocketedBalls.add(ball);
            pocketedPockets.add(pocket);
        }
        ball.pocket();
    }

    public void shot(){
        collide = false;
        if(GS.getStatus() == GameStatus.BREAK_SHOT){
            GS.setStatus(GameStatus.OPEN_TABLE);
        }
    }

    public void shootingEnded() {
        // TODO: Complete this part

        if(!breakshotEnded){
            if(pocketedBalls.isEmpty() && collideWallCount < 4){
                GP.resetGame();
                return;
            }
        }
        breakshotEnded = true;
        if(collideWallCount == 0 && pocketedBalls.isEmpty()) foulOccurred();
        if(!collide) foulOccurred();

        if(GS.getStatus() != GameStatus.OPEN_TABLE && GS.getStatus() != GameStatus.FOUL){
            GP.selectPocket();
        }else if(GS.getStatus() != GameStatus.FOUL) {
            GP.selectSpin();
        }

        Player shooter = GS.getTurn();
        Player player = GS.getNotTurn();

        boolean succesfull = false;
        boolean endGame = false;
        Pocket endPocket = null;
        for(int i = 0; i < pocketedBalls.size(); i++){
            Ball ball = pocketedBalls.get(i);
            Pocket pocket = pocketedPockets.get(i);

            if(ball.getNumber() == 8){
                endGame = true;
                endPocket = pocket;
            }else if(ball.isBicolor() == shooter.isBiColor()){
                shooter.setScore(shooter.getScore() + 1);
                if(GP.getSelectedPocket() == null) succesfull = true;
                else if(pocket == GP.getSelectedPocket()) succesfull = true;
            }else{
                player.setScore(player.getScore() + 1);
            }
        }
        if(endGame){
            if(GS.getTurn().getScore() != 7) win(GS.getNotTurn(), GS.getTurn());
            else if(foulState) win(GS.getNotTurn(), GS.getTurn());
            else if(endPocket != GP.getSelectedPocket()) win(GS.getNotTurn(), GS.getTurn());
            else win(GS.getTurn(), GS.getNotTurn());
        }
        pocketedBalls.clear();
        pocketedPockets.clear();
        collideWallCount = 0;
        if(!succesfull) GS.switchTurn();
    }

    public void collideWall() {
        collideWallCount += 1;
    }

    public void collided(Ball ball2) {
        collide = true;
        if(ball2.getNumber() == 8 && GS.getTurn().getScore() != 7){
            foulOccurred();
            return;
        }
        if(GS.getStatus() == GameStatus.OPEN_TABLE){
            return;
        };
        if((ball2.getNumber() > 8) != GS.getTurn().isBiColor()){
            if(ball2.getNumber() != 8) foulOccurred();
        }
    }

    public void win(Player winner, Player looser){
        System.out.println(winner.getName());
        File.addNewGame(winner, looser);
        GP.resetGame();
    }
}