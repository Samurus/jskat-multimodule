package org.jskat.ai.julius;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.jskat.ai.mjl.Helper;
import org.jskat.player.ImmutablePlayerKnowledge;
import org.jskat.util.Card;
import org.jskat.util.CardList;
import org.jskat.util.GameType;
import org.jskat.util.Player;
import org.jskat.util.Rank;
import org.jskat.util.SkatConstants;
import org.jskat.util.Suit;

public class BidEvaluator {


  public static Integer bidMore(ImmutablePlayerKnowledge knowledge) {
    Integer currentBid = knowledge.getHighestBid().values().stream().mapToInt(v -> v).max().orElse(0);
    Integer maxBid = evaluateMaxBid(knowledge.getOwnCards(), knowledge.getPlayerPosition());

    if (maxBid > currentBid) {
      return SkatConstants.getNextBidValue(currentBid);
    } else {
      return -1;
    }
  }

  public static Integer bidMore(CardList ownCards, Player playerPosition, Integer currentBid) {
    Integer maxBid = evaluateMaxBid(ownCards, playerPosition);

    if (maxBid > currentBid) {
      return SkatConstants.getNextBidValue(currentBid);
    } else {
      return -1;
    }
  }

  public static GameType suggestedGameType(CardList ownCards, Player playerPosition) {
    List<Card> jacks = ownCards.availableJacks();
    List<Card> aces = ownCards.availableAces();
    Suit mostFrequentSuitColor = ownCards.getMostFrequentSuit();
    int relevantCardsGrand = 4;
    int relevantCardsSuit = 4;
    int relevantValueNull = 6;

    if (playerPosition.equals(Player.MIDDLEHAND)){
      relevantCardsGrand++;
      relevantCardsSuit++;
    }

    //null
    if (ownCards.getTotalValue() < relevantValueNull) {
      return GameType.NULL;
    }

    //grand
    if (jacks.size() + aces.size() > relevantCardsGrand) {
      return GameType.GRAND;
    }



    //suit
    GameType clubs = checkForSuitGame(ownCards, aces, mostFrequentSuitColor,
        relevantCardsSuit);
    if (clubs != null) {
      return clubs;
    }
    return GameType.PASSED_IN;
  }

  private static GameType checkForSuitGame(CardList ownCards, List<Card> aces,
      Suit mostFrequentSuitColor, int relevantCardsSuit) {
    int aceCountMinusTrump = aces.size();
    if (ownCards.contains(Card.getCard(mostFrequentSuitColor, Rank.ACE))) {
      aceCountMinusTrump--;
    }
    if (ownCards.getSuitCount(mostFrequentSuitColor, true) + aceCountMinusTrump > relevantCardsSuit) {
      switch (mostFrequentSuitColor) {
        case CLUBS:
          return GameType.CLUBS;
        case SPADES:
          return GameType.SPADES;
        case HEARTS:
          return GameType.HEARTS;
        case DIAMONDS:
          return GameType.DIAMONDS;
      }
    }
    return null;
  }

  public static Integer evaluateMaxBid(CardList ownCards, Player playerPosition) {
    GameType gameType = suggestedGameType(ownCards, playerPosition);

    int multiplier = Helper.getMultiplier(ownCards);
    if (gameType.equals(GameType.PASSED_IN)) return -1;
    if (gameType.equals(GameType.GRAND)) return multiplier * SkatConstants.getGameBaseValue(gameType, false, false);
    if (gameType.equals(GameType.NULL)) return SkatConstants.getGameBaseValue(gameType, false, false);

    return multiplier * SkatConstants.getGameBaseValue(gameType, false, false);
  }

  public static Boolean holdBid(ImmutablePlayerKnowledge knowledge) {
    Integer max = knowledge.getHighestBid().values().stream().mapToInt(v -> v).max().orElse(0);
    Integer maxBid = evaluateMaxBid(knowledge.getOwnCards(), knowledge.getPlayerPosition());

    return maxBid > max;

  }
}
