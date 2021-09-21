package model;

import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Test;

class GameStoreTest {

    GameStore gameStore;
    Costumer costumer;
    ArrayList<Game> games;
    Game game;
    Shelve shelve;

    public void scenaryGame() {
        gameStore = new GameStore();
        game = new Game(1, "Call of duty", "Weapons and Fun", 30000, "A", 4);
        games.add(game);
    }

    public void scenaryCostumer() {
        gameStore = new GameStore();
        costumer = new Costumer(12345, "111", "Pepito", games);
    }

    public void scenaryShelve() {
        gameStore = new GameStore();
        shelve = new Shelve("A", 4);
    }

    @Test
    public void addGame() {
        scenaryGame();
        String message = gameStore.addGame(game.getCode(), game.getGameName(), game.getReview(), game.getPrice(),
                game.getShelveName(), game.getAmount());
        Game g = gameStore.getGames().get(gameStore.getGames().size() - 1);
        assertEquals(message, "Game added succesfully");

        assertEquals(g.getCode(), game.getCode());
        assertEquals(g.getGameName(), game.getGameName());
        assertEquals(g.getReview(), game.getReview());
        assertEquals(g.getPrice(), game.getPrice());
        assertEquals(g.getShelveName(), game.getShelveName());
        assertEquals(g.getAmount(), game.getAmount());

    }

    @Test
    public void addCostumer() {
        scenaryCostumer();
        boolean added = gameStore.addClient(costumer.getId(), costumer.getCode(), costumer.getName(), games);
        Costumer c = gameStore.getCostumers().get(gameStore.getCostumers().size() - 1);
        assertEquals(added, true);

        assertEquals(c.getId(), costumer.getId());
        assertEquals(c.getCode(), costumer.getCode());
        assertEquals(c.getName(), costumer.getName());
        assertEquals(c.getGames(), costumer.getGames());

    }

    @Test
    public void addShelve() {
        scenaryGame();
        String message = gameStore.addShelve(shelve.getNameShelve(), shelve.getSize());
        Shelve s = gameStore.getShelves().get(gameStore.getShelves().size() - 1);
        assertEquals(message, "Shelve added succesfully");

        assertEquals(s.getNameShelve(), shelve.getNameShelve());
        assertEquals(s.getSize(), shelve.getSize());

    }

    @Test
    public void repeatedVerifyShelve() {

        scenaryShelve();

    }

    @Test
    public void repeatedVerifyCostumer() {

        scenaryCostumer();

        boolean added = gameStore.verifyRepeatCostumer("Pepito");

        assertEquals(added, "The costumer already exist in the game store");
    }

}
