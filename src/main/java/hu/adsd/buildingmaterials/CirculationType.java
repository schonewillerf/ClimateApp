package hu.adsd.buildingmaterials;

public enum CirculationType
{
    fossiele_energie( 1, "#1287A8" ),
    secundaire_grondstoffen( 1, "#93A661" ),
    REUSED( 0.50, "#DAA82F" ),
    NEW( 1, "#F26D21" ),
    LINEAR( 1, "#C02F1D" ),
    UNSET( 0, "#C02F1D" );

    private final double factor;
    private final String colorCode;

    CirculationType( final double factor, final String colorCode )
    {
        this.factor = factor;
        this.colorCode = colorCode;
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
