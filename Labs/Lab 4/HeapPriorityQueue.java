/**
 * Array Heap implementation of a priority queue
 *
 * @author Lachlan Plant
 */
public class HeapPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V>
{
    private final Entry<K, V>[] storage; //The Heap itself in array form
    private       int           tail;        //Index of last element in the heap
    
    /**
     * Default constructor
     */
    public HeapPriorityQueue()
    {
        this(16);
    }
    
    /**
     * HeapPriorityQueue constructor with max storage of size elements
     */
    @SuppressWarnings("unchecked")
    public HeapPriorityQueue(int size)
    {
        storage = new Entry[size];
        tail    = -1;
    }
    
    /****************************************************
     *
     *             Priority Queue Methods
     *
     ****************************************************/
    
    /**
     * Returns the number of items in the priority queue.
     * O(1)
     *
     * @return number of items
     */
    public int size()
    {
        return tail + 1;
    }
    
    /**
     * Tests whether the priority queue is empty.
     * O(1)
     *
     * @return true if the priority queue is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return tail == -1;
    }
    
    /**
     * Inserts a key-value pair and returns the entry created.
     * O(log(n))
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     *
     * @return the entry storing the new key-value pair
     *
     * @throws IllegalArgumentException if the heap is full
     */
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException
    {
        if (tail >= storage.length - 1)
            throw new IllegalArgumentException();
        else
        {
            storage[++tail] = new Entry<>(key, value);
            upHeap(tail);
            return storage[tail];
        }
    }
    
    /**
     * Returns (but does not remove) an entry with minimal key.
     * O(1)
     *
     * @return entry having a minimal key (or null if empty)
     */
    public Entry<K, V> min()
    {
        if (!isEmpty())
            return storage[0];
        else
            return null;
    }
    
    /**
     * Removes and returns an entry with minimal key.
     * O(log(n))
     *
     * @return the removed entry (or null if empty)
     */
    public Entry<K, V> removeMin()
    {
        if (isEmpty())
            return null;
        
        Entry<K, V> entry = storage[0];
        swap(0, tail);
        storage[tail--] = null;
        downHeap(0);
        
        return entry;
    }
    
    
    /****************************************************
     *
     *           Methods for Heap Operations
     *
     ****************************************************/
    
    /**
     * Algorithm to place element after insertion at the tail.
     * O(log(n))
     */
    private void upHeap(int location)
    {
        while (location > 0)
        {
            if (storage[location].getKey().compareTo(storage[parent(location)].getKey()) <= 0)
            {
                swap(location, parent(location));
                location = parent(location);
            }
            else
                break;
        }
    }
    
    /**
     * Algorithm to place element after removal of root and tail element placed at root.
     * O(log(n))
     */
    private void downHeap(int location)
    {
        int leftIndex;
        int rightIndex;
        int smallerIndex;
        
        while ((leftIndex = location * 2 + 1) <= tail)
        {
            smallerIndex = leftIndex;
            if ((rightIndex = location * 2 + 2) <= tail)
            {
                if (storage[leftIndex].getKey().compareTo(storage[rightIndex].getKey()) > 0)
                    smallerIndex = rightIndex;
            }
            if (storage[smallerIndex].getKey().compareTo(storage[location].getKey()) < 0)
            {
                swap(location, smallerIndex);
                location = smallerIndex;
            }
            else
                break;
        }
    }
    
    /**
     * Find parent of a given location,
     * Parent of the root is the root
     * O(1)
     */
    private int parent(int location)
    {
        return (location - 1) / 2;
    }
    
    
    /**
     * Inplace swap of 2 elements, assumes locations are in array
     * O(1)
     */
    private void swap(int location1, int location2)
    {
        Entry<K, V> entry = storage[location1];
        storage[location1] = storage[location2];
        storage[location2] = entry;
    }
}
