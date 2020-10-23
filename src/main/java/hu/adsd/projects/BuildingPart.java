package hu.adsd.projects;

import hu.adsd.products.Product;

import java.util.ArrayList;
import java.util.List;

public class BuildingPart
{
    private final String name;
    private final List<Product> products;

    public BuildingPart( String name )
    {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public List<Product> getProducts()
    {
        return products;
    }
}
