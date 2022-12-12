package com.sakamoto.skillreport.service;

import com.sakamoto.skillreport.model.Classroom;
import com.sakamoto.skillreport.model.Note;
import com.sakamoto.skillreport.model.NoteType;
import com.sakamoto.skillreport.model.User;
import com.sakamoto.skillreport.repository.ClassroomRepository;
import com.sakamoto.skillreport.repository.NoteRepository;
import com.sakamoto.skillreport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotesService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final ClassroomRepository classroomRepository;

    @Autowired
    public NotesService(NoteRepository noteRepository, UserRepository userRepository, ClassroomRepository classroomRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.classroomRepository = classroomRepository;
    }

    public List<Note> getAllNotesByStudentId(Long studentId) {
        User student = userRepository.findById(studentId).orElse(null);
        if (student == null) {
            return null;
        }
        return noteRepository.findAllByStudent(student);
    }

    public List<Note> getAllNotesByClassroomId(Long classroomId) {
        Classroom classroom = classroomRepository.findById(classroomId).orElse(null);
        if (classroom == null) {
            return null;
        }
        return noteRepository.findAllByClassroom(classroom);
    }

    public boolean createNote(String content, NoteType type, Long studentId, Long classroomId) {
        User student = userRepository.findById(studentId).orElse(null);
        Classroom classroom = classroomRepository.findById(classroomId).orElse(null);
        if (student == null || classroom == null) {
            return false;
        }
        Note note = new Note(content, type, student, classroom);
        return noteRepository.save(note).getId() != null;
    }

    public boolean updateNoteById(Long noteId, String content) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if (note == null) {
            return false;
        }
        note.setContent(content);
        return noteRepository.save(note).getId() != null;
    }

    public boolean deleteNoteById(Long noteId) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if (note == null) {
            return false;
        }
        noteRepository.delete(note);
        return true;
    }
}
