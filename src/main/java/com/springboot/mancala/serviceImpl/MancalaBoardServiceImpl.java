package com.springboot.mancala.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.springboot.mancala.model.MancalaStore;
import com.springboot.mancala.model.Pit;
import com.springboot.mancala.model.Player;

import com.springboot.mancala.service.MancalaBoardService;

public class MancalaBoardServiceImpl implements MancalaBoardService {

    //  Composition
    List<Pit> pits = new ArrayList<>(12);
    List<MancalaStore> mancalaStores = new ArrayList<>(2);

    public List<Pit> getPits() {
        return pits;
    }

    public void setPits(List<Pit> pits) {
        this.pits = pits;
    }

    public List<MancalaStore> getMancalaStores() {
        return mancalaStores;
    }

    public void setMancalaStores(List<MancalaStore> mancalaStores) {
        this.mancalaStores = mancalaStores;
    }

    public void initBoard(Player player1, Player player2)
    {

        // init player1 side
        for (byte i = 1; i <=6 ; i++) {
            Pit pit = new Pit(i, player1, (byte) 6);
            pits.add(pit);

        }

        // init player2 side
        for (byte i = 1; i <=6 ; i++) {
            Pit pit = new Pit(i, player2, (byte) 6);
            pits.add(pit);

        }

        // Adding Mancala Store for Player 1 and Player 2.
        MancalaStore mancalaStore1 = new MancalaStore((byte) 1, player1, (byte) 0);
        MancalaStore mancalaStore2 = new MancalaStore((byte) 2, player2, (byte) 0);

        mancalaStores.add(mancalaStore1);
        mancalaStores.add(mancalaStore2);

    }

}