package com.schule;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	    String activeFileIn = "images/input.txt"; //args[args.length-2];
        String activeFileOut = "images/output.txt"; //args[args.length-1];

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
            // do nothing.
        }
    }
}
