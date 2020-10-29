package hu.adsd.products;

import hu.adsd.ClimateApp;
import hu.adsd.dataservice.DataServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A class for displaying available products
 */
public class ProductsListController implements Initializable
{
    private int selected;

    // a layout component for stacking products in rows and columns
    @FXML
    private TilePane productsBox;

    public ProductsListController( int selected )
    {
        this.selected = selected;
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        // Initialise with scene with toilet products
        setOrUpdateProductsBox( "toilet" );
    }

    // Method for handling button click to go to other screen
    public void goToProject() throws IOException
    {
        // Change the Scene
        ClimateApp.goToScreen( "projectProductsConfigurationView", selected );
    }

    // Set or Update productsBox TilePane content with ProductCards
    private void setOrUpdateProductsBox( String room )
    {
        DataServiceImpl dataService = new DataServiceImpl();

        // Clear old products from TilePane
        productsBox.getChildren().clear();

        // Loop over products retrieved from DB using Room parameter
        for ( Product product : dataService.getProductsFilteredSortedAndByRoom( "", ProductSort.NAME, room ) )
        {
            // Create loader for loading productCardView VBOX
            FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../productCardView.fxml" ) );

            // Use the ProductCardController Constructor with the current Product from the Loop
            fxmlLoader.setControllerFactory( controller -> new ProductCardController( product, selected ) );

            // Load the VBox into the productsBox
            try
            {
                productsBox.getChildren().add( fxmlLoader.load() );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }

    // Methode for button click selecting bathroom
    public void selectBathroom()
    {
        setOrUpdateProductsBox( "badkamer" );
    }

    // Methode for button click selecting toilet
    public void selectToilet()
    {
        setOrUpdateProductsBox( "toilet" );
    }

    // Methode for button click selecting kitchen
    public void selectKitchen()
    {
        setOrUpdateProductsBox( "keuken" );
    }

    // Methode for button click selecting roof
    public void selectRoof()
    {
        setOrUpdateProductsBox( "dak" );
    }

    public void selectLivingRoom()
    {
        setOrUpdateProductsBox( "woonkamer" );
    }
}
