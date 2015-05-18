/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2_group7_battleships.enums;

/**
 *
 * @author Jan
 */
public enum ReturnCode {

    PLACED_SUCESSFULLY("The ship has been placed successfully."), NOT_FREE("Not all affected fields are free."),
    INDEX_OUT_OF_BOUND("Not all affected fields are on the board."), NOT_AVAILABLE("The ship type is not available"),
    INVALID_GAMESTATE("The game is in an invalid state.");

    private final String message;

    private ReturnCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
