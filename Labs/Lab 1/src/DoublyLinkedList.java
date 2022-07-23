class DoublyLinkedList
{
    DNode head;
    DNode tail;
    
    DoublyLinkedList(int sz)
    {
        // Dummy nodes
        head = new DNode(null, null, null);
        tail = new DNode(null, null, null);
        
        head.setNext(tail);
        
        DNode nodePrevious = head;
        DNode nodeCurrent;
        
        if (sz > 0)
        {
            // add further nodes
            for (int i = 0; i < sz; ++i)
            {
                // create node and attach it to the list
                nodeCurrent = new DNode(Integer.toString(i), nodePrevious, tail);
                nodePrevious.setNext(nodeCurrent);
                nodePrevious = nodeCurrent;
            }
        }
        
        tail.setPrevious(nodePrevious);
    }
    
    // create and display a linked list
    public static void main(String[] args)
    {
        /* Create the list */
        DoublyLinkedList list = new DoublyLinkedList(5);
        /* Print the list */
        list.print();
        /* delete first and print */
        list.deleteFirst();
        list.print();
        /* delete last and print 5 times */
        for (int i = 0; i < 5; ++i)
        {
            list.deleteLast();
            list.print();
        }
    }
    
    /**
     * Print all the elements of the list assuming that they are Strings
     */
    public void print()
    {
        /* Print the list */
        DNode current = head.getNext(); // point to the first node
        while (current != tail)
        {
            System.out.print((String) current.getElement() + " ");
            current = current.getNext(); // move to the next
        }
        System.out.println();
    }
    
    public void deleteFirst()
    {
        DNode node = head.getNext();
        if (node != tail)
        {
            head = head.getNext();
        }
    }
    
    public void deleteLast()
    {
        DNode node = tail.getPrevious();
        if (node != head)
        {
            tail = tail.getPrevious();
        }
    }
}
