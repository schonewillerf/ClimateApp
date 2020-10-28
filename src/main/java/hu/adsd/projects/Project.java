package hu.adsd.projects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Project
{
    private String projectName;
    private List<ProductsConfiguration> configurations;

    // Project Constructor
    public Project( String projectName, String configurationName )
    {
        // Set the projectName
        this.projectName = projectName;

        // Create an empty list and ad one configuration with name
        this.configurations = new ArrayList<>();
        this.configurations.add( new ProductsConfiguration( configurationName ) );
    }

    public List<ProductsConfiguration> getConfigurations()
    {
        return configurations;
    }

}
