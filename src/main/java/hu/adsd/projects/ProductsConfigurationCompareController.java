package hu.adsd.projects;

import hu.adsd.ClimateApp;
import hu.adsd.products.Product;
import hu.adsd.products.ProductCardController;
import hu.adsd.products.ProductCompaireCardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class ProductsConfigurationCompareController implements Initializable
{
    ProductsConfiguration productsConfiguration1;
    ProductsConfiguration productsConfiguration2;

    @FXML
    private VBox content1Box;

    @FXML
    private VBox content2Box;

    public ProductsConfigurationCompareController( int selected1, int selected2 )
    {
        this.productsConfiguration1 = ClimateApp.getProject().getConfigurations().get( selected1 );
        this.productsConfiguration2 = ClimateApp.getProject().getConfigurations().get( selected2 );
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        System.out.println( "The following ProductConfigurations were generously provided by your SM" );
        System.out.println( "ProductConfiguration 1. " + productsConfiguration1.getName() );
        System.out.println( "ProductConfiguration 2. " + productsConfiguration2.getName() );

        addContent( productsConfiguration1.getBuildingParts(), content1Box );
        addContent( productsConfiguration2.getBuildingParts(), content2Box );
    }

    private void addContent( Set<BuildingPart> buildingParts, VBox contentBox )
    {
        for ( BuildingPart buildingPart : buildingParts )
        {
            for ( Product product : buildingPart.getProducts() )
            {
                // Create loader for loading productCardView VBOX
                FXMLLoader fxmlLoader =
                        new FXMLLoader( getClass().getResource( "../../../productCompaireCardView.fxml" ) );

                // Use the ProductCardController Constructor with the current Product from the Loop
                fxmlLoader.setControllerFactory( controller -> new ProductCompaireCardController( product ) );

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
        }
    }

    public void goToProject() throws IOException
    {
        ClimateApp.goToScreen( "projectView" );
    }
}
