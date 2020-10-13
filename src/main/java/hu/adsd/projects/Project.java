package hu.adsd.projects;

import java.util.ArrayList;
import java.util.List;

public class Project
{
    private List<BuildingPart> projectBuildingParts;

    private List<String> projectConfigurations;

    public Project()
    {
        this.projectBuildingParts = new ArrayList<>();
        this.projectConfigurations = new ArrayList<>();
    }

    // getters and setters
    public List<BuildingPart> getProjectBuildingParts()
    {
        return projectBuildingParts;
    }

    public List<String> getProjectConfigurations()
    {
        return projectConfigurations;
    }

    public void setProjectBuildingParts(List<BuildingPart> buildingParts)
    {
        this.projectBuildingParts = buildingParts;
    }

    public void setProjectConfigurations(List<String> configurations)
    {
        this.projectConfigurations = configurations;
    }

}
