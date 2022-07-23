/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 * LinkedBinarySearchTree for tree traversal lab
 *
 * @param <E>
 *
 * @author Lachlan Plant
 */
public class LinkedBinarySearchTree<E extends Comparable<E>> implements Iterable<E>
{
    private static class Node<E>
    {
        E       elem;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        
        public Node(E e, Node<E> p, Node<E> l, Node<E> r)
        {
            elem   = e;
            parent = p;
            left   = l;
            right  = r;
        }
    }
    
    private Node<E> root;
    private int     size;
    
    /**
     *
     */
    public LinkedBinarySearchTree()
    {
        root = null;
        size = 0;
    }
    
    /**
     * Adds elem into BST
     *
     * @param elem
     *
     * @return
     */
    public boolean add(E elem)
    {
        if (root == null)
        {
            root = new Node<>(elem, null, null, null);
            size++;
            return true;
        }
        else
        {
            root = insert(elem, root, null);
            return true;
        }
    }
    
    /**
     * Recursive BST insertion
     *
     * @param elem
     * @param curr
     * @param from
     *
     * @return
     */
    private Node<E> insert(E elem, Node<E> curr, Node<E> from)
    {
        if (curr == null)
        {
            curr = new Node<>(elem, from, null, null);
            size++;
            return curr;
        }
        if (elem.compareTo(curr.elem) < 0)
        {
            curr.left = insert(elem, curr.left, curr);
        }
        if (elem.compareTo(curr.elem) > 0)
        {
            curr.right = insert(elem, curr.right, curr);
        }
        return curr;
    }
    
    
    /*****************************************************************
     *
     * Recursive Printing Functions
     *
     *****************************************************************/
    
    /**
     * Caller method for preorder recursive printing
     */
    public void printPreorderRecursive()
    {
        System.out.print("Recursive Preorder Printing: ");
        preorderRecursive(root);
        System.out.println();
    }
    
    /**
     * preorder tree traversal, prints(curr.elem + ", ")
     *
     * @param curr
     */
    private void preorderRecursive(Node<E> curr)
    {
        //Implement Here
        System.out.print(curr.elem + ", ");
        if (curr.left != null)
            preorderRecursive(curr.left);
        if (curr.right != null)
        preorderRecursive(curr.right);
    }
    
    /**
     * Caller method for inorder recursive printing
     */
    public void printInorderRecursive()
    {
        System.out.print("Recursive Inorder Printing: ");
        inorderRecursive(root);
        System.out.println();
    }
    
    /**
     * inorder tree traversal, prints(curr.elem + ", ")
     *
     * @param curr
     */
    private void inorderRecursive(Node<E> curr)
    {
        //Implement Here
        if (curr.left != null)
            inorderRecursive(curr.left);
        System.out.print(curr.elem + ", ");
        if (curr.right != null)
            inorderRecursive(curr.right);
    }
    
    
    /**
     * Caller method for postorder recursive printing
     */
    public void printPostorderRecursive()
    {
        System.out.print("Recursive Postorder Printing: ");
        postorderRecursive(root);
        System.out.println();
    }
    
    /**
     * postorder tree traversal, prints(curr.elem + ", ")
     *
     * @param curr
     */
    private void postorderRecursive(Node<E> curr)
    {
        //Implement Here
        if (curr.left != null)
            postorderRecursive(curr.left);
        if (curr.right != null)
            postorderRecursive(curr.right);
        System.out.print(curr.elem + ", ");
    }
    
    
    /*****************************************************************
     *
     * Iterator Functions
     *
     *****************************************************************/
    public Iterator iterator()
    {
        return new InorderIterator();
    }
    
    public Iterator inorderIterator()
    {
        return new InorderIterator();
    }
    
    public Iterator preorderIterator()
    {
        return new PreorderIterator();
    }
    
    
    /*****************************************************************
     *
     * Iterators
     *
     *****************************************************************/
    
    
    /**
     * Tree Iterator using preorder traversal for ordering
     */
    private class PreorderIterator implements Iterator<E>
    {
        Node<E> next;
        private final Stack<Node<E>> stack;
        
        public PreorderIterator()
        {
            //Implement Here
            next = root;
            stack = new Stack<>();
        }
        
        public boolean hasNext()
        {
            return next != null || !stack.empty();
        }
        
        public E next()
        {
            Node<E> node = null;
            
            if (next != null)
                stack.push(next);
            
            if (!stack.empty())
            {
                node = stack.pop();
                if (node.left != null)
                    stack.push(node.right);
                next = node.left;
            }
            
            return node != null ? node.elem : null;
        }
        
        public void remove()
        {
            // not implemented
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * Tree Iterator using inorder traversal for ordering
     */
    private class InorderIterator implements Iterator<E>
    {
        
        Node<E> next;
        private final Stack<Node<E>> stack;
        
        public InorderIterator()
        {
            //Implement Here
            next = root;
            stack = new Stack<>();
        }
        
        public boolean hasNext()
        {
            return next != null || !stack.empty();
        }
        
        public E next()
        {
            Node<E> node = null;
            
            while (next != null)
            {
                stack.push(next);
                next = next.left;
            }
            
            if (!stack.empty())
            {
                node = stack.pop();
                next = node.right;
            }
    
            return node != null ? node.elem : null;
        }
        
        public void remove()
        {
            // not implemented
            throw new UnsupportedOperationException();
        }
    }
}
