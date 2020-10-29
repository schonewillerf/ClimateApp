package hu.adsd.projects;

import hu.adsd.products.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectProductController implements Initializable
{
    private Product product;

    @FXML
    private ImageView imageComponent;

    @FXML
    private Label nameLabel;

    @FXML
    private Label circulationLabel;

    @FXML
    private Label energyLabel;

    @FXML
    private Label originLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private Button quantityAddButton;

    @FXML
    private Button quantitySubButton;

    @FXML
    private Button modifyButton;

    @FXML
    private Button deleteButton;

    public ProjectProductController( Product product )
    {
        this.product = product;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // Add image to productCardComponent
        InputStream inputStream = getClass().getResourceAsStream( product.getImagePath() );
        javafx.scene.image.Image image = new Image( inputStream );
        imageComponent.setImage( image );

        nameLabel.setText( product.getName() );
        circulationLabel.setText( product.getCirculationType() );
        energyLabel.setText( product.getEmbodiedCandE() );

        // Filler data, needs to be from database
        originLabel.setText( "Lokaal" );

        quantityLabel.setText( String.valueOf(product.getQuantity()) );

        quantityAddButton.setOnAction( event -> addQuantity( product ) );
        quantitySubButton.setOnAction( event -> subQuantity( product ) );
    }

    public void addQuantity( Product product )
    {
        product.setQuantity( product.getQuantity() + 1 );
        quantityLabel.setText( String.valueOf( product.getQuantity() ) );
    }

    public void subQuantity( Product product )
    {
        product.setQuantity( product.getQuantity() - 1 );
        quantityLabel.setText( String.valueOf( product.getQuantity() ) );
    }
}
