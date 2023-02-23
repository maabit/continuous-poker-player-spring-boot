package org.continuouspoker.player.logic;

import org.continuouspoker.player.model.Bet;
import org.continuouspoker.player.model.Card;
import org.continuouspoker.player.model.Player;
import org.continuouspoker.player.model.Table;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

public class Strategy {

   public Bet decide(final Table table) {
      System.out.println(table);

      Integer currentPot = table.getPot();

      List<Card> communityCards = table.getCommunityCards();

      Optional<Player> player = table.getPlayers().stream().filter(p -> p.getName().equals("str8-flush")).findFirst();

      if(!player.isPresent()){
         return new Bet().bet(0);
      }

      @Valid List<Card> cards = player.get().getCards();

      Card firstCard = cards.get(0);
      Card secondCard = cards.get(1);



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
