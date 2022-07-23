/**
 * A simple node class for a singly-linked list.  Each node has a
 * reference to a stored element and a next node.
 * This class is based on the <code>DNode</code> class by Roberto Tamassia.
 *
 * @author Jochen Lang
 */

public class Node
{
    private Object element;
    private Node   next;
    
    Node() { this(null, null); }
    
    Node(Object e, Node n)
    {
        element = e;
        next    = n;
    }
    
    public Object getElement()             { return element; }
    
    public void setElement(Object newElem) { element = newElem; }
    
    public Node getNext()                  { return next; }
    
    public void setNext(Node newNext)      { next = newNext; }
}
