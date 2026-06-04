package Model.Utils;

import java.awt.*;

public enum GameStatus {
    BREAK_SHOT("Break Shot"),
    OPEN_TABLE("Open Table"),
    NORMAL_PLAY("Normal Play"),
    BALL_IN_HAND("Ball In Hand"),
    FOUL("Foul!"),
    PLAYER1_WIN("Player 1 Wins!"),
    PLAYER2_WIN("Player 2 Wins!");

    private String text;
    GameStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}