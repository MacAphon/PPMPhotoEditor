package com.schule;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	    String activeFileIn = args[args.length-2]; //"images/input.txt";
        String activeFileOut = args[args.length-1]; //"images/output.txt";

        try {
            Scanner sc = new Scanner(new File(activeFileIn));
            PrintWriter pw = new PrintWriter(activeFileOut);
            String s;

            while(sc.hasNext()){
                s = sc.next();
                pw.write(s + " ");
            }
            sc.close();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
