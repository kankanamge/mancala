package com.springboot.mancala.serviceImpl;

import com.springboot.mancala.model.GameState;
import com.springboot.mancala.model.Pit;
import com.springboot.mancala.model.Player;
import com.springboot.mancala.service.MancalaBoardService;
import com.springboot.mancala.service.MancalaGameService;

import java.util.ArrayList;
import java.util.List;

public class MancalaGameServiceImpl implements MancalaGameService {
    MancalaBoardService mancalaBoard = new MancalaBoardServiceImpl();
    List<Player> players = new ArrayList<>(2);
    String status;
    byte turn;
    GameState state;

    public MancalaGameServiceImpl() {

        Player player1 = new Player("Player1", (byte) 1);
        Player player2 = new Player("Player2", (byte) 2);
        initMancalaGame(player1, player2);
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte getTurn() {
        return turn;
    }

    public void setTurn(byte turn) {
        this.turn = turn;
    }

    public MancalaBoardService getMancalaBoard() {
        return mancalaBoard;
    }

    public void setMancalaBoard(MancalaBoardService mancalaBoard) {
        this.mancalaBoard = mancalaBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }


    public void initMancalaGame(Player player1, Player player2)
    {
        MancalaBoardService mancalaBoard = new MancalaBoardServiceImpl();
        mancalaBoard.initBoard(player1, player2);
        setMancalaBoard(mancalaBoard);

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        setPlayers(players);
        setState(GameState.notPlaying);

        // Let player-1 start the game.
        setTurn((byte) 1);

        System.out.println("Init Done");

    }

    public void play(byte pitnum, byte numStones ,byte player)
    {

        int takeStonesFromPit = 0;

        if (player == 1) {
            // Take out number of stones of the player-1 selected pit and Minus them further in algorithm.
            takeStonesFromPit = mancalaBoard.getPits().get(pitnum-1).getNumberOfStones();
        } else {
            // Take out number of stones of the player-2 selected pit and Minus them further in algorithm.
            takeStonesFromPit =  mancalaBoard.getPits().get(pitnum+5).getNumberOfStones();
        }

        if (player == 1) {

            byte stonesOfPit = mancalaBoard.getPits().get(pitnum-1).getNumberOfStones();

            int remainedMoves = stonesOfPit;

            // Add player-1 selected stones to next pits.
            for (int i = 1; i <= stonesOfPit ; i++) {

                remainedMoves = remainedMoves - 1;

                if (mancalaBoard.getPits().get(pitnum-1).getPitOwner().getPlayerNumber() == 1) {

                    int nextPitIndex = pitnum-1+i;
                    int lastPitIndex = nextPitIndex;

                    if (nextPitIndex == 6) {

                        /**
                         * Player-1 has right to sows stone to own Mancala Store(big pit).
                         * 6th of pit index belongs to Player-1 Mancala Store.
                         * Therefore, Put Stone to Player 1 Mancala Store.
                         */
                        mancalaBoard.getMancalaStores().get(0).setNumberOfStones(
                                (byte) (mancalaBoard.getMancalaStores().get(0).getNumberOfStones()+1)
                        );

                        if (i+1 > stonesOfPit) {
                            // If this is the last stone, then break the loop.
                            break;
                        }
                        else {
                            // Since stone added to Mancala, decrease number of stones by one, to take effect in the next loop.
                            --stonesOfPit;
                        }

                    }

                    // Player-1 do not have right to sows stone to Player-2 Mancala Store(big pit).
                    if (nextPitIndex <= 11) {
                        mancalaBoard.getPits().get(nextPitIndex)
                                .setNumberOfStones(
                                        (byte) (mancalaBoard.getPits().get(nextPitIndex).getNumberOfStones()+1)
                                );
                    }
                    else {
                        // Skip the Player-2 Mancala Store(big pit) and play from next pit index.
                        int cyclicPitIndex = nextPitIndex-12;

                        while (cyclicPitIndex >=12) {
                            cyclicPitIndex -= 12;
                        }

                        lastPitIndex = cyclicPitIndex;

                        if (cyclicPitIndex == 6) {
                            // Put stone in to Player-1 Mancala Store.
                            mancalaBoard.getMancalaStores().get(0).setNumberOfStones(
                                    (byte) (mancalaBoard.getMancalaStores().get(0).getNumberOfStones()+1)
                            );

                            if (i+1 > stonesOfPit) {
                                // If this is the last stone, then break the loop.
                                break;
                            }
                            else {
                                // Since stone added to Mancala, decrease number of stones by one, to take effect in the next loop.
                                --stonesOfPit;
                            }
                        }

                        mancalaBoard.getPits().get(cyclicPitIndex)
                                .setNumberOfStones(
                                        (byte) (mancalaBoard.getPits().get(cyclicPitIndex).getNumberOfStones()+1)
                                );
                    }

                    /**
                     * Last Stone place and decide a bonus turn, cumulate player and opposite stones or
                     * Give turn to the other player.
                     */
                    if (i == stonesOfPit) {

                        // This is the last Stone of this player in this round decide what to do next.
                        int numOfStonesInLastPit = mancalaBoard.getPits().get(lastPitIndex).getNumberOfStones();

                        if (numOfStonesInLastPit == 1
                                && mancalaBoard.getPits().get(lastPitIndex).getPitOwner().getPlayerNumber() == 1) {

                            /**
                             * Pit was previously empty.
                             * So do cumulate player stones and oppiste side and Place to Player's Mancala
                             */
                            int MancalaCurrentNumOfStones= mancalaBoard.getMancalaStores().get(0).getNumberOfStones();
                            int oppsiteSideStones= mancalaBoard.getPits()
                                    .get(lastPitIndex + (11 - (2*lastPitIndex))).getNumberOfStones();

                            int lastIndexStones= mancalaBoard.getPits().get(lastPitIndex).getNumberOfStones();

                            mancalaBoard.getMancalaStores().get(0)
                                    .setNumberOfStones((byte) (MancalaCurrentNumOfStones + oppsiteSideStones + lastIndexStones));

                            /**
                             * Stones cumulated in Player's Mancala.
                             * So deplete player pit and opposite pit.
                             */
                            mancalaBoard.getPits().get(lastPitIndex).setNumberOfStones((byte) 0);

                            // Empty opposite Pit
                            mancalaBoard.getPits().get(lastPitIndex + (11 - (2*lastPitIndex))).setNumberOfStones((byte) 0);
                        }

                        // Give Turn to player-2
                        setTurn((byte) 2);
                    }

                }
            }
            // Take out intial number of stones of the pit and minus them from final cumulated stones
            mancalaBoard.getPits().get(pitnum-1).setNumberOfStones((byte) (mancalaBoard.getPits().get(pitnum-1).getNumberOfStones() - takeStonesFromPit));

        } else // Player 2 Move
        {
            byte stonesOfPit = mancalaBoard.getPits().get(pitnum+5).getNumberOfStones();

            int remainedMoves = stonesOfPit;

            for (int i = 1; i <= stonesOfPit ; i++) {

                remainedMoves =  remainedMoves - 1;

                if (mancalaBoard.getPits().get(pitnum+5).getPitOwner().getPlayerNumber() == 2) {

                    int nextPitIndex = pitnum+5+i;
                    int lastPitIndex = nextPitIndex;

                    if (nextPitIndex <= 11) {
                        mancalaBoard.getPits().get(nextPitIndex)
                                .setNumberOfStones(
                                        (byte) (mancalaBoard.getPits().get(nextPitIndex).getNumberOfStones()+1)
                                );
                    }
                    else {
                        int cyclicPitIndex = nextPitIndex-12;

                        while (cyclicPitIndex >=12) {
                            cyclicPitIndex -= 12;
                        }

                        lastPitIndex = cyclicPitIndex;

                        if (cyclicPitIndex == 0) {
                            // Put Stone in in Player-2 Mancala Store
                            mancalaBoard.getMancalaStores().get(1).setNumberOfStones(
                                    (byte) (mancalaBoard.getMancalaStores().get(1).getNumberOfStones()+1)
                            );

                            if (i+1 > stonesOfPit) {
                                // If this is the last stone, then break the loop.
                                break;
                            }
                            else {
                                // Since stone added to Mancala, decrease number of stones by one, to take effect in the next loop.
                                --stonesOfPit;
                            }
                        }

                        mancalaBoard.getPits().get(cyclicPitIndex)
                                .setNumberOfStones(
                                        (byte) (mancalaBoard.getPits().get(cyclicPitIndex).getNumberOfStones()+1)
                                );
                    }

                    /**
                     * Last Stone place and decide a bonus turn, cumulate player and opposite stones or
                     * Give turn to the other player.
                     */
                    if (i == stonesOfPit) {

                        // This is the last Stone of this player in this round decide what to do next.
                        int numOfStonesInLastPit = mancalaBoard.getPits().get(lastPitIndex).getNumberOfStones();

                        if (numOfStonesInLastPit == 1
                                && mancalaBoard.getPits().get(lastPitIndex).getPitOwner().getPlayerNumber() == 2) {

                            /**
                             * Pit was previously empty.
                             * So do cumulate player stones and oppiste side and Place to Player's Mancala.
                             */
                            int MancalaCurrentNumOfStones= mancalaBoard.getMancalaStores().get(1).getNumberOfStones();
                            int oppsiteSideStones= mancalaBoard.getPits()
                                    .get(lastPitIndex - (11 - (2 * (11 - lastPitIndex)))).getNumberOfStones();

                            int lastIndexStones= mancalaBoard.getPits().get(lastPitIndex).getNumberOfStones();

                            mancalaBoard.getMancalaStores().get(1)
                                    .setNumberOfStones((byte) (MancalaCurrentNumOfStones + oppsiteSideStones + lastIndexStones));

                            // Stones cumulated in Player's Mancala so deplete player pit and opposite pit.
                            mancalaBoard.getPits().get(lastPitIndex).setNumberOfStones((byte) 0);
                            // Empty opposite Pit.
                            mancalaBoard.getPits().get(lastPitIndex - (11 - (2 * (11 - lastPitIndex)))).setNumberOfStones((byte) 0);
                        }

                        // Give Turn to player-1
                        setTurn((byte) 1);

                    }

                }
            }

            // Take out intial number of stones of the pit and minus them from final cumulated stones.
            mancalaBoard.getPits().get(pitnum+5).setNumberOfStones((byte) (mancalaBoard.getPits().get(pitnum+5).getNumberOfStones() - takeStonesFromPit));

        }

        // Check if the game is over.
        boolean isGameFinished = true;

        for (int i = 0; i <=5 ; i++) {

            // If player-1's pits are empty, then finish the Game.
            int stonesNum = mancalaBoard.getPits().get(i).getNumberOfStones();

            if (stonesNum > 0 ) {
                isGameFinished = false;
                break;
            }

        }

        if (!isGameFinished) {

            isGameFinished = true;

            for (int i = 6; i <=11 ; i++) {

                // If player-2's pits are empty, then finish the Game.
                int stonesNum = mancalaBoard.getPits().get(i).getNumberOfStones();

                if (stonesNum > 0 ) {
                    isGameFinished = false;
                    break;
                }

            }
        }

        if (isGameFinished) {

            setStatus("gamefinish");
            setState(GameState.finished);

            // Find winner of the Game or Game is a Draw.
            calculateWinner();

            // End of the game.
            setTurn((byte) 40);

            System.out.println("************************************");
            System.out.println("**                                **");
            System.out.println("**        Game is Finished        **");
            System.out.println("**                                **");
            System.out.println("************************************");

            System.out.println("sumOfStonesofAllpits() :: " + sumOfStonesofAllpits());

        }
        else {
            setState(GameState.playing);
            System.out.println(" >>>>>>>>>>>   sumOfStonesofAllpits() :: " + sumOfStonesofAllpits());
        }

    }

    public void calculateWinner() {

        int stonesOfPlayer1 = 0;
        int stonesOfPlayer2 = 0;

        for (int i = 0; i <=5 ; i++) {
            stonesOfPlayer1 += mancalaBoard.getPits().get(i).getNumberOfStones();
        }

        stonesOfPlayer1 += mancalaBoard.getMancalaStores().get(0).getNumberOfStones();

        System.out.printf("Player-1 totally has %d stones\n", stonesOfPlayer1);

        for (int i = 6; i <=11 ; i++) {
            stonesOfPlayer2 += mancalaBoard.getPits().get(i).getNumberOfStones();
        }

        stonesOfPlayer2 += mancalaBoard.getMancalaStores().get(1).getNumberOfStones();
        System.out.printf("Player-2 totally has %d stones\n", stonesOfPlayer2);

        if (stonesOfPlayer1 == stonesOfPlayer2) {
            setState(GameState.draw);
        }
        else if (stonesOfPlayer1 > stonesOfPlayer2) {
            setState(GameState.player1Win);
        }
        else {
            setState(GameState.player2Win);
        }
    }

    public int sumOfStonesofAllpits() {

        int sum = 0;

        for (Pit pit : mancalaBoard.getPits()) {

            sum += pit.getNumberOfStones();

        }

        sum += mancalaBoard.getMancalaStores().get(0).getNumberOfStones();
        sum += mancalaBoard.getMancalaStores().get(1).getNumberOfStones();

        return sum;
    }
}