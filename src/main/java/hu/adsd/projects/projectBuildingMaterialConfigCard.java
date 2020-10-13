package hu.adsd.projects;

import hu.adsd.buildingmaterials.CirculationType;
import hu.adsd.buildingmaterials.Product;
import hu.adsd.buildingmaterials.ProductSort;
import javafx.event.ActionEvent;
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
    private final CirculationType circulationType;
    private final Product product;

    @FXML
    private ComboBox<String> circulationDropdown;

    @FXML
    private Label embodiedCarbonLabel;

    @FXML
    private Label embodiedEnergyLabel;

    public projectBuildingMaterialConfigCard( CirculationType circulationType, Product product )
    {
        this.circulationType = circulationType;
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

        circulationDropdown.getSelectionModel().selectedItemProperty().addListener(
            ( options, oldVal, newVal ) -> updateDropdown()
        );
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        // insert every circulationtype in dropdown
        for(CirculationType type : CirculationType.values())
        {
            circulationDropdown.getItems().add(type.toString());
        }

        // select the right circulationtype per product
        int i = 0;
        for(CirculationType type : CirculationType.values())
        {
            if(product.getCirculationType().equals(type))
            {
                circulationDropdown.getSelectionModel().select(i);
            } else 
            {  
                i++;
            }
        }

        // set carbon and energy amounts
        if(circulationDropdown.getSelectionModel().getSelectedIndex()==CirculationType.values().length-1)
        {
            System.out.println("unset");
        }

        // set label values
        embodiedCarbonLabel.setText("Embodied Carbon: " + product.getMaxCarbonAmount());
        embodiedEnergyLabel.setText("Embodied Energy: " + product.getMinCarbonAmount());

        

        // carbon check changing color function
        changeColor();
    }

    public void updateDropdown() 
    {
        for(CirculationType type : CirculationType.values())
        {
            if(circulationDropdown.getValue().toLowerCase().equals(type.toString().toLowerCase()))
            {
                product.setCirculationType(type);
            }
        }
    }

    private void changeColor()
    {
        embodiedCarbonLabel.setStyle("-fx-text-fill: fff");
        embodiedEnergyLabel.setStyle("-fx-text-fill: fff");

        if(product.getMinCarbonAmount()==0)
        {
            this.setStyle("-fx-background-color: #1287A8");
        } else if(product.getMinCarbonAmount()<=0.25)
        {
            this.setStyle("-fx-background-color: #93A661; -fx-text-fill: fff");
        } else if(product.getMinCarbonAmount()<=0.5)
        {
            this.setStyle("-fx-background-color: #EBC944; -fx-text-fill: fff");
        } else if(product.getMinCarbonAmount()<=0.75)
        {
            this.setStyle("-fx-background-color: #F26D21; -fx-text-fill: fff");
        } else
        {
            this.setStyle("-fx-background-color: #C02F1D; -fx-text-fill: fff");
        }
        
    }
    
}
