package com.trial.axby.service;

import com.trial.axby.domain.Player;
import com.trial.axby.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TurnValidator {
    private final PlayerRepository playerRepository;

    AtomicInteger turn = new AtomicInteger(0);

    public TurnValidator(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Optional<Player> whoseTurn(){
        if (turn.get()%2 == 0){
            return playerRepository.findById(1);
        }
        else return playerRepository.findById(2);
    }

    boolean bothPlayersRegistered (){
        return playerRepository.count() == 2;
    }

    public int numberOfPlayersRegistered(){
        return (int)(playerRepository.count());
    }

    public boolean isValidTurn(int id){
        if(id == 1 || id == 2)
            return whoseTurn().get().getId() == id;
        else return false;
    }

    public void nextTurn(){
        turn.incrementAndGet();
    }

    public void resetTurnCounter(){
        turn.set(0);
    }

}
