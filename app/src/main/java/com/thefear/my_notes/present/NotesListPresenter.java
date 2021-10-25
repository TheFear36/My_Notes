package com.thefear.my_notes.present;

import com.thefear.my_notes.domain.NotesRepository;
import com.thefear.my_notes.view.NotesListView;

public class NotesListPresenter {

    private final NotesListView view;

    private final NotesRepository repository;

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes() {
        view.showNotes(repository.getNotes());
    }
}
