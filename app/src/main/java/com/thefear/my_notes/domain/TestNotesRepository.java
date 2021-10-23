package com.thefear.my_notes.domain;

import com.thefear.my_notes.R;

import java.util.ArrayList;
import java.util.List;

public class TestNotesRepository implements NotesRepository {

    private static TestNotesRepository mInstance;
    private ArrayList<Note> notes = null;

    public static TestNotesRepository getInstance() {
        if(mInstance == null)
            mInstance = new TestNotesRepository();

        return mInstance;
    }

    private TestNotesRepository() {
        notes = new ArrayList<Note>();
    }
    @Override
    public ArrayList<Note> getNotes() {
        return this.notes;
    }
    public void addNote(Note note) {
        notes.add(note);
    }

}
