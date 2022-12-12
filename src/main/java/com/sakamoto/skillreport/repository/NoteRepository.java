package com.sakamoto.skillreport.repository;

import com.sakamoto.skillreport.model.Classroom;
import com.sakamoto.skillreport.model.Note;
import com.sakamoto.skillreport.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByStudent(User student);

    List<Note> findAllByClassroom(Classroom classroom);
}