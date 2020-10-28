package hu.adsd.projects;

import hu.adsd.products.Product;

import java.util.HashSet;
import java.util.Set;

public class BuildingPart
{
    private final String name;
    private final Set<Product> products;

    public BuildingPart( String name )
    {
        this.name = name;
        this.products = new HashSet<>();
    }

    public String getName()
    {
        return name;
    }

    public Set<Product> getProducts()
    {
        return products;
    }

    @Override
    public boolean equals( Object object )
    {
        if ( object == null ) return false;
        if ( !( object instanceof BuildingPart ) ) return false;
        if ( object == this ) return true;

        return this.getName().equals( ( (BuildingPart) object ).getName() );
    }

    @Override
    public int hashCode()
    {
        return this.getName().hashCode();
    }
}
