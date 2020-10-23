package hu.adsd.projects;

import hu.adsd.ClimateApp;

import java.io.IOException;

public class ProjectController
{
    public void toProductConfiguration() throws IOException
    {
        ClimateApp.goToScreen( "projectProductsConfigurationView" );
    }
}
