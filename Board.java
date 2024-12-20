import java.util.Arrays;

public class Board {
    char[] pieces = {'B','K','N','P','Q','R','b','k','n','b','q','r'}; //capital letters for white and lowercase letters for black
    long[] bitboards; //array of size 12 with order of bitboards the same as order of pieces
    
    public Board(String fen){
        initializeBitboards();
        char[] fen_array = fen.toCharArray();
        for(int i =0; i < fen_array.length;i++){
           int bitboard_index = Arrays.binarySearch(pieces, (char) fen_array[i]);
           if (bitboard_index > 0){
            bitboards[bitboard_index] =  bitboards[bitboard_index] * 2 + 0b1;
            
            for(int k = 0; k < pieces.length; k++){
                if(k != i){
                    bitboards[k] = bitboards[k] * 2 + 0b0;
                }
                
            }
           }
           else{
            if (fen_array[i] != '/'){
                int skips = (int) fen_array[i];
                for(int j = 0; j < skips; j++){
                    for(int k = 0; k < pieces.length; k++){
                        bitboards[k] = bitboards[k] * 2 + 0b0;
                    }
                }
            }
           }
           
        }
    }
    public void initializeBitboards(){
        bitboards = new long[pieces.length];
        for(int i = 0; i < pieces.length; i ++){
            bitboards[i] = 0b0;
        }
    }
    public void displayBitboards(){
        for(int i = 0; i < pieces.length; i++){
            System.out.println(bitboards[i]);
        }
    }
}
