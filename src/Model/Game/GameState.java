package Model.Game;

import Model.Entities.Player;
import Model.Utils.GameStatus;

public class GameState {
    Player Player1, Player2;
    boolean Turn;

    GameStatus status = GameStatus.BREAK_SHOT;

    public GameState(Player player1, Player player2) {
        Player1 = player1;
        Player2 = player2;
        Turn = true;
    }

    public void reset(){
        Turn = true;
        Player1.setScore(0);
        Player2.setScore(0);
        getPlayer1().setBiColor(null);
        getPlayer2().setBiColor(null);
        status = GameStatus.BREAK_SHOT;
    }

    public Player getPlayer1() {
        return Player1;
    }

    public Player getPlayer2() {
        return Player2;
    }

    public Player getTurn() {
        return (Turn ? Player1 : Player2);
    }

    // xD
    public Player getNotTurn(){
        return (Turn ? Player2 : Player1);
    }

    public void switchTurn() {
        Turn = !Turn;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }
}
