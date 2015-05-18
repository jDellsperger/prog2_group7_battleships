package prog2_group7_battleships.ihm;

import java.util.Scanner;
import prog2_group7_battleships.ctrl.Controller;
import prog2_group7_battleships.enums.GameMode;
import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.ShipType;

public class View {

    Controller ctrl;

    public void setController(Controller ctrl) {
        this.ctrl = ctrl;
    }

    public void queryPlacement() {
        Orientation orientation = null;
        ShipType type = null;
        int xCoordinate = -1;
        int yCoordinate = -1;
        Scanner s = new Scanner(System.in);
        System.out.println("You need to place your ships");
        System.out.println("The ships orientation (h = horizontal, v = vertical)");
        switch (s.next()) {
            case "h":
                orientation = Orientation.HORIZONTAL;
                break;
            case "v":
                orientation = Orientation.VERTICAL;
                break;
            default:
                System.out.println("Invalid Input!!");
                System.exit(0);
        }

        System.out.println("The ships length (carrier, battleship, submarine, destroyer, boat):");
        switch (s.next()) {
            case "carrier":
                type = ShipType.CARRIER;
                break;
            case "battleship":
                type = ShipType.BATTLESHIP;
                break;
            case "submarine":
                type = ShipType.SUBMARINE;
                break;
            case "destroyer":
                type = ShipType.DESTROYER;
                break;
            case "boat":
                type = ShipType.BOAT;
                break;
            default:
                System.out.println("Invalid Input!!");
                System.exit(0);
        }

        System.out.println("xCoordinate (1 - 10):");
        xCoordinate = Integer.parseInt(s.next());

        System.out.println("yCoordinate (1 - 10):");
        yCoordinate = Integer.parseInt(s.next());

        this.ctrl.placeShip(orientation, type, xCoordinate, yCoordinate);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void queryMode() {
        Scanner s = new Scanner(System.in);
        System.out.println("The game mode (s = single, m = multi)");
        GameMode mode = null;
        switch (s.next()) {
            case "s":
                mode = GameMode.SINGLE;
                break;
            case "m":
                mode = GameMode.MULTI;
                break;
            default:
                System.out.println("Invalid Input!!");
                System.exit(0);
        }

    }

}
