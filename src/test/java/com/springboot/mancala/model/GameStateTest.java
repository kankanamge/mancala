package com.springboot.mancala.model;

import com.springboot.mancala.service.MancalaGameService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.springboot.mancala.model.GameState.notPlaying;
import static org.junit.Assert.*;

public class GameStateTest {
    @Autowired
    MancalaGameService mancalaGameService;

    /**
     * @throws java.lang.Exception
     * Called before every test case method.
     */
    @Before
    public void setUp() throws Exception {
        System.out.println("Executing a new test");
    }

    /**
     * @throws java.lang.Exception
     * Called after every test case method.
     */
    @After
    public void tearDown() throws Exception {
        System.out.println("Execution done");
    }

    @Test
    public void values() {
        assertEquals("notPlaying", notPlaying.name());
    }
}