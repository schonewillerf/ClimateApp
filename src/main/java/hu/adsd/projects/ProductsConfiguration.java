package hu.adsd.projects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hu.adsd.products.Product;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProductsConfiguration
{
    private String name;
    private String embodiedEnergy;
    private int numberOfProducts;
    private List<BuildingPart> buildingParts;

    // Constructor with overloading
    //
    // Constructor without BuildingParts parameter
    public ProductsConfiguration( String name )
    {
        this( name, new ArrayList<>() );
    }
    //
    //  Constructor with BuildingParts parameter
    public ProductsConfiguration( String name, List<BuildingPart> buildingParts )
    {
        this.name = name;

        // Create deep copy from config with gson library
        /**
         * In a perfect world I would like to build my own house, grow my own food and make my own clothes,
         * but usually there isn't that much time so I go to the shop. Similarly I could have written a
         * method in order make a deep copy and though it feels like overkill, Gson saved some time and effort :)
         */
        Gson gson = new Gson();
        Type setType = new TypeToken<ArrayList<BuildingPart>>(){}.getType();
        this.buildingParts = gson.fromJson( gson.toJson( buildingParts ), setType );

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
        // Get existing or new BuildingPart
        BuildingPart buildingPart = getExistingOrNewBuildingPart( product.getBuildingPart() );

        // Add product to BuildingPart
        buildingPart.addProduct( product );

        // Update products and energy
        updateProductsAndEnergy();
    }

    private BuildingPart getExistingOrNewBuildingPart( String buildingPartName )
    {
        BuildingPart buildingPart = new BuildingPart( buildingPartName );

        // Check if BuildingPart is already in Configuration
        //
        // Returns true if names are same
        if ( buildingParts.contains( buildingPart ) )
        {
            // Make buildingPart reference the BuildingPart from list
            buildingPart = buildingParts.get( buildingParts.indexOf( buildingPart ) );
        }
        else
        {
            // Add the new BuildingPart to Configuration
            buildingParts.add( buildingPart );
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
