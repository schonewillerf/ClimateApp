package hu.adsd.projects;

import hu.adsd.buildingmaterials.CirculationType;
import hu.adsd.buildingmaterials.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class projectBuildingMaterialConfigCard extends VBox implements Initializable
{
    private final CirculationConfiguration circulationConfiguration;
    private final Product product;

    @FXML
    private ComboBox<CirculationType> circulationDropdown;

    @FXML
    private Label embodiedCarbonLabel;

    @FXML
    private Label embodiedEnergyLabel;

    public projectBuildingMaterialConfigCard( CirculationConfiguration circulationConfiguration, Product product )
    {
        this.circulationConfiguration = circulationConfiguration;
        this.product = product;

        // load the project buildingmaterialconfig card FXML VBOX
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../projectBuildingMaterialConfigCardView.fxml" ) );
        fxmlLoader.setRoot( this );
        fxmlLoader.setController( this );

        try
        {
            fxmlLoader.load();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        // Add CirculationTypes to Dropdown
        circulationDropdown.getItems().setAll( CirculationType.values() );

        // Set initial selected Circulation Type
        circulationDropdown.getSelectionModel().select( this.circulationConfiguration.getCirculationType() );

        // Set a listener on the Circulation Dropdown
        circulationDropdown.getSelectionModel().selectedItemProperty().addListener(
                ( options, oldVal, newVal ) -> updateCirculationConfiguration( newVal )
        );

        setOrUpdateLabels();

        changeColor();

        // This should better be done from FXML or CSS
        embodiedCarbonLabel.setStyle("-fx-text-fill: fff");
        embodiedEnergyLabel.setStyle("-fx-text-fill: fff");
    }

    private void updateCirculationConfiguration( CirculationType selectedCirculationType )
    {
        // Update CirculationType
        this.circulationConfiguration.setCirculationType( selectedCirculationType );

        // Update Labels
        setOrUpdateLabels();

        // Update Color
        changeColor();
    }

    private void setOrUpdateLabels()
    {
        embodiedCarbonLabel.setText(
                String.format(
                        "Embodied Carbon: %.2f",
                        product.getTotalEmbodiedCarbon( this.circulationConfiguration.getCirculationType().getFactor() )
                )
        );

        embodiedEnergyLabel.setText(
                "Embodied Energy: " +
                product.getTotalEmbodiedEnergy( this.circulationConfiguration.getCirculationType().getFactor() )
        );
    }

    private void changeColor()
    {
        this.setStyle(
                String.format(
                        "-fx-background-color: %s",
                        this.circulationConfiguration.getCirculationType().getColorCode()
                )
        );
    }
}
