package model;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class GameStoreTest {

    private GameStore gameStore = new GameStore();
    private Costumer costumer;
    private Game game;
    private Shelve shelve;

    private void scenaryGame() {
        game = new Game(1, "Call of duty", "Weapons and Fun", 30000, "A", 4);
    }

    private void scenaryCostumer() {
        ArrayList<Game> games = new ArrayList<>();
        games.add(game);
        costumer = new Costumer(12345, "111", "Pepito", games);
    }

    private void scenaryShelve() {
        shelve = new Shelve("A", 4);
    }

    @Test
    public void addShelve() {
        scenaryShelve();
        int size = gameStore.getShelves().size();
        gameStore.addShelve(shelve.getNameShelve(), shelve.getSize());
        Shelve s = gameStore.getShelves().get(gameStore.getShelves().size() - 1);
        assertEquals(size + 1, gameStore.getShelves().size());

        assertEquals(s.getNameShelve(), shelve.getNameShelve());
        assertEquals(s.getSize(), shelve.getSize());

    }

    @Test
    public void addGame() {
        addShelve();
        scenaryGame();

        int size = gameStore.getGames().size();
        gameStore.addGame(game.getCode(), game.getGameName(), game.getReview(), game.getPrice(), game.getShelveName(),
                game.getAmount());
        Game g = gameStore.getGames().get(gameStore.getGames().size() - 1);
        assertEquals(size + 1, gameStore.getGames().size());

        assertEquals(g.getCode(), game.getCode());
        assertEquals(g.getGameName(), game.getGameName());
        assertEquals(g.getReview(), game.getReview());
        assertEquals(g.getPrice(), game.getPrice());
        assertEquals(g.getShelveName(), game.getShelveName());
        assertEquals(g.getAmount(), game.getAmount());

    }

    @Test
    public void addCostumer() {
        addGame();
        scenaryCostumer();
        boolean added = gameStore.addClient(costumer.getId(), costumer.getCode(), costumer.getName(),
                costumer.getWishList());
        Costumer c = gameStore.getCostumers().get(gameStore.getCostumers().size() - 1);
        assertEquals(added, true);

        assertEquals(c.getId(), costumer.getId());
        assertEquals(c.getCode(), costumer.getCode());
        assertEquals(c.getName(), costumer.getName());
        assertEquals(c.getGames(), costumer.getGames());

    }

    @Test
    public void repeatedVerifyCostumer() {
        scenaryCostumer();
        boolean added = gameStore.verifyRepeatCostumer("Juan");
        assertEquals(added, false);
    }
}
