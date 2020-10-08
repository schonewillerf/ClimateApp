package hu.adsd.projects;

import hu.adsd.buildingmaterials.CirculationType;
import hu.adsd.buildingmaterials.Product;

import java.util.ArrayList;
import java.util.List;

public class BuildingMaterialContainer
{
    // Class properties
    private Product product;
    private List<CirculationType> circulationTypes;

    // Default constructor
    public BuildingMaterialContainer( Product product, int numberOfConfigurations )
    {
        this.product = product;

        this.circulationTypes = new ArrayList<>();

        for( int i = 0; i < numberOfConfigurations; i++)
        {
            circulationTypes.add( CirculationType.UNSET );
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

    public List<CirculationType> getCirculationTypes()
    {
        return circulationTypes;
    }

    public void setCirculationTypes( List<CirculationType> circulationTypes )
    {
        this.circulationTypes = circulationTypes;
    }
}
