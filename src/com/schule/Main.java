package com.schule;

public class Main {

    public static void main(String[] args) {
	    String fileI = args[args.length-2];
        String fileO = args[args.length-1];
        PPMImg file = PPMImgIO.readImg(fileI);

        // optional command line arguments
        for (int i=0; i< args.length-2; i++){
            switch (args[i]) {
                case "--red-channel":
                    file.channel("red");
                    break;
                case "--green-channel":
                    file.channel("green");
                    break;
                case "--blue-channel":
                    file.channel("blue");
                    break;
                case "--invert-colors":
                    file.invertColors();
                    break;
                case "--bw-soft":
                    file.bw("soft");
                    break;
                case "--bw-hard":
                    file.bw("hard");
                    break;
                case "--gauss-blur":
                    file.kernel(new int[][]{
                                    {1, 4, 6, 4,1},
                                    {4,16,24,16,4},
                                    {6,24,36,24,6},
                                    {4,16,24,16,4},
                                    {1, 4, 6, 4,1}},
                            null, 256);
                    break;
                case "--edge-detect":
                    file.kernel(new int[][]{
                                    {1, 4, 0,- 4,-1},
                                    {4,16, 0,-16,-4},
                                    {6,24, 0,-24,-6},
                                    {4,16, 0,-16,-4},
                                    {1, 4, 0,- 4,-1}},
                            new int[][]{
                                    { 1,  4,  6,  4, 1},
                                    { 4, 16, 24, 16, 4},
                                    { 0,  0,  0,  0, 0},
                                    {-4,-16,-24,-16,-4},
                                    {-1,- 4,- 6,- 4,-1}},
                            1);
                    break;
                case "--sharpen":
                    file.kernel(new int[][]{
                                    {-1,- 4,-  6,- 4,-1},
                                    {-4,-16,- 24,-16,-4},
                                    {-6,-24, 219,-24,-6},
                                    {-4,-16,- 24,-16,-4},
                                    {-1,- 4,-  6,- 4,-1}},
                            null, 1);
                    break;
            }
        }

        PPMImgIO.writeImg(fileO, file);
    }
}
