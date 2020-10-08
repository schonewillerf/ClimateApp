package hu.adsd.projects;

import hu.adsd.buildingmaterials.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class projectBuildingMaterialCard extends VBox implements Initializable
{
    private final Product product;

    // Labels buttons etc for BuildingMaterialCard
    @FXML
    private Label nameLabel;

    public projectBuildingMaterialCard( Product product)
    {
        // set the product
        this.product = product;

        // load the project buildingmaterial card FXML VBOX
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../projectBuildingMaterialCardView.fxml" ) );
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

    // Initialise the product card FXML
    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        // Set labels etc
        nameLabel.setText( this.product.getName() );
    }
}
