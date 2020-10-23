package hu.adsd.projects;

import hu.adsd.ClimateApp;

import java.io.IOException;

public class ProductsConfigurationController
{

    public void goToProject() throws IOException
    {
        ClimateApp.goToScreen( "projectView" );
    }

    public void goToAvailableMaterials() throws IOException
    {
        ClimateApp.goToScreen( "productsListView" );
    }
}
