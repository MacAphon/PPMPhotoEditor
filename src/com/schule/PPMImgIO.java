package com.schule;

import java.io.*;
import java.util.*;

public class PPMImgIO {
    public static PPMImg readImg(String file){
        PPMImg image = null;
        Pixel[][] img;
        int width;
        int height;
        int cDepth;

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

            img = new Pixel[height][width];

            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++) {
                    r = Integer.parseInt(sc.next());
                    g = Integer.parseInt(sc.next());
                    b = Integer.parseInt(sc.next());
                    img[i][j] = new Pixel(r, g, b);
                }
            }
            image = new PPMImg(width, height, cDepth, img);
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    public static void writeImg(String file, PPMImg image){
        int width = image.getWidth();
        int height = image.getHeight();
        int cDepth = image.getCDepth();
        Pixel[][] img = image.getPixel();

        try {
            PrintWriter pw = new PrintWriter(file);

            pw.println("P3");
            pw.write(width + " ");
            pw.println(height);
            pw.println(cDepth);

            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++) {
                    pw.write(img[i][j].getR() + " ");
                    pw.write(img[i][j].getG() + " ");
                    pw.write(img[i][j].getB() + " ");
                }
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
