package prog2_group7_battleships.wrk;

public class Field {

    private boolean shotAt;
    private boolean nextToShip;
    private Ship ship;

    public Field() {
        this.shotAt = false;
        this.nextToShip = false;
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

    public boolean isNextToShip() {
        return this.nextToShip;
    }

    public void setNextToShip(boolean nextToShip) {
        this.nextToShip = nextToShip;
    }

    public Ship getShip() {
        return this.ship;
    }

}
