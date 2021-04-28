package com.schule;

import java.io.*;
import java.util.*;

public class PPMImage {

    public PPMImage(String fileIn){
        try {
            Scanner sc = new Scanner(new File(fileIn));
            String s = sc.next();

            if ( !(s.equals("p3") || s.equals("P3")) ){
                System.out.println("Selected input file is not a valid PPM file. Aborting");
                System.exit(0);
            }

            int width = Integer.parseInt(sc.next());
            int height = Integer.parseInt(sc.next());
            int cDepth = Integer.parseInt(sc.next());

            int r, g, b;

            Pixel[][] image = new Pixel[height][width];

            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++) {
                    r = Integer.parseInt(sc.next());
                    g = Integer.parseInt(sc.next());
                    b = Integer.parseInt(sc.next());
                    image[i][j] = new Pixel(r, g, b);
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
