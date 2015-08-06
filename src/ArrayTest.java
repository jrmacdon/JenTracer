/**
 * Created by kevin on 7/15/15.
 */
public class ArrayTest {

    public static void extremes (int[] a) {
        int smallestIndex = 0;
        int largestIndex = 0;

        for (int i = 1; i < a.length; i++){
            if (i == 3){
                System.out.println("Hello. This is index " + i);
            }
            if (a[i] < a[smallestIndex]){
                    smallestIndex = i;
            }
            if (a[i] > a[largestIndex]){
                largestIndex = i;
            }
        }

        System.out.println("The smallest number is " + a[smallestIndex] + " in index " + smallestIndex + " and the largest number is " + a[largestIndex] + " in index " + largestIndex + ".");

    }
    public static void main(String[] args) {
        int[] ages = {50, 8, 2, -27, 29};
        extremes(ages);


    }


}
