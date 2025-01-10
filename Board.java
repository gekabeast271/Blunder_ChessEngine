import java.util.Arrays;

public class Board {
    char[] pieces = { 'B', 'K', 'N', 'P', 'Q', 'R', 'b', 'k', 'n', 'p', 'q', 'r' }; // capital letters for white and
                                                                                    // lowercase letters for black
                                                                                    // (needs to be in binarySearch
                                                                                    // order)
    long[] bitboards; // array of size 12 with order of bitboards the same as order of pieces

    public Board(String fen) {

        initializeBitboards();
        char[] fen_array = fen.toCharArray(); //works as intended
        
        
        for (int i = 0; i < fen_array.length; i++) {
            
            int bitboard_index = Arrays.binarySearch(pieces, (char) fen_array[i]); // works as intended
            
            System.out.println("bitboard index for " + fen_array[i] + " is " + bitboard_index);
            if (bitboard_index >= 0) {
                System.out.println("bitboard index piece: " + pieces[bitboard_index]);
                System.out.println("Bitboard for " + pieces[11]); //change 
                System.out.println("Before: " + longtoBinary(bitboards[11]));
                bitboards[bitboard_index] *= 0b10;
                
                bitboards[bitboard_index] += 0b1;
                
                
                for (int k = 0; k < pieces.length; k++) {
                    if (k != bitboard_index) {
                        bitboards[k] = bitboards[k] * 0b10;
                    }

                }
                System.out.println("After: " + longtoBinary(bitboards[11]));
                // System.out.println("After for loop for other bitboards: " + bitboards[bitboard_index]);
            } else {
                System.out.println("fen_array[i] is " + fen_array[i]);
                if (fen_array[i] != '/') {
                    int skips = Character.getNumericValue(fen_array[i]);
                    // if(skips != 3){
                    //     return;
                    // }
                    for (int j = 0; j < skips; j++) {
                        for (int k = 0; k < pieces.length; k++) {
                            // System.out.println( fen_array[k] + ": when blank bitboards before: " + bitboards[k]);
                            bitboards[k] = bitboards[k] * 0b10;
                            // System.out.println( fen_array[k] + ": when blank bitboards after : " + bitboards[k]);
                        }
                        System.out.println("bitboard for r after the " + (j+1) + " round of adding zero " + longtoBinary(bitboards[11]));

                    }
                    
                }
            }

        }
    }

    public void initializeBitboards() {
        bitboards = new long[pieces.length];
        for (int i = 0; i < pieces.length; i++) {
            bitboards[i] = 0b0;
        }
    }

    public void displayBitboards() {
        for (int i = 0; i < pieces.length; i++) {
            System.out.println("bitboard for " + pieces[i] + ": " + longtoBinary(bitboards[i]));
        }
    }
    public static String longtoBinary(long l){
        int n = 0;
        String binaryString = "";
        boolean running = true;
        int i = 0;
        while (running){
            
            if (l < (long) Math.pow(2, i)){
                n = i-1;
                // System.out.println("n = " + n);
                running = false;
            }
            i++;
            
        }
        // System.out.println("n = " + n);

        for(int j = n; j >=0; j--){
            if (l >= Math.pow(2, j)){
                l = l - (long) Math.pow(2, j);
                binaryString = binaryString + "1";
            }
            else{
                binaryString = binaryString +"0";

            }
        }
        if (n == -1){
            binaryString = "0";
        }
        return binaryString;
    }
}
