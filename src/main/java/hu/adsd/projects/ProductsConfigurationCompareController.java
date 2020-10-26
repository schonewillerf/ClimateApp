package hu.adsd.projects;

import hu.adsd.ClimateApp;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductsConfigurationCompareController implements Initializable
{
    ProductsConfiguration productsConfiguration1;
    ProductsConfiguration productsConfiguration2;

    public ProductsConfigurationCompareController( int selected1, int selected2)
    {
        this.productsConfiguration1 = ClimateApp.getProject().getConfigurations().get( selected1 );
        this.productsConfiguration2 = ClimateApp.getProject().getConfigurations().get( selected2 );
    }

    @Override
    public void initialize( URL url, ResourceBundle resourceBundle )
    {
        System.out.println( "The following ProductConfigurations were generously provided by your SM");
        System.out.println( "ProductConfiguration 1. " + productsConfiguration1.getName() );
        System.out.println( "ProductConfiguration 2. " + productsConfiguration2.getName() );
    }

    public void goToProject() throws IOException
    {
        ClimateApp.goToScreen( "projectView" );
    }
}
