package com.springboot.mancala.service;

import com.springboot.mancala.model.GameState;
import com.springboot.mancala.model.Pit;
import com.springboot.mancala.model.Player;
import com.springboot.mancala.serviceImpl.MancalaBoardServiceImpl;
import com.springboot.mancala.serviceImpl.MancalaGameServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.internal.util.StringUtil.join;

@RunWith(MockitoJUnitRunner.class)
public class MancalaGameServiceTest {

    @InjectMocks
    MancalaGameService mancalaGameService = new MancalaGameServiceImpl();
    MancalaBoardService mancalaBoard = new MancalaBoardServiceImpl();
    Player playerObj;

    @BeforeEach
    void init_mocks() {
        try {
            MockitoAnnotations.openMocks(this).close();
        } catch (Exception e) {
            throw new MockitoException(
                    join(
                            "Failed to release mocks",
                            "",
                            "This should not happen unless you are using a third-party mock maker"),
                    e);
        }
    }

    @Test
    public void initMancalaGame() {
        Player player1 = new Player("Player1", (byte) 1);
        Player player2 = new Player("Player2", (byte) 2);
        Assert.assertNotNull(player1.getPlayerNumber());
        Assert.assertNotNull(player2.getPlayerNumber());
        mancalaGameService.initMancalaGame(player1, player2);
        Assert.assertEquals(mancalaGameService.getState(), GameState.notPlaying);
        Assert.assertEquals(mancalaGameService.getTurn(), 1);
    }

    @Test
    public void play() {
        byte pitnum = 1;
        byte numStones = 6;
        byte player = 1;

        mancalaGameService.play(pitnum, numStones, player);
        Assert.assertEquals(mancalaGameService.getState(), GameState.playing);

        List<Pit> pits = new ArrayList<>();
        Player player1 = new Player("Player1", (byte) 1);
        Pit pt1 = new Pit((byte) 1, player1, (byte) 6);
        pits.add(pt1);
        mancalaBoard.setPits(pits);
        Assert.assertEquals(mancalaBoard.getPits().get(pitnum-1).getNumberOfStones(), 6);
        Assert.assertEquals(mancalaBoard.getPits().get(pitnum-1).getPitOwner().getPlayerNumber(), 1);
    }
}