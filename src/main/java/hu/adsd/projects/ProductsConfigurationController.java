package hu.adsd.projects;

import hu.adsd.ClimateApp;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductsConfigurationController implements Initializable
{
    ProductsConfiguration productsConfiguration;

    public ProductsConfigurationController( int selectedConfiguration )
    {
        this.productsConfiguration = ClimateApp
                .getProject()
                .getConfigurations()
                .get( selectedConfiguration );
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        System.out.println( "The following ProductConfigurations was generously provided by your SM");
        System.out.println( "ProductConfiguration " + productsConfiguration.getName() );
    }

    public void goToProject() throws IOException
    {
        ClimateApp.goToScreen( "projectView" );
    }

    public void goToAvailableMaterials() throws IOException
    {
        ClimateApp.goToScreen( "productsListView" );
    }
}
