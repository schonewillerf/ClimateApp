package hu.adsd.projects;

import hu.adsd.ClimateApp;
import hu.adsd.buildingmaterials.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class projectBuildingMaterialCard extends VBox implements Initializable
{
    private final Product product;

    private boolean editQuantity;

    // Labels buttons etc for BuildingMaterialCard
    @FXML
    private Label nameLabel;

    @FXML
    private ImageView productImage;

    @FXML
    private TextField quantityField;

    @FXML
    private Button editOrSaveButton;

    @FXML
    private Button cancelButton;

    public projectBuildingMaterialCard( Product product )
    {
        // set the product
        this.product = product;

        // load the project buildingmaterial card FXML VBOX
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource( "../../../projectBuildingMaterialCardView.fxml" )
        );
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

    // Initialise the product card FXML
    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        // Set labels etc
        nameLabel.setText( this.product.getName() );

        InputStream inputStream = getClass().getResourceAsStream( this.product.getImagePath() );
        Image image = new Image( inputStream );
        productImage.setImage( image );

        quantityField.setText( Integer.toString( this.product.getQuantity() ) );

        editOrSaveButton.setOnAction( event -> editOrSaveButtonClicked() );
        cancelButton.setOnAction( event -> cancelButtonClicked() );
    }

    void editOrSaveButtonClicked()
    {
        if ( !editQuantity )
        {
            System.out.println( "make editable" );

            quantityField.setEditable( true );
            editOrSaveButton.setText( "Opslaan" );
            cancelButton.setDisable( false );

            editQuantity = true;
        }
        else
        {
            System.out.println("save quantity");

            this.product.setQuantity( Integer.parseInt( quantityField.getText() ) );

            try
            {
                ClimateApp.goToScreen( "projectView" );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }

    void cancelButtonClicked()
    {
        System.out.println("cancel edit");

        quantityField.setEditable( false );
        quantityField.setText( Integer.toString( this.product.getQuantity() ) );

        editOrSaveButton.setText( "Aanpassen" );
        cancelButton.setDisable( true );
    }
}
