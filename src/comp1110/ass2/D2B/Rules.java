package comp1110.ass2.D2B;

public class Rules {
    private boolean isPhase1Over; // whether the first phase is over
    private boolean isPhase2Over; // whether the second phase is over
    private boolean canPlaceTile; // whether a tile can be placed
    private boolean isVictory; // whether there is a victory
    private int currentRound; // current round number
    private String currentPlayer; // current player

    // Check whether the first phase is over
    public boolean isPhase1Over(){return false;}

    // Check whether the second phase is over
    public boolean isPhase2Over(){return false;}

    public static boolean isCoordinate(String s) {
        return s.matches("\\d+,\\d+");
    }
}