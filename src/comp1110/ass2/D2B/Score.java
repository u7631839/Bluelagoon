package comp1110.ass2.D2B;

public class Score {
    Player player;
    int score;
    Score(Player player, int score){
        this.player=player;
        this.score=score;
    }
    public int getScore(){
        return score;
    }
}
