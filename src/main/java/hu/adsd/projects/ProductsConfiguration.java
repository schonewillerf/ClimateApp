package hu.adsd.projects;

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

    public void addProduct( Product product )
    {
        // Increment number of products
        this.numberOfProducts += product.getQuantity();

        // Loop over existing BuildingParts
        for ( BuildingPart buildingPart : buildingParts )
        {
            // Check if building part matches product building part
            if ( buildingPart.getName().equals( product.getBuildingPart() ))
            {
                // Add product to building part if there is a match
                buildingPart.getProducts().add( product );
                // Also exit the method after product was added
                return;
            }
        }

        // Will only execute if product is not added yet
        //
        // Add product to new building part
        BuildingPart buildingPart = new BuildingPart( product.getBuildingPart() );
        buildingPart.getProducts().add( product );
        //
        // Add building part to configuration
        buildingParts.add( buildingPart );
    }
}
