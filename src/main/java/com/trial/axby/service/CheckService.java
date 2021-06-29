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


    public boolean checkForWin(Player p, int insertPosition){
        Color col = p.getColor();
        Color[] state = getBoard().getState();
        List<int[]> list = possiblePosition(insertPosition);
        for (int[] a : list) {
            int consecutiveColor = 0;
            for (int j : a) {
                if (state[j] == col)
                    consecutiveColor++;
            }
            if (consecutiveColor == board.getInARow())
                return true;
        }
        return false;
    }

    // return possible list of positions to check for win condition
    public List<int[]> possiblePosition(int index){

        List<int[]> list = new ArrayList();
        int nStacks = board.getNStacks();
        int inARow = board.getInARow();

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

            if(bound >= index && bound < board.getBoardSize()){
                list.add(generateVerticalPosition(whichStack+(i*nStacks)));
            }
        }

        // possible left diagonal positions for the index
        int leftDiagonal = index;
        for(int i = 0; i < inARow; i++){
            if (leftDiagonal - (nStacks + 1) < 0 || isLeftBoundary(leftDiagonal)){
                break;
            }
            leftDiagonal = leftDiagonal - (nStacks + 1);
        }
        for(int i = leftDiagonal; i <= index; i += (nStacks + 1) ){
            if(i + ((inARow - 1)*(nStacks + 1)) < board.getState().length && !hasExceededRightBoundary(i)){
                int[] a = new int[5];
                for (int j = 0; j < 5; j++)
                    a[j] = leftDiagonal + j*(nStacks + 1);
                list.add(a);
            }
            else {
                break;
            }
        }

        // possible right diagonal positions for the index
        int rightDiagonal = index;
        for(int i = 0; i < inARow; i++){
            if (rightDiagonal - (nStacks - 1) < 0 || isRightBoundary(rightDiagonal)) {
                break;
            }
            rightDiagonal = rightDiagonal - (nStacks - 1);
        }
        for(int i = rightDiagonal; i <= index; i += (nStacks - 1) ){
            if(i + ((inARow - 1)*(nStacks - 1)) < board.getState().length && !hasExceededLeftBoundary(i)){
                int[] a = new int[5];
                for (int j = 0; j < 5; j++)
                    a[j] = rightDiagonal + j*(nStacks - 1);
                list.add(a);
            }
            else{
                break;
            }
        }

        return list;
    }

    private boolean isRightBoundary(int i) {
        return (i+1) % board.getNStacks() == 0;
    }

    private boolean hasExceededRightBoundary(int i) {
        return i % board.getNStacks() > ( (i + (board.getInARow()-1)) * (board.getNStacks()+1) ) % board.getNStacks();
    }

    private boolean isLeftBoundary(int i) {
        return i % board.getNStacks() == 0;
    }

    private boolean hasExceededLeftBoundary(int i) {
        return i % board.getNStacks() < ( (i + (board.getInARow()-1)) * (board.getNStacks()-1) ) % board.getNStacks();
    }

    private int[] generateVerticalPosition(int i) {
        int inARow = board.getInARow();
        int[] pos = new int[inARow];
        for (int j = 0; j < inARow; j++)
            pos[j] = i + (j * board.getNStacks());

        return pos;
    }

    private int[] generateHorizontalPosition(int i) {
        int inARow = board.getInARow();
        int[] pos = new int[inARow];
        for (int j = 0; j < inARow; j++)
            pos[j] = i+j;

        return pos;
    }


    public boolean bothPlayersJoined() {
        boolean flag;
        flag =  playerRepository.count()==2;
        return flag;
    }
}
