package hu.adsd.projects;

import hu.adsd.products.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductsConfiguration
{
    private String name;
    private String embodiedEnergy;
    private int numberOfProducts;
    private Set<BuildingPart> buildingParts;

    // Constructor with overloading
    //
    // Constructor without BuildingParts parameter
    public ProductsConfiguration( String name )
    {
        this( name, new HashSet<>() );
    }
    //
    //  Constructor with BuildingParts parameter
    public ProductsConfiguration( String name, Set<BuildingPart> buildingParts )
    {
        this.name = name;
        this.buildingParts = new HashSet<>();

        for ( BuildingPart buildingPart : buildingParts )
        {
            this.buildingParts.add( buildingPart );
        }

        updateProductsAndEnergy();
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

    public Set<BuildingPart> getBuildingParts()
    {
        return buildingParts;
    }

    public void setBuildingParts( Set<BuildingPart> buildingParts )
    {
        this.buildingParts = buildingParts;
    }


    public void addProduct( Product product )
    {
        // Get existing or new BuildingPart
        BuildingPart buildingPart = getExistingOrNewBuildingPart( product.getBuildingPart() );

        // Add product to BuildingPart
        buildingPart.getProducts().add( product );

        // Update products and energy
        updateProductsAndEnergy();
    }

    private BuildingPart getExistingOrNewBuildingPart( String buildingPartName )
    {
        BuildingPart buildingPart = new BuildingPart( buildingPartName );

        if ( !buildingParts.contains( buildingPart ) )
        {
            buildingParts.add( buildingPart );
        }
        else
        {
            for ( BuildingPart existingBuildingPart : buildingParts )
            {
                if ( existingBuildingPart.getName().equals( buildingPartName ) )
                {
                    buildingPart = existingBuildingPart;
                }
            }
        }

        return buildingPart;
    }

    private void updateProductsAndEnergy()
    {
        int numberOfProductsCount = 0;
        double totalCarbon = 0;
        double totalJoule = 0;

        for ( BuildingPart buildingPart : buildingParts )
        {
            for ( Product product : buildingPart.getProducts() )
            {
                numberOfProductsCount += product.getQuantity();
                totalCarbon += product.getTotalEmbodiedCarbon();
                totalJoule += product.getTotalEmbodiedJoule();
            }
        }

        this.numberOfProducts = numberOfProductsCount;
        this.embodiedEnergy = String.format( "%s mg / %s kJ", totalCarbon, totalJoule );
    }
}
