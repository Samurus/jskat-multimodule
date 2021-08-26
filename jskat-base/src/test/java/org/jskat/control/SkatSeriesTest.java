/**
 * Copyright (C) 2020 Jan Schäfer (jansch@users.sourceforge.net)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.jskat.control;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Arrays;

import com.google.common.eventbus.EventBus;
import java.util.HashMap;
import java.util.Map;
import org.jskat.AbstractJSkatTest;
import org.jskat.ai.algorithmic.AlgorithmicAIPlayer;
import org.jskat.ai.newalgorithm.AlgorithmAI;
import org.jskat.ai.rnd.AIPlayerRND;
import org.jskat.ai.rnd.JuliusAI;
import org.jskat.gui.UnitTestView;
import org.jskat.player.JSkatPlayer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.slf4j.LoggerFactory;

/**
 * Test class for {@link SkatSeries}
 */
public class SkatSeriesTest extends AbstractJSkatTest {

    private final static String TABLE_NAME = "Table 1";

    @BeforeAll
    public static void turnOffLogging(){
        final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.OFF );
    }

    @Test
    public void testSkatSeriesRun()
        throws ExecutionException, InterruptedException, NoSuchFieldException {

        long l = System.currentTimeMillis();

        int rounds = 3000;
        
        System.out.printf("starte %d serien: %n", rounds);

        String julius = "Julius";
        String rando = "rando";
        String algo = "algo";
//        AlgorithmicAIPlayer()
//        JuliusAI
//        AIPlayerRND
//        AlgorithmAI (bugged)
        AlgorithmicAIPlayer algorithmAI = new AlgorithmicAIPlayer(algo);
        AIPlayerRND aiPlayerRND = new AIPlayerRND(rando);
        JuliusAI juliusAI = new JuliusAI(julius);

        List<SkatGame> playerLongMap = runSubsetOfSeries(rounds, Arrays.asList(
            juliusAI,
            algorithmAI,
            aiPlayerRND));
        long l1 = System.currentTimeMillis() - l;
        System.out.println("Spieldauer: " + l1);
        Map<String, Double> summary = getSummary(playerLongMap, julius, rando, algo);

        System.out.println(summary);

    }

    private List<SkatGame> runSubsetOfSeries(int rounds, List<JSkatPlayer> players)
        throws InterruptedException, ExecutionException {

        SkatSeries series = initSkatGame();
        series.setPlayers(players);

        series.setMaxRounds(rounds, false);
        CompletableFuture.runAsync(series::run).get();

        return series.getData().getGames();


    }

    private Map<String, Double> getSummary(List<SkatGame> games, String julius, String rando,
        String algo) {
        Map<String, Double> playerIntegerMap = new HashMap<>();

        playerIntegerMap.put(julius, resultValueForPlayer(games, julius));
        playerIntegerMap.put(rando, resultValueForPlayer(games, rando));
        playerIntegerMap.put(algo, resultValueForPlayer(games, algo));
        return playerIntegerMap;
    }


    private double resultValueForPlayer(List<SkatGame> games, String playerName) {

        long isWon = games.stream()
            .filter(i -> i.getData().getDeclarer() != null) //eingepasste Spiele
            .filter(i -> i.getDeclarerPlayerInstance().getPlayerName().equals(playerName))
            .filter(i -> i.getData().getGameResult().isWon())
            .count();

        long isLost = games.stream()
            .filter(i -> i.getData().getDeclarer() != null)
            .filter(i -> i.getDeclarerPlayerInstance().getPlayerName().equals(playerName))
            .filter(i -> !i.getData().getGameResult().isWon())
            .count();

        long isWonAsNotDeclarer = games.stream()
            .filter(i -> i.getData().getDeclarer() != null)
            .filter(i -> !i.getDeclarerPlayerInstance().getPlayerName().equals(playerName))
            .filter(i -> !i.getData().getGameResult().isWon())
            .count();

        Integer sumOfGames = games.stream()
            .filter(i -> i.getData().getDeclarer() != null)
            .filter(i -> i.getDeclarerPlayerInstance().getPlayerName().equals(playerName))
            .map(i -> i.getData().getResult().getGameValue())
            .reduce(0, Integer::sum);

        return (sumOfGames + 40 * isWonAsNotDeclarer + 50 * isWon - 50 * isLost) / (double) games.size();
    }

    private SkatSeries initSkatGame() {
        JSkatEventBus.TABLE_EVENT_BUSSES.put(TABLE_NAME, new EventBus());
        final SkatSeries series = new SkatSeries(TABLE_NAME);
        final UnitTestView view = new UnitTestView();
        series.setView(view);
        return series;
    }

}
