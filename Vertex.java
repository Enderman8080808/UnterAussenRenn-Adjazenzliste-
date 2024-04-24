public class Vertex
{
    private String zID;
    private boolean zMarkiert;
    
    public Vertex(String pID)
    {
        zID = pID;
        zMarkiert = false;
    }
    
    public String getID()
    {
        return zID;
    }
    
    public void setMark(boolean pMark)
    {
        zMarkiert = pMark;
    }
    
    public boolean isMarked()
    {
        return zMarkiert;
    }
}
