package hu.adsd.projects;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class ProductsTotalComponent extends VBox
{
    public ProductsTotalComponent()
    {
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "../../../projectProductsTotal.fxml" ) );
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

    public void buttonTest()
    {
        System.out.println( "test van knop" );
    }
}
