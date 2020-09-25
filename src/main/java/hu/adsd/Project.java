package hu.adsd;

import java.util.ArrayList;
import java.util.List;

public class Project
{
    private List<Product> projectProducts;

    public Project()
    {
        this.projectProducts = new ArrayList<>();
    }

    public List<Product> getProjectMaterials()
    {
        return projectProducts;
    }

    public void addProjectMaterial( Product product )
    {
        this.projectProducts.add( product );
    }

    public void removeProjectMaterial( int index )
    {
        this.projectProducts.remove( index );
    }

    public void updateProjectMaterial( int index, Product product )
    {
        this.projectProducts.set( index, product );
    }

    public Product getProjectMaterial( int index )
    {
        return projectProducts.get( index );
    }
}
