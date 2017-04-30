package ninja.DriverPackage;

import ninja.emilior.GameEngine;
import ninja.emilior.PlayerInfo;
import ninja.emilior.PlayerStrategy;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    private static final String NAIVE_IDENTIFIER = "naive";


    public static void main(String[] args) {
        try {
            int totalDesiredGames = Integer.parseInt(args[0]);
            ArrayList<String> actualParticipants = countPlayers(args);
            PlayerStrategy[] strats = formatize(actualParticipants);
            HashMap<Integer, Integer> winnerCount = countWinners(strats, totalDesiredGames);
            for (int winner : winnerCount.keySet()) {
                System.out.println("Player" + winner + " has won " + winnerCount.get(winner) + " times");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks given array to see the valid players and adds to an arraylist.
     *
     * @param args
     * @return
     */
    public static ArrayList<String> countPlayers(String[] args) {
        ArrayList<String> actualParticipants = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            if (args[i].equalsIgnoreCase(NAIVE_IDENTIFIER)) {
                actualParticipants.add(NAIVE_IDENTIFIER);
            }
        }
            return actualParticipants;
    }

    /**
     * Creates a hashmap of winners with Keys of winner number and values of amount of times they have won.
     *
     * @param strats
     * @param total
     * @return
     */

    public static HashMap<Integer, Integer> countWinners(PlayerStrategy[] strats, int total){
        HashMap<Integer, Integer> winnerCount = new HashMap<>();
        for (int i = 0; i < total; i++) {
            GameEngine goFish = new GameEngine(strats.length, strats);
            ArrayList<Integer> winners = goFish.playGame();
            for (Integer winner : winners) {
                if (winnerCount.containsKey(winner)) {
                    winnerCount.put(winner, (winnerCount.get(winner) + 1));
                } else {
                    winnerCount.put(winner, 1);
                }
            }
        }
        return winnerCount;
    }

    /**
     * Method to format arrayList of strings to Array of PlayerStrategy objects.
     *
     * @param participants
     * @return
     */
    public static PlayerStrategy[] formatize (ArrayList <String> participants) {
        PlayerStrategy[] output = new PlayerStrategy[participants.size()];
        for (int i = 0; i < participants.size(); i++) {
            switch (participants.get(i)) {
                case (NAIVE_IDENTIFIER):
                    output[i] = new NaivePlayer(i, participants.size());
                    break;
                default:
                    break;
            }
        }
        return output;
    }
}

