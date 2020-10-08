package hu.adsd.projects;

import hu.adsd.buildingmaterials.CirculationType;
import hu.adsd.buildingmaterials.Product;

import java.util.ArrayList;
import java.util.List;

public class BuildingPart
{
    String name;
    List<BuildingMaterialContainer> buildingMaterialContainers;

    public BuildingPart( String name )
    {
        this.name = name;
        this.buildingMaterialContainers = new ArrayList<>();

        // Add some dummy products
        this.buildingMaterialContainers.add(
                new BuildingMaterialContainer(
                        new Product(
                                1,
                                "Raymond is awesome",
                                3,
                                3,
                                CirculationType.fossiele_energie,
                                1
                        ),
                        4
                )
        );

        this.buildingMaterialContainers.add(
                new BuildingMaterialContainer(
                        new Product(
                                1,
                                "and handsome",
                                3,
                                3,
                                CirculationType.fossiele_energie,
                                1
                        ),
                        4
                )
        );

    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public List<BuildingMaterialContainer> getBuildingMaterialContainers()
    {
        return buildingMaterialContainers;
    }

    public void setBuildingMaterialContainers( List<BuildingMaterialContainer> buildingMaterialContainers )
    {
        this.buildingMaterialContainers = buildingMaterialContainers;
    }
}
