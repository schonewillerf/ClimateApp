package hu.adsd.projects;

import hu.adsd.ClimateApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductsConfigurationController implements Initializable
{
    private ProductsConfiguration productsConfiguration;
    private int selectedConfiguration;

    @FXML
    private Label refEnergy;

    @FXML
    private Label totalEnergy;

    @FXML
    private Label differenceEnergy;

    @FXML
    private VBox buildingPartsBox;

    public ProductsConfigurationController( int selectedConfiguration )
    {
        this.selectedConfiguration = selectedConfiguration;
        this.productsConfiguration = ClimateApp
                .getProject()
                .getConfigurations()
                .get( selectedConfiguration );
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        // Create BuildingParts with inner BuildingMaterials
        for ( BuildingPart buildingPart : productsConfiguration.getBuildingParts() )
        {
            // Eerst nieuw vierkant maken

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

        refEnergy.setText( "Lineair: " + productsConfiguration.getEmbodiedEnergy() );
        totalEnergy.setText( "Huidig: " + productsConfiguration.getEmbodiedEnergy() );
        differenceEnergy.setText( "Bespaard: " + productsConfiguration.getEmbodiedEnergy() );

    }

    public void goToProject() throws IOException
    {
        ClimateApp.goToScreen( "projectView" );
    }

    public void addProduct() throws IOException
    {
        ClimateApp.goToProductsScreen( selectedConfiguration );
    }
}
