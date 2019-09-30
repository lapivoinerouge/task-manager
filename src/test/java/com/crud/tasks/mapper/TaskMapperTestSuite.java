package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaskMapperTestSuite {

    private TaskMapper taskMapper = new TaskMapper();

    @Test
    public void testMapToTask() {
        //given
        TaskDto taskDto = new TaskDto(1L, "title", "content");

        //when
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //then
        assertEquals("title", mappedTask.getTitle());
        assertEquals("content", mappedTask.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //given
        Task task = new Task(1L, "title", "content");

        //when
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);

        //then
        assertEquals("title", mappedTaskDto.getTitle());
        assertEquals("content", mappedTaskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "title 1", "content 1"));
        taskList.add(new Task(2L, "title 2", "content 2"));

        //when
        List<TaskDto> mappedList = taskMapper.mapToTaskDtoList(taskList);

        //then
        assertEquals("title 1", mappedList.get(0).getTitle());
        assertEquals("title 2", mappedList.get(1).getTitle());
    }
}
