package com.schule;

import java.io.*;
import java.util.*;

public class PPMImage {
    private Pixel[][] image;
    private int width;
    private int height;
    private int cDepth;

    public PPMImage(String file){
        try {
            Scanner sc = new Scanner(new File(file));
            String s = sc.next();

            if ( !(s.equals("p3") || s.equals("P3")) ){
                System.out.println("Selected input file is not a valid PPM file. Aborting");
                System.exit(0);
            }

            width = Integer.parseInt(sc.next());
            height = Integer.parseInt(sc.next());
            cDepth = Integer.parseInt(sc.next());

            int r, g, b;

            image = new Pixel[height][width];

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

    public void writeFile(String fileOut){
        try {
            PrintWriter pw = new PrintWriter(new File(fileOut));

            pw.println("P3");
            pw.write(String.valueOf(width) + " ");
            pw.println(String.valueOf(height));
            pw.println(String.valueOf(cDepth));

            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++) {
                    pw.write(String.valueOf(image[i][j].getR()) + " ");
                    pw.write(String.valueOf(image[i][j].getG()) + " ");
                    pw.write(String.valueOf(image[i][j].getB()) + " ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
