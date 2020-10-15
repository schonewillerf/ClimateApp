package hu.adsd.buildingmaterials;

public enum CirculationType
{
    LOCALREUSE("Locaal hergebruik", 0.01, "#66CD00"),
    REUSED( "Hergebruikt", 0.05, "#28993b" ),
    REPAIRED( "Gerepareerd", 0.10, "#fbb117" ), // Beer color
    REMANUFACTURED( "Hergefabriceerd", 0.20, "#fa9016" ),
    RECYCLED( "Recycled", 0.40, "#F26D21" ),
    LINEAR( "Nieuw", 1, "#C02F1D");

    private final String dutch;
    private final double factor;
    private final String colorCode;

    CirculationType( final String dutch, final double factor, final String colorCode )
    {
        this.dutch = dutch;
        this.factor = factor;
        this.colorCode = colorCode;
    }

    @Override
    public String toString()
    {
        return dutch;
    }

    public double getFactor()
    {
        return factor;
    }

    public String getColorCode()
    {
        return colorCode;
    }
}
