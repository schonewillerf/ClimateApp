package hu.adsd.projects;

import hu.adsd.products.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuildingPartController implements Initializable
{
    private BuildingPart buildingPart;

    @FXML
    private Label nameLabel;

    @FXML
    private VBox buildingMaterialContainerBox;

    public BuildingPartController( BuildingPart buildingPart )
    {
        this.buildingPart = buildingPart;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        nameLabel.setText( buildingPart.getName() );
        for ( Product product : buildingPart.getProducts() )
        {
            // Create loader for loading productCardView VBOX
            FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../variantCard.fxml" ) );

            // Use the ProductCardController Constructor with the current Product from the Loop
            fxmlLoader.setControllerFactory( controller -> new ProjectProductController( product ) );

            // Load the VBox into the productsBox
            try
            {
                buildingMaterialContainerBox.getChildren().add( fxmlLoader.load() );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }
}
