// ==========================================================================
// $Id: addTemplate.cpp,v 1.1 2005/11/02 23:13:32 jlang Exp $
// CSI2110 Lab code Node List skeleton class
// ==========================================================================
// (C)opyright:
//
//   Jochen Lang
//   SITE, University of Ottawa
//   800 King Edward Ave.
//   Ottawa, On., K1N 6N5
//   Canada. 
//   http://www.site.uottawa.ca
// 
// Creator: jlang (Jochen Lang)
// Email:   jlang@site.uottawa.ca
// ==========================================================================
// $Log: addTemplate.cpp,v $
//
// ==========================================================================

import java.util.*;

public class NodeList<E>
{
    // The linked list which is to be adapted
    protected LinkedList<E> linkedList = new LinkedList<E>();
    
    // Directly available
    public int size()
    {
        return linkedList.size();
    }
    
    // Directly available
    public boolean isEmpty()
    {
        return linkedList.isEmpty();
    }
    
    // fixed
    public boolean isFirst(E element)
    {
        return element == first();
    }
    
    // fixed
    public boolean isLast(E element)
    {
        return element == last();
    }
    
    // Directly available
    public E first()
            throws NoSuchElementException
    {
        return linkedList.element();
    }
    
    // Directly available
    public E last()
            throws NoSuchElementException
    {
        return linkedList.getLast();
    }
    
    // fixed
    E prev(E element)
            throws NoSuchElementException
    {
        if (element == null)
            throw new NullPointerException();
        
        ListIterator<E> listIterator = linkedList.listIterator();
        
        while (listIterator.hasNext())
            if (listIterator.next() == element)
            {
                listIterator.previous();
                return listIterator.previous();
            }
        
        throw new NoSuchElementException();
    }
    
    
    // fixed
    E next(E element)
            throws NoSuchElementException
    {
        if (element == null)
            throw new NullPointerException();
        
        ListIterator<E> listIterator = linkedList.listIterator();
        
        while (listIterator.hasNext())
            if (listIterator.next() == element)
                return listIterator.next();
        
        throw new NoSuchElementException();
    }
    
    // fixed
    public void swapElements(E element1, E element2)
            throws NoSuchElementException
    {
        boolean flag1 = false, flag2 = false;
        
        if (element1 != null && element2 != null)
        {
            ListIterator<E> iterator1 = linkedList.listIterator();
            ListIterator<E> iterator2 = linkedList.listIterator();
            
            while (iterator1.hasNext())
            {
                if (iterator1.next() == element1)
                {
                    flag1 = true;
                    break;
                }
            }
            while (iterator2.hasNext())
            {
                if (iterator2.next() == element2)
                {
                    flag2 = true;
                    break;
                }
            }
            
            if (flag1 && flag2)
            {
                iterator1.set(element2);
                iterator2.set(element1);
            }
        }
        
        if (!(flag1 && flag2))
            throw new NoSuchElementException();
    }
    
    
    // fixed
    public void set(E currElement, E repElement)
            throws NoSuchElementException
    {
        if (currElement == null || repElement == null)
            throw new NullPointerException();
        
        ListIterator<E> listIterator = linkedList.listIterator();
        
        while (listIterator.hasNext())
        {
            if (listIterator.next() == currElement)
            {
                listIterator.set(repElement);
                return;
            }
        }
        
        throw new NoSuchElementException();
    }
    
    
    // Directly available
    public void addFirst(E element)
    {
        linkedList.addFirst(element);
    }
    
    // Directly available
    public void addLast(E element)
    {
        linkedList.addLast(element);
    }
    
    // fixed
    public void addBefore(E currElement, E addElement)
            throws NoSuchElementException
    {
        if (currElement == null || addElement == null)
            throw new NullPointerException();
        
        ListIterator<E> listIterator = linkedList.listIterator();
        while (listIterator.hasNext())
        {
            if (listIterator.next() == currElement)
            {
                listIterator.previous();
                listIterator.add(addElement);
                return;
            }
        }
        
        throw new NoSuchElementException();
    }
    
    // fixed
    public void addAfter(E currElement, E addElement)
            throws NoSuchElementException
    {
        if (currElement == null || addElement == null)
            throw new NullPointerException();
        
        ListIterator<E> listIterator = linkedList.listIterator();
        while (listIterator.hasNext())
        {
            if (listIterator.next() == currElement)
            {
                listIterator.add(addElement);
                return;
            }
        }
        
        throw new NoSuchElementException();
    }
    
    // fixed
    public E remove(E element)
            throws NoSuchElementException
    {
        if (element == null)
            throw new NullPointerException();
        
        ListIterator<E> listIterator = linkedList.listIterator();
        while (listIterator.hasNext())
        {
            E currentElement = listIterator.next();
            if (currentElement == element)
            {
                listIterator.remove();
                return currentElement;
            }
        }
        
        throw new NoSuchElementException();
    }
}
