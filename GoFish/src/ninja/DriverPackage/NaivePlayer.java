package ninja.DriverPackage;

import ninja.emilior.Hand;
import ninja.emilior.Play;
import ninja.emilior.PlayerStrategy;
import ninja.emilior.RecordedPlay;

/**
 * Created by emili on 3/6/2017.
 */
public class NaivePlayer implements PlayerStrategy {
    private int totalPlayers;
    private int myNumber;

    public NaivePlayer(int myNumber, int totalNum){
        initialize(myNumber, totalNum);
    }


    @Override
    public void initialize(int yourPlayerNumber, int totalNumberOfPlayers) {
        myNumber = yourPlayerNumber;
        totalPlayers = totalNumberOfPlayers;
    }

    @Override
    public Play doTurn(Hand hand) {
        int hold = (int) (Math.random()*hand.size());
        int cardChoice = hand.getCard(hold).getRank();
        int targetPlayer = (int) (Math.random()* totalPlayers);
        while (targetPlayer == myNumber){
            targetPlayer = (int) (Math.random()* totalPlayers);
        }
        return new Play(cardChoice, targetPlayer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NaivePlayer that = (NaivePlayer) o;

        if (totalPlayers != that.totalPlayers) return false;
        return myNumber == that.myNumber;
    }

    @Override
    public int hashCode() {
        int result = totalPlayers;
        result = 31 * result + myNumber;
        return result;
    }

    /**
     * Does nothing. Naive players don't think about the state of the game.
     *
     * @param recordedPlay an object representing the information of the play that just occurred and its results.
     */
    @Override
    public void playOccurred(RecordedPlay recordedPlay) {
        return;
    }
}
