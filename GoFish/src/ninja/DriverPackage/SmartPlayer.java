package ninja.DriverPackage;

import ninja.emilior.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by emili on 3/6/2017.
 */
public class SmartPlayer implements PlayerStrategy {
    private int myNumber;
    private int totalPlayers;
    private ArrayList<RecordedPlay> seenPlays;
    private HashMap<Integer, HashSet<Integer>> cardMap;

    public SmartPlayer(int myNum, int total){
        initialize(myNum, total);
    }

    @Override
    public void initialize(int yourPlayerNumber, int totalNumberOfPlayers) {
        myNumber = yourPlayerNumber;
        totalPlayers = totalNumberOfPlayers;
        cardMap = new HashMap<>();
        for (int player = 0; player < totalNumberOfPlayers; player++){
            if (player != myNumber){
                cardMap.put(player, new HashSet<>());
            }
        }
    }

    @Override
    public Play doTurn(Hand hand) {
        for (int player : cardMap.keySet()){
            for (Card card : hand){
                if (cardMap.get(player).contains(card.getRank())){
                    return new Play(card.getRank(), player);
                }
            }
        }

        //Do random choice.
        int hold = (int) (Math.random()*hand.size());
        int cardChoice = hand.getCard(hold).getRank();
        int targetPlayer = (int) (Math.random()*totalPlayers);
        while (targetPlayer == myNumber){
            targetPlayer = (int) (Math.random()*totalPlayers);
        }
        return new Play(cardChoice, targetPlayer);
    }

    @Override
    public void playOccurred(RecordedPlay recordedPlay) {
        HashSet<Integer> updateSet = cardMap.get(recordedPlay.getSourcePlayer());
        updateSet.add(recordedPlay.getRank());
        cardMap.put(recordedPlay.getSourcePlayer(), updateSet);

        if (cardMap.get(recordedPlay.getTargetPlayer()).contains(recordedPlay.getRank())){
            HashSet<Integer> removalSet = cardMap.get(recordedPlay.getTargetPlayer());
            removalSet.remove(recordedPlay.getRank());
            cardMap.put(recordedPlay.getTargetPlayer(), removalSet);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmartPlayer that = (SmartPlayer) o;

        if (myNumber != that.myNumber) return false;
        return totalPlayers == that.totalPlayers;
    }

    @Override
    public int hashCode() {
        int result = myNumber;
        result = 31 * result + totalPlayers;
        return result;
    }
}
