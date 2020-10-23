package hu.adsd.projects;

import java.util.ArrayList;
import java.util.List;

public class ProductsConfiguration
{
    private String name;
    private String embodiedEnergy;
    private int numberOfProducts;
    private List<BuildingPart> buildingParts;

    public ProductsConfiguration( String name )
    {
        this.name = name;

        this.embodiedEnergy = "0 mg / 0 kJ";
        this.numberOfProducts = 0;
        this.buildingParts = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getEmbodiedEnergy()
    {
        return embodiedEnergy;
    }

    public void setEmbodiedEnergy( String embodiedEnergy )
    {
        this.embodiedEnergy = embodiedEnergy;
    }

    public int getNumberOfProducts()
    {
        return numberOfProducts;
    }

    public void setNumberOfProducts( int numberOfProducts )
    {
        this.numberOfProducts = numberOfProducts;
    }

    public List<BuildingPart> getBuildingParts()
    {
        return buildingParts;
    }

    public void setBuildingParts( List<BuildingPart> buildingParts )
    {
        this.buildingParts = buildingParts;
    }
}
