package com.schule;

public class Main {

    public static void main(String[] args) {
	    String activeFileIn = args[args.length-2];
        String activeFileOut = args[args.length-1];
        PPMImage file = new PPMImage(activeFileIn);
        file.writeFile(activeFileOut);
    }
}
