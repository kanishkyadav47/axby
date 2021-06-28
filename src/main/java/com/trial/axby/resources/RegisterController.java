package com.trial.axby.resources;

import com.trial.axby.domain.Color;
import com.trial.axby.domain.Player;
import com.trial.axby.repository.PlayerRepository;
import com.trial.axby.service.TurnValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private final TurnValidator turnValidator;
    private final PlayerRepository playerRepository;


    @Autowired
    public RegisterController(TurnValidator turnValidator, PlayerRepository playerRepository) {
        this.turnValidator = turnValidator;
        this.playerRepository = playerRepository;
    }

    @PostMapping("/registerPlayer/{name}")
    public int register(@PathVariable String name){
        int currentNumberOfPlayers = (int) (playerRepository.count());
        if(currentNumberOfPlayers < 2){
            playerRepository.save(getPlayer(currentNumberOfPlayers, name));
            return currentNumberOfPlayers + 1;
        }
        return 0;
    }

    Color assignColor(int currentNumber){
        if(currentNumber == 1){
        return playerRepository.findAll().iterator().next().getColor() == Color.BLACK ? Color.WHITE : Color.BLACK; //invert the color that's currently stored in db
        }
        return Color.WHITE;
    }

    Player getPlayer(int currentNumberOfPlayers, String name){
        int id = currentNumberOfPlayers + 1;
        Player player = new Player(id, assignColor(currentNumberOfPlayers), name);
        return player;
    }
}
