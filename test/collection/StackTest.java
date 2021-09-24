package collection;

import model.Game;
import collection.stack.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class StackTest {

    public Stack<Game> setUpScenary1() {
        Stack<Game> stack1 = new Stack<>();
        return stack1;
    }

    public Stack<Game> setUpScenary2() {
        Stack<Game> stack2 = new Stack<>();
        return stack2;
    }

    @Test
    public void pushAndPopItems() {
        Stack<Game> stack1 = setUpScenary1();
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        stack1.push(game1);
        stack1.push(game2);

        assertEquals(game2, stack1.pop());
        assertEquals(game1, stack1.pop());
    }

    @Test
    public void pushAndPopItems2() {
        Stack<Game> stack2 = setUpScenary2();
        Game game1 = null;
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        stack2.push(game1);
        stack2.push(game2);

        assertEquals(game2, stack2.pop());
        assertEquals(game1, stack2.pop());
    }

    @Test
    public void isEmpty() {
        Stack<Game> stack1 = setUpScenary1();
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        stack1.push(game1);
        stack1.push(game2);

        boolean founded = stack1.isEmpty();

        assertEquals(founded, false);
    }

    @Test
    public void isEmpty2() {
        Stack<Game> stackr = setUpScenary2();
        Game game = null;
        stackr.push(game);

        boolean founded = stackr.isEmpty();

        assertEquals(founded, true);
    }

    @Test
    public void size() {
        Stack<Game> stack1 = setUpScenary1();
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        stack1.push(game1);
        stack1.push(game2);

        int size = stack1.size();

        assertEquals(size, 2);
    }

    @Test
    public void size2() {
        Stack<Game> stackr = setUpScenary1();
        Game game = null;
        stackr.push(game);

        int size = stackr.size();

        assertEquals(size, 0);
    }

    @Test
    public void top() {
        Stack<Game> stack1 = setUpScenary1();
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        stack1.push(game1);
        stack1.push(game2);

        Game gameToCompare = stack1.top();

        assertEquals(gameToCompare, game2);

    }

    @Test
    public void top2() {
        Stack<Game> stackr = setUpScenary2();
        Game game = null;
        stackr.push(game);

        Game gameToCompare = stackr.top();

        assertEquals(gameToCompare, game);

    }

    @Test
    public void getInfo() {
        Stack<Game> stack1 = setUpScenary1();
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        stack1.push(game1);
        stack1.push(game2);

        String gameToCompare = stack1.getInfo();

        assertEquals(gameToCompare, 2 - 1);

    }

    @Test
    public void getInfo2() {
        Stack<Game> stackr = setUpScenary2();
        Game game = null;
        stackr.push(game);

        String gameToCompare = stackr.getInfo();

        assertEquals(gameToCompare, "");

    }

}
