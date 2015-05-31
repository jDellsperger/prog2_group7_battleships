package prog2_group7_battleships.ihm;

import prog2_group7_battleships.enums.Orientation;
import prog2_group7_battleships.enums.ShipType;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ControlsSidepaneController {
	@FXML
	private ComboBox shipTypeSelectionBox;
	
	@FXML
	private RadioButton horizonalRadio;
	@FXML
	private RadioButton verticalRadio;
	
	private ToggleGroup orientationRadioGroup;
	
	public void initialize() {
		this.orientationRadioGroup = new ToggleGroup();
		this.orientationRadioGroup.getToggles().addAll(this.horizonalRadio, this.verticalRadio);
		
		// TODO add elements to comboBox
	}
	
	public Orientation getOrientation() {
		if (this.orientationRadioGroup.selectedToggleProperty().equals(this.horizonalRadio)) {
			return Orientation.HORIZONTAL;
		} else {
			return Orientation.VERTICAL;
		}
	}
	
	/**
	 * Gets the selected ship type and resets UI-control it to the smallest availiable ship
	 * @return
	 */
	public ShipType getAndResetShipType() {
		// TODO update the available ship types and set'em again
		return null;
	}
}
