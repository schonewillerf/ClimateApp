package hu.adsd.projects;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ProductsTitleComponent extends VBox
{
    public ProductsTitleComponent()
    {
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../titleProductsLabel.fxml" ) );
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
}
