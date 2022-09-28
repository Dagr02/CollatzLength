package collatzLab;
import java.util.Scanner;
import java.util.Arrays;
public class collatzLength {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int startRange = sc.nextInt();
        int endRange = sc.nextInt();
        //int num = sc.nextInt();
        long startTime = System.nanoTime();
        int[] ar1 = new int[(endRange+1)-startRange]; //normal nums
        int j = startRange;
        for(int i = 0; i < ar1.length;i++){
             ar1[i] = j;
             j++;
        }
        Arrays.sort(ar1);
        int[] ar = new int[ar1.length]; //collatzlen array
        for(int i = 0; i < ar.length;i++){
            ar[i] = collatz(ar1[i],ar1,ar);
        }
        //recQuickSort(ar,ar1,0,ar.length-1); //pass in collatz array
        //for(int i =0; i < ar.length;i++){
            //System.out.print(ar1[i] + " ");
        //}
        System.out.println();
       for(int i =0; i < ar.length;i++){
           System.out.print(ar[i] + " ");
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println();
        System.out.println("It took: " + totalTime);

    }
    public static int collatz(int num,int[] ar,int[] ar1){ //ar = collatz len array / ar1 is normal num array
        int inputNum = num;
        int collatzLen = 0;
        while(num != 1){
            if(num < inputNum && num == ar1[num-1]){
                collatzLen += ar[num-1];
                break;
            }
            if(num % 2 == 0){
                collatzLen++;
                num = num / 2;

            }
            else{
                collatzLen++;
                num = ((num*3) + 1);
            }

        }
        return collatzLen;
    }
    public static long medianOf3(int[] ar,int[] ar1,int left, int right){ //this would work on collatz length
        int middle = (left+right)/2;
        if(ar[left] > ar[middle]){ //order left & middle
            swap(ar,left,middle);
            swap(ar1,left,middle);
        }
        if(ar[left] > ar[right]){ //order left and right
            swap(ar,left,right);
            swap(ar1,left,right);
        }
        if(ar[middle] > ar[right]){ //order middle and right
            swap(ar,middle,right);
            swap(ar1,middle,right);
        }

        swap(ar,middle,right);
        swap(ar1,middle,right);
        return ar[right];

    }
    public static void recQuickSort(int[] ar,int[]ar1,int left, int right){
        int size = right-left+1;
        if(right-left <= 0){
            return;
        }
        else{
            long median = medianOf3(ar,ar1,left,right);
            int partition = partitionIt(ar,ar1,left,right,median);
            recQuickSort(ar,ar1,left,partition-1);
            recQuickSort(ar,ar1,partition+1,right);

        }
    }
    public static int partitionIt(int[] ar,int[]ar1,int left, int right, long pivot){
        int leftPtr = left-1;
        int rightPtr = right;
        while(true){
            while(ar[++leftPtr] < pivot){} //scan right
            while(ar[--rightPtr] > pivot){} //scan left
            if(leftPtr >= rightPtr){ //if the pointers cross
                break;
            }
            else{
                swap(ar,leftPtr,rightPtr);
                swap(ar1,leftPtr,rightPtr);
            }
        }
        swap(ar,leftPtr,right);
        swap(ar1,leftPtr,right);
        return leftPtr;
    }
    public static void swap(int[] ar,int i, int j){
        int temp = ar[j];
        ar[j] = ar[i];
        ar[i] = temp;
    }
    public static void insertionSort(int[] ar,int[]ar1){
        for(int outer = 1; outer < ar.length;outer++){
            int temp = ar[outer];
            int temp1 = ar1[outer];
            int inner = outer;
            while(inner > 0 && ar[inner-1] >= temp){
                ar[inner] = ar[inner-1];//swap
                ar1[inner] = ar1[inner-1];
                inner--;
            }
            ar[inner] = temp;
            ar1[inner] = temp1;
        }

    }
}
