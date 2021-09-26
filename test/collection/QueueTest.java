package collection;

import model.Costumer;
import model.Game;
import collection.queue.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class QueueTest {

    public Queue<Costumer> setUpScenary1() {
        Queue<Costumer> queue1 = new Queue<>();
        return queue1;
    }

    public Queue<Costumer> setUpScenary2() {
        Queue<Costumer> queue2 = new Queue<>();
        return queue2;
    }

    @Test
    public void queueAndDequeueItems() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        Queue<Costumer> queue1 = setUpScenary1();

        Costumer costumer1 = new Costumer(1, "1234", "Juanita", games);
        Costumer costumer2 = new Costumer(2, "123", "Pedro", games);
        queue1.enqueue(costumer1);
        queue1.enqueue(costumer2);

        assertEquals(costumer1, queue1.dequeue());
        assertEquals(costumer2, queue1.dequeue());
    }

    @Test
    public void queueAndDequeueItems2() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        Queue<Costumer> queue2 = setUpScenary1();

        Costumer costumer1 = null;
        Costumer costumer2 = new Costumer(2, "123", "Pedro", games);
        queue2.enqueue(costumer1);
        queue2.enqueue(costumer2);

        assertEquals(costumer1, queue2.dequeue());
        assertEquals(costumer2, queue2.dequeue());

    }

    @Test
    public void isEmpty() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        Queue<Costumer> queue1 = setUpScenary1();

        Costumer costumer1 = new Costumer(1, "1234", "Juanita", games);
        queue1.enqueue(costumer1);

        boolean founded = queue1.isEmpty();

        assertEquals(founded, false);
    }

    @Test
    public void isEmpty2() {

        Queue<Costumer> queue2 = setUpScenary1();

        Costumer costumer1 = null;
        queue2.enqueue(costumer1);

        boolean founded = queue2.isEmpty();

        assertEquals(true, founded);
    }

    @Test
    public void front() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        Queue<Costumer> queue1 = setUpScenary1();

        Costumer costumer1 = new Costumer(1, "1234", "Juanita", games);
        queue1.enqueue(costumer1);

        Costumer costumerToCompare = queue1.front();

        assertEquals(costumerToCompare, costumer1);

    }

    @Test
    public void front2() {

        Queue<Costumer> queue1 = setUpScenary1();

        Costumer costumer1 = null;

        queue1.enqueue(costumer1);

        Costumer costumerToCompare = queue1.front();

        assertEquals(null, costumerToCompare);

    }

    @Test
    public void getLast() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        Queue<Costumer> queue1 = setUpScenary1();

        Costumer costumer1 = new Costumer(1, "1234", "Juanita", games);
        Costumer costumer2 = null;
        queue1.enqueue(costumer1);
        queue1.enqueue(costumer2);

        Costumer c = queue1.getLast();

        assertEquals(costumer2, c);
    }

    @Test
    public void size() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        Queue<Costumer> queue1 = setUpScenary1();

        Costumer costumer1 = new Costumer(1, "1234", "Juanita", games);
        Costumer costumer2 = new Costumer(2, "123", "Pedro", games);
        queue1.enqueue(costumer1);
        queue1.enqueue(costumer2);

        int size = queue1.size();

        assertEquals(2, size);
    }

    @Test
    public void size2() {

        Queue<Costumer> queue2 = setUpScenary1();

        Costumer costumer1 = null;
        queue2.enqueue(costumer1);

        boolean render = queue2.isEmpty();

        assertEquals(true, render);
    }

    @Test
    public void search() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        Queue<Costumer> queue1 = setUpScenary1();

        Costumer costumer1 = new Costumer(1, "1234", "Juanita", games);
        Costumer costumer2 = new Costumer(2, "123", "Pedro", games);
        queue1.enqueue(costumer1);
        queue1.enqueue(costumer2);

        boolean founded = queue1.search(costumer1);
        boolean founded2 = queue1.search(costumer2);

        assertEquals(true, founded);
        assertEquals(true, founded2);
    }

    @Test
    public void search2() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        Queue<Costumer> queue2 = setUpScenary1();

        Costumer costumer1 = null;

        queue2.enqueue(costumer1);

        boolean founded = queue2.search(costumer1);

        assertEquals(false, founded);

    }

    @Test
    public void clear() {

        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        Queue<Costumer> queue1 = setUpScenary1();

        Costumer costumer1 = new Costumer(1, "1234", "Juanita", games);
        Costumer costumer2 = new Costumer(2, "123", "Pedro", games);
        queue1.enqueue(costumer1);
        queue1.enqueue(costumer2);

        queue1.clear();

        boolean render = queue1.isEmpty();

        assertEquals(true, render);
    }

    @Test
    public void clear2() {
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        ArrayList<Game> games = new ArrayList<>();
        games.add(game1);
        games.add(game2);

        Queue<Costumer> queue2 = setUpScenary1();

        Costumer costumer1 = null;
        queue2.enqueue(costumer1);

        queue2.clear();

        boolean render = queue2.isEmpty();

        assertEquals(true, render);

    }
}
