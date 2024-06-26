public class List<ContentType>
{
    protected Element<ContentType> zKopf;
    protected Element<ContentType> zPositionszeiger;
    protected Element<ContentType> zEnde;

    public List()
    {
        zKopf =  new Element<ContentType>();
        zPositionszeiger = zKopf;
        zEnde = new Element<ContentType>();
        zKopf.setzeNachfolger(zEnde);
        zEnde.setzeVorgaenger(zKopf);
    }
    
    public boolean isEmpty()
    {
        return (zKopf.gibNachfolger() == zEnde);
    }
    
    public boolean hasAccess()
    {
        if (isEmpty() || zPositionszeiger == zKopf || zPositionszeiger == zEnde)
            return false;
        else return true;
    }        
    
    public void next()
    {
        if (hasAccess())
            zPositionszeiger = zPositionszeiger.gibNachfolger();
    }
    
    public void toFirst()
    {
        if (! isEmpty()) zPositionszeiger = zKopf.gibNachfolger();
    }
    
    public void toLast()
    {
        if (! isEmpty()) zPositionszeiger = zEnde.gibVorgaenger();
    }
    
    public ContentType getContent()
    {
        if (! hasAccess()) return null;
        else return zPositionszeiger.gibInhalt(); 
    }
    
    public void setContent(ContentType pContent)
    {
        if (hasAccess() && pContent != null)
            zPositionszeiger.setzeInhalt(pContent);
    }
    
    public void append(ContentType pContent)
    {
        if (pContent != null)
        {
            Element<ContentType> lNeu = new Element<ContentType>();
            lNeu.setzeInhalt(pContent);
            lNeu.setzeVorgaenger(zEnde.gibVorgaenger());
            lNeu.setzeNachfolger(zEnde);
            lNeu.gibVorgaenger().setzeNachfolger(lNeu);
            zEnde.setzeVorgaenger(lNeu);
        }
    }
    
    public void insert(ContentType pContent)
    {
        if (pContent == null || (! isEmpty() && ! hasAccess())){}//nichts machen
        else
        {
            if (isEmpty()) zPositionszeiger = zEnde;
            Element<ContentType> lNeu = new Element<ContentType>();
            lNeu.setzeInhalt(pContent);
            lNeu.setzeNachfolger(zPositionszeiger);
            lNeu.setzeVorgaenger(zPositionszeiger.gibVorgaenger());
            lNeu.gibVorgaenger().setzeNachfolger(lNeu);
            zPositionszeiger.setzeVorgaenger(lNeu);
        }
    }
    
    public void remove()
    {
        if (hasAccess())
        {
            zPositionszeiger.gibVorgaenger().setzeNachfolger(zPositionszeiger.gibNachfolger());
            zPositionszeiger.gibNachfolger().setzeVorgaenger(zPositionszeiger.gibVorgaenger());
            zPositionszeiger = zPositionszeiger.gibNachfolger();
        }
    }
    
    public void concat(List<ContentType> pList)
    {
        if (this != pList && pList != null && ! pList.isEmpty())
        {            
            zEnde.gibVorgaenger().setzeNachfolger(pList.zKopf.gibNachfolger());
            pList.zKopf.gibNachfolger().setzeVorgaenger(zEnde.gibVorgaenger());            
            Element<ContentType> lNeuesEnde = zEnde;//merkt sich das alte Ende
            zEnde = pList.zEnde;//zEnde ist nun das Ende der anderen Liste
            lNeuesEnde.setzeVorgaenger(pList.zKopf);//wird zum Ende der Parameterliste
            pList.zKopf.setzeNachfolger(lNeuesEnde);//Kopf der Parameterliste mit dem neuen Ende der Parameterliste verbinden
            pList.zEnde = lNeuesEnde;
            pList.zPositionszeiger = lNeuesEnde;
        }        
    }
}