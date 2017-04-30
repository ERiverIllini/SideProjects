package ninja.emilior;

import java.util.*;

/**
 * Created by emili on 3/6/2017.
 */
public class GameEngine {
    private final static int MAX_PLAYER_SIZE = 10;
    private Deck deck;
    private int totalBooksPlayed = 0;

    private PlayerInfo[] playerData;
    private HashSet<PlayerInfo> invalidPlayers;



    public GameEngine(int playerTotal, PlayerStrategy[] strategies){
        invalidPlayers = new HashSet<>();
        playerTotal = (playerTotal > MAX_PLAYER_SIZE) ? MAX_PLAYER_SIZE : playerTotal;
        this.deck = new Deck();
        playerData = new PlayerInfo[playerTotal];
        for (int i = 0; i < playerTotal; i++){
            Hand playersHand = new Hand(deck.draw(5));
            int score = 0;
            playerData[i] = new PlayerInfo(score, playersHand, strategies[i]);
        }
    }

    public ArrayList<Integer> playGame(){
        int turn = 1;
        while (true){
            for (int currentPlayer = 0; currentPlayer < playerData.length; currentPlayer++){
                if (!invalidPlayers.contains(playerData[currentPlayer])){
                    checkForDraw(currentPlayer);
                    boolean willFish = true;
                    while (willFish && !invalidPlayers.contains(playerData[currentPlayer])){
                        System.out.println("Turn: "+turn);
                        try{
                            willFish = makePlay(currentPlayer);
                        } catch (Exception e){
                            System.out.println(deck.isEmpty());
                            willFish = false;
                        }
                        if (totalBooksPlayed == 13){
                            return winners();
                        }
                        System.out.println(totalBooksPlayed);
                        updateValidPlayers();
                        turn+=1;
                    }
                }
            }
        }
    }

    public void updateValidPlayers(){
        if (deck.isEmpty()){
            for (PlayerInfo availablePlayer : playerData){
                if (availablePlayer.getHand().size() == 0){
                    invalidPlayers.add(availablePlayer);
                }
            }
        }
    }

    public void checkForDraw(int currentPlayer){
        if (playerData[currentPlayer].getHand().size() == 0){
            if (deck.isEmpty()) {
                playerData[currentPlayer].getHand().cards.add(deck.draw());
            }
        }
    }

    /**
     *
     *
     * @param currentPlayer
     * @return
     */
    public boolean makePlay(int currentPlayer){
        boolean fishing = true;
        Play currentPlay = playerData[currentPlayer].getPlayer().doTurn(playerData[currentPlayer].getHand());
        List<Card> adjustedCards = takeCards(currentPlay.getTargetPlayer(), currentPlay.getRank());
        playerData[currentPlayer].getHand().cards.addAll(adjustedCards);
        RecordedPlay record = new RecordedPlay(currentPlayer, currentPlay.getRank(),
                currentPlay.getTargetPlayer(), adjustedCards);

        if (adjustedCards.size() == 0){
            if (!deck.isEmpty()){
                Card drawnCard = deck.draw();
                playerData[currentPlayer].getHand().cards.add(drawnCard);
            }
            fishing = false;
        }

        System.out.println(playRecord(record));
        for (PlayerInfo playerDatum : playerData){
            playerDatum.getPlayer().playOccurred(record);
        }
        checkForBook(currentPlayer);
        return fishing;
    }

    public ArrayList<Integer> winners(){
        int highest = 0;
        ArrayList<Integer> winnerList = new ArrayList<>();
        for (PlayerInfo playerDatum : playerData){
            highest = (playerDatum.getScore() > highest) ? playerDatum.getScore() : highest;
        }
        for (int i = 0; i < playerData.length; i++){
            System.out.println("Player"+i+" had a score of "+playerData[i].getScore()+".");
            if (playerData[i].getScore() == highest){
                winnerList.add(i);
            }
        }
        return winnerList;
    }

    /**
     * Checks if a player has a book by counting the amount of each card the player has in their
     * hand. If a book is found, removes the cards from their hand and increments their score by one.
     *
     * @param currentPlayer
     */

    public void checkForBook(int currentPlayer){
        HashMap<Integer, Integer> cardCount = new HashMap<>();
        ArrayList<Integer> ranksToTake = new ArrayList<>();
        for (Card card : playerData[currentPlayer].getHand()){
            if (cardCount.containsKey(card.getRank())){
                cardCount.put(card.getRank(), cardCount.get(card.getRank())+1);
            }
            else {
                cardCount.put(card.getRank(), 1);
            }
            if (cardCount.get(card.getRank()) == 4){
                playerData[currentPlayer].setScore(playerData[currentPlayer].getScore()+1);
                totalBooksPlayed++;
                ranksToTake.add(card.getRank());
                System.out.println(bookMessage(currentPlayer, card.getRank()));
            }
        }
        for (int rank : ranksToTake){
            takeCards(rank, currentPlayer);
        }
    }

    public String playRecord(RecordedPlay recordedPlay){
        return "Player"+recordedPlay.getSourcePlayer()+" asked Player"+recordedPlay.getTargetPlayer()+" for "+
                Card.CARD_NAMES[recordedPlay.getRank()]+"s and got "+recordedPlay.getCardsReturned().size()+" card(s).";
    }

    public String bookMessage(int currentPlayer, int rank){
        return "Player"+currentPlayer+" has made a book of "+rank+"s";
    }


    /**
     * Helper function to adjust player hands. Removes cards from a players hand that match given rank then returns it.
     *
     * @param target
     * @param targetRank
     */

    public List<Card> takeCards(int targetRank, int target){
        List<Card> foundCards = new ArrayList<>();
        if (playerData[target].getHand().hasRank(targetRank)){
            for (Card card : playerData[target].getHand()){
                if (card.getRank() == targetRank){
                    foundCards.add(card);
                }
            }
        }
        playerData[target].getHand().cards.removeAll(foundCards);
        return foundCards;
    }

}
