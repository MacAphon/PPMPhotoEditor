package com.schule;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	    String activeFileIn = args[args.length-2];
        String activeFileOut = args[args.length-1];
        PPMImage file = new PPMImage(activeFileIn);

        // optional command line arguments
        if(Arrays.asList(args).contains("--red-channel")){
            file.channel("red");
        }
        if(Arrays.asList(args).contains("--green-channel")){
            file.channel("green");
        }
        if(Arrays.asList(args).contains("--blue-channel")){
            file.channel("blue");
        }
        if(Arrays.asList(args).contains("--invert-colors")){
            file.invertColors();
        }
        if(Arrays.asList(args).contains("--bw-soft")){
            file.bw("soft");
        }
        if(Arrays.asList(args).contains("--bw-hard")){
            file.bw("hard");
        }

        file.writeFile(activeFileOut);
    }
}
