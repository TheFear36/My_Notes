package com.thefear.my_notes.view;

import com.thefear.my_notes.domain.Note;

import java.util.List;

public interface NotesListView {
    void showNotes(List<Note> notes);
}
