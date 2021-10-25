package com.thefear.my_notes.view;

import com.thefear.my_notes.domain.Note;

import java.util.ArrayList;
import java.util.List;

public interface NotesListView {
    void showNotes(ArrayList<Note> notes);
}
