public class Node
{
    // Instance variables:
    private Object element;
    private Node   next;
    
    /**
     * Creates a node with null references to its element and next node.
     */
    public Node()
    {
        this(null, null);
    }
    
    /**
     * Creates a node with the given element and next node.
     */
    public Node(Object e, Node n)
    {
        element = e;
        next    = n;
    }
    
    // Accessor methods:
    public Object getElement()
    {
        return element;
    }
    
    // Modifier methods:
    public void setElement(Object newElem)
    {
        element = newElem;
    }
    
    public Node getNext()
    {
        return next;
    }
    
    public void setNext(Node newNext)
    {
        next = newNext;
    }
}

