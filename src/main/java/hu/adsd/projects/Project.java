package hu.adsd.projects;

import hu.adsd.products.Product;

import java.util.ArrayList;
import java.util.List;

public class Project
{
    private List<BuildingPart> projectBuildingParts;
    public final List<String> projectConfigurations;

    public Project()
    {
        this.projectBuildingParts = new ArrayList<>();
        this.projectConfigurations = new ArrayList<>();

        // Initialise a new Project with at least one Configuration
        this.projectConfigurations.add( "Mijn eerste configuratie" );
    }

    // getters and setters
    public List<BuildingPart> getProjectBuildingParts()
    {
        return projectBuildingParts;
    }

    public List<String> getProjectConfigurations(int i)
    {
        return projectConfigurations;
    }

    public void setProjectBuildingParts( List<BuildingPart> buildingParts )
    {
        this.projectBuildingParts = buildingParts;
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
        // Check if Project has at least one Configuration
        if ( this.projectConfigurations.size() < 2 )
        {
            return;
        }

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
                System.out.println( "index of config: " + i );

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

    public void addProductToProject( Product product )
    {
        // Set product quantity to 1
        // As a double check
        product.setQuantity( 1 );

        BuildingMaterialContainer buildingMaterialContainer = new BuildingMaterialContainer(
                product,
                this.projectConfigurations.size()
        );

        for ( BuildingPart buildingPart : this.projectBuildingParts )
        {
            if ( buildingPart.getName().equals( product.getBuildingPart() ) )
            {
                buildingPart.getBuildingMaterialContainers().add( buildingMaterialContainer );
                return;
            }
        }

        BuildingPart buildingPart = new BuildingPart( product.getBuildingPart() );

        buildingPart.getBuildingMaterialContainers().add( buildingMaterialContainer );

        this.projectBuildingParts.add( buildingPart );
    }

    public double getProjectConfigurationTotalCarbon( int index )
    {
        double carbonTotal = 0;

        // Outer loop of BuildingParts
        for ( BuildingPart bP : this.projectBuildingParts )
        {
            // Inner loop of BuildingMaterialContainers
            for ( BuildingMaterialContainer bMC : bP.getBuildingMaterialContainers() )
            {
                //
                carbonTotal += bMC
                        .getProduct()
                        .getTotalEmbodiedCarbon( bMC
                                .getCirculationConfigurations()
                                .get( index )
                                .getCirculationType()
                                .getFactor() );
            }
        }

        return carbonTotal;
    }

    public double getProjectConfigurationTotalEnergy( int index )
    {
        double energyTotal = 0;

        // Outer loop of BuildingParts
        for ( BuildingPart bP : this.projectBuildingParts )
        {
            // Inner loop of BuildingMaterialContainers
            for ( BuildingMaterialContainer bMC : bP.getBuildingMaterialContainers() )
            {
                //
                energyTotal += bMC
                        .getProduct()
                        .getTotalEmbodiedEnergy( bMC
                                .getCirculationConfigurations()
                                .get( index )
                                .getCirculationType()
                                .getFactor() );
            }
        }

        return energyTotal;
    }
}
