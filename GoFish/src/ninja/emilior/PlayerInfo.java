package ninja.emilior;

/**
 * Created by emili on 3/7/2017.
 */
public class PlayerInfo {
    private PlayerStrategy player;
    private int score;
    private Hand hand;

    PlayerInfo(int newScore, Hand newHand, PlayerStrategy designatedStrat){
        player = designatedStrat;
        hand = newHand;
        score = newScore;
    }

    public PlayerStrategy getPlayer() {
        return player;
    }

    public void setPlayer(PlayerStrategy player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerInfo that = (PlayerInfo) o;

        if (score != that.score) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        return hand != null ? hand.equals(that.hand) : that.hand == null;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + score;
        result = 31 * result + (hand != null ? hand.hashCode() : 0);
        return result;
    }
}
