package org.jskat.ai.julius;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jskat.util.Card;
import org.jskat.util.CardList;
import org.jskat.util.Player;
import org.junit.jupiter.api.Test;

class BidEvaluatorTest {

  @Test
  public void extractBiddingStatesForAiLearning() throws IOException {
    String filePath = "C:\\Users\\achen\\Downloads\\iss2-games-04-2021(1).sgf";

    for (int playerPosition = 0; playerPosition < Player.values().length; playerPosition++) {
      try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
        Integer finalPlayerPosition = playerPosition;
        stream.skip(190000).limit(200000)
            .filter(game -> game.contains("P" + finalPlayerPosition + "[zoot]"))
            .collect(Collectors.toList())
            .forEach(game -> {
              IssGameDto issGameDto = extractedGame(game);
              Map<Integer, Integer> maxBidByPlayerPosition = IssGameEvaluator.maxBidByPlayerPosition(
                  issGameDto);
              Map<Integer, String[]> handByPlayerPosition = issGameDto.getHandByPlayerPosition();
              System.out.println(finalPlayerPosition + Arrays.toString(
                  handByPlayerPosition.get(finalPlayerPosition)) + maxBidByPlayerPosition.get(
                  finalPlayerPosition).toString());
            });
      }
    }
  }


  @Test
  public void compareBiddingHandsWithJuliusBidHelper() throws IOException {
    String filePath = "C:\\Users\\achen\\Downloads\\iss2-games-04-2021(1).sgf";

    AtomicReference<Integer> total = new AtomicReference<>(0);
    AtomicReference<Integer> countJuliusBidTooHigh = new AtomicReference<>(0);
    AtomicReference<Integer> countJuliusBidTooLow = new AtomicReference<>(0);

    try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
      stream.skip(190000).limit(200000).forEach(game -> {
        IssGameDto issGameDto = extractedGame(game);
        Map<Integer, Integer> maxBidByPlayerPosition = IssGameEvaluator.maxBidByPlayerPosition(
            issGameDto);
        Map<Integer, String[]> handByPlayerPosition = issGameDto.getHandByPlayerPosition();

        for (int i = 0; i < maxBidByPlayerPosition.size(); i++) {
          String[] strings = handByPlayerPosition.get(i);
          List<Card> hand = new ArrayList<>();
          for (String cardString : strings) {
            hand.add(Card.getCardFromString(cardString));
          }
          CardList hadCardList = new CardList(hand);
          Player playerPosition = Player.values()[i];
          Integer currentBid = 0;
          Integer myBid = BidEvaluator.bidMore(hadCardList, playerPosition, currentBid);

          total.getAndSet(total.get() + 1);
          if (myBid > 17 && maxBidByPlayerPosition.get(i) < 18) {
            countJuliusBidTooHigh.getAndSet(countJuliusBidTooHigh.get() + 1);
          } else if (myBid < 18 && maxBidByPlayerPosition.get(i) > 17) {
            countJuliusBidTooLow.getAndSet(countJuliusBidTooLow.get() + 1);
          }
        }
      });
//        String s = stream.skip(3542).limit(100).findAny().toString();

      System.out.println(total);
      System.out.println(countJuliusBidTooHigh);
      System.out.println(countJuliusBidTooLow);
    }
  }


  String zootAsPlayer1 = "w S8.D7.D8.C7.DQ.DJ.ST.D9.HQ.SK.CK.S9.CA.CT.HJ.SJ.H8.DT.H7.SQ.DK.C9.DA.CJ.C8.HK.CQ.H9.SA.HA.S7.HT 1 18 0 p 2 20 1 y 2 22 1 y 2 23 1 y 2 24 1 y 2 p 1 s w S7.HT 1 C.HT.DT 0 DQ 1 CA 2 DK 1 H7 2 HK 0 HQ 2 SA 0 ST 1 S9 2 DA 0 D9 1 CT 1 H8 2 HA 0 SK 2 H9 0 DJ 1 HJ 1 SQ 2 CQ 0 S8 2 C9 0 C7 1 CK 1 S7 2 C8 0 D8 2 CJ 0 D7 1 SJ";

  @Test
  public void readGame() {

    IssGameDto issGameDto = extractedGame(zootAsPlayer1);
    System.out.println(issGameDto.toString());
  }


  @Test
  public void highestBidsByPlayerNumber() {

    IssGameDto issGameDto = extractedGame(zootAsPlayer1);
    Map<Integer, Integer> integerIntegerMap = IssGameEvaluator.maxBidByPlayerPosition(issGameDto);
    System.out.println(integerIntegerMap);


  }


  public static class IssGameEvaluator {

    public static Map<Integer, Integer> maxBidByPlayerPosition(IssGameDto gameDto) {
      Map<Integer, Integer> bidMap = new HashMap<>() {{
        put(0, 0);
        put(1, 0);
        put(2, 0);
      }};
      if (gameDto == null || gameDto.getBidding() == null || gameDto.getBidding().length() < 1) {
        return bidMap;
      }

      String bidding = gameDto.getBidding();
      String[] bidArray = bidding.split(" ");
      for (int i = 0; i < bidArray.length; i++) {
        if (bidArray[i + 1].chars().allMatch(Character::isDigit)) {
          bidMap.put(Integer.valueOf(bidArray[i]), Integer.valueOf(bidArray[i + 1]));
        }
        if (bidArray[i + 1].contains("y")) { //hören == Spieler 0
          bidMap.put(Integer.valueOf(bidArray[i]), Integer.valueOf(bidArray[i - 1]));
        }
        i++;
      }
      return bidMap;
    }
  }

  private IssGameDto extractedGame(String issSkatEntry) {
    IssGameDto issGameDto = new IssGameDto();
    String[] splittedGame;
    String dealedCards;
    String test = issSkatEntry.split("\\[")[13];
    String replace = test.replace("]R", "").replaceFirst("w ", "");

    dealedCards = replace.substring(0, 95);
    if (!replace.contains("p")) {
      System.out.println("Interesting game: " + replace);
      issGameDto.setGamePlay(replace.substring(96));
      issGameDto.setBidding("");
    } else {
      int endOfBidding = replace.lastIndexOf("p ");
      String bidding = replace.substring(96, endOfBidding + 2);
      String gamePlay = replace.substring(endOfBidding + 2);
      issGameDto.setGamePlay(gamePlay);
      issGameDto.setBidding(bidding);
    }

    Map<Integer, String[]> playerHands = new HashMap<>();
    playerHands.put(0, dealedCards.substring(0, 30).split("\\."));
    playerHands.put(1, dealedCards.substring(30, 60).split("\\."));
    playerHands.put(2, dealedCards.substring(60, 90).split("\\."));
    issGameDto.setHandByPlayerPosition(playerHands);

    return issGameDto;
  }


  public class IssGameDto {

    public Map<Integer, String[]> getHandByPlayerPosition() {
      return handByPlayerPosition;
    }

    public void setHandByPlayerPosition(
        Map<Integer, String[]> handByPlayerPosition) {
      this.handByPlayerPosition = handByPlayerPosition;
    }

    Map<Integer, String[]> handByPlayerPosition;

    String bidding;


    public String getBidding() {
      return bidding;
    }

    public void setBidding(String bidding) {
      this.bidding = bidding;
    }

    public String getGamePlay() {
      return gamePlay;
    }

    public void setGamePlay(String gamePlay) {
      this.gamePlay = gamePlay;
    }

    String gamePlay;


  }
}
