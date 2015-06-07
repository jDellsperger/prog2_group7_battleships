package prog2_group7_battleships.ihm;

import java.util.Scanner;
import prog2_group7_battleships.ctrl.Controller;
import prog2_group7_battleships.enums.GameMode;
import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.ShipType;

public class ConsoleView implements Viewable {

    private Controller ctrl;
    private Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void setController(Controller ctrl) {
        this.ctrl = ctrl;
    }

    @Override
    public void queryPlacement() {
        Orientation orientation = null;
        ShipType type = null;
        int xCoordinate = -1;
        int yCoordinate = -1;
        System.out.println("You need to place your ships");
        while (null == orientation) {
            System.out.println("The ships orientation (h = horizontal, v = vertical)");
            switch (this.scanner.next()) {
                case "h":
                    orientation = Orientation.HORIZONTAL;
                    break;
                case "v":
                    orientation = Orientation.VERTICAL;
                    break;
                default:
                    System.out.println("Invalid Input! Try again.");
            }
        }

        while (null == type) {
            System.out.println("The ships length (carrier, battleship, submarine, destroyer, boat):");
            switch (this.scanner.next()) {
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
                    System.out.println("Invalid Input! Try again.");
            }
        }

        while (xCoordinate < 0 || xCoordinate > 9) {
            System.out.println("xCoordinate (1 - 10):");
            try {
                xCoordinate = Integer.parseInt(this.scanner.next()) - 1;
            } catch (NumberFormatException e) {
                xCoordinate = -1;
            }
            if (xCoordinate < 0 || xCoordinate > 9) {
                System.out.println("Invalid Input! Try again.");
            }
        }

        while (yCoordinate < 0 || yCoordinate > 9) {
            System.out.println("yCoordinate (1 - 10):");
            try {
                yCoordinate = Integer.parseInt(this.scanner.next()) - 1;
            } catch (NumberFormatException e) {
                yCoordinate = -1;
            }
            if (yCoordinate < 0 || yCoordinate > 9) {
                System.out.println("Invalid Input! Try again.");
            }
        }

        this.ctrl.placeShip(orientation, type, xCoordinate, yCoordinate);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void queryMode() {
        GameMode mode = null;
        while (null == mode) {
            System.out.println("The game mode (s = single, m = multi)");
            switch (this.scanner.next()) {
                case "s":
                    mode = GameMode.SINGLE;
                    break;
                case "m":
                    mode = GameMode.MULTI;
                    break;
                default:
                    System.out.println("Invalid Input! Try again.");
            }
        }
        this.ctrl.setGameMode(mode);
    }

    @Override
    public void queryShooting() {
        int xCoordinate = -1;
        int yCoordinate = -1;

        while (xCoordinate < 0 || xCoordinate > 9) {
            System.out.println("xCoordinate (1 - 10):");
            try {
                xCoordinate = Integer.parseInt(this.scanner.next()) - 1;
            } catch (NumberFormatException e) {
                xCoordinate = -1;
            }
            if (xCoordinate < 0 || xCoordinate > 9) {
                System.out.println("Invalid Input! Try again.");
            }
        }

        while (yCoordinate < 0 || yCoordinate > 9) {
            System.out.println("yCoordinate (1 - 10):");
            try {
                yCoordinate = Integer.parseInt(this.scanner.next()) - 1;
            } catch (NumberFormatException e) {
                yCoordinate = -1;
            }
            if (yCoordinate < 0 || yCoordinate > 9) {
                System.out.println("Invalid Input! Try again.");
            }
        }

        this.ctrl.shoot(xCoordinate, yCoordinate);
    }

    @Override
    public void updateView() {
        // TODO Auto-generated method stub

    }

    @Override
    public void displayGameOver() {
        // TODO Auto-generated method stub
    }

    @Override
    public void queryPlayerSwitch() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayTitle(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
