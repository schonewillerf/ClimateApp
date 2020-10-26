package hu.adsd.projects;

import java.util.ArrayList;
import java.util.List;

public class Project
{
    private List<ProductsConfiguration> configurations;

    public Project()
    {
        this.configurations = new ArrayList<>();

        // Project should have at least one configuration
        this.configurations.add( new ProductsConfiguration( "Mijn eerste configuratie" ) );
        this.configurations.add( new ProductsConfiguration( "Een bonus configuratie" ) );
    }

    public List<ProductsConfiguration> getConfigurations()
    {
        return configurations;
    }

    public void setConfigurations( List<ProductsConfiguration> configurations )
    {
        this.configurations = configurations;
    }
}
