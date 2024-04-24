/**
 * @author T. Hammersen
 * @version Januar 2021
 */
public class Stack<ContentType>
{
    private Elements<ContentType> zEnde;

    public Stack()
    {
        zEnde = new Elements<ContentType>();
    }
    
    public boolean isEmpty()
    {
        return zEnde.gibVorgaenger() == null;
    }
    
    public void push (ContentType pContent)
    {
        if (pContent != null)
        {
            Elements<ContentType> lNeu = new Elements<ContentType>();
            lNeu.setzeInhalt(pContent);
            lNeu.setzeVorgaenger(zEnde.gibVorgaenger());
            zEnde.setzeVorgaenger(lNeu);
        }
    }
    
    public void pop()
    {
        if (! isEmpty())
            zEnde.setzeVorgaenger(zEnde.gibVorgaenger().gibVorgaenger());
    }
    
    public ContentType top()
    {
        if (isEmpty()) return null;
        else return zEnde.gibVorgaenger().gibInhalt();
    }
}
