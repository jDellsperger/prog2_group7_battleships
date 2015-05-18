package prog2_group7_battleships.wrk;

public enum GameMode {

    SINGLE(0), MULTI_CLIENT(1), MULTI_SERVER(2);

    private final int mode;

    private GameMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return this.mode;
    }

}
