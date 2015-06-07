package prog2_group7_battleships.ihm;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.ShipType;

public class ControlsSidepaneController {

    @FXML
    private ChoiceBox<ShipType> shipTypeSelectionBox;

    @FXML
    private RadioButton horizonalRadio;
    @FXML
    private RadioButton verticalRadio;

    private ToggleGroup orientationRadioGroup;

    @FXML
    private void initialize() {
        this.orientationRadioGroup = new ToggleGroup();
        this.orientationRadioGroup.getToggles().addAll(this.horizonalRadio, this.verticalRadio);
        this.orientationRadioGroup.selectToggle(this.verticalRadio);
    }
    
    public void setShipTypeSelection(ArrayList<ShipType> shipTypes) {
        this.shipTypeSelectionBox.getItems().clear();
        for (ShipType shipType : shipTypes) {
            this.shipTypeSelectionBox.getItems().add(shipType);
        }
        this.shipTypeSelectionBox.setValue(shipTypes.get(0));
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
