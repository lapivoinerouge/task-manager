package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService service;

    @Test
    public void shouldFetchAllTasks() throws Exception {
        //given
        List<TaskDto> list = new ArrayList<>();
        list.add(new TaskDto(1, "title 1", "content 1"));
        list.add(new TaskDto(2, "title 2", "content 2"));

        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(list);

        //when & then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("title 1")))
                .andExpect(jsonPath("$[1].content", is("content 2")));
    }

    @Test
    public void shouldFetchTaskById() throws Exception {
        //given
        Task task = new Task(1, "title 1", "content 1");
        TaskDto taskDto = new TaskDto(1, "title 1", "content 1");
        when(service.getTask(1L)).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //when & then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("title 1")))
                .andExpect(jsonPath("$.content", is("content 1")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //given
        Task task = new Task(1, "title 1", "content 1");
        TaskDto taskDto = new TaskDto(1, "title 1", "content 1");

        when(service.getTask(1L)).thenReturn(ofNullable(task));

        //when & then
        mockMvc.perform(delete("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //given
        Task task = new Task(1, "title 1", "content 1");
        TaskDto taskDto = new TaskDto(1, "title 1", "content 1");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //when & then
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //given
        Task task = new Task(1, "title 1", "content 1");
        TaskDto taskDto = new TaskDto(1, "title 1", "content 1");
        when(service.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //when & then
        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}