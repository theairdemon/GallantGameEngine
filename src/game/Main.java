package game;

import engine.GameEngine;
import engine.IGameLogic;

public class Main {

    public static void main(String[] args) {
        try {
            boolean vSync = false;
            IGameLogic gameLogic = new DummyGame();
            GameEngine gameEng = new GameEngine("GAME", 600, 480, vSync, gameLogic);
            gameEng.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }

}
