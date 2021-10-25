package com.thefear.my_notes.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;
import com.thefear.my_notes.R;
import com.thefear.my_notes.domain.Note;
import com.thefear.my_notes.domain.TestNotesRepository;
import com.thefear.my_notes.present.NotesListPresenter;
import com.thefear.my_notes.view.NotesListView;

import java.util.ArrayList;

public class NotesListFragment extends Fragment implements NotesListView {
    LinearLayout notesContainer;
    MaterialButton createButton;
    MaterialButton settingsButton;

    public NotesListFragment() {
        super(R.layout.fragment_notes_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notesContainer = view.findViewById(R.id.notes_container);
        createButton = view.findViewById(R.id.create_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewNote();
            }
        });
        settingsButton = view.findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });

        NotesListPresenter presenter = new NotesListPresenter(this, TestNotesRepository.getInstance());
        presenter.requestNotes();
    }

    @Override
    public void showNotes(ArrayList<Note> notes) {
        for (Note note : notes) {
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, notesContainer, false);
            TextView nameNote = itemView.findViewById(R.id.title_note);
            nameNote.setText(note.getTitle());

            notesContainer.addView(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickOnItemView();
                }
            });
        }
    }

    void createNewNote() {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new NotesInfoFragment())
                .addToBackStack(null)
                .commit();
    }

    void openSettings() {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new SettingsFragment())
                .addToBackStack(null)
                .commit();
    }

    void clickOnItemView() {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new NotesInfoFragment())
                .addToBackStack(null)
                .commit();
    }
}
