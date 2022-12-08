package com.sakamoto.skillreport.repository;

import com.sakamoto.skillreport.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
}