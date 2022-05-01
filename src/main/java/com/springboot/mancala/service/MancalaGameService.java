package com.springboot.mancala.service;

import java.util.List;

import com.springboot.mancala.model.GameState;
import com.springboot.mancala.model.Player;

import org.springframework.stereotype.Service;

@Service
public interface MancalaGameService {

    public abstract GameState getState();

    public void setState(GameState state);

    public String getStatus();

    public void setStatus(String status);

    public byte getTurn();

    public void setTurn(byte turn);

    public MancalaBoardService getMancalaBoard();

    public void setMancalaBoard(MancalaBoardService mancalaBoard);

    public List<Player> getPlayers();

    public void setPlayers(List<Player> players);

    public void initMancalaGame(Player player1, Player player2);

    public void play(byte pitnum, byte numStones ,byte player);

    public void calculateWinner();

    public int sumOfStonesofAllpits();

}
