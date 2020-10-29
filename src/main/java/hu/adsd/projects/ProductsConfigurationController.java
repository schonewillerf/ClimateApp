package hu.adsd.projects;

import hu.adsd.ClimateApp;
import hu.adsd.products.Product;
import hu.adsd.products.ProductCardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductsConfigurationController implements Initializable
{
    @FXML
    private VBox buildingPartsBox;

    ProductsConfiguration productsConfiguration;

    public ProductsConfigurationController( int selectedConfiguration )
    {
        this.productsConfiguration = ClimateApp
                .getProject()
                .getConfigurations()
                .get( selectedConfiguration );
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        System.out.println( "The following ProductsConfiguration was generously provided by your SM");
        System.out.println( "ProductConfiguration " + productsConfiguration.getName() );


        // Create BuildingParts with inner BuildingMaterials
        for ( BuildingPart buildingPart : productsConfiguration.getBuildingParts() )
        {
            // Eerst nieuw vierkant maken
            /*
            buildingspartbox.getchildren().add(
             */
            // Create loader for loading productCardView VBOX
            FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../projectBuildingPartView.fxml" ) );

            // Use the ProductCardController Constructor with the current Product from the Loop
            fxmlLoader.setControllerFactory( controller -> new BuildingPartController( buildingPart ) );

            // Load the VBox into the productsBox
            try
            {
                buildingPartsBox.getChildren().add( fxmlLoader.load() );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }

        }

    }

    public void goToProject() throws IOException
    {
        ClimateApp.goToScreen( "projectView" );
    }

    public void goToAvailableMaterials() throws IOException
    {
        ClimateApp.goToScreen( "productsListView" );
    }
}
