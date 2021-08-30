package org.jskat.ai.julius;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.jskat.ai.AbstractAIPlayer;
import org.jskat.data.GameAnnouncement;
import org.jskat.data.GameAnnouncement.GameAnnouncementFactory;
import org.jskat.util.Card;
import org.jskat.util.CardList;
import org.jskat.util.GameType;
import org.jskat.util.Player;
import org.jskat.util.Suit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Random player for testing purposes and driving the other players nuts.
 */
public class JuliusAI extends AbstractAIPlayer {

  private static Logger log = LoggerFactory.getLogger(JuliusAI.class);

  public JuliusAI() {
    this("Julius"); //$NON-NLS-1$
  }

  public JuliusAI(final String newPlayerName) {
    setPlayerName(newPlayerName);
  }

  @Override
  public Card playCard() {
    return getPlayableCards(knowledge.getTrickCards()).get(0);
//    if (knowledge.getPlayerPosition() == knowledge.getDeclarer()) {
//      Card card = SinglePlayerCardEvaluator.playCard(knowledge, rules,
//          getPlayableCards(knowledge.getTrickCards()));
//      if (card == null) {
//        card = getPlayableCards(knowledge.getTrickCards()).get(0);
//      }
//      return getPlayableCards(knowledge.getTrickCards()).get(0);
//    }else {
//      return getPlayableCards(knowledge.getTrickCards()).get(0);
//    }

  }

  @Override
  public Boolean pickUpSkat() {
    return true;
  }

  @Override
  public Boolean playGrandHand() {
    return false;
  }

  @Override
  public GameAnnouncement announceGame() {
    final GameAnnouncementFactory factory = GameAnnouncement.getFactory();

    factory.setGameType(
        BidEvaluator.suggestedGameType(knowledge.getOwnCards(), knowledge.getPlayerPosition()));

    return factory.getAnnouncement();
  }

  @Override
  public Integer bidMore(final int nextBidValue) {
    return BidEvaluator.bidMore(knowledge);
  }

  @Override
  public Boolean holdBid(final int currBidValue) {
    return BidEvaluator.holdBid(knowledge);
  }

  @Override
  public void startGame() {
    // do nothing
  }


  @Override
  public CardList getCardsToDiscard() {
    final CardList result = new CardList();

    CardList discardableCards = new CardList(knowledge.getOwnCards());
    GameType gameType = BidEvaluator.suggestedGameType(discardableCards,
        knowledge.getPlayerPosition());

    if (!gameType.equals(GameType.NULL)) {
      discardableCards.removeAll(List.of(Card.CA, Card.DA, Card.SA, Card.HA));
      discardableCards.removeAll(List.of(Card.CJ, Card.DJ, Card.SJ, Card.HJ));
      //blank drücken
      List<Card> blankCards = findBlankCards(discardableCards, 1);
      List<Card> blankCards2 = findBlankCards(discardableCards, 2);
      List<Card> blankCards3 = findBlankCards(discardableCards, 3);
      List<Card> blankCards4 = findBlankCards(discardableCards, 4);
      if (blankCards.size() > 1) {
        result.add(blankCards.get(0));
        result.add(blankCards.get(1));

      } else if (blankCards2.size() > 0) {
        result.add(blankCards2.get(0));
        result.add(blankCards2.get(1));

      } else if (blankCards3.size() > 0) {
        result.add(blankCards3.get(0));
        result.add(blankCards3.get(1));

      } else if (blankCards4.size() > 0) {
        result.add(blankCards4.get(0));
        result.add(blankCards4.get(1));
      } else {
        discardableCards.sort(gameType);
        result.add(discardableCards.get(0));
        result.add(discardableCards.get(1));
      }

    } else {
      //TODO Null -> Lücke drücken -> Ass zu 4. -> blanke 8 -> 7+10 -> 8+9 ...
      discardableCards.sort(gameType);
      result.add(discardableCards.get(0));
      result.add(discardableCards.get(1));

    }

    if (result.size() != 2) {
      log.error("Falsche Anzahl Karten gedrückt");
    }
    return result;
  }

  private List<Card> findBlankCards(CardList discardableCards, int amount) {
    ArrayList<Card> cards = new ArrayList<>();
    int amountClubs = discardableCards.getSuitCount(Suit.CLUBS, false);
    int amountSpades = discardableCards.getSuitCount(Suit.SPADES, false);
    int amountHearts = discardableCards.getSuitCount(Suit.HEARTS, false);
    int amountDiamonds = discardableCards.getSuitCount(Suit.DIAMONDS, false);

    if (amountClubs == amount) {
      cards.addAll(StreamSupport.stream(discardableCards.spliterator(), false)
          .filter(i -> i.getSuit().equals(Suit.CLUBS)).collect(Collectors.toList()));
    }
    if (amountSpades == amount) {
      cards.addAll(StreamSupport.stream(discardableCards.spliterator(), false)
          .filter(i -> i.getSuit().equals(Suit.SPADES)).collect(Collectors.toList()));
    }
    if (amountHearts == amount) {
      cards.addAll(StreamSupport.stream(discardableCards.spliterator(), false)
          .filter(i -> i.getSuit().equals(Suit.HEARTS)).collect(Collectors.toList()));
    }
    if (amountDiamonds == amount) {
      cards.addAll(StreamSupport.stream(discardableCards.spliterator(), false)
          .filter(i -> i.getSuit().equals(Suit.DIAMONDS)).collect(Collectors.toList()));

    }
    return cards;
  }

  @Override
  public void preparateForNewGame() {
    // nothing to do for AIPlayerRND
  }

  @Override
  public void finalizeGame() {
    // nothing to do for AIPlayerRND
  }

  @Override
  public Boolean callContra() {
    return false;
  }

  @Override
  public Boolean callRe() {
    return false;
  }
}