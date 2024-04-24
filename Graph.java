public class Graph
{
    private List<Vertex> zKnoten;
    private List<Edge> zKanten;
    
    public Graph()
    {
        zKnoten = new List<Vertex>();    
        zKanten = new List<Edge>();
    }
    
    public void addVertex(Vertex pVertex)
    {
        if (pVertex!= null && pVertex.getID() != null)
        {
            zKnoten.toFirst();
            while (zKnoten.hasAccess())
            {
                if (zKnoten.getContent().getID().equals (pVertex.getID()))
                {
                    return;//Abbruch der Schleife
                }
                zKnoten.next();
            }
            zKnoten.append(pVertex);
        }
    }
    
    private boolean existierenKnotenDerKante(Edge pEdge)
    {
        boolean lExistiert1 = false;
        boolean lExistiert2 = false;
        zKnoten.toFirst();
        while (zKnoten.hasAccess())
        {
            if (zKnoten.getContent().getID().equals(pEdge.getVertices()[0].getID()))
            {
                lExistiert1 = true;
            }
            if (zKnoten.getContent().getID().equals(pEdge.getVertices()[1].getID()))
            {
                lExistiert2 = true;
            }
            zKnoten.next();
        }
        return lExistiert1 && lExistiert2;
    }
    
    public void addEdge(Edge pEdge)
    {
        if (pEdge == null || pEdge.getVertices()[0].getID().equals(pEdge.getVertices()[1].getID()))
        {
            return;//nichts machen
        }
        else
        {
            if (existierenKnotenDerKante(pEdge))
            {
                zKanten.toFirst();
                while (zKanten.hasAccess())
                {
                    if (zKanten.getContent().getVertices()[0].getID().equals(pEdge.getVertices()[0].getID())
                    && zKanten.getContent().getVertices()[1].getID().equals(pEdge.getVertices()[1].getID())
                    || zKanten.getContent().getVertices()[0].getID().equals(pEdge.getVertices()[1].getID())
                    && zKanten.getContent().getVertices()[1].getID().equals(pEdge.getVertices()[0].getID()))
                    {
                        return;//Kante gibt es bereits
                    }
                    zKanten.next();
                }
                zKanten.append(pEdge);
            }
        }
    }
    
    public void removeVertex(Vertex pVertex)
    {
        zKanten.toFirst();
        while (zKanten.hasAccess())
        {
            if (zKanten.getContent().getVertices()[0].getID().equals(pVertex.getID())            
            || zKanten.getContent().getVertices()[1].getID().equals(pVertex.getID()))            
            {
                zKanten.remove();
            }
            zKanten.next();
        }
        zKnoten.toFirst();
        while (zKnoten.hasAccess())
        {
            if (zKnoten.getContent().getID().equals(pVertex.getID()))
            {
                zKnoten.remove();
                zKnoten.toLast();//Abbruch der Schleife
            }
            zKnoten.next();
        }
    }
    
    public void removeEdge(Edge pEdge)
    {
        zKanten.toFirst();
        while (zKanten.hasAccess())
        {
            if (zKanten.getContent().getVertices()[0].getID().equals(pEdge.getVertices()[0].getID())
            && zKanten.getContent().getVertices()[1].getID().equals(pEdge.getVertices()[1].getID())
            || zKanten.getContent().getVertices()[0].getID().equals(pEdge.getVertices()[1].getID())
            && zKanten.getContent().getVertices()[1].getID().equals(pEdge.getVertices()[0].getID()))
            
            {
                zKanten.remove();
                zKanten.toLast();
            }
            zKanten.next();
        }
    }
    
    public Vertex getVertex(String pID)
    {
        zKnoten.toFirst();
        while (zKnoten.hasAccess())
        {
            if (zKnoten.getContent().getID().equals(pID))
                return zKnoten.getContent();
            zKnoten.next();    
        }
        return null;
    }
    
    public List<Vertex> getVertices()
    {
        List<Vertex> lNeu = new List<Vertex>();
        zKnoten.toFirst();
        while (zKnoten.hasAccess())
        {
            lNeu.append(zKnoten.getContent());
            zKnoten.next();
        }
        return lNeu;
    }
    
    public List<Vertex> getNeighbours(Vertex pVertex)
    {
        List<Vertex> lNeu = new List<Vertex>();
        zKanten.toFirst();
        while (zKanten.hasAccess())
        {
            if (zKanten.getContent().getVertices()[0].getID().equals (pVertex.getID()))
                lNeu.append(zKanten.getContent().getVertices()[1]);
            else if (zKanten.getContent().getVertices()[1].getID().equals (pVertex.getID()))
                lNeu.append(zKanten.getContent().getVertices()[0]);
            zKanten.next();
        }
        return lNeu;
    }
    
    public List<Edge> getEdges()
    {
        List<Edge> lNeu = new List<Edge>();
        zKanten.toFirst();
        while (zKanten.hasAccess())
        {
            lNeu.append(zKanten.getContent());
            zKanten.next();
        }
        return lNeu;
    }
    
    public List<Edge> getEdges(Vertex pVertex)
    {
        List<Edge> lNeu = new List<Edge>();
        zKanten.toFirst();
        while (zKanten.hasAccess())
        {
            if (zKanten.getContent().getVertices()[0].getID().equals(pVertex.getID())
            || zKanten.getContent().getVertices()[1].getID().equals(pVertex.getID()))
                lNeu.append(zKanten.getContent());
            zKanten.next();
        }
        return lNeu;
    }
    
    public Edge getEdge(Vertex pVertex, Vertex pAnotherVertex)
    {
        zKanten.toFirst();
        while (zKanten.hasAccess())
        {
            if (zKanten.getContent().getVertices()[0].getID().equals(pVertex.getID())
            && zKanten.getContent().getVertices()[1].getID().equals(pAnotherVertex.getID())
            || zKanten.getContent().getVertices()[1].getID().equals(pVertex.getID())
            && zKanten.getContent().getVertices()[0].getID().equals(pAnotherVertex.getID()))
            {
                return zKanten.getContent();
            }
            zKanten.next();
        }
        return null;
    }
    
    public void setAllVertexMarks(boolean pMark)
    {
        zKnoten.toFirst();
        while (zKnoten.hasAccess())
        {
            zKnoten.getContent().setMark(pMark);
            zKnoten.next();
        }
    }
    
    public boolean allVerticesMarked()
    {
        zKnoten.toFirst();
        while (zKnoten.hasAccess())
        {
            if (! zKnoten.getContent().isMarked())
                return false;
            zKnoten.next();
        }
        return true;
    }
    
    public void setAllEdgeMarks(boolean pMark)
    {
        zKanten.toFirst();
        while (zKanten.hasAccess())
        {
            zKanten.getContent().setMark(pMark);
            zKanten.next();
        }
    }
    
    public boolean allEdgesMarked()
    {
        zKanten.toFirst();
        while (zKanten.hasAccess())
        {
            if (! zKanten.getContent().isMarked())
                return false;
            zKanten.next();
        }
        return true;
    }
    
    public boolean isEmpty()
    {
        return zKnoten.isEmpty();
    }
}
