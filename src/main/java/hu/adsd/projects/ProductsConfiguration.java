package hu.adsd.projects;

import java.util.ArrayList;
import java.util.List;

public class ProductsConfiguration
{
    private String name;
    private List<BuildingPart> buildingParts;

    public ProductsConfiguration( String name )
    {
        this.name = name;
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

    public List<BuildingPart> getBuildingParts()
    {
        return buildingParts;
    }

    public void setBuildingParts( List<BuildingPart> buildingParts )
    {
        this.buildingParts = buildingParts;
    }
}
