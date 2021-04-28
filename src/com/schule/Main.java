package com.schule;

public class Main {

    public static void main(String[] args) {
	    String activeFileIn = args[args.length-2]; //"images/input.txt";
        String activeFileOut = args[args.length-1]; //"images/output.txt";

        PPMImage file = new PPMImage(activeFileIn);
        file.writeFile(activeFileOut);
    }
}
