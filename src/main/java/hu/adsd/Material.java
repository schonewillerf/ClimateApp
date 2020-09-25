package hu.adsd;

public class Material
{
    private int id;
    private String name;
    private String carbonAmount;
    private CirculationType circulationType;
    private int quantity;

    public Material( int id, String name, String carbon, CirculationType circulationType, int quantity )
    {
        this.id = id;
        this.name = name;
        this.carbonAmount = carbon;
        this.circulationType = circulationType;
        this.quantity = quantity;
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

    public String getCarbonAmount()
    {
        return carbonAmount;
    }

    public void setCarbonAmount( String carbonAmount )
    {
        this.carbonAmount = carbonAmount;
    }

    public CirculationType getCirculationType()
    {
        return circulationType;
    }

    public void setCirculationType( CirculationType circulationType )
    {
        this.circulationType = circulationType;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return String.format(
                "Id: %s, Naam: %s, Hoeveelheid koolstof: %s, Circulatietype: %s, Kwantiteit: %s,",
                this.id,
                this.name,
                this.carbonAmount,
                this.circulationType,
                this.quantity
        );
    }

    public float getTotalCarbonAmount()
    {
        return Float.valueOf( this.carbonAmount ) * this.quantity;
    }
}
