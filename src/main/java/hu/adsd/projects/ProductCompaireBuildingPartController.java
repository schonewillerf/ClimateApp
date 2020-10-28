package hu.adsd.projects;

import hu.adsd.products.Product;
import hu.adsd.products.ProductCompaireCardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductCompaireBuildingPartController implements Initializable
{
    private BuildingPart buildingPart;
    private BuildingPart otherBuildingPart;

    @FXML
    private Label buildingPartLabel;

    @FXML
    private VBox contentBox;

    @FXML
    private VBox otherContentBox;

    public ProductCompaireBuildingPartController( BuildingPart buildingPart, BuildingPart otherBuildingPart )
    {
        this.buildingPart = buildingPart;
        this.otherBuildingPart = otherBuildingPart;
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        buildingPartLabel.setText( buildingPart.getName() );

        // Loop over products in buildingPart first
        for ( Product product : buildingPart.getProducts() )
        {
            // Add product to contentBox
            addProductToContent( product, contentBox );

            // Check if the other buildingPart has the same product
            if ( otherBuildingPart.getProducts().contains( product ) )
            {
                Product otherProduct = otherBuildingPart
                        .getProducts()
                        .get( otherBuildingPart.getProducts().indexOf( product ) );

                // Add it to the UI
                addProductToContent( otherProduct, otherContentBox );
            }
            else
            {
                // addSpacer( otherContentBox );
            }
        }

        // Loop over products that are in the otherBuildingPart only
        //
        // Create a shallow copy from the list first in order to remove non unique products non destructive
        List<Product> uniqueToOtherConfig = new ArrayList<>( otherBuildingPart.getProducts() );
        //
        // Remove all non unique products from the copied list
        uniqueToOtherConfig.removeAll( buildingPart.getProducts() );
        //
        // Loop over the non unique list
        for ( Product product : uniqueToOtherConfig )
        {
            addProductToContent( product, otherContentBox );
        }
    }

    private void addProductToContent( Product product, VBox box )
    {
        // Create loader for loading productCardView VBOX
        FXMLLoader fxmlLoader =
                new FXMLLoader( getClass().getResource( "../../../productCompaireCardView.fxml" ) );

        // Use the ProductCardController Constructor with the current Product from the Loop
        fxmlLoader.setControllerFactory( controller -> new ProductCompaireCardController( product ) );

        // Load the VBox into the productsBox
        try
        {
            box.getChildren().add( fxmlLoader.load() );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }
}
