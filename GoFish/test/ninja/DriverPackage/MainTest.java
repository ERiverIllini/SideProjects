package ninja.DriverPackage;

import ninja.emilior.Card;
import ninja.emilior.PlayerStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static ninja.DriverPackage.Main.countPlayers;
import static ninja.DriverPackage.Main.countWinners;
import static ninja.DriverPackage.Main.formatize;
import static org.junit.Assert.*;

/**
 * Created by emili on 3/7/2017.
 */
public class MainTest {


    @Test
    public void testRandIssues()  {
        String[] args = {"naive", "naive", "naive"};
        int totalDesiredGames = 1000;
        ArrayList<String> actualParticipants = countPlayers(args);
        PlayerStrategy[] strats = formatize(actualParticipants);
        HashMap<Integer, Integer> winnerCount = countWinners(strats, totalDesiredGames);
        int totalWins = 0;
        for (int winner : winnerCount.keySet()) {
            totalWins += winnerCount.get(winner);
        }
        assertTrue(totalWins>=totalDesiredGames);
    }
}