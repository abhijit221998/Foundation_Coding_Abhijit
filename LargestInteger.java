package foundation.assignment;
import java.util.Scanner;
import java.util.Arrays;

public class LargestInteger {
    public static void main(String[] args) {
        System.out.println("Enter size:- ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Integer[] arr = new Integer[n];
        for(int i =0;i<n;i++){
            arr[i]= sc.nextInt();
        }

        findLargest(arr,n);
    }

    private static void findLargest(Integer[] arr, int n){
        Arrays.sort(arr, (a,b) -> {
            int n1 = a;
            int n2 = b;
            n1 = Integer.valueOf(""+a+b);
            n2 = Integer.valueOf(""+b+a);

            if(n1>n2){
                return -1;
            }else {
                return 1;
            }

        });

        for(int i =0;i<n;i++){
            System.out.print(arr[i]);
        }
    }
}
