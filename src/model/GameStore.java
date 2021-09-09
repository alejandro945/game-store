package model;

public class GameStore {
    private static GameStore instance;

    private GameStore() {
    }

    public static GameStore getInstance() {
        if (instance == null) {
            instance = new GameStore();
        }
        return instance;
    }
}
