package algorithms;

public class Optimal {
    private static int page_faults;
    public static void calculate(int[] reference, int frames)
    {
        int pointer = 0;
        page_faults = 0;
        int ref_len = reference.length;
        boolean isFull = false;
        int buffer[];
        boolean hit[];
        int fault[];
        int mem_layout[][];

        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        hit = new boolean[ref_len];
        fault = new int[ref_len];
        for(int j = 0; j < frames; j++)
        {
            buffer[j] = -1;
        }

        for(int i = 0; i < ref_len; i++)
        {
            int search = -1;
            for(int j = 0; j < frames; j++)
            {
                if(buffer[j] == reference[i])
                {
                    search = j;
                    hit[i] = true;
                    fault[i] = page_faults;
                    break;
                }
            }

            if(search == -1)
            {
                if(isFull)
                {
                    int index[] = new int[frames];
                    boolean index_flag[] = new boolean[frames];
                    for(int j = i + 1; j < ref_len; j++)
                    {
                        for(int k = 0; k < frames; k++)
                        {
                            if((reference[j] == buffer[k]) && (index_flag[k] == false))
                            {
                                index[k] = j;
                                index_flag[k] = true;
                                break;
                            }
                        }
                    }
                    int max = index[0];
                    pointer = 0;
                    if(max == 0)
                    {
                        max = 200;
                    }

                    for(int j = 0; j < frames; j++)
                    {
                        if(index[j] == 0)
                        {
                            index[j] = 200;
                        }
                        if(index[j] > max)
                        {
                            max = index[j];
                            pointer = j;
                        }
                    }
                }
                buffer[pointer] = reference[i];
                page_faults++;
                fault[i] = page_faults;
                if(!isFull)
                {
                    pointer++;
                    if(pointer == frames)
                    {
                        pointer = 0;
                        isFull = true;
                    }
                }
            }
            for(int j = 0; j < frames; j++)
            {
                mem_layout[i][j] = buffer[j];
            }
        }
    }

    public static int getPage_faults() {
        return page_faults;
    }
}
