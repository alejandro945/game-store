
package collection;

import model.Game;

import collection.hashTable.Hash;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashTest {

    public Hash<Integer, Game> setUpScenary1() {
        Hash<Integer, Game> hash = new Hash<>(2);
        return hash;
    }

    public Hash<Integer, Game> setUpScenary2() {
        Hash<Integer, Game> hash = new Hash<>(3);
        return hash;
    }

    @Test
    public void search() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        Hash<Integer, Game> hash = setUpScenary1();

        hash.addElement(2, game1);
        hash.addElement(1, game2);

        Game gameFounded = hash.search(2);
        Game gameFounded2 = hash.search(1);

        assertEquals(game2, gameFounded);
        assertEquals(game1, gameFounded2);

    }

    @Test
    public void search2() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        Game game3 = new Game(3, "Call of duty", "Fun", 15000, "A", 9);

        Hash<Integer, Game> hash = setUpScenary2();

        hash.addElement(1, game1);
        hash.addElement(1, game2);
        hash.addElement(2, game3);

        boolean render = hash.isFull();

        assertEquals(true, render);
    }

    @Test
    public void addElement() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        Hash<Integer, Game> hash = setUpScenary1();

        hash.addElement(2, game1);
        hash.addElement(1, game2);

        boolean render = hash.isFull();

        assertEquals(true, render);

    }

    @Test
    public void addElement2() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        Game game3 = new Game(3, "Call of duty", "Fun", 15000, "A", 9);

        Hash<Integer, Game> hash = setUpScenary2();

        hash.addElement(1, game1);
        hash.addElement(1, game2);
        hash.addElement(2, game3);

        boolean render = hash.isFull();

        assertEquals(true, render);
    }

    @Test
    public void removeElement() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        Hash<Integer, Game> hash = setUpScenary1();

        hash.addElement(2, game1);
        hash.addElement(1, game2);

        hash.remove(1);
        hash.remove(2);

        boolean render = hash.isEmpty();

        assertEquals(true, render);

    }

    @Test
    public void removeElement2() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        Game game3 = new Game(3, "Call of duty", "Fun", 15000, "A", 9);

        Hash<Integer, Game> hash = setUpScenary2();

        hash.addElement(1, game1);
        hash.addElement(1, game2);
        hash.addElement(2, game3);

        hash.remove(1);
        hash.remove(2);

        int size = hash.getSize();

        assertEquals(1, size);
    }

    @Test
    public void isEmpty() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        Hash<Integer, Game> hash = setUpScenary1();

        hash.addElement(2, game1);
        hash.addElement(1, game2);

        boolean render = hash.isEmpty();

        assertEquals(false, render);

    }

    @Test
    public void isFull() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        Hash<Integer, Game> hash = setUpScenary2();

        hash.addElement(2, game1);
        hash.addElement(1, game2);

        boolean render = hash.isFull();

        assertEquals(false, render);

    }

    @Test
    public void getSize() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        Hash<Integer, Game> hash = setUpScenary1();

        hash.addElement(2, game1);
        hash.addElement(1, game2);

        hash.remove(1);
        hash.remove(2);

        int size = hash.getSize();

        assertEquals(0, size);
    }

    @Test
    public void getSize2() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        Game game3 = new Game(3, "Call of duty", "Fun", 15000, "A", 9);

        Hash<Integer, Game> hash = setUpScenary2();

        hash.addElement(1, game1);
        hash.addElement(1, game2);
        hash.addElement(2, game3);

        hash.remove(2);

        int size = hash.getSize();

        assertEquals(2, size);
    }
}
