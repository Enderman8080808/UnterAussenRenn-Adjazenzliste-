public class Testklasse
{
    private Graph zGraph;
    
    public Testklasse()
    {
        zGraph = new Graph();
        
        Vertex l1 = new Vertex("1");
        Vertex l2 = new Vertex("2");
        Vertex l3 = new Vertex("3");
        Vertex l4 = new Vertex("4");
        
        zGraph.addVertex(l1);
        zGraph.addVertex(l2);
        zGraph.addVertex(l3);
        zGraph.addVertex(l4);
        
        zGraph.addEdge(new Edge(l1, l3, 0));
        zGraph.addEdge(new Edge(l3, l4, 0));
        zGraph.addEdge(new Edge(l1, l2, 0));
    }
    
    public void tiefendurchlauf(String pStartID)
    {
        Vertex lStart = zGraph.getVertex(pStartID);
        if (lStart != null)
        {
            Stack<Vertex> lRand = new Stack<Vertex>();
            lRand.push(lStart);
            lStart.setMark(true);
            tiefendurchlauf(lRand);
            zGraph.setAllVertexMarks(false);
        }
    }
    
    public void tiefendurchlauf(Stack<Vertex> pStack)
    {                        
        List<Vertex> lNachbarn = zGraph.getNeighbours(pStack.top());
        lNachbarn.toFirst();
        while(lNachbarn.hasAccess())
        {
            if (! lNachbarn.getContent().isMarked())
            {
                lNachbarn.getContent().setMark(true);
                pStack.push(lNachbarn.getContent());
                System.out.println(lNachbarn.getContent().getID());
                tiefendurchlauf(pStack);
            }
            lNachbarn.next();
        }            
        pStack.pop();                    
    }
    
    public List<Vertex> unteraussenrenn(String pID){
        Vertex lID = zGraph.getVertex(pID);
        Stack<Vertex> lStack = new Stack<>();
        List<Vertex> lReihenfolge = new List<>();
        
        if(lID != null){
            lStack.push(lID);
            lReihenfolge.append(lID);
            lID.setMark(true);
            lReihenfolge = unteraussenrenn(lStack, lReihenfolge);
            zGraph.setAllVertexMarks(false);
        }
        
        return lReihenfolge;
    }
    
    public List<Vertex> unteraussenrenn(Stack<Vertex> pStack, List<Vertex> pReihenfolge){
        List<Vertex> lNachbar = zGraph.getNeighbours(pStack.top());
        lNachbar.toFirst();
        
        while(lNachbar.hasAccess()){
            if(!lNachbar.getContent().isMarked()){
                lNachbar.getContent().setMark(true);
                pStack.push(lNachbar.getContent());
                pReihenfolge.append(lNachbar.getContent());
                pReihenfolge.concat(unteraussenrenn(pStack ,pReihenfolge));
            }
            lNachbar.next();
        }
        
        return pReihenfolge;
    }
}
