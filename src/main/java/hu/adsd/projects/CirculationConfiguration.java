package hu.adsd.projects;

import hu.adsd.buildingmaterials.CirculationType;

public class CirculationConfiguration
{
    private CirculationType circulationType;

    public CirculationConfiguration( CirculationType circulationType )
    {
        this.circulationType = circulationType;
    }

    public CirculationType getCirculationType()
    {
        return circulationType;
    }

    public void setCirculationType( CirculationType circulationType )
    {
        this.circulationType = circulationType;
    }
}
