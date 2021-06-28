package com.trial.axby.resources;

import com.trial.axby.domain.Board;
import com.trial.axby.service.CheckService;

import com.trial.axby.service.FinisherService;
import com.trial.axby.service.MoveService;
import com.trial.axby.service.TurnValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MoveController {

    private final MoveService moveService;
    private final CheckService checkService;
    private final TurnValidator turnValidator;
    private final FinisherService finisherService;


    @Autowired
    public MoveController(MoveService moveService, CheckService checkService, TurnValidator turnValidator, FinisherService finisherService) {
        this.moveService = moveService;
        this.checkService = checkService;
        this.turnValidator = turnValidator;
        this.finisherService = finisherService;
    }

    @GetMapping("/board")
    public Board currentState() {
        return checkService.getBoard();
    }

    @GetMapping("/bothplayers")
    public boolean bothPlayersJoined() {
        return (checkService.bothPlayersJoined() && !finisherService.isPlayerLeft());
    }

    @PostMapping("/rematch")
    public void reset(){
        finisherService.reset();
    }

    @PostMapping("/endgame")
    public void endGame(){
        finisherService.endGame();
    }

    @PostMapping("/move/player/{playerId}/stacknum/{nStack}")
    public Board makeMove(@PathVariable int playerId, @PathVariable int nStack){
        Board board =checkService.getBoard();
        if(turnValidator.isValidTurn(playerId)){
            if(moveService.insert(turnValidator.whoseTurn().get(), nStack)) {
                turnValidator.nextTurn();
            }
        }
        return board;
    }

    @GetMapping("/myturn/{playerId}")
    public boolean whoseTurn(@PathVariable int playerId){
        return (playerId == turnValidator.whoseTurn().get().getId());
    }

}
