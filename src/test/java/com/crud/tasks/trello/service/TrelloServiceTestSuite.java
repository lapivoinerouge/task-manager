package com.crud.tasks.trello.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Mock
    SimpleEmailService simpleEmailService;

    @Mock
    AdminConfig adminConfig;

    private List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();

    @Test
    public void testFetchTrelloBoards() {
        //given
        trelloBoardDtoList.add(new TrelloBoardDto("1", "name", new ArrayList<>()));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //when
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();

        //then
        assertEquals("name", result.get(0).getName());
    }

    @Test
    public void testCreateTrelloCard() {
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "name", "url");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");

        //when
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //then
        assertEquals(createdTrelloCardDto, result);
        assertEquals("1", result.getId());
        assertEquals("name", result.getName());
        assertEquals("url", result.getShortUrl());
    }
}
