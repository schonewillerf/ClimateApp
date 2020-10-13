package hu.adsd.buildingmaterials;

import hu.adsd.buildingmaterials.Product;
import hu.adsd.dataservice.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A custom component class as both the root and controller of the FXML document
 * <p>
 * By extending VBox we get all the functionalities and by serving as the controller as well,
 * we have access to the Material from the constructor during initialization
 */
public class ProductCardComponent extends VBox implements Initializable
{
    private final Product product;

    @FXML
    private Label productNameLabel;

    @FXML
    private Label carbonAmountLabel;

    @FXML
    private Button addButton;

    @FXML
    private ImageView productImage;

    public ProductCardComponent( Product product )
    {
        this.product = product;

        // Loads a product VBox from FXML document and sets root and controller as this class.
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../productCardView.fxml" ) );
        fxmlLoader.setRoot( this );
        fxmlLoader.setController( this );

        try
        {
            fxmlLoader.load();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        productNameLabel.setText( product.getName() );

        // Adds image to productCardComponent
        Image image = new Image(product.getImagePath());
        productImage.setImage(image);


        // Adds lambda expression to addButton action
        // Add sql method
        addButton.setOnAction( event -> new DatabaseHandler().addProductToProjectById( product.getId() ) );
    }
}
