package com.example.todolist_prac.service;

import com.example.todolist_prac.model.TodoEntity;
import com.example.todolist_prac.model.TodoRequest;
import com.example.todolist_prac.model.TodoResponse;
import com.example.todolist_prac.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    public final TodoRepository todoRepository;

    // 추가
    public TodoEntity add(TodoRequest todoRequest) {

        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(todoRequest.getTitle());
        todoEntity.setOrder(todoRequest.getOrder());
        todoEntity.setCompleted(todoRequest.getCompleted());

        return todoRepository.save(todoEntity);
    }

    // 조회
    public TodoEntity searchById(Long id) {
        var byId = todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return byId;
    }


    // 전체 조회

    public List<TodoEntity> searchAll() {
        var all = todoRepository.findAll();

        return all;
    }

    // update
    public TodoEntity updateById(Long id, TodoRequest request) {
        TodoEntity todoEntity = this.searchById(id);
        if (request.getTitle() != null) {
            todoEntity.setTitle(request.getTitle());
        }
        if (request.getOrder() != null) {
            todoEntity.setOrder(request.getOrder());
        }
        if (request.getCompleted() != null) {
            todoEntity.setCompleted(request.getCompleted());
        }

        return todoRepository.save(todoEntity);
    }

    // 삭제

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    // 전체 삭제

    public void deleteAll() {
        todoRepository.deleteAll();
    }

}