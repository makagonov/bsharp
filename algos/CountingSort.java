import java.util.Arrays;

public class CountingSort {
   public static void sort(int [] a) {
     int maxVal = 0;
     for (int i : a) {
       if (i < 0) throw new IllegalArgumentException("Counting sort can only be used for non-negative numbers");
       if (i > maxVal) maxVal = i;
     }
     int n = a.length;
     //counts[i] stores how many elements with value i we have
     int [] counts = new int[maxVal + 1];
     for (int i : a) counts[i]++;
     
     //modify the array, s.t. counts[i] stores the number of elements <= i
     //the idea is that if you know how many elements in the array are less than i
     //then you know exactly at what position the element resides in a sorted array
     for (int i = 1; i <= maxVal; i++)
       counts[i] = counts[i - 1] + counts[i];
     
     int [] sorted = new int [n + 1];
     for (int i = n - 1; i >= 0; i--) {
       int val = a[i];
       //there are "count" elements that are less than a[i]
       //so a[i] is at position (counts[val]) in the sorted array
       sorted[counts[val]] = a[i];
       //decrementing count for a[i], so that next appearance of value a[i] 
       //did no write the value to the same cell in the sorted array, but wrote it to the cells before it
       counts[a[i]]--;
     }
     for (int i = 1; i <= n; i++)
       a[i - 1] = sorted[i];
   }
   
   public static void main (String [] args) {
     int [] a = new int []{2, 5, 3, 0, 2, 3, 0, 3};
     CountingSort.sort(a);
     System.out.println(Arrays.toString(a));
   }
}
