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
        int length = in.nextInt();
        int height = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        String outputString = in.nextLine();
        String[] style = new String[height];

        for (int i = 0; i < height; i++) {
            String ROW = in.nextLine();
            style[i] = ROW;
        }
        
        for(int x = 0; x < height; x++){
            for (int i = 0; i< outputString.length(); i++){
                int index = outputString.toUpperCase().charAt(i) - 'A';
                if (index < 0) {
                    index = 26;
                }

                System.out.print(style[x].substring(index * length, index * length + length));
                
            }
            System.out.println();
        }
    }

    public static void mainRob(String args[]) {
        Scanner in = new Scanner(System.in);
        int letterLength = in.nextInt();
        int letterHeight = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        char[] requestedString = in.nextLine().toLowerCase().toCharArray();

        String[] asciiRows = new String[letterHeight];

        for (int i = 0; i < letterHeight; i++) {
            asciiRows[i] = in.nextLine();
        }

        for (int i = 0; i<asciiRows.length;i++) {
            String line = "";
            for (char c : requestedString){
                int startIndex = (c-'a') >= 0 ? (c - 'a') * letterLength : ('z' - 'a' + 1) * letterLength;
                line += asciiRows[i].subSequence(startIndex, startIndex + letterLength);
            }
            System.out.println(line);
        }
    }
}
