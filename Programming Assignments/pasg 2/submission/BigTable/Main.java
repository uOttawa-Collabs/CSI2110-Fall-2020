import java.io.*;

class Main
{
    private static final int MAX_LENGTH = 1000;

    private static int         L;
    private static int         n;
    private static int[]       length;
    private static int[]       currentX;
    private static int[]       bestX;
    private static int         bestK;
    private static boolean[][] visited;

    public static void main(String[] args)
    {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            int numberTestCases = Integer.parseInt(readLine(reader));

            length = new int[MAX_LENGTH];

            for (int caseCount = 0; caseCount < numberTestCases; ++caseCount)
            {
                if (caseCount != 0)
                    writer.println();

                L = Integer.parseInt(readLine(reader)) * 100;    // m to cm
                int carLength;
                n = 0;
                do
                {
                    carLength = Integer.parseInt(readLine(reader));
                    if (carLength > 0)
                        length[n++] = carLength;
                }
                while (carLength > 0 && n < MAX_LENGTH);

                currentX = new int[n];
                bestX    = new int[n];
                bestK    = -1;
                visited  = new boolean[n + 1][L + 1];

                backtrackSolve(0, L, 0);

                writer.println(bestK);
                for (int i = 0; i < bestK; ++i)
                     writer.println(bestX[i] == 0 ? "port" : "starboard");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        writer.close();
    }

    public static String readLine(BufferedReader reader) throws IOException
    {
        String line;

        do
        {
            line = reader.readLine();
        }
        while (line == null || line.equals(""));

        return line;
    }

    public static void backtrackSolve(int currentK, int currentS, int totalLength)
    {
        if (currentK > bestK)
        {
            bestK = currentK;
            System.arraycopy(currentX, 0, bestX, 0, bestK);
        }

        if (currentK < n)
        {
            int newS = currentS - length[currentK];

            if (currentS >= length[currentK] && !visited[currentK + 1][newS])
            {
                currentX[currentK] = 1;
                backtrackSolve(currentK + 1, newS, totalLength + length[currentK]);
                visited[currentK + 1][newS] = true;
            }

            if (2 * L - totalLength - currentS >= length[currentK] && !visited[currentK + 1][currentS])
            {
                currentX[currentK] = 0;
                backtrackSolve(currentK + 1, currentS, totalLength + length[currentK]);
                visited[currentK + 1][currentS] = true;
            }
        }
    }
}
