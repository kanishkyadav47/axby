package com.trial.axby.service;

import com.trial.axby.domain.Board;
import com.trial.axby.domain.Color;
import com.trial.axby.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MoveService {

    private final Board board;
    private final CheckService checkService;
    private final FinisherService finisherService;

    @Autowired
    public MoveService(CheckService checkService, FinisherService finisherService) {
        this.checkService = checkService;
        this.finisherService = finisherService;
        this.board = Board.getInstance();
    }

    public boolean insert( Player player, int inStack) {

        boolean inserted = false;
        //inStack = getInputFromPlayer(player);

        Color[] st = board.getState();
        int[] in = retrieveNextInsertingIndex(inStack - 1);
        for (int j : in) {
            if (st[j] == Color.Empty) {
                st[j] = player.getColor();
                inserted = true;
                break;
            }
        }


        if (inserted) {
            /**
             * player won
             */
            if (checkService.checkForWin(player)) {
                finisherService.playerWon(player);
            }

            /**
             * No space on the board
             */
            if (!checkService.spaceAvailable()) {
                finisherService.boardFull();
            }
        }
        else{
            /**
             * No space on selected stack
             */
            board.setMoveMessage(Board.STACK_FULL);
        }

        return inserted;

    }

    private int[] retrieveNextInsertingIndex ( int inStack){
        int[] in = new int[board.getDepth()];
        for (int i = board.getDepth() - 1; i >= 0; i--) {
            in[i] = inStack + (i * board.getNStacks());
        }
        return in;
    }

    private int getInputFromPlayer(Player player){
        System.out.println("\n********************************");
        board.display();
        System.out.println("Your turn "+player.getName());
        Board b = Board.getInstance();

        String inp;
        Scanner in = new Scanner(System.in);


        inp = in.nextLine();

        int stack = Integer.parseInt(inp);

        if(stack<1 || stack > b.getNStacks()){
            System.out.println("Please enter a valid stack number within the range");
        }
        return stack;
    }


}
