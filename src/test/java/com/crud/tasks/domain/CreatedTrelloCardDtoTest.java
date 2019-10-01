package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CreatedTrelloCardDtoTest {

    @Test
    public void testGetterWithNoArgs() {
        //given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();

        //when
        //then
        assertNull(createdTrelloCardDto.getId());
        assertNull(createdTrelloCardDto.getName());
        assertNull(createdTrelloCardDto.getShortUrl());
    }

    @Test
    public void testGetterWithAllArgs() {
        //given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "name", "url");

        //when
        //then
        assertEquals("1", createdTrelloCardDto.getId());
        assertEquals("name", createdTrelloCardDto.getName());
        assertEquals("url", createdTrelloCardDto.getShortUrl());
    }
}
