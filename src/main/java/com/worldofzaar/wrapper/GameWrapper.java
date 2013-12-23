package com.worldofzaar.wrapper;

import com.worldofzaar.entity.ActiveCard;
import com.worldofzaar.entity.Game;
import com.worldofzaar.entity.Hero;
import com.worldofzaar.entity.enums.Activity;
import com.worldofzaar.entity.enums.Location;
import com.worldofzaar.modelAttribute.Card;
import com.worldofzaar.modelAttribute.Move;
import com.worldofzaar.service.ActiveCardService;
import com.worldofzaar.service.ActiveWarriorCardService;
import com.worldofzaar.service.HeroService;
import com.worldofzaar.util.UserInformation;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Дмитрий
 * Date: 20.12.13
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
public class GameWrapper {


    private Game game;
    private HashMap<Integer, ActiveCard> allCards;
    private HashMap<Integer, Hero> allHeroes;
    private UserInformation userInformation;

    public GameWrapper(Game game, UserInformation userInformation) {
        this.game = game;
        this.userInformation = userInformation;
        allCards = new HashMap<Integer, ActiveCard>();
        allHeroes = new HashMap<Integer, Hero>();
        initiateHashMapData();
    }

    private void initiateHashMapData() {
        initiateAllHeroes();
        initiateAllCards();
    }

    private void initiateAllCards() {
        Collection<Hero> heroes = allHeroes.values();
        if (heroes.size() == 0)
            return;
        for (Hero hero : heroes) {
            if (hero.getHeroCard().getFirstActiveWarriorCard() != null)
                allCards.put(hero.getHeroCard().getFirstActiveWarriorCard().getActiveCardId(), hero.getHeroCard().getFirstActiveWarriorCard());
            if (hero.getHeroCard().getSecondActiveWarriorCard() != null)
                allCards.put(hero.getHeroCard().getSecondActiveWarriorCard().getActiveCardId(), hero.getHeroCard().getSecondActiveWarriorCard());
            if (hero.getHeroCard().getThirdActiveWarriorCard() != null)
                allCards.put(hero.getHeroCard().getThirdActiveWarriorCard().getActiveCardId(), hero.getHeroCard().getThirdActiveWarriorCard());
            if (hero.getHeroCard().getSupportCard() != null)
                allCards.put(hero.getHeroCard().getSupportCard().getActiveCardId(), hero.getHeroCard().getSupportCard());
        }
    }

    private Hero getHero(Integer userId) {
        Collection<Hero> collection = allHeroes.values();
        for (Hero hero : collection) {
            if (hero != null && hero.getUserId().equals(userId))
                return hero;
        }
        return null;
    }

    private Hero getCurrentHero() {
        return getHero(userInformation.getUserId());
    }

    private void initiateAllHeroes() {
        if (game.getFirstHero() != null)
            allHeroes.put(game.getFirstHero().getHeroId(), game.getFirstHero());
        if (game.getSecondHero() != null)
            allHeroes.put(game.getSecondHero().getHeroId(), game.getSecondHero());
        if (game.getThirdHero() != null)
            allHeroes.put(game.getThirdHero().getHeroId(), game.getThirdHero());
        if (game.getFourthHero() != null)
            allHeroes.put(game.getFourthHero().getHeroId(), game.getFourthHero());
    }

    public boolean move(Move move) {
        if (!move.valid())
            return false;

        ActiveCard myCard = allCards.get(move.getMyCardId());
        if (myCard.getHero().getUserId().equals(userInformation.getUserId()))
            return false;

        if (move.isCardAttack()) {
            ActiveCard enemyCard = allCards.get(move.getEnemyCardId());
            return attackCard(myCard, enemyCard);
        }

        Hero enemy = allHeroes.get(move.getEnemyId());
        return attackHero(myCard, enemy);

    }

    private boolean attackHero(ActiveCard card, Hero hero) {
        if (card == null || hero == null)
            return false;
        HeroService heroService = new HeroService();
        if (card.getActiveWarriorCard() == null)
            return false;
        boolean isAttacked = hero.attackHero(card.getActiveWarriorCard().getCurrentAttack());
        if (hero.isDead())
            hero.setDeadStep(game.getStep());
        heroService.updateHero(hero);
        return isAttacked;
    }

    private boolean attackCard(ActiveCard myCard, ActiveCard enemyCard) {
        if (myCard == null || enemyCard == null)
            return false;
        ActiveWarriorCardService activeWarriorCardService = new ActiveWarriorCardService();
        if (myCard.getActiveWarriorCard() == null || enemyCard.getActiveWarriorCard() == null)
            return false;
        boolean isAttacked = enemyCard.attackCard(myCard);
        activeWarriorCardService.updateCard(enemyCard.getActiveWarriorCard());
        return isAttacked;
    }

    public boolean throwOff(Card card) {
        if (!card.valid()) return false;
        ActiveCard myCard = allCards.get(card.getCardId());
        if (myCard == null || !myCard.getHero().getUserId().equals(userInformation.getUserId()))
            return false;
        if (myCard.getLocation() == Location.HAND) {
            myCard.setLocation(Location.TALON);
            ActiveCardService activeCardService = new ActiveCardService();
            activeCardService.updateCard(myCard);

            Hero hero = myCard.getHero();
            hero.setEnergy(hero.getEnergy() + myCard.getEnergyOfThrow());

            HeroService heroService = new HeroService();
            heroService.updateHero(hero);

            return true;
        }
        return false;
    }

    public boolean skipMove() {
        Hero hero = getCurrentHero();
        if (hero.getActivity() == Activity.ACTIVE) {
            hero.setNegativeEffect(hero.getNegativeEffect() + 1);
            return true;
        }
        return false;
    }

}
