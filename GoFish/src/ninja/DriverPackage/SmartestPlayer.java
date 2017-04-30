package ninja.DriverPackage;

import ninja.emilior.*;

import java.util.ArrayList;

/**
 * Created by emili on 3/6/2017.
 */
public class SmartestPlayer implements PlayerStrategy, ArtificialStrategy {
    private ArrayList<RecordedPlay> seenPlays;


    @Override
    public void initialize(int yourPlayerNumber, int totalNumberOfPlayers) {

    }

    @Override
    public Play doTurn(Hand hand) {
        return null;
    }

    @Override
    public void playOccurred(RecordedPlay recordedPlay) {
        seenPlays.add(recordedPlay);
    }


   @Override
    public ArrayList<Card> bestCards() {
        return null;
    }
}
