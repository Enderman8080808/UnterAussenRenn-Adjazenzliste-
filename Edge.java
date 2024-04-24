public class Edge
{
    private Vertex[] zKnoten;
    private double zGewicht;
    private boolean zMarkiert;
    
    public Edge(Vertex pVertex, Vertex pAnotherVertex, double pWeight)
    {
        zKnoten = new Vertex[2];
        zKnoten[0] = pVertex;
        zKnoten[1] = pAnotherVertex;
        zGewicht = pWeight;
        zMarkiert = false;
    }

    public void setWeight(double pWeight)
    {
        zGewicht = pWeight;
    }
    
    public double getWeight()
    {
        return zGewicht;
    }
    
    public Vertex[] getVertices ()
    {
        return zKnoten;
    }
    
    public void setMark(boolean pMark)
    {
        zMarkiert = pMark;
    }
    
    public boolean isMarked ()
    {
        return zMarkiert;
    }
}
