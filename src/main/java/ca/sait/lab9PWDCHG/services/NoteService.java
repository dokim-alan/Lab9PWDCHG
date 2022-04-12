package ca.sait.lab9PWDCHG.services;

import ca.sait.lab9PWDCHG.dataaccess.NoteDB;
import ca.sait.lab9PWDCHG.dataaccess.UserDB;
import java.util.List;
import ca.sait.lab9PWDCHG.models.Note;
import ca.sait.lab9PWDCHG.models.User;

public class NoteService {
    public Note get(int id) throws Exception {
        NoteDB noteDB = new NoteDB();
        Note note = noteDB.get(id);
        return note;
    }
    
    public List<Note> getAll(String email) throws Exception {
        NoteDB noteDB = new NoteDB();
        List<Note> notes = noteDB.getAll(email);
        return notes;
    }
    
    public void insert(String title, String contents, String owner) throws Exception {
        Note note = new Note(0, title, contents);
        UserDB userDB = new UserDB();
        User user = userDB.get(owner);
        note.setOwner(user);
        
        NoteDB noteDB = new NoteDB();
        noteDB.insert(note);
    }
    
    public void update(int noteId, String title, String contents, String owner) throws Exception {
        NoteDB noteDB = new NoteDB();
        Note note = noteDB.get(noteId);
        note.setTitle(title);
        note.setContents(contents);
        
        noteDB.update(note);
    }
    
    public void delete(int noteId) throws Exception {
        NoteDB noteDB = new NoteDB();
        Note note = noteDB.get(noteId);
        noteDB.delete(note);
    }
}
