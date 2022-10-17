package foundation.assignment;

import java.util.Scanner;

public class Power {

    static int power(int x, int n){
        if (n==0)
            return 1;
        if (n==1)
            return x;
        if (n%2 ==0)
            return power(x,n/2)*power(x,n/2);
        else
            return power(x,(int)(n/2))*power(x,n-(int)(n/2));

    }
    public static void main(String[] args) {
        System.out.println("Enter x value= ");
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        System.out.println("Enter n value= ");
        int n = in.nextInt();

        System.out.println(power(x,n));
    }
}
