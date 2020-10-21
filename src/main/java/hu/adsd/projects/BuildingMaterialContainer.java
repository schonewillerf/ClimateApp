package hu.adsd.projects;

import hu.adsd.buildingmaterials.CirculationType;
import hu.adsd.buildingmaterials.Product;

import java.util.ArrayList;
import java.util.List;

public class BuildingMaterialContainer
{
    // Class properties
    private Product product;
    private final List<CirculationConfiguration> circulationConfigurations;

    // Default constructor
    public BuildingMaterialContainer( Product product, int projectConfigurations )
    {
        this.product = product;

        this.circulationConfigurations = new ArrayList<>();

        for ( int i = 0; i < projectConfigurations; i++ )
        {
            this.getCirculationConfigurations().add( new CirculationConfiguration( CirculationType.REUSED ) );
        }
    }

    // Standard getters and setters
    public Product getProduct()
    {
        return product;
    }

    public void setProduct( Product product )
    {
        this.product = product;
    }

    public List<CirculationConfiguration> getCirculationConfigurations()
    {
        return circulationConfigurations;
    }

    public void addCirculationConfiguration()
    {
        this.getCirculationConfigurations().add( new CirculationConfiguration( CirculationType.REUSED ) );
    }
}
