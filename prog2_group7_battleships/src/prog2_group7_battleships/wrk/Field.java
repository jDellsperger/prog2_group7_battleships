package prog2_group7_battleships.wrk;

public class Field {

    private boolean shotAt;
    private Ship ship;

    public Field() {
        this.shotAt = false;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean isShotAt() {
        return this.shotAt;
    }

    public void setShotAt(boolean shotAt) {
        this.shotAt = shotAt;
    }

    public Ship getShip() {
        return this.ship;
    }

}
