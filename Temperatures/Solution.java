import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int numTemps = in.nextInt(); // the number of temperatures to analyse

        int output = 5527;

        if (numTemps != 0) {
            for (int i = 0; i < numTemps; i++) {
                int t = in.nextInt(); 

                if (Math.abs(t) < Math.abs(output)){
                    output = t;
                } else if (Math.abs(t) == Math.abs(output) && output < t) {
                    output = t;
                }
            }
        } else {
            output = 0;
        }

        System.out.println(output);
    }

    public static void mainRob(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse

        int[] temps = new int[n];
        for (int i = 0; i < n; i++) {
            temps[i] = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
        }

        int minDistance = Integer.MAX_VALUE;
        int bestTemp = 0;
        List<Integer> occurences = new ArrayList<>();

        for(int i = 0; i < temps.length; i++) {
            occurences.add(temps[i]);
            int tempAbs = Math.abs(temps[i]);
            if (tempAbs < minDistance) {
                minDistance = tempAbs;
                bestTemp = temps[i];
            }
        }

        System.out.println(
            occurences.contains(Math.abs(bestTemp)) && 
            occurences.contains(0 - Math.abs(bestTemp)) ? 
                minDistance : bestTemp
        );
    }
}
