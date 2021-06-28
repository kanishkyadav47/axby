package com.trial.axby.service;

import com.trial.axby.domain.Board;
import com.trial.axby.domain.Color;
import com.trial.axby.domain.Player;
import com.trial.axby.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckService {

    private final PlayerRepository playerRepository;

    private final Board board;

    public CheckService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        this.board = Board.getInstance();
    }

    public boolean spaceAvailable(){
        return board.hasSpace();
    }

    public Board getBoard() {
        return board;
    }


// TODO: 1. optimise checking for just the neighbourhood
// TODO: 2. Diagonal condition
    public boolean checkForWin(Player p){
        Color col = p.getColor();
        Color[] state = this.board.getState();
        for(int i = 0; i < state.length; i++){
            if(state[i] == col){
                List<int[]> list = possiblePosition(i);
                for (int[] a : list){
                    int consecutiveColor = 0;
                    for(int j : a){
                        if(state[j] == col)
                            consecutiveColor++;
                    }
                    if(consecutiveColor == this.board.getInARow())
                        return true;
                }
            }
        }
        return false;
    }

    // return possible list of positions to check for win condition
    public List<int[]> possiblePosition(int index){

        List<int[]> list = new ArrayList();
        int nStacks = this.board.getnStacks();
        int inARow = this.board.getInARow();

        int leftEnd = (index/nStacks)*nStacks;
        int rightEnd = leftEnd + 8;

        // possible horizontal positions for the index
        for(int i = leftEnd; i <= index; i++){
            if(i+(inARow-1) >= index && i+(inARow-1) <= rightEnd){
                int[] pos = generateHorizontalPosition(i);
                list.add(pos);
            }
        }

        // possible vertical positions for the index
        int currentDepth = leftEnd/nStacks ;
        int whichStack = index-leftEnd;
        for(int i = 0; i <=currentDepth; i++){
            int bound = (whichStack + (i * nStacks)) + ((inARow - 1) * nStacks);

            if(bound >= index && bound < this.board.getBoardSize()){
                list.add(generateVerticalPosition(whichStack+(i*nStacks)));
            }
        }


        return list;
    }

    private int[] generateVerticalPosition(int i) {
        int inARow = this.board.getInARow();
        int[] pos = new int[inARow];
        for (int j = 0; j < inARow; j++)
            pos[j] = i + (j* this.board.getnStacks());

        return pos;
    }

    private int[] generateHorizontalPosition(int i) {
        int inARow = this.board.getInARow();
        int[] pos = new int[inARow];
        for (int j = 0; j < inARow; j++)
            pos[j] = i+j;

        return pos;
    }


    public boolean bothPlayersJoined() {
        return playerRepository.count()==2;
    }
}
