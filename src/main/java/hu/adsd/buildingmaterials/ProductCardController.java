package hu.adsd.buildingmaterials;

import hu.adsd.ClimateApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductCardController implements Initializable
{
    private final Product product;

    @FXML
    private Label productNameLabel;

    @FXML
    private Button addButton;

    @FXML
    private ImageView productImage;

    public ProductCardController(Product product)
    {
        this.product = product;
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        productNameLabel.setText( product.getName() );

        // Add image to productCardComponent
        InputStream inputStream = getClass().getResourceAsStream( product.getImagePath() );
        Image image = new Image( inputStream );
        productImage.setImage( image );


        // Use lambda expression to addButton action
        addButton.setOnAction( event -> ClimateApp.getProject().addProductToProject( this.product ) );
    }
}
