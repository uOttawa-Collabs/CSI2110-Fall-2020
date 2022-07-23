import java.lang.reflect.Array;
import java.util.ArrayList;

public class MergeSort<T extends Comparable<T>>
{
    private final Class<T> type;

    public MergeSort(T[] sequence, Class<T> type)
    {
        this.type = type;
        sort(sequence, 0, sequence.length - 1);
    }

    private void sort(T[] sequence, int left, int right)
    {
        if (left >= right)
            return;

        int mid = (left + right) / 2;

        sort(sequence, left, mid);
        sort(sequence, mid + 1, right);
        merge(sequence, left, mid, right);
    }

    @SuppressWarnings("unchecked")
    private void merge(T[] sequence, int left, int mid, int right)
    {
        T[] temp = (T[]) Array.newInstance(type, sequence.length);

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right)
        {
            if (sequence[i].compareTo(sequence[j]) <= 0)
            {
                temp[k] = sequence[i];
                i++;
            }
            else
            {
                temp[k] = sequence[j];
                j++;
            }
            k++;
        }
        while (i <= mid)
        {
            temp[k] = sequence[i];
            k++;
            i++;
        }
        while (j <= right)
        {
            temp[k] = sequence[j];
            k++;
            j++;
        }

        for (int m = 0; m < k; m++)
            sequence[left + m] = temp[m];
    }
}
