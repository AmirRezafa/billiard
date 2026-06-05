package View.Panels;

import Controller.GameController;
import Controller.PhysicsEngine;
import Controller.SelectPocket;
import Controller.SelectSpin;
import Model.Entities.Ball;
import Model.Entities.Player;
import Model.Entities.Pocket;
import Model.Game.Game;
import Model.Game.GameState;
import Model.Utils.GameStatus;
import View.Components.BallView;
import View.Components.CueView;
import View.Components.PocketView;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private int ballR = 5;
    private GameController GC;
    private PhysicsEngine PE;
    private GameState GS;
    private SelectPocket SP;
    private SelectSpin SS;

    private boolean firstFrame = true;
    private Player player1;
    private Player player2;


    enum State{
        SELECT_POCKET("Select Pocket"),
        SELECT_SPIN("Select Spin"),
        GAME("Game");

        private String name;
        State(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private Pocket selectedPocket;
    private int selectedSpin;

    State state = State.GAME;
    State laststate = State.GAME;

    public GamePanel(){
        setBackground(Color.BLACK);

        // TODO: Fix this part
        player1 = new Player("AmirReza");
        player2 = new Player("Awmir");
        
        GS = new GameState(
                player1,
                player2
        );


        PE = new PhysicsEngine(this);

        GC = new GameController(this, PE, GS);

        PE.setGC(GC);

        SP = new SelectPocket(this);

        SS = new SelectSpin(this);


        addMouseMotionListener(GC);
        addMouseListener(GC);

        Timer timer = new Timer(
                8,
                e -> repaint()
        );
        timer.start();
    }

    private void drawPowerBar(Graphics2D g2, int power, int x, int y, int width, int height) {
        int filledWidth = width * power / 100;
        GradientPaint color = new GradientPaint(x, y,  new Color(180, 255, 0),
                x + width, y, new Color(255, 80, 0)
        );
        g2.setColor(Color.GRAY);
        g2.fillRoundRect(x, y, width, height, 15, 15);
        g2.setPaint(color);
        g2.fillRoundRect(x, y, filledWidth, height, 15, 15);
    }

    private void drawSpinOptions(Graphics2D g2, double w){
        // w + 8w, 0.75w + 4w
        Composite composite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                0.7f
        ));

        BallView.Draw((int)(5 * w) - (int)(1.5 * w), (int)(w * 2.75) - (int)(1.5 * w),
                0, (int)(1.5 * w),
                0, Color.white, g2, false);

        g2.setComposite(composite);


        int r = (int)(0.35 * w);
        int[] xs = new int[5], ys = new int[5];
        xs[0] = (int)(5 * w) - r; ys[0] = (int)(1.75 * w) - r;
        xs[1] = (int)(5 * w) - r; ys[1] = (int)(2.75 * w) - r;
        xs[2] = (int)(5 * w) - r; ys[2] = (int)(3.75 * w) - r;
        xs[3] = (int)(4 * w) - r; ys[3] = (int)(2.75 * w) - r;
        xs[4] = (int)(6 * w) - r; ys[4] = (int)(2.75 * w) - r;

        for(int i = 0; i < 5; i++){
            g2.setColor(Color.red);
            g2.fillOval(xs[i], ys[i], 2 * r, 2 * r);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        double w = getWidth() * 0.1;

        ballR = (int) (w * 0.15);

        PE.updateBalls(w, ballR);

        g2.setColor(new Color(160, 82, 45));
        g2.fillRoundRect((int) (0.75 * w), (int) (0.5 * w),
                (int) (8.5 * w), (int) (4.5 * w), (int) (w / 5), (int) (w / 5));

        g2.setColor(Color.GREEN);
        g2.fillRoundRect((int) w, (int) (w * 0.75), (int) (w * 8)
                , (int) (w * 4), (int) (w / 3), (int) (w / 3));

        if (firstFrame) {
            firstFrame = false;
            GC.createPockets();
        }
        drawPowerBar(g2, GC.getPowerrange(), (int) (2.5 * w), (int) (6 * w), (int) (5 * w), (int)(w / 5));

        for (Pocket pocket : Game.getPockets()) {
            PocketView.Draw((int)(pocket.getX() * w), (int)(pocket.getY() * w),
                    (int)(pocket.getR() * w), g2);
        }

        for (Ball ball : Game.getBalls()) {
            if (!ball.isOntable()) continue;
            BallView.Draw((int) (ball.getX() * w), (int) (ball.getY() * w),
                    0, ballR, ball.getNumber(),
                    ball.getColor(), g2, true);
        }

        if(state == State.SELECT_SPIN){
            drawSpinOptions(g2, w);
        }

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, (int)(w / 5)));

        g2.drawString(GS.getStatus().getText(), (int) (0.75 * w),(int) (0.5 * w - w / 8));

        String timeText = "Time: 60S";

        FontMetrics fm = g2.getFontMetrics();

        g2.drawString(timeText, (int) (9.25 * w) - fm.stringWidth(timeText),
                (int) (0.5 * w - w / 8));

        String turn = "Turn: " + GS.getTurn().getName();
        String selectState = state.getName();
        String player2s = player2.getName() + ": " + player2.getScore();

        g2.drawString(player1.getName() + ": " + player1.getScore(),
                (int) (0.75 * w), (int) (5.25 * w));

        g2.drawString(turn, (int) (5 * w) -
                        fm.stringWidth(turn) / 2, (int) (5.25 * w));

        if(state != State.GAME){
            g2.drawString(selectState, (int) (5 * w) -
                    fm.stringWidth(selectState) / 2, (int) (0.5 * w - w / 8));
        }

        g2.drawString(player2s, (int) (9.25 * w) - fm.stringWidth(player2s), (int) (5.25 * w));



        BallView.Draw((int) (0.75 * w), (int) (5.25 * w) + (int)(ballR * 0.5),
                0, (int)ballR, player1.getColorNumber(),
                GC.getBallColor(player2.getColorNumber()), g2, false);

        BallView.Draw((int) (9.25 * w) - fm.stringWidth(player2s),
                (int) (5.25 * w) + (int)(ballR * 0.5),
                0, (int)(ballR), player2.getColorNumber(),
                GC.getBallColor(player2.getColorNumber()), g2, false);

        if(state == State.GAME) {
            if (GC.isShowCue()) CueView.draw(g2, Game.getCue(), Game.getCueBall(), w, ballR, GC.getPowerrange());

        }


    }

    public int getBallR() {
        return ballR;
    }

    public void resetGame() {
        Game.reset();
        GS.reset();
        GC.reset();
        PE.reset();
    }

    public void selectPocket(){
        laststate = state;
        state = State.SELECT_POCKET;
        removeMouseListener(GC);
        removeMouseMotionListener(GC);
        addMouseListener(SP);
    }

    public void selectSpin(){
        laststate = state;
        state = State.SELECT_SPIN;
        removeMouseListener(GC);
        removeMouseMotionListener(GC);
        addMouseListener(SS);
    }

    public void pocketSelected(Pocket pocket) {
        state = State.SELECT_SPIN;
        selectedPocket = pocket;
        removeMouseListener(SP);
        addMouseListener(SS);
    }

    public void spinSelected(int i) {
        state = laststate;
        removeMouseListener(SS);
        selectedSpin = i;
        addMouseListener(GC);
        addMouseMotionListener(GC);
    }

    public int getSelectedSpin() {
        return selectedSpin;
    }

    public Pocket getSelectedPocket() {
        return selectedPocket;
    }
}
