package org.jskat.ai.julius;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.jskat.data.SkatTableOptions.RuleSet;
import org.jskat.data.Trick;
import org.jskat.player.ImmutablePlayerKnowledge;
import org.jskat.util.Card;
import org.jskat.util.CardList;
import org.jskat.util.GameType;
import org.jskat.util.Suit;
import org.jskat.util.rule.SkatRule;

public class SinglePlayerCardEvaluator {

  public static Card playCard(ImmutablePlayerKnowledge knowledge, SkatRule rules,
      CardList playableCards) {







    //Trump unten - oben - unten bis keine Trümpfe da sind
    CardList myTrumpCards = new CardList(StreamSupport.stream(playableCards.spliterator(), false)
        .filter(i -> i.getSuit().equals(knowledge.getTrumpSuit())).collect(Collectors.toList()));

    int trumpCountInGame = 11;
    for (Trick completedTrick : knowledge.getCompletedTricks()) {
      trumpCountInGame = trumpCountInGame - completedTrick.getCardList().getTrumpCount(knowledge.getTrumpSuit());
    }


    if (trumpCountInGame > 8) {
      myTrumpCards.sort(knowledge.getGameType());
      return myTrumpCards.get( myTrumpCards.getLastIndexOfSuit(knowledge.getTrumpSuit()));
    } else if (trumpCountInGame > 5) {
      myTrumpCards.sort(knowledge.getGameType());
      return myTrumpCards.get(myTrumpCards.getFirstIndexOfSuit(knowledge.getTrumpSuit()));
    }

    if (knowledge.couldOpponentsHaveTrump()) {
      myTrumpCards.sort(knowledge.getGameType());
      return myTrumpCards.get(myTrumpCards.getLastIndexOfSuit(knowledge.getTrumpSuit()));
    } else {
      //dann Fehl von Oben runter
      Suit mostFrequentSuit = playableCards.getMostFrequentSuit(knowledge.getTrumpSuit());
      playableCards.sort(GameType.valueOf(mostFrequentSuit.toString()));
      return playableCards.get(0);
    }

  }

}
