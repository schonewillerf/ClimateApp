package hu.adsd.products;

public class Product implements Cloneable
{
    // Attributes
    private int id;
    private final String name;
    private int quantity;
    private final CirculationType circulationType;
    private final double carbon;
    private final double energy;
    private final double refCarbon;
    private final double refEnergy;
    private final String buildingPart;

    // Constructor
    public Product(
            int id,
            String name,
            int quantity,
            CirculationType circulationType,
            double carbon,
            double energy,
            double refCarbon,
            double refEnergy,
            String buildingPart
    )
    {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.circulationType = circulationType;
        this.carbon = carbon;
        this.energy = energy;
        this.refCarbon = refCarbon;
        this.refEnergy = refEnergy;
        this.buildingPart = buildingPart;
    }

    // Override Methods
    //
    // Check if products match based on id
    @Override
    public boolean equals( Object object )
    {
        if ( object == this ) return true;
        if ( !(object instanceof Product) ) return false;

        return this.id == ( (Product) object ).getId();
    }
    //
    // Should override hashCode if equals is overridden
    @Override
    public int hashCode()
    {
        return id;
    }
    //
    // Might be used for writing Bil of Material
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
                this.carbon,
                this.energy,
                this.quantity
        );
    }

    // Getters and Setters
    //
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

    public double getCarbon()
    {
        return carbon;
    }

    public double getEnergy()
    {
        return energy;
    }

    public String getEmbodiedCandE()
    {
        return String.format( "%.2f mg / %.2f kJ", this.carbon, this.energy );
    }

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

    public String getBuildingPart()
    {
        return buildingPart;
    }

    public double getTotalEmbodiedCarbon()
    {
        return this.carbon * this.quantity;
    }

    public double getTotalEmbodiedJoule()
    {
        return this.energy * this.quantity;
    }
}
