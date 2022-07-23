/**
 * Interface for a stack: a collection of objects that are inserted
 * and removed according to the last-in first-out principle.
 *
 * @author Roberto Tamassia
 * @author Michael Goodrich
 * @see EmptyStackException
 */

public interface Stack
{
    /**
     * Return the number of elements in the stack.
     *
     * @return number of elements in the stack.
     */
    int size();
    
    /**
     * Return whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    boolean isEmpty();
    
    /**
     * Inspect the element at the top of the stack.
     *
     * @return top element in the stack.
     *
     * @throws EmptyStackException if the stack is empty.
     */
    Object top()
            throws EmptyStackException;
    
    /**
     * Insert an element at the top of the stack.
     *
     * @param element element to be inserted.
     */
    void push(Object element);
    
    /**
     * Remove the top element from the stack.
     *
     * @return element removed.
     *
     * @throws EmptyStackException if the stack is empty.
     */
    Object pop()
            throws EmptyStackException;
}

