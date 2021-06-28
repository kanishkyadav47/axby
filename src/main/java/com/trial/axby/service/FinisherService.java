package com.trial.axby.service;

import com.trial.axby.domain.Board;
import com.trial.axby.domain.Player;
import com.trial.axby.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinisherService {
    private final PlayerRepository playerRepository;
    private final TurnValidator turnValidator;

    private Board board = Board.getInstance();

    private boolean playerLeft = false;


    @Autowired
    public FinisherService(PlayerRepository playerRepository, TurnValidator turnValidator) {
        this.playerRepository = playerRepository;
        this.turnValidator = turnValidator;
    }


    public void playerWon(Player winner){
        board.setMoveMessage(winner.getName() + " won the game.");
    }

    public void boardFull(){
        board.setMoveMessage(Board.BOARD_FULL);
    }


    public void endGame() {
        playerLeft = true;
    }

    public void reset() {
            board.reset();
            turnValidator.resetTurnCounter();

    }
    public boolean isPlayerLeft() {
        return playerLeft;
    }

}
