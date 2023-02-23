package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public class Strategy {

   enum Pair {
      NONE,
      DOUBLE,
      DOUBLETROUBLE,
      TRIPLE,
      QUADRUPLE
   }

   public Pair findPair(List<Card> handCards, List<Card> communityCards){

      int kind = 0;

      String firstHandCard = handCards.get(0).getRank().getValue();
      String secondHandCard = handCards.get(0).getRank().getValue();

      if(firstHandCard.equals(secondHandCard)) kind++;

      if(kind == 1) return Pair.DOUBLE;

      return Pair.NONE;
   }



   public Bet decide(final Table table) {
      System.out.println(table);

      Integer currentPot = table.getPot();

      List<Card> communityCards = table.getCommunityCards();

      Optional<Player> player = table.getPlayers().stream().filter(p -> p.getName().equals("Str8Flush")).findFirst();

      if(!player.isPresent()){
         return new Bet().bet(0);
      }

      @Valid List<Card> cards = player.get().getCards();

      Card firstCard = cards.get(0);
      Card secondCard = cards.get(1);

      if(findPair(cards, communityCards)== Pair.DOUBLE) return new Bet().bet(table.getMinimumRaise());

      switch (firstCard.getRank().getValue()){
         case "A":
         case "K":
         case "Q":
         case "J":
            return new Bet().bet(table.getMinimumBet());
      }

      switch (secondCard.getRank().getValue()){
         case "A":
         case "K":
         case "Q":
         case "J":
            return new Bet().bet(table.getMinimumBet());
      }

      return new Bet().bet(0);
   }

}
