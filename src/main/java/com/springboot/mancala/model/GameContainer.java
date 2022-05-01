package com.springboot.mancala.model;

import com.springboot.mancala.service.MancalaGameService;
import com.springboot.mancala.serviceImpl.MancalaGameServiceImpl;

public class GameContainer {

    private static MancalaGameService mancalaGame = null;

    public static MancalaGameService getMancalaGame() {
        return mancalaGame;
    }

    public static void setMancalaGame(MancalaGameService mancalGame) {
        GameContainer.mancalaGame = mancalGame;
    }

    public static void newGame()
    {
        mancalaGame = new MancalaGameServiceImpl();
    }
}
