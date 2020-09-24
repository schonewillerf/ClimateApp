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
        this.projectMaterials.add( material );
    }

    public void removeProjectMaterial( int index )
    {
        this.projectMaterials.remove( index );
    }

    public void updateProjectMaterial( int index, Material material )
    {
        this.projectMaterials.set( index, material );
    }

    public Material getProjectMaterial( int index )
    {
        return projectMaterials.get( index );
    }
}
