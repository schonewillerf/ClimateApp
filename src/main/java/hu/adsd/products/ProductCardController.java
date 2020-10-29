package hu.adsd.products;

import hu.adsd.ClimateApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductCardController implements Initializable
{
    private final Product product;
    private final int selected;

    @FXML
    private Label productNameLabel;

    @FXML
    private Button addButton;

    @FXML
    private ImageView productImage;

    @FXML
    private VBox rootElement;

    public ProductCardController(Product product, int selected)
    {
        this.selected = selected;
        this.product = product;
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        // Set backgroundColor in rootElement
        rootElement.setStyle( String.format( "-fx-background-color: %s", product.getBackgroundColor() ) );

        productNameLabel.setText( product.getName() );

        // Add image to productCardComponent
        InputStream inputStream = getClass().getResourceAsStream( product.getImagePath() );
        Image image = new Image( inputStream );
        productImage.setImage( image );


        // Use lambda expression to addButton action
         addButton.setOnAction( event -> ClimateApp.addProductToProject( this.product, selected ) );
    }
}
