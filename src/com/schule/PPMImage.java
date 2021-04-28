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
            PrintWriter pw = new PrintWriter(fileOut);

            pw.println("P3");
            pw.write(width + " ");
            pw.println(height);
            pw.println(cDepth);

            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++) {
                    pw.write(image[i][j].getR() + " ");
                    pw.write(image[i][j].getG() + " ");
                    pw.write(image[i][j].getB() + " ");
                }
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // isolates the specified color channel
    public void channel(String color){
        switch (color) {
            case "red":
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        image[i][j].setG(0);
                        image[i][j].setB(0);
                    }
                }
                break;
            case "green":
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        image[i][j].setR(0);
                        image[i][j].setB(0);
                    }
                }
                break;
            case "blue":
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        image[i][j].setR(0);
                        image[i][j].setG(0);
                    }
                }
                break;
        }
    }

    public void invertColors() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                image[i][j].setR(cDepth - image[i][j].getR());
                image[i][j].setG(cDepth - image[i][j].getG());
                image[i][j].setB(cDepth - image[i][j].getB());
            }

        }
    }

    // black and white
    public void bw(String mode){
        switch (mode){

            case "soft":
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        double rLoc = image[i][j].getR();
                        double gLoc = image[i][j].getG();
                        double bLoc = image[i][j].getB();
                        int av = (int) (rLoc+gLoc+bLoc)/3;
                        image[i][j].setR(av);
                        image[i][j].setG(av);
                        image[i][j].setB(av);
                    }

                }
                break;

            case "hard":
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        double rLoc = image[i][j].getR();
                        double gLoc = image[i][j].getG();
                        double bLoc = image[i][j].getB();
                        int av = (int) (rLoc+gLoc+bLoc)/3;
                        if(av > cDepth/2){
                            image[i][j].setR(cDepth);
                            image[i][j].setG(cDepth);
                            image[i][j].setB(cDepth);
                        } else {
                            image[i][j].setR(0);
                            image[i][j].setG(0);
                            image[i][j].setB(0);
                        }
                    }
                }
                break;
        }
    }

    // takes the average of a 5x5 square around the current pixel
    public void blur(){
        for (int i = 2; i < height-2; i++) {
            for (int j = 2; j < width-2; j++) {
                int[][] rVals = new int[5][5];
                int[][] gVals = new int[5][5];
                int[][] bVals = new int[5][5];
                for (int k = 0; k < 5; k++){
                    for (int l = 0; l < 5; l++){
                        rVals[k][l] = image[i+k-2][j+l-2].getR();
                        gVals[k][l] = image[i+k-2][j+l-2].getG();
                        bVals[k][l] = image[i+k-2][j+l-2].getB();
                    }
                }
                image[i][j].setR((int) Arrays.stream(rVals).flatMapToInt(Arrays::stream).average().getAsDouble());
                image[i][j].setG((int) Arrays.stream(gVals).flatMapToInt(Arrays::stream).average().getAsDouble());
                image[i][j].setB((int) Arrays.stream(bVals).flatMapToInt(Arrays::stream).average().getAsDouble());
            }
        }
    }


}
