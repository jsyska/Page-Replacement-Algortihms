package algorithms;

import java.util.Arrays;

public class ALRU {

    private static int page_faults;

    static boolean findAndUpdate(int x,int arr[], boolean second_chance[],int frames)
    {
        int i;
        for(i = 0; i < frames; i++)
        {
            if(arr[i] == x)
            {
                second_chance[i] = true;
                return true;
            }
        }
        return false;
    }

    static int replaceAndUpdate(int x,int arr[], boolean second_chance[],int frames,int pointer)
    {
        while(true)
        {
            if(!second_chance[pointer])
            {
                arr[pointer] = x;
                return (pointer+1)%frames;
            }
            second_chance[pointer] = false;
            pointer = (pointer+1)%frames;
        }
    }

    public static void calculate(int[] reference, int frames)
    {
        int pointer,i,l,x;
        pointer = 0;
        page_faults = 0;
        int arr[] = new int[frames];
        Arrays.fill(arr,-1);
        boolean second_chance[] = new boolean[frames];
        l = reference.length;

        for(i = 0; i<l; i++)
        {
            x = reference[i];
            if(!findAndUpdate(x,arr,second_chance,frames))
            {
                pointer = replaceAndUpdate(x,arr,
                        second_chance,frames,pointer);

                page_faults++;
            }
        }
    }

    public static int getPage_faults() {
        return page_faults;
    }
}
