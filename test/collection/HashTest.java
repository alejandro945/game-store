
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

        Game gameFounded = hash.search(1);
        Game gameFounded2 = hash.search(2);

        assertEquals(game2, gameFounded);
        assertEquals(game1, gameFounded2);

    }

    @Test
    public void search2() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        Hash<Integer, Game> hash = setUpScenary2();

        hash.addElement(1, game1);
        hash.addElement(1, game2);

        Game gameFounded = hash.search(3);
        Game gameFounded2 = hash.search(1);

        assertEquals(null, gameFounded);
        assertEquals(game1, gameFounded2);
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

    @Test
    public void getMax() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        Hash<Integer, Game> hash = setUpScenary1();

        hash.addElement(2, game1);
        hash.addElement(1, game2);

        int max = hash.getMax();

        assertEquals(2, max);

    }

    @Test
    public void getMax2() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        Game game3 = new Game(3, "Call of duty", "Fun", 15000, "A", 9);

        Hash<Integer, Game> hash = setUpScenary2();

        hash.addElement(1, game1);
        hash.addElement(1, game2);
        hash.addElement(2, game3);

        hash.remove(2);

        int max = hash.getMax();

        assertEquals(3, max);

    }

    @Test
    public void getIndexInTable() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        Hash<Integer, Game> hash = setUpScenary1();

        hash.addElement(2, game1);
        hash.addElement(1, game2);

        int index = hash.getIndexInTable(2);
        int index2 = hash.getIndexInTable(1);

        assertEquals(0, index);
        assertEquals(1, index2);

    }

    @Test
    public void getIndexInTable2() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        Game game3 = new Game(3, "Call of duty", "Fun", 15000, "A", 9);

        Hash<Integer, Game> hash = setUpScenary2();

        hash.addElement(1, game1);
        hash.addElement(1, game2);
        hash.addElement(2, game3);

        int index = hash.getIndexInTable(2);
        int index2 = hash.getIndexInTable(1);
        int index3 = hash.getIndexInTable(1);

        assertEquals(2, index);
        assertEquals(1, index2);
        assertEquals(1, index3);

    }

    @Test
    public void getRack() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        Hash<Integer, Game> hash = setUpScenary1();

        hash.addElement(2, game1);
        hash.addElement(1, game2);

        Game gameObtained = hash.getRack(0);
        Game gameObtained2 = hash.getRack(1);

        assertEquals(game1, gameObtained);
        assertEquals(game2, gameObtained2);

    }

    @Test
    public void getRack2() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        Game game3 = new Game(3, "Call of duty", "Fun", 15000, "A", 9);

        Hash<Integer, Game> hash = setUpScenary2();

        hash.addElement(1, game1);
        hash.addElement(1, game2);
        hash.addElement(2, game3);

        Game gameObtained = hash.getRack(0);
        Game gameObtained2 = hash.getRack(1);
        Game gameObtained3 = hash.getRack(2);

        assertEquals(game3, gameObtained);
        assertEquals(game1, gameObtained2);
        assertEquals(game2, gameObtained3);

    }

}
