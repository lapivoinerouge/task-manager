package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrelloMapperTestSuite {

    private TrelloMapper trelloMapper = new TrelloMapper();
    private List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
    private List<TrelloBoard> trelloBoardList = new ArrayList<>();
    private List<TrelloListDto> trelloListDtoList = new ArrayList<>();
    private List<TrelloList> trelloListList = new ArrayList<>();

    @Test
    public void testMapToBoards() {
        //given
        trelloBoardDtoList.add(new TrelloBoardDto("1", "board 1", new ArrayList<>()));
        trelloBoardDtoList.add(new TrelloBoardDto("2", "board 2", new ArrayList<>()));

        //when
        List<TrelloBoard> mappedBoards = trelloMapper.mapToBoards(trelloBoardDtoList);

        //then
        assertEquals(2, mappedBoards.size());
        assertEquals("board 1", mappedBoards.get(0).getName());
        assertEquals("board 2", mappedBoards.get(1).getName());
    }

    @Test
    public void testMapToBoardsDto() {
        //given
        trelloBoardList.add(new TrelloBoard("1", "board 1", new ArrayList<>()));
        trelloBoardList.add(new TrelloBoard("2", "board 2", new ArrayList<>()));

        //when
        List<TrelloBoardDto> mappedBoardsDto = trelloMapper.mapToBoardsDto(trelloBoardList);

        //then
        assertEquals(2, mappedBoardsDto.size());
        assertEquals("board 1", mappedBoardsDto.get(0).getName());
        assertEquals("board 2", mappedBoardsDto.get(1).getName());
    }

    @Test
    public void testMapToList() {
        //given
        trelloListDtoList.add(new TrelloListDto("1", "list 1", false));
        trelloListDtoList.add(new TrelloListDto("2", "list 2", false));

        //when
        List<TrelloList> mappedList = trelloMapper.mapToList(trelloListDtoList);

        //then
        assertEquals(2, mappedList.size());
        assertEquals("list 1", mappedList.get(0).getName());
        assertEquals("list 2", mappedList.get(1).getName());
    }

    @Test
    public void testMapToListDto() {
        //given
        trelloListList.add(new TrelloList("1", "list 1", false));
        trelloListList.add(new TrelloList("2", "list 2", false));

        //when
        List<TrelloListDto> mappedList = trelloMapper.mapToListDto(trelloListList);

        //then
        assertEquals(2, mappedList.size());
        assertEquals("list 1", mappedList.get(0).getName());
        assertEquals("list 2", mappedList.get(1).getName());
    }

    @Test
    public void testMapToCard() {
        //given
        TrelloCardDto cardDto = new TrelloCardDto("card 1", "description", "5", "1");

        //when
        TrelloCard mappedCard = trelloMapper.mapToCard(cardDto);

        //then
        assertEquals("card 1", mappedCard.getName());
        assertEquals("description", mappedCard.getDescription());
        assertEquals("5", mappedCard.getPos());
        assertEquals("1", mappedCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //given
        TrelloCard card = new TrelloCard("card 1", "description", "5", "1");

        //when
        TrelloCardDto mappedCardDto = trelloMapper.mapToCardDto(card);

        //then
        assertEquals("card 1", card.getName());
        assertEquals("description", card.getDescription());
        assertEquals("5", card.getPos());
        assertEquals("1", card.getListId());
    }
}
