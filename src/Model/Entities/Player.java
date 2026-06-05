package Model.Entities;

public class Player {
    private String name;
    private int foulCount = 0;
    private int score = 0;
    private Boolean biColor;
    private int wins = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getFoulCount() {
        return foulCount;
    }

    public Boolean isBiColor() {
        return biColor;
    }

    public int getWins() {
        return wins;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // We don't need to set name again
    public void setFoulCount(int foulCount) {
        this.foulCount = foulCount;
    }

    public void setBiColor(Boolean biColor) {
        this.biColor = biColor;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getColorNumber(){
        if(biColor == null) return 8;
        else if(biColor) return 15;
        else return 7;
    }

    public String getData() {
        return name +
                ", " + foulCount +
                ", " + score;
    }
}
