package com.springboot.mancala.service;

import com.springboot.mancala.model.MancalaStore;
import com.springboot.mancala.model.Player;
import com.springboot.mancala.serviceImpl.MancalaBoardServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.internal.util.StringUtil.join;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MancalaBoardServiceTest {

    @InjectMocks
    MancalaBoardService mancalaBoardService = new MancalaBoardServiceImpl();

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
    public void initBoard() {
        Player player1 = new Player("Player1", (byte) 1);
        Player player2 = new Player("Player2", (byte) 2);
        mancalaBoardService.initBoard(player1, player2);
        Assert.assertEquals(mancalaBoardService.getMancalaStores().size(),2);
        Assert.assertEquals(mancalaBoardService.getMancalaStores().get(0).getPitOwner().getName(),"Player1");
    }

    @Test
    public void setMancalaStores() {
        List<MancalaStore> mancalaStores = new ArrayList<>();
        Player player1 = new Player("Player3", (byte) 1);
        Player player2 = new Player("Player4", (byte) 2);
        MancalaStore mancalaStore1 = new MancalaStore((byte) 1, player1, (byte) 0);
        MancalaStore mancalaStore2 = new MancalaStore((byte) 2, player2, (byte) 0);
        mancalaStores.add(mancalaStore1);
        mancalaStores.add(mancalaStore2);
        mancalaBoardService.setMancalaStores(mancalaStores);
        Assert.assertEquals(mancalaBoardService.getMancalaStores().size(),2);
        Assert.assertEquals(mancalaBoardService.getMancalaStores().get(0).getPitOwner().getName(),"Player3");
    }
}