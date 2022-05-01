package com.springboot.mancala.service;

import com.springboot.mancala.model.MancalaStore;
import com.springboot.mancala.model.Pit;
import com.springboot.mancala.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MancalaBoardService {

    public List<Pit> getPits();

    public void setPits(List<Pit> pits);

    public List<MancalaStore> getMancalaStores();

    public void setMancalaStores(List<MancalaStore> mancalaStores);

    public void initBoard(Player player1, Player player2);

}

