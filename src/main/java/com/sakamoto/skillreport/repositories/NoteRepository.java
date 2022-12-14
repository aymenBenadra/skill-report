package com.sakamoto.skillreport.repositories;

import com.sakamoto.skillreport.models.Note;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends Repository<Note, Long> {
    Optional<Note> findById(Long id);

    List<Note> findAllByStudentId(Long studentId);

    List<Note> findAllByClassroomId(Long classroomId);

    Note save(Note note);

    void delete(Note note);

    Long count();
}