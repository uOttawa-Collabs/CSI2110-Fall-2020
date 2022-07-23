import java.util.Arrays;
import java.util.Stack;

class Pair<K extends Comparable<K>, V>
        implements Comparable<Pair<K, V>>
{
    private final K key;
    private final V value;

    public Pair(K key, V value)
    {
        this.key   = key;
        this.value = value;
    }

    public K getKey()
    {
        return key;
    }

    public V getValue()
    {
        return value;
    }

    @Override
    public int compareTo(Pair<K, V> o)
    {
        return key.compareTo(o.key);
    }

}

public class PartTwo
{
    public static void main(String[] args)
    {
        testSet1();
        testSet2();
    }

    public static void testSet1()  // Stable
    {
        final int[][] A =
                {
                        { 0, 1, 2 },
                        { 0, 2, 1 },
                        { 2, 0, 1 }
                };
        final int[][] B =
                {
                        { 0, 1, 2 },
                        { 0, 1, 2 },
                        { 0, 1, 2 }
                };

        final int[] students  = { 0, 2, 1 };
        final int[] employers = { 0, 2, 1 };

        System.out.println(isStable(students, employers, A, B));
    }

    public static void testSet2()  // Unstable
    {
        final int[][] A =
                {
                        { 0, 1, 2 },
                        { 0, 2, 1 },
                        { 2, 0, 1 }
                };
        final int[][] B =
                {
                        { 0, 1, 2 },
                        { 0, 1, 2 },
                        { 0, 1, 2 }
                };

        final int[] students  = { 0, 1, 2 };
        final int[] employers = { 0, 1, 2 };

        System.out.println(isStable(students, employers, A, B));
    }


    @SuppressWarnings("unchecked")
    public static boolean isStable(int[] students, int[] employers, int[][] A, int[][] B)
    {
        // Initialization
        Stack<Integer> stack = new Stack<>();
        Pair<Integer, Integer>[][] PQ = (Pair<Integer, Integer>[][]) new Pair[B.length][B[0].length];
        int[] PQcount = new int[B.length];

        int[] studentsNew = new int[students.length];
        int[] employersNew = new int[employers.length];

        for (int i = 0; i < employers.length; ++i)    // Run n times: O(n)
            stack.push(i);                            // Stack push: O(1)

        for (int i = 0; i < B.length; ++i)            // Run n times: O(n ^ 2 * log n)
        {
            for (int j = 0; j < B[i].length; ++j)     // Run n times: O(n)
            {
                PQ[i][j] = new Pair<>(B[i][j], j);    // O(1)
            }
            Arrays.sort(PQ[i]);                       // Array sort: O(n log n)
            PQcount[i] = 0;
        }

        Arrays.fill(studentsNew, -1);             // O(n)
        Arrays.fill(employersNew, -1);            // O(n)

        // Stability check: re-execute GS algorithm: O(n ^ 2)
        while (!stack.empty())
        {
            int e      = stack.pop();
            int s      = PQ[e][PQcount[e]++].getValue(); // O(1), instead of O(log n) in Q2
            int ePrime = studentsNew[s];

            if (ePrime == -1)
            {
                studentsNew[s]  = e;
                employersNew[e] = s;
            }
            else if (A[s][e] < A[s][ePrime])
            {
                studentsNew[s]       = e;
                employersNew[e]      = s;
                employersNew[ePrime] = -1;
                stack.push(ePrime);
            }
            else
            {
                stack.push(e);
            }
        }
        // Direct comparison: O(n)
        return Arrays.equals(students, studentsNew) && Arrays.equals(employers, employersNew);
    }
}
