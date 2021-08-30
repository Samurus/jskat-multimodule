package org.jskat.ai.julius;

import org.jskat.player.ImmutablePlayerKnowledge;
import org.jskat.util.Card;
import org.jskat.util.CardList;
import org.jskat.util.rule.SkatRule;

public class OpponentPlayerCardEvaluator {
  public static Card playCard(ImmutablePlayerKnowledge knowledge, SkatRule rules,
      CardList playableCards){

    return knowledge.getOwnCards().get(0);
  }

}
