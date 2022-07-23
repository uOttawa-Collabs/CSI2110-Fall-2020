import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

/**
 * CSI2110D 2020F - Programming Assignment 1
 * GaleShapley.java - Stable matching algorithm
 *
 * @author Jake Wang (*)
 */
public class GaleShapley
{
    private Stack<Integer>           Sue;
    private int[]                    students;
    private int[]                    employers;
    private int[][]                  A;
    private PriorityQueue<Pair<Integer, Integer>>[] PQ;

    private String[] namesOfStudents;
    private String[] namesOfEmployers;

    /**
     * Initialization of Gale-Shapley Algorithm
     *
     * @param filename Input file
     *
     * @throws IOException when an I/O error occurred
     */
    @SuppressWarnings("unchecked")
    public void initialize(String filename)
            throws IOException
    {
        Sue = new Stack<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

        // Read numbers of students and employers
        int n = Integer.parseInt(bufferedReader.readLine());

        // Allocating space
        students  = new int[n];
        employers = new int[n];
        A         = new int[n][n];
        PQ        = new PriorityQueue[n];

        namesOfStudents  = new String[n];
        namesOfEmployers = new String[n];

        int[][] B = new int[n][n];

        // Push all employers in the stack
        for (int i = 0; i < n; ++i)
             Sue.push(i);

        // Initialize all entries to -1
        for (int i = 0; i < n; ++i)
             students[i] = employers[i] = -1;

        // Read names of employers
        for (int i = 0; i < n; ++i)
             namesOfEmployers[i] = bufferedReader.readLine();

        // Read names of students
        for (int i = 0; i < n; ++i)
             namesOfStudents[i] = bufferedReader.readLine();

        // Create Priority Queues
        for (int i = 0; i < n; ++i)
        {
            PQ[i] = new PriorityQueue<>();
        }

        // Read ranking data
        for (int i = 0; i < n; ++i)
        {
            String[] row = bufferedReader.readLine().split(" ");
            for (int j = 0; j < n; ++j)
            {
                String[] pair = row[j].split(",");
                A[j][i] = Integer.parseInt(pair[1]);
                PQ[i].add(new Pair<>(Integer.parseInt(pair[0]), j));
            }
        }
    }

    /**
     * Execute Gale-Shapley Algorithm
     */
    public void execute()
    {
        while (!Sue.empty())
        {
            int e      = Sue.pop();
            int s      = PQ[e].poll().getValue();
            int ePrime = students[s];

            if (ePrime == -1) // s is unmatched
            {
                // Match (e, s)
                students[s]  = e;
                employers[e] = s;
            }
            else if (A[s][e] < A[s][ePrime]) // s prefers e to ePrime
            {
                // Replace (ePrime, s) with (e, s)
                students[s]       = e;
                employers[e]      = s;
                employers[ePrime] = -1;
                Sue.push(ePrime);
            }
            else
            {
                // s rejects the offer from e
                Sue.push(e);
            }
        }
    }

    /**
     * Save the result into a file
     *
     * @param fileName Desired path for the result file
     */
    public void save(String fileName)
            throws IOException
    {
        FileWriter    fileWriter = new FileWriter(fileName);
        StringBuilder builder    = new StringBuilder();

        for (int i = 0; i < employers.length; ++i)
        {
            builder.setLength(0);
            builder.append("Match ").append(i).append(": ").append(namesOfEmployers[i]).append(" - ").append(
                    namesOfStudents[employers[i]]).append('\n');
            fileWriter.write(builder.toString());
        }

        fileWriter.close();
    }

    /**
     * Entry point of this class
     *
     * @param args CLI Arguments
     */
    public static void main(String[] args)
    {
        // testAll();
        Scanner     scanner     = new Scanner(System.in);
        GaleShapley galeShapley = new GaleShapley();

        try
        {
            System.out.print("Please key in the input filename: ");
            String fileName = scanner.nextLine();

            galeShapley.initialize(fileName);
            galeShapley.execute();
            galeShapley.save("matches_" + fileName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Test all provided input file
     */
    public static void testAll()
    {
        final String testFilePrefix   = "test_N";
        final int[]  testSizes        = { 3, 10, 50 };
        final String testFileSuffix   = ".txt";
        final String outputFilePrefix = "matches_";

        try
        {
            GaleShapley galeShapley = new GaleShapley();

            for (int size : testSizes)
            {
                String testFileName   = testFilePrefix + size + testFileSuffix;
                String outputFileName = outputFilePrefix + testFilePrefix + size + testFileSuffix;

                galeShapley.initialize(testFileName);
                galeShapley.execute();
                galeShapley.save(outputFileName);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
