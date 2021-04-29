package com.schule;

public class Main {

    public static void main(String[] args) {
	    String activeFileIn = args[args.length-2];
        String activeFileOut = args[args.length-1];
        PPMImage file = new PPMImage(activeFileIn);

        // optional command line arguments
        for (int i=0; i< args.length-2; i++){
            switch (args[i]) {
                case "--red-channel" -> file.channel("red");
                case "--green-channel" -> file.channel("green");
                case "--blue-channel" -> file.channel("blue");
                case "--invert-colors" -> file.invertColors();
                case "--bw-soft" -> file.bw("soft");
                case "--bw-hard" -> file.bw("hard");
                case "--blur" -> file.blur();
            }
        }

        file.writeFile(activeFileOut);
    }
}
