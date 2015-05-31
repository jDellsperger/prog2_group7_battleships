package prog2_group7_battleships.ihm;

import java.util.Arrays;
import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.ShipType;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ControlsSidepaneController {

    @FXML
    private ChoiceBox<ShipType> shipTypeSelectionBox;

    @FXML
    private RadioButton horizonalRadio;
    @FXML
    private RadioButton verticalRadio;

    private ToggleGroup orientationRadioGroup;

    public void initialize() {
        this.orientationRadioGroup = new ToggleGroup();
        this.orientationRadioGroup.getToggles().addAll(this.horizonalRadio, this.verticalRadio);

        this.shipTypeSelectionBox.getItems().addAll(Arrays.asList(ShipType.values()));
    }

    public Orientation getOrientation() {
        if (this.orientationRadioGroup.getSelectedToggle() == this.horizonalRadio) {
            return Orientation.HORIZONTAL;
        } else {
            return Orientation.VERTICAL;
        }
    }

    /**
     * Gets the selected ship type and resets UI-control it to the smallest
     * availiable ship
     *
     * @return
     */
    public ShipType getAndResetShipType() {
        return (ShipType) this.shipTypeSelectionBox.getValue();
    }
}
