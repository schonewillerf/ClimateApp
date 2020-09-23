package hu.adsd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductsListController implements Initializable
{
    @FXML
    private TilePane productsBox;

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        MaterialDatabaseParser db = new MaterialDatabaseParser();

        for ( Material material : db.getMaterialList() )
        {
            // Add Custom ProductCard Components
            productsBox.getChildren().add( new ProductCardComponent( material ) );
        }
    }
}
