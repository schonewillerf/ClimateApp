package hu.adsd.projects;

import hu.adsd.products.Product;

import java.util.ArrayList;
import java.util.List;

public class BuildingPart
{
    // Attributes
    private final String name;
    private final List<Product> products;

    // Constructor
    public BuildingPart( String name )
    {
        this.name = name;
        this.products = new ArrayList<>();
    }

    // Overrides
    //
    // Check if BuildingPart is same based on name
    @Override
    public boolean equals( Object object )
    {
        if ( object == this ) return true;
        if ( !( object instanceof BuildingPart ) ) return false;

        return this.name.equals( ( (BuildingPart) object ).getName() );
    }
    //
    // Should override hashCode if equals is overridden
    @Override
    public int hashCode()
    {
        return this.name.hashCode();
    }

    // Getters
    //
    public String getName()
    {
        return name;
    }
    //
    public List<Product> getProducts()
    {
        return products;
    }

    // Add product to BuildingPart
    public void addProduct( Product product )
    {
        // Check if product is already in BuildingPart
        // Returns true if products have same id
        if ( products.contains( product ) )
        {
            // Get the product from the list
            Product oldProduct = products.get( products.indexOf( product ) );

            // Increment the quantity of the product
            oldProduct.setQuantity( oldProduct.getQuantity() + product.getQuantity() );
        }
        else
        {
            // Add the new product to list
            products.add( product );
        }
    }
}
