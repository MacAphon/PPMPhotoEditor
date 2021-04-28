package com.schule;

import java.io.*;
import java.util.*;

public class PPMImage {
    public PPMImage(String fileIn, String fileOut){
        try {
            Scanner sc = new Scanner(new File(fileIn));
            PrintWriter pw = new PrintWriter(fileOut);
            String s = sc.next();

            if ( !(s.equals("p3") || s.equals("P3")) ){
                System.out.println("Selected input file is not a valid PPM file. Aborting");
                System.exit(0);
            }

            int width = Integer.parseInt(sc.next());
            int height = Integer.parseInt(sc.next());
            int cDepth = Integer.parseInt(sc.next());

            Pixel[][] image = new Pixel[height][width];

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
