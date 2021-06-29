package com.trial.axby;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.trial.axby.domain.Board;
import com.trial.axby.domain.Color;
import com.trial.axby.domain.Player;
import com.trial.axby.repository.PlayerRepository;
import com.trial.axby.service.CheckService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CheckServiceTest {


    private Player player1;
    private Player player2;

    CheckService checkService;

    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private Board board;


    public CheckServiceTest() {
    }

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        init();
        checkService = new CheckService(playerRepository);
    }

    @Test
    public void should_return_true_when_player_won_at_horizontal_position() {


        CheckService checkService1 = Mockito.spy(checkService);
        when(checkService1.getBoard()).thenReturn(board);
        when(board.getState()).thenReturn(stateAfterHorizontalWinningInsert());

        assertTrue(checkService1.checkForWin(player1, 2));
    }

    @Test
    public void should_return_true_when_player_won_at_vertical_position() {


        CheckService checkService1 = Mockito.spy(checkService);
        when(checkService1.getBoard()).thenReturn(board);
        when(board.getState()).thenReturn(stateAfterVerticalWinningInsert());

        assertTrue(checkService1.checkForWin(player1, 37));
    }

    @Test
    public void should_return_true_when_player_won_at_left_Diagonal_position() {


        CheckService checkService1 = Mockito.spy(checkService);
        when(checkService1.getBoard()).thenReturn(board);
        when(board.getState()).thenReturn(stateAfterLeftDiagonalWinningInsert());

        assertTrue(checkService1.checkForWin(player1, 50));
    }

    @Test
    public void should_return_true_when_player_won_at_right_Diagonal_position() {


        CheckService checkService1 = Mockito.spy(checkService);
        when(checkService1.getBoard()).thenReturn(board);
        when(board.getState()).thenReturn(stateAfterRightDiagonalWinningInsert());

        assertTrue(checkService1.checkForWin(player1, 48));
    }


    private void init(){
        this.player1 = new Player(1, Color.WHITE, "P1");
        this.player2 = new Player(2, Color.BLACK, "P2");
    }

    private Color[] stateAfterHorizontalWinningInsert(){
        return new Color[]{
                Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.Empty, Color.Empty, Color.BLACK, Color.BLACK,
                Color.Empty, Color.BLACK, Color.Empty, Color.BLACK, Color.BLACK, Color.Empty, Color.Empty, Color.WHITE, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
        };
    }

    private Color[] stateAfterVerticalWinningInsert(){
        return new Color[]{
                Color.BLACK, Color.WHITE, Color.BLACK, Color.Empty, Color.BLACK, Color.Empty, Color.Empty, Color.BLACK, Color.Empty,
                Color.Empty, Color.WHITE, Color.WHITE, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.BLACK, Color.Empty,
                Color.Empty, Color.WHITE, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.WHITE, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.WHITE, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
        };
    }

    private Color[] stateAfterLeftDiagonalWinningInsert(){
        return new Color[]{
                Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK, Color.WHITE, Color.Empty, Color.Empty, Color.BLACK, Color.Empty,
                Color.Empty, Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK, Color.Empty, Color.Empty, Color.WHITE, Color.Empty,
                Color.Empty, Color.Empty, Color.WHITE, Color.BLACK, Color.WHITE, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.WHITE, Color.BLACK, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.WHITE, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
        };
    }

    private Color[] stateAfterRightDiagonalWinningInsert(){
        return new Color[]{
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK, Color.WHITE,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.WHITE, Color.BLACK, Color.WHITE, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.BLACK, Color.WHITE, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.WHITE, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
        };
    }
}
