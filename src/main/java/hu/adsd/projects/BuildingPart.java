package hu.adsd.projects;

import java.util.ArrayList;
import java.util.List;

public class BuildingPart
{
    private final String name;
    private final List<BuildingMaterialContainer> buildingMaterialContainers;

    public BuildingPart( String name )
    {
        this.name = name;
        this.buildingMaterialContainers = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public List<BuildingMaterialContainer> getBuildingMaterialContainers()
    {
        return buildingMaterialContainers;
    }
}
