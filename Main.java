public class Main {
    public static void main(String args[]){
        Board b = new Board("r3r1k1/pp3nPp/1b1p1B2/1q1P1N2/8/P4Q2/1P3PK1/R6R");
        System.out.println("-----------------------");
        b.displayBitboards();
        // long bitboard = 0b000000001000;
        // System.out.println(bitboard);
        // System.out.println(bitboard + 0b010);
    }
}
