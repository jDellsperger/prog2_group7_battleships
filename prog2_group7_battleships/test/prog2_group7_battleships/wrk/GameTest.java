/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2_group7_battleships.wrk;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import prog2_group7_battleships.enums.GameMode;
import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.ReturnCode;
import prog2_group7_battleships.enums.ShipType;

/**
 *
 * @author Jan
 */
public class GameTest {

    public GameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of placeShip method, of class Game.
     */
    @Test
    public void testPlaceShip() {
        System.out.println("placeShip: Valid Values");
        Orientation orientation = Orientation.HORIZONTAL;
        ShipType type = ShipType.CARRIER;
        int xCoordinate = 0;
        int yCoordinate = 0;
        Game instance = new Game();
        instance.setGameMode(GameMode.SINGLE);
        ReturnCode expResult = ReturnCode.PLACED_SUCCESSFULLY;
        ReturnCode result = instance.placeShip(orientation, type, xCoordinate, yCoordinate);
        assertEquals(expResult, result);

        System.out.println("placeShip: Type not available");
        type = ShipType.CARRIER;
        expResult = ReturnCode.NOT_AVAILABLE;
        result = instance.placeShip(orientation, type, xCoordinate, yCoordinate);
        assertEquals(expResult, result);

        System.out.println("placeShip: Fields not free");
        type = ShipType.BATTLESHIP;
        expResult = ReturnCode.NOT_FREE;
        result = instance.placeShip(orientation, type, xCoordinate, yCoordinate);
        assertEquals(expResult, result);

        System.out.println("placeShip: Index out of bounds");
        xCoordinate = 7;
        expResult = ReturnCode.INDEX_OUT_OF_BOUND;
        result = instance.placeShip(orientation, type, xCoordinate, yCoordinate);
        assertEquals(expResult, result);
    }

}
