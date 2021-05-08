package algorithms;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class LRU
{
    public static int page_faults;
    public static void calculate(int pages[], int capacity)
    {
        int n = pages.length;
        HashSet<Integer> s = new HashSet<>(capacity);

        HashMap<Integer, Integer> indexes = new HashMap<>();

        page_faults = 0;
        for (int i=0; i<n; i++)
        {
            if (s.size() < capacity)
            {
                if (!s.contains(pages[i]))
                {
                    s.add(pages[i]);

                    page_faults++;
                }
                indexes.put(pages[i], i);
            }


            else
            {

                if (!s.contains(pages[i]))
                {

                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;

                    Iterator<Integer> itr = s.iterator();

                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (indexes.get(temp) < lru)
                        {
                            lru = indexes.get(temp);
                            val = temp;
                        }
                    }

                    s.remove(val);
                    indexes.remove(val);
                    s.add(pages[i]);

                    page_faults++;
                }

                indexes.put(pages[i], i);
            }
        }
    }
    public static int getPage_faults() {
        return page_faults;
    }
}
