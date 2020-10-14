package hu.adsd.projects;

import hu.adsd.buildingmaterials.Product;

import java.util.ArrayList;
import java.util.List;

public class Project
{
    private List<BuildingPart> projectBuildingParts;
    private List<String> projectConfigurations;

    public Project()
    {
        this.projectBuildingParts = new ArrayList<>();
        this.projectConfigurations = new ArrayList<>();
    }

    // getters and setters
    public List<BuildingPart> getProjectBuildingParts()
    {
        return projectBuildingParts;
    }

    public List<String> getProjectConfigurations()
    {
        return projectConfigurations;
    }

    public void setProjectBuildingParts(List<BuildingPart> buildingParts)
    {
        this.projectBuildingParts = buildingParts;
    }

    public void setProjectConfigurations(List<String> configurations)
    {
        this.projectConfigurations = configurations;
    }

    public void addConfiguration()
    {
        if ( this.projectConfigurations.size() < 4 )
        {
            // Add a new configuration if the number of configurations is less than 4
            this.projectConfigurations.add( String.format( "Configuratie %s", this.projectConfigurations.size() + 1 ) );

            // Also add a new Config for each BuildingMaterial in BuildingMaterialContainer in BuildingPart
            //
            // Outer loop of BuildingParts
            for ( BuildingPart bP : this.projectBuildingParts )
            {
                // Inner loop of BuildingMaterialContainers
                for ( BuildingMaterialContainer bMC : bP.getBuildingMaterialContainers() )
                {
                    // Add a new CirculationType of 'UNSET' to the list
                    bMC.addCirculationConfiguration();
                }
            }
        }
    }

    public void removeConfiguration( String configuration )
    {
        // Initialise an int for storing the index
        int configIndex = 0;

        // Get the index from the to be removed configuration
        for ( int i = 0; i < this.projectConfigurations.size(); i++ )
        {
            if ( this.projectConfigurations.get( i ).equals( configuration ) )
            {
                // This confirms the hypotheses that sometimes configs with the same name
                // Are mistaken with the same index
                // During Sprint Review only remove the most right columns!!
                System.out.println("index of config: " + i);
                
                configIndex = i;
            }
        }

        // Remove the Configuration from Project
        this.projectConfigurations.remove( configuration );

        // Also remove the Config for each BuildingMaterial in BuildingMaterialContainer in BuildingPart
        //
        // Outer loop of BuildingParts
        for ( BuildingPart bP : this.projectBuildingParts )
        {
            // Inner loop of BuildingMaterialContainers
            for ( BuildingMaterialContainer bMC : bP.getBuildingMaterialContainers() )
            {
                // Add a new CirculationType of 'UNSET' to the list
                bMC.getCirculationConfigurations().remove( configIndex );
            }
        }
        
    }

    public void addProductToProject( Product product, String buildingPartName )
    {
        BuildingMaterialContainer buildingMaterialContainer = new BuildingMaterialContainer(
                product,
                this.projectConfigurations.size()
        );

        for ( BuildingPart buildingPart : this.projectBuildingParts )
        {
            if ( buildingPart.getName().equals( buildingPartName ) )
            {
                buildingPart.getBuildingMaterialContainers().add( buildingMaterialContainer );
                return;
            }
        }

        BuildingPart buildingPart = new BuildingPart( buildingPartName );

        buildingPart.getBuildingMaterialContainers().add( buildingMaterialContainer );

        this.projectBuildingParts.add( buildingPart );
    }
}
