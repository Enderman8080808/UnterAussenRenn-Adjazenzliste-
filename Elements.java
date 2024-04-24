public class Elements<ContentType>
{
    private ContentType zInhalt;
    private Elements<ContentType> zVorgaenger;
    
    public Elements()
    {
        zInhalt = null;
    }
    
    public ContentType gibInhalt()
    {
        return zInhalt;
    }
    
    public void setzeInhalt(ContentType pInhalt)
    {
        zInhalt = pInhalt;
    }
    
    public Elements<ContentType> gibVorgaenger()
    {
        return zVorgaenger;
    }
    
    public void setzeVorgaenger(Elements<ContentType> pVorgaenger)
    {
        zVorgaenger = pVorgaenger;
    }
}
