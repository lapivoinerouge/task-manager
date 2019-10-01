package com.crud.tasks.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TaskDtoTestSuite {

    @Test
    public void testGetterWithNoArgs() {
        //given
        TaskDto taskDto = new TaskDto();
        //when
        //then
        assertEquals(0, taskDto.getId());
        assertNull(taskDto.getTitle());
        assertNull(taskDto.getContent());
    }

    @Test
    public void testGetterWithAllArgs() {
        //given
        TaskDto taskDto = new TaskDto(1, "title", "content");

        //when
        //then
        assertEquals(1, taskDto.getId());
        assertEquals("title", taskDto.getTitle());
        assertEquals("content", taskDto.getContent());
    }
}
