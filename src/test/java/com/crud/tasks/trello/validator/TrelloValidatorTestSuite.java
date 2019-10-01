package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrelloValidatorTestSuite {

    private TrelloValidator validator = new TrelloValidator();

    private List<TrelloBoard> trelloBoards = new ArrayList<>();

    @Test
    public void testValidateTrelloBoards() {
        //given
        trelloBoards.add(new TrelloBoard("1", "name", new ArrayList<>()));

        //when
        List<TrelloBoard> result = validator.validateTrelloBoards(trelloBoards);

        //then
        assertEquals(1,result.size());
        assertEquals("name", result.get(0).getName());
    }

    @Test
    public void shouldReturnEmptyList() {
        //given
        trelloBoards.add(new TrelloBoard("1", "test", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2", "test", new ArrayList<>()));

        //when
        List<TrelloBoard> result = validator.validateTrelloBoards(trelloBoards);

        //then
        assertEquals(0,result.size());
    }
}
