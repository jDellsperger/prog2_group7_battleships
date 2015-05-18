package prog2_group7_battleships.wrk;

public class Field {

    private boolean shotAt;
    private Ship ship;

    public Field() {
        this.shotAt = false;
    }

    public boolean isOccupied() {
        boolean isOccupied = false;
        if (null != this.ship) {
            isOccupied = true;
        }
        return isOccupied;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

}
