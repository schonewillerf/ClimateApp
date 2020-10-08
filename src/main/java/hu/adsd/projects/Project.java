package hu.adsd.projects;

import java.util.ArrayList;
import java.util.List;

public class Project
{
    private List<BuildingPart> projectBuildingParts;

    public Project()
    {
        this.projectBuildingParts = new ArrayList<>();
    }

    public List<BuildingPart> getProjectBuildingParts()
    {
        return projectBuildingParts;
    }
}
