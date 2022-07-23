// ==========================================================================
// $Id: Verify.java,v 1.1 2006/10/24 00:21:19 jlang Exp $
// CSI2110 Lab code: BST height comparison
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
// $Log: Verify.java,v $
// Revision 1.1  2006/10/24 00:21:19  jlang
// Added Lab 6
// Revision 1.2 2015/10/31 Lachlan Plant
// (Lachlan needs to add here the details on his update)
// ==========================================================================

import net.datastructures.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Verify
{
    protected int d_totalHeightAVL;
    protected int d_totalHeightBST;
    protected int d_minAVL, d_maxAVL;
    protected int d_minBST, d_maxBST;
    protected int d_noTrees;

    /**
     * Entry point: compare height statistics
     * of different size BST and AVL trees
     */
    public static void main(String[] _argv)
    {
        Verify test          = new Verify();
        int    numNodesStart = 2;
        int    numSteps      = 10;

        if (_argv.length > 0) numSteps = Integer.parseInt(_argv[0]);

        System.out.println("Height Comparison: with and without AVL balancing");
        System.out.printf("%6s %10s %10s %10s %10s %10s %10s %10s\n",
                          "N", "min", "max", "avg",
                          "min", "max", "avg", "log(N)");

        int numNodes = numNodesStart;
        for (int i = 0; i < numSteps; ++i)
        {
            // create regular BST and AVL search trees
            test.randomTrees(numNodes, Math.max(10, numNodes << 2));
            // print statistics
            System.out.printf("%6s ", numNodes);
            test.printStatistics();
            numNodes <<= 1; // multiply by 2
        }
    }

    /**
     * Initialize internal height counters
     */
    protected void initCounters(int _maxHeight, int _noTrees)
    {
        d_minAVL         = _maxHeight + 1;
        d_maxAVL         = -1;
        d_minBST         = _maxHeight + 1;
        d_maxBST         = -1;
        d_totalHeightAVL = 0;
        d_totalHeightBST = 0;
        d_noTrees        = _noTrees;
    }

    /**
     * Update internal height counters after new tree has been constructed
     */
    protected void updateCounters(int _heightAVL, int _heightBST)
    {
        d_totalHeightAVL += _heightAVL;
        d_totalHeightBST += _heightBST;
        if (_heightAVL > d_maxAVL) d_maxAVL = _heightAVL;
        if (_heightAVL < d_minAVL) d_minAVL = _heightAVL;
        if (_heightBST > d_maxBST) d_maxBST = _heightBST;
        if (_heightBST < d_minBST) d_minBST = _heightBST;
    }

    /**
     * Print statistics about the trees generated
     */
    public void printStatistics()
    {
        System.out.printf("%10d %10d %10.3f %10d %10d %10.3f %10.3f\n",
                          d_minBST,
                          d_maxBST,
                          ((double) d_totalHeightBST) / d_noTrees,
                          d_minAVL,
                          d_maxAVL,
                          ((double) d_totalHeightAVL) / d_noTrees,
                          Math.log(d_noTrees));
    }

    /**
     * Generate _nTrees random trees each with _noNodes
     */
    public void randomTrees(int _noNodes, int _nTrees)
    {
        // Initialize minimum and maximum counters
        initCounters(_noNodes, _nTrees);
        // generate _nTrees and update statistics
        for (int i = 0; i < _nTrees; ++i)
        {
            // Create AVL and BST trees using AVLTreeMap and TreeMap respectively.
            AVLTreeMap<Integer, Boolean> treeAVL = new AVLTreeMap<>();
            TreeMap<Integer, Boolean>    treeBST = new TreeMap<>();

            // loop to insert _noNodes keys into tree
            int                   count    = 0;
            Map<Integer, Boolean> keyUsage = new HashMap<>();

            do
            {
                // create random key between 0 and the maximum integer
                int randKey = (int) (Math.random() * Integer.MAX_VALUE);
                // make sure the generated random key is not used before
                if (!keyUsage.containsKey(randKey))
                {
                    // insert into AVL
                    treeAVL.put(randKey, true);

                    // insert into BST
                    treeBST.put(randKey, true);

                    // next key
                    ++count;
                    keyUsage.put(randKey, true);
                }
            }
            while (count < _noNodes);

            updateCounters(height(getInnerTree(treeAVL)), height(getInnerTree(treeBST)));
            // updateCounters(0, 0); // Needs change !!!
        }
    }

    /**
     * Adapt the Euler tour to calculate height (see Lab 4)
     * Note: BST and AVL trees are binary trees
     */
    public <E> int height(BinaryTree<E> bTree)
    {
        return eularTourGetHeight(bTree, bTree.root());
    }

    private <E> int eularTourGetHeight(BinaryTree<E> binaryTree, Position<E> position)
    {
        if (position == null)    // Position does not exist, returning -1 so the height of the leaf will be 0
            return -1;
        else
            return Math.max(eularTourGetHeight(binaryTree, binaryTree.left(position)),
                            eularTourGetHeight(binaryTree, binaryTree.right(position))) + 1;
    }

    @SuppressWarnings("unchecked")
    private <K, V> LinkedBinaryTree<Entry<K, V>> getInnerTree(TreeMap<K, V> treeMap)
    {
        try
        {
            Field field = TreeMap.class.getDeclaredField("tree");
            field.setAccessible(true);

            return (LinkedBinaryTree<Entry<K, V>>) field.get(treeMap);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
