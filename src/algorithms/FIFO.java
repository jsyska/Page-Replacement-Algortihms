package algorithms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FIFO {
    private static int page_faults;
    public static void pageFaults(int pages[], int capacity)
    {
        int n = pages.length;
        HashSet<Integer> s = new HashSet<>(capacity);

        Queue<Integer> indexes = new LinkedList<>() ;

        page_faults = 0;
        for (int i=0; i<n; i++)
        {
            if (s.size() < capacity)
            {
                if (!s.contains(pages[i]))
                {
                    s.add(pages[i]);
                    page_faults++;
                    indexes.add(pages[i]);
                }
            }
            else
            {
                if (!s.contains(pages[i]))
                {
                    int val = indexes.peek();
                    indexes.poll();
                    s.remove(val);
                    s.add(pages[i]);
                    indexes.add(pages[i]);
                    page_faults++;
                }
            }
        }
    }

    public static int getPage_faults() {
        return page_faults;
    }
}
