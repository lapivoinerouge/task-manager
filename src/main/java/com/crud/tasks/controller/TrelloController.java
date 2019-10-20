package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/boards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();

//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//
//        trelloBoards.forEach(trelloBoardDto -> {
//
//            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
//
//            System.out.println("This board contains lists: ");
//
//            trelloBoardDto.getLists().forEach(trelloList ->
//                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
//        });

//        trelloBoards.stream()
//                .filter(t -> t.getName() != null)
//                .filter(t -> t.getId() != null)
//                .filter(t -> t.getName().contains("kodilla"))
//                .forEach(t -> System.out.println(t.getId() + " " + t.getName()));
        }

    @RequestMapping(method = RequestMethod.POST, value = "/cards")
        public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {

        return trelloFacade.createCard(trelloCardDto);
    }
}


