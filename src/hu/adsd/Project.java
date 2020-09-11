package hu.adsd;

import java.util.ArrayList;
import java.util.List;

public class Project
{
    private List<Material> projectMaterials;

    public Project()
    {
        this.projectMaterials = new ArrayList<>();
    }

    public List<Material> getProjectMaterials()
    {
        return projectMaterials;
    }

    public void addProjectMaterial( Material material )
    {
        material.setQuantity( 1 );
        this.projectMaterials.add( material );
    }

    public void removeProjectMaterial( int index )
    {
        this.projectMaterials.remove( index );
    }
}
