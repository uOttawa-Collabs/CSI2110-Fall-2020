public class DNode
{
    private Object element;
    private DNode  previous;
    private DNode  next;
    
    DNode() { this(null, null, null); }
    
    DNode(Object e, DNode p, DNode n)
    {
        element  = e;
        previous = p;
        next     = n;
    }
    
    public Object getElement()                 { return element; }
    
    public void setElement(Object newElem)     { element = newElem; }
    
    public DNode getPrevious()                 { return previous; }
    
    public void setPrevious(DNode newPrevious) { previous = newPrevious; }
    
    public DNode getNext()                     { return next; }
    
    public void setNext(DNode newNext)         { next = newNext; }
}
