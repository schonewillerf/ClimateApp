package hu.adsd.projects;

import hu.adsd.buildingmaterials.CirculationType;
import hu.adsd.buildingmaterials.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private Label circulationTypeLabel;

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
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        circulationTypeLabel.setText( this.circulationType.toString() );

        // carbon check changing color function
        changeColor();
    }

    public void buttonTest()
    {
        System.out.println("test van knop");
    }

    private void changeColor()
    {
        circulationTypeLabel.setStyle("-fx-text-fill: fff");
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
