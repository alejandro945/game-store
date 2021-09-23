package collection;

import model.Game;
import collection.stack.*;

import static org.junit.Assert.*;

import org.junit.Test;

class StackTest {

    public Stack<Game> setUpScenary1() {
        Stack<Game> stack1 = new Stack<>();
        return stack1;
    }

    public Stack<Game> setUpScenary2() {
        Stack<Game> stack2 = new Stack<>();
        return stack2;
    }

    @Test
    public void pushItems() {
        Stack<Game> stack1 = setUpScenary1();
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        stack1.push(game1);
        stack1.push(game2);

        boolean render = false;
        if (!stack1.isEmpty()) {
            render = true;
        }
        assertTrue(render);

        assertEquals(game2, stack1.pop());
        assertEquals(game1, stack1.pop());
    }

    @Test
    public void pushItems2() {
        Stack<Game> stack2 = setUpScenary2();
        Game game1 = null;
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);
        stack2.push(game1);
        stack2.push(game2);

        boolean render = false;
        if (!stack2.isEmpty()) {
            render = true;
        }
        assertTrue(render);

        assertEquals(game2, stack2.pop());
        assertEquals(game1, stack2.pop());
    }

    @Test
    public void searchItems() {
        Stack<Game> stack1 = setUpScenary1();
        Game game1 = new Game(1, "Clash of clans", "Fun", 40000, "A", 10);
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        stack1.push(game1);
        stack1.push(game2);

        boolean founded = stack1.isEmpty();

        assertEquals(founded, false);
    }

    @Test
    public void searchItems2() {
        Stack<Game> stack1 = setUpScenary1();
        Game game1 = null;
        Game game2 = new Game(2, "Clash Royale", "Fun", 20000, "A", 20);

        stack1.push(game1);
        stack1.push(game2);

        boolean founded = stack1.isEmpty();

        assertEquals(founded, false);
    }

}
