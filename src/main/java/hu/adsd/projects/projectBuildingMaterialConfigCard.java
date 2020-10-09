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
    }

    public void buttonTest()
    {
        System.out.println("test van knop");
    }
    
}
