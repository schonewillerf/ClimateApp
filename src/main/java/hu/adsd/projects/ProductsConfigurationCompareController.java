package hu.adsd.projects;

import hu.adsd.ClimateApp;
import hu.adsd.products.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsConfigurationCompareController implements Initializable
{
    ProductsConfiguration productsConfiguration1;
    ProductsConfiguration productsConfiguration2;

    @FXML
    private VBox contentBox;

    public ProductsConfigurationCompareController( int selected1, int selected2 )
    {
        this.productsConfiguration1 = ClimateApp.getProject().getConfigurations().get( selected1 );
        this.productsConfiguration2 = ClimateApp.getProject().getConfigurations().get( selected2 );
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        addContent();

        addTotals();
    }

    private void addContent()
    {
        // Loop over the BuildingParts from productsConfiguration1
        for ( BuildingPart buildingPart : productsConfiguration1.getBuildingParts() )
        {
            // Check if the other productsConfiguration has the same BuildingPart or create an empty BuildingPart
            BuildingPart otherBuildingPart = new BuildingPart( buildingPart.getName() );
            //
            // Returns true if productsConfiguration2 has a building part with the same name
            if ( productsConfiguration2.getBuildingParts().contains( otherBuildingPart ) )
            {
                // Assign the otherConfigBuildingPart to the BuildingPart from the list
                otherBuildingPart = productsConfiguration2
                        .getBuildingParts()
                        .get( productsConfiguration2.getBuildingParts().indexOf( otherBuildingPart ) );
            }

            addBuildingPartsToContent( buildingPart, otherBuildingPart );
        }

        // Loop over building parts that are in the other productsConfiguration only
        //
        // Create a shallow copy from the list first in order to remove non unique building parts non destructive
        List<BuildingPart> uniqueToOtherConfig = new ArrayList<>(productsConfiguration2.getBuildingParts());
        //
        // Remove all non unique building parts from the copied list
        uniqueToOtherConfig.removeAll( productsConfiguration1.getBuildingParts() );
        //
        // Loop over non unique list
        for ( BuildingPart buildingPart : uniqueToOtherConfig )
        {
            addBuildingPartsToContent( new BuildingPart( buildingPart.getName() ), buildingPart );
        }
    }

    private void addBuildingPartsToContent( BuildingPart buildingPart, BuildingPart otherBuildingPart )
    {
        // Create loader for loading productCardView VBOX
        FXMLLoader fxmlLoader =
                new FXMLLoader( getClass().getResource( "../../../productCompaireBuildingPartView.fxml" ) );

        // Use the ProductCardController Constructor with the current Product from the Loop
        fxmlLoader.setControllerFactory( controller -> new ProductCompaireBuildingPartController( buildingPart,
                otherBuildingPart ) );

        // Load the VBox into the productsBox
        try
        {
            contentBox.getChildren().add( fxmlLoader.load() );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    private void addTotals()
    {
        // Add beautiful totals
    }

    public void goToProject() throws IOException
    {
        ClimateApp.goToScreen( "projectView" );
    }
}
