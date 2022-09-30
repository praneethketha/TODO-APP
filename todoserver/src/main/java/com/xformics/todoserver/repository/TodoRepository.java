package com.xformics.todoserver.repository;
import com.xformics.todoserver.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "SELECT * FROM todo t WHERE t.completed = ?1 ORDER BY t.id ASC", nativeQuery = true)
    List<Todo> findByCompletedAsc(Boolean completed);

    @Query(value = "SELECT * FROM todo t ORDER BY t.id ASC", nativeQuery = true)
    List<Todo> findAllByIdAsc();
}
