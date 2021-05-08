package algorithms;

import java.util.Random;

public class Page {

    private int number;

    public Page(int number){
        this.number=number;
    }

    public static Page[] generate(int numberOfPages, int pagesRange ) {
        Page[] result = new Page[numberOfPages];
        for (int i = 0; i < numberOfPages; i++) {
            result[i] = new Page(getRandomNumberInRange(0,pagesRange));
        }
        return result;
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    public String toString() {
        return "Page{" +
                "number=" + number +
                '}';
    }

    public int getNumber() {
        return number;
    }
}
