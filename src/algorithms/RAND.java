package algorithms;

public class RAND {
    private static int page_faults;

    public static int getPage_faults() {
        return page_faults;
    }

    static boolean existsPage(int[] frame, int page){
        for(int i=0 ; i<frame.length;i++){
            if (frame[i] == page)
            return true;
        }
        return false;
    }

    public static void calculate(int[] arr, int frames) {
        int pages = arr.length;
        int[] frame = new int[frames];
        page_faults = 0;
        int phit = 0;
        for (int i = 0; i < frames; i++) {
            frame[i] = -1;
        }
        int randomIdx = 0;
        for (int i = 0; i < pages; i++) {
            int currentPage = arr[i];
            if (i < frames)
                frame[i] = currentPage;
            else {
                if (!existsPage(frame, currentPage)) {
                    randomIdx = Page.getRandomNumberInRange(0, frames-1);
                    frame[randomIdx] = currentPage;
                    page_faults++;
                }
            }
        }
    }
}
