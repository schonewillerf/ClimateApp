package hu.adsd.buildingmaterials;

public class Product
{
    private int id;
    private String name;
    private double minCarbonAmount;
    private double maxCarbonAmount;
    // private CirculationType circulationType;
    private int quantity;
    private String imagePath;
    private String buildingPart;

    public Product(
            int id,
            String name,
            double minCarbon,
            double maxCarbon,
            // CirculationType circulationType,
            int quantity,
            String buildingPart
    )
    {
        this.id = id;
        this.name = name;
        this.minCarbonAmount = minCarbon;
        this.maxCarbonAmount = maxCarbon;
        // this.circulationType = circulationType;
        this.quantity = quantity;
        this.buildingPart = buildingPart;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public double getMinCarbonAmount()
    {
        return minCarbonAmount;
    }

    public void setMinCarbonAmount( double minCarbonAmount )
    {
        this.minCarbonAmount = minCarbonAmount;
    }

    public double getMaxCarbonAmount()
    {
        return maxCarbonAmount;
    }

    public void setMaxCarbonAmount( double maxCarbonAmount )
    {
        this.maxCarbonAmount = maxCarbonAmount;
    }

    /*public CirculationType getCirculationType()
    {
        return circulationType;
    }

    public void setCirculationType( CirculationType circulationType )
    {
        this.circulationType = circulationType;
    }*/

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }

    public String getImagePath()
    {
        return String.format( "../../../images/%s.jpg", this.id );
    }

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

    public String getBuildingPart()
    {
        return buildingPart;
    }

    public void setBuildingPart( String buildingPart )
    {
        this.buildingPart = buildingPart;
    }

    @Override
    public String toString()
    {
        return String.format(
                "Id: %s, Naam: %s, " +
                        "Minimale hoeveelheid koolstof: %s, " +
                        "Maximale hoeveelheid koolstof: %s, " +
                        "Kwantiteit: %s,",
                this.id,
                this.name,
                this.minCarbonAmount,
                this.maxCarbonAmount,
                // this.circulationType,
                this.quantity
        );
    }

    public double getTotalMinCarbonAmount()
    {
        return this.minCarbonAmount * this.quantity;
    }

    public double getTotalMaxCarbonAmount()
    {
        return this.maxCarbonAmount * this.quantity;
    }

    public double getTotalEmbodiedCarbon( double factor)
    {
        return this.minCarbonAmount * this.quantity * factor;
    }

    public double getTotalEmbodiedEnergy( double factor )
    {
        return this.maxCarbonAmount * this.quantity * factor;
    }
}
