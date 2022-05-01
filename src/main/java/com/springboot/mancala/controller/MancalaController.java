package com.springboot.mancala.controller;

import com.springboot.mancala.model.GameContainer;
import com.springboot.mancala.service.MancalaBoardService;
import com.springboot.mancala.service.MancalaGameService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MancalaController {

    @RequestMapping("/game")
    public String game(Map<String, Object> model) {

        // Start new game.
        GameContainer.newGame();

        MancalaGameService mancalaGame = GameContainer.getMancalaGame();
        MancalaBoardService mancalaBoard =  mancalaGame.getMancalaBoard();

        model.put("mancalaBoard", mancalaBoard);

        String status = "Game on...";

        model.put("state", mancalaGame.getState());
        model.put("turn", mancalaGame.getTurn());

        model.put("status", status);
        return "mancala";
    }

    @RequestMapping("/play")
    public String play(Map<String, Object> model,
                       @RequestParam(value = "player", required = true) byte player,
                       @RequestParam(value = "pitnum", required = true) byte pitnum,
                       @RequestParam(value = "numstones", required = true) byte numstones
    ) {

        MancalaGameService mancalaGame = GameContainer.getMancalaGame();
        MancalaBoardService mancalaBoard =  mancalaGame.getMancalaBoard();

        GameContainer.getMancalaGame().play(pitnum, numstones, player);

        model.put("mancalaBoard", mancalaBoard);
        model.put("state", mancalaGame.getState());
        model.put("turn", mancalaGame.getTurn());
        
        return "mancala";
    }

    /**
     * Front page of the mancala game.
     * @return  index.
     */
    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        return "index";
    }
}
