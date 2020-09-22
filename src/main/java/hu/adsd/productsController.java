package hu.adsd;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class productsController implements Initializable
{
    @FXML
    private VBox productsBox;

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        MaterialDatabaseParser db = new MaterialDatabaseParser();

        for ( Material material : db.getMaterialList() )
        {
            /*VBox vBox = new VBox();

            vBox.getChildren().add( new Label( material.getName() ) );
            vBox.getChildren().add( new Label( String.valueOf( material.getCarbonAmount() ) ) );

            Button button = new Button( "click me");

            vBox.getChildren().add( button );

            productsBox.getChildren().add( vBox );*/

            try
            {
                VBox vBox = (VBox) FXMLLoader.load( getClass().getResource( "../../productCard.fxml" ) );

                productsBox.getChildren().add( vBox );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }
}
