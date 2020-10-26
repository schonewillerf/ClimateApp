package hu.adsd.projects;

import hu.adsd.products.CirculationType;
import hu.adsd.products.Product;

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

        // Add some random products for testing the BOM generation
        // Code is only temporary until adding products works properly again
        BuildingPart myTest = new BuildingPart( "dak" );
        myTest.getProducts().add( new Product( 1, "Raymond", 1, CirculationType.LINEAR, 0,0,0,0,"dak" ) );
        myTest.getProducts().add( new Product( 2, "is", 100000, CirculationType.LINEAR, 0,0,0,0,"dak" ) );
        myTest.getProducts().add( new Product( 3, "Awesome", 9999999, CirculationType.LINEAR, 0,0,0,0,"dak" ) );
        this.buildingParts.add( myTest );
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
