package com.schule;

import java.math.*;
import java.util.*;

public class PPMImg {
    private Pixel[][] img;
    private Pixel[][] img2;
    private int[][] red;
    private int[][] green;
    private int[][] blue;
    private final int width;
    private final int height;
    private final int cDepth;

    public PPMImg(int width, int height, int cDepth, Pixel[][] image){
        this.width = width;
        this.height = height;
        this.cDepth = cDepth;
        img = image;
        img2 = image;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getCDepth() { return cDepth; }
    public Pixel getPixel(int y, int x) { return img[y][x]; }
    public Pixel[][] getPixel(){ return img; }


    public void channel(String color){
        switch (color) {
            case "red":
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        img[i][j].setG(0);
                        img[i][j].setB(0);
                    }
                }
                break;
            case "green":
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        img[i][j].setR(0);
                        img[i][j].setB(0);
                    }
                }
                break;
            case "blue":
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        img[i][j].setR(0);
                        img[i][j].setG(0);
                    }
                }
                break;
        }
    }

    public void invertColors() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                img[i][j].setR(cDepth - img[i][j].getR());
                img[i][j].setG(cDepth - img[i][j].getG());
                img[i][j].setB(cDepth - img[i][j].getB());
            }

        }
    }

    public void bw(String mode){
        switch (mode){

            case "soft":
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        double rLoc = img[i][j].getR();
                        double gLoc = img[i][j].getG();
                        double bLoc = img[i][j].getB();
                        int av = (int) (rLoc+gLoc+bLoc)/3;
                        img[i][j].setRGB(av);

                    }

                }
                break;

            case "hard":
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        double rLoc = img[i][j].getR();
                        double gLoc = img[i][j].getG();
                        double bLoc = img[i][j].getB();
                        int av = (int) (rLoc+gLoc+bLoc)/3;
                        if(av > cDepth/2){
                            img[i][j].setRGB(cDepth);
                        } else {
                            img[i][j].setRGB(0);
                        }
                    }
                }
                break;
        }
    }
    //TODO
    public void kernel(int[][] vals, int[][] vals2, int value){
        int[][] rVal = new int[5][5];
        int[][] gVal = new int[5][5];
        int[][] bVal = new int[5][5];
        int[][] val = new int[5][5];
        int[][] val2 = new int[5][5];
        int vert;
        int horiz;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int valueb = value;
                for (int k = 0; k < 5; k++){
                    for (int l = 0; l < 5; l++){

                        if(vals2 != null){
                            try {
                                val[k][l] = (int) (img[i + k - 2][j + l - 2].getR()
                                        + img[i + k - 2][j + l - 2].getR()
                                        + img[i + k - 2][j + l - 2].getR()) / 3;
                                val2[k][l] = (int) (img[i + k - 2][j + l - 2].getR()
                                        + img[i + k - 2][j + l - 2].getR()
                                        + img[i + k - 2][j + l - 2].getR()) / 3;
                            }catch (IndexOutOfBoundsException e){
                                val[k][l] = 0;
                                val2[k][l] = 0;
                            }
                        } else {
                            try{
                                rVal[k][l] = img[i+k-2][j+l-2].getR() ;//* vals[k][l];
                                gVal[k][l] = img[i+k-2][j+l-2].getG() ;//* vals[k][l];
                                bVal[k][l] = img[i+k-2][j+l-2].getB() ;//* vals[k][l];
                            }catch (IndexOutOfBoundsException e) {
                                rVal[k][l] = 0;
                                gVal[k][l] = 0;
                                bVal[k][l] = 0;
                                valueb -= vals[k][l];
                            }
                        }
                    }
                }
                if(vals2 != null){
                    vert = (int) Math.pow(Arrays.stream(val).flatMapToInt(Arrays::stream).average().getAsDouble(), 2);
                    horiz = (int) Math.pow(Arrays.stream(val2).flatMapToInt(Arrays::stream).average().getAsDouble(), 2);
                    img2[i][j].setRGB((int) Math.sqrt(vert + horiz));
                } else {
                    img2[i][j].setR((int) Arrays.stream(rVal).flatMapToInt(Arrays::stream).average().getAsDouble() );///valueb);
                    img2[i][j].setG((int) Arrays.stream(gVal).flatMapToInt(Arrays::stream).average().getAsDouble() );///valueb);
                    img2[i][j].setB((int) Arrays.stream(bVal).flatMapToInt(Arrays::stream).average().getAsDouble() );///valueb);
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                img[i][j].setR(img2[i][j].getR());
                img[i][j].setG(img2[i][j].getG());
                img[i][j].setB(img2[i][j].getB());
            }
        }
    }
}
