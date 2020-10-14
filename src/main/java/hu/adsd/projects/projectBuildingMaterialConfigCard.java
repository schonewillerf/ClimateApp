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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class projectBuildingMaterialConfigCard extends VBox implements Initializable
{
    // private CirculationType circulationType;
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
        //this.circulationType = circulationType;
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
        // insert every circulationtype in dropdown
        /*for(CirculationType type : CirculationType.values())
        {
            circulationDropdown.getItems().add(type.toString());
        }*/
        //
        // Add CirculationTypes to Dropdown
        circulationDropdown.getItems().setAll( CirculationType.values() );

        // select the right circulationtype per product
        /*int i = 0;
        for(CirculationType type : CirculationType.values())
        {
            if(product.getCirculationType().equals(type))
            {
                circulationDropdown.getSelectionModel().select(i);
            } else 
            {  
                i++;
            }
        }*/
        //
        // Set initial selected Circulation Type
        circulationDropdown.getSelectionModel().select( this.circulationConfiguration.getCirculationType() );

        // While it works to create a Selection Listener inside the Constructor
        // It is better practice to do UI related stuff inside the initialize method
        //
        // Set a listener on the Circulation Dropdown
        circulationDropdown.getSelectionModel().selectedItemProperty().addListener(
                ( options, oldVal, newVal ) -> updateDropdown( newVal )
        );

        // set carbon and energy amounts
        /*if(circulationDropdown.getSelectionModel().getSelectedIndex()==CirculationType.values().length-1)
        {
            System.out.println("unset");
        }*/

        // set label values
        if(circulationDropdown.getSelectionModel().getSelectedItem() == CirculationType.UNSET)
        {
            embodiedCarbonLabel.setText("Embodied Carbon: 0");
            embodiedEnergyLabel.setText("Embodied Energy: 0");
            changeColor(0);
        }
        else
        {
            setOrUpdateLabels(circulationDropdown.getSelectionModel().getSelectedItem());
        }
        // carbon check changing color function

    }

    // Accepting the new value as parameter prevents us having to check it again
    // Also consider renaming this method as it is executed when the dropdown selection is changed
    // Maybe updateCirculationConfiguration() or onDropdownSelectionChange()
    private void updateDropdown( CirculationType selectedCirculationType )
    {
        /*for(CirculationType type : CirculationType.values())
        {
            if(circulationDropdown.getValue().toLowerCase().equals(type.toString().toLowerCase()))
            {
                product.setCirculationType(type);
            }
        }*/
        //
        // Also it should update the Configuration aka the CirculationType
        // The CirculationType property is something that could be dropped from the Product class
        //
        // This was however a bit tricky, because a enum values are like final values
        // This can however be solved by adding the enum inside a new class ;)
        //
        // Update CirculationType
        this.circulationConfiguration.setCirculationType( selectedCirculationType );

        // Update Labels
        setOrUpdateLabels(selectedCirculationType);
    }

    /**
     * Change the value of the labels based on the circulation type / circulation configuration
     * The product related to this configuration always stays the same
     *
     * Maybe make an assumption like
     *      New products is 100% of Carbon
     *      Linear product is 90% of Carbon from product
     *
     * We will replace this with a more exact formula later, or when more date is available from Database
     *
     * Please try to improve this implementation so the numbers can change during Sprint Review
     */
    private void setOrUpdateLabels(CirculationType selectedCirculationType)
    {
        if (selectedCirculationType != CirculationType.UNSET)
        {
            // Madeup values per CirculationType
            double circType_linear = 0.7;
            double circType_reused = 0.1;
            double circType_fossiele_energie = 0.9;
            double circType_secundaire_grondstoffen = 0.4;
            double circType_new = 1.0;

            double value = 0;

            switch(selectedCirculationType.toString().toLowerCase()) 
            {
                case"linear":
                    value = circType_linear;
                    break;

                case"reused":
                    value = circType_reused;
                    break;

                case"fossiele_energie":
                    value = circType_fossiele_energie;
                    break;

                case"secundaire_grondstoffen":
                    value = circType_secundaire_grondstoffen;
                    break;

                case"new":
                    value = circType_new;
                    break;
            }

            embodiedCarbonLabel.setText("Embodied Energy: " + product.getMaxCarbonAmount() * value);
            embodiedEnergyLabel.setText("Embodied Energy: " + product.getMinCarbonAmount() * value);
            changeColor(value);
        }
        else
        {
            embodiedCarbonLabel.setText("Embodied Carbon: 0");
            embodiedEnergyLabel.setText("Embodied Energy: 0"); 
            changeColor(0);
        }
    }

    /**
     * Make this color change based on the circulation type / circulation configuration
     * The product related to this configuration always stays the same
     *
     * Please use Allman Style from now on
     *
     * Also try to further implement this method so colors will change during Sprint Review
     */
    private void changeColor(double value)
    {
        embodiedCarbonLabel.setStyle("-fx-text-fill: fff");
        embodiedEnergyLabel.setStyle("-fx-text-fill: fff");

        if(product.getMinCarbonAmount() * value==0)
        {
            this.setStyle("-fx-background-color: #1287A8");
        }
        else if(product.getMinCarbonAmount() * value<=0.25)
        {
            this.setStyle("-fx-background-color: #93A661; -fx-text-fill: fff");
        }
        else if(product.getMinCarbonAmount() * value<=0.5)
        {
            this.setStyle("-fx-background-color: #DAA82F; -fx-text-fill: fff");
        }
        else if(product.getMinCarbonAmount() * value<=0.75)
        {
            this.setStyle("-fx-background-color: #F26D21; -fx-text-fill: fff");
        }
        else
        {
            this.setStyle("-fx-background-color: #C02F1D; -fx-text-fill: fff");
        }
    }
}
