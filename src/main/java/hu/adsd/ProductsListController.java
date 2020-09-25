package hu.adsd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;

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

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        // Create a connection with the database.
        // Could be replaced with future API class.
        DatabaseHandler db = new DatabaseHandler();

        // Loops over products from the database.
        for ( Product product : db.getMaterialList() )
        {
            // Add Custom ProductCard Components to TilePane.
            productsBox.getChildren().add( new ProductCardComponent( product ) );
        }
    }
}
