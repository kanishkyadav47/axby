package com.trial.axby.domain;

/**
 * Singleton pattern implemented for only one instance in the application
 *
 * Represents the game board.
 * Structure is in the form of 1-D array. Slicing at every 10th index.
 *
 * 0  1  2  3  4  5  6  7  8
 * 9 10 11 12 13 14 15 16 17
 * 18 19 20 21 22 23 24 25 26
 * 27 28 29 30 31 32 33 34 35
 * 36 37 38 39 40 41 42 43 44
 * 45 46 47 48 49 50 51 52 53
 */
public class Board {

    private int depth = 6;
    private int nStacks = 9;
    private int inARow = 5;

    public static final String STACK_FULL = "Stack is full. Please select another stack";
    public static final String BOARD_FULL = "Board full. Thanks for playing";

    private String moveMessage;


    private int boardSize = depth*nStacks;
    private Color[] state = new Color[boardSize];
    private static Board _instance = null;


    /**
    private constructor for restricted instantiation
     */
    private Board(){
        for(int i = 0; i<boardSize; i++)
            state[i] = Color.Empty;
    }


    /**
     * Method to return the current instance of the class
     */
    public static Board getInstance(){
        if(_instance == null){
            _instance = new Board();
        }
        return _instance;
    }

    public Color[] getState() {
        return state;
    }

    public int getDepth() {
        return depth;
    }

    public int getNStacks() {
        return nStacks;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getInARow() {
        return inARow;
    }

    public String getMoveMessage() {
        return moveMessage;
    }

    public void setMoveMessage(String moveMessage) {
        this.moveMessage = moveMessage;
    }

    public void display(){
        for(int i = 0; i < state.length; i++){
            if(i%9 == 0)
                System.out.println("\n");
            if(state[i] == Color.Empty)
                System.out.print("[ ]  ");
            else if (state[i] == Color.BLACK)
                System.out.print("[O]  ");
            else
                System.out.print("[X]  ");
        }
    }

    public boolean hasSpace(){
        for(int i = 0; i<this.getBoardSize(); i++){
            if(state[i]==Color.Empty)
                return true;
        }
        return false;
    }

    public void reset(){
        for(int i = 0; i<boardSize; i++)
            state[i] = Color.Empty;
        setMoveMessage(null);
    }
}
