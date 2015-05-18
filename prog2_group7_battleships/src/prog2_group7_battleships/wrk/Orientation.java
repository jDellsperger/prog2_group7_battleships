/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2_group7_battleships.wrk;

/**
 *
 * @author Jan
 */
public enum Orientation {

    HORIZONTAL(0), VERTICAL(1);

    private final int orientation;

    private Orientation(int orientation) {
        this.orientation = orientation;
    }

    public int getOrientation() {
        return this.orientation;
    }

}
