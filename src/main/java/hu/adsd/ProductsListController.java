package hu.adsd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A class for displaying available products
 */
public class ProductsListController implements Initializable
{
    // a layout component for stacking products in rows and columns
    @FXML
    private TilePane productsBox;

    @FXML
    private Button toiletButton,kitchenButton,bathroomButton,roofButton;

    @FXML
    private ComboBox<ProductSort> sortingBox;

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        // Populate Sorting Box
        sortingBox.getItems().setAll( ProductSort.values() );

        // Set initial sorting state
        sortingBox.getSelectionModel().select( ProductSort.CARBON );

        sortingBox.getSelectionModel().selectedItemProperty().addListener(
                ( options, oldVal, newVal ) -> setOrUpdateProducts( newVal )
        );

        // Set products
        setOrUpdateProducts( ProductSort.CARBON );
    }

    // Method for handling button click to go to other screen
    public void goToProject() throws IOException
    {
        ClimateApp.goToScreen( "projectView" ); // Change the Scene
    }

    private void setOrUpdateProducts( ProductSort sort )
    {
        // Remove all items from ProductCard
        productsBox.getChildren().clear();

        // Create a connection with the database.
        // Could be replaced with future API class.
        DatabaseHandler db = new DatabaseHandler();

        // Loops over products from the database.
        for ( Product product : db.getProductsList( sort ) )
        {
            // Add Custom ProductCard Components to TilePane.
            productsBox.getChildren().add( new ProductCardComponent( product ) );
        }
    }

    public void selectBathroom()
    {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        productsBox.getChildren().clear();

        for ( Product product : databaseHandler.getProductByRoom( "badkamer" ) )
        {
            productsBox.getChildren().add( new ProductCardComponent( product ) );
        }
    }

    public void selectToilet()
    {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        productsBox.getChildren().clear();

        for ( Product product : databaseHandler.getProductByRoom( "toilet" ) )
        {
            productsBox.getChildren().add( new ProductCardComponent( product ) );
        }
    }

    public void selectKitchen()
    {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        productsBox.getChildren().clear();

        for ( Product product : databaseHandler.getProductByRoom( "keuken" ) )
        {
            productsBox.getChildren().add( new ProductCardComponent( product ) );
        }
    }

    public void selectRoof()
    {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        productsBox.getChildren().clear();

        for ( Product product : databaseHandler.getProductByRoom( "dak" ) )
        {
            productsBox.getChildren().add( new ProductCardComponent( product ) );
        }
    }
}
