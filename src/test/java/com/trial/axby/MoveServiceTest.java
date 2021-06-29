package com.trial.axby;

import static org.junit.Assert.assertArrayEquals;

import com.trial.axby.domain.Board;
import com.trial.axby.domain.Color;
import com.trial.axby.domain.Player;
import com.trial.axby.service.CheckService;
import com.trial.axby.service.FinisherService;
import com.trial.axby.service.MoveService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MoveServiceTest {

    private Player player1;
    private Player player2;

    MoveService moveService;

    @Mock
    private CheckService checkService;
    @Mock
    private FinisherService finisherService;

    public MoveServiceTest() {
    }

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        init();
        moveService = new MoveService(checkService, finisherService);
    }

    @Test
    public void should_insert_at_correct_position() {

        moveService.insert(player1, 5);
        moveService.insert(player2, 8);
        moveService.insert(player1, 8);

        assertArrayEquals(Board.getInstance().getState(), stateAfterInsert());

    }




    private void init(){
        this.player1 = new Player(1, Color.WHITE, "P1");
        this.player2 = new Player(2, Color.BLACK, "P2");
    }

    private Color[] stateAfterInsert(){
        return new Color[]{Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.WHITE, Color.Empty, Color.Empty, Color.BLACK, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.WHITE, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
                Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty, Color.Empty,
        };

    }
}
