package Model.Entities;

public class Player {
    private String name;
    private int foulCount = 0;
    private boolean biColor;
    private int wins = 0;

    public Player(String name, int foulCount, boolean biColor, int wins) {
        this.name = name;
        this.foulCount = foulCount;
        this.biColor = biColor;
        this.wins = wins;
    }

    public String getName() {
        return name;
    }

    public int getFoulCount() {
        return foulCount;
    }

    public boolean isBiColor() {
        return biColor;
    }

    public int getWins() {
        return wins;
    }

    // We don't need to set name again
    public void setFoulCount(int foulCount) {
        this.foulCount = foulCount;
    }

    public void setBiColor(boolean biColor) {
        this.biColor = biColor;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }
}
