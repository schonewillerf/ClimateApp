package hu.adsd.products;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductCompaireCardController implements Initializable
{
    private final Product product;

    @FXML
    private Label productNameLabel;

    @FXML
    private ImageView productImage;

    @FXML
    private VBox rootElement;

    public ProductCompaireCardController(Product product)
    {
        this.product = product;
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        // Set backgroundColor in rootElement
        rootElement.setStyle( String.format( "-fx-background-color: %s; -fx-border-color: black; -fx-border-width: 1px", product.getBackgroundColor() ) );

        productNameLabel.setText( product.getName() );

        // Add image to productCardComponent
        InputStream inputStream = getClass().getResourceAsStream( product.getImagePath() );
        Image image = new Image( inputStream );
        productImage.setImage( image );
    }
}
