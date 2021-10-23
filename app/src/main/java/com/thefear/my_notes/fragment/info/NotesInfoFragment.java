package com.thefear.my_notes.fragment.info;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.thefear.my_notes.R;
import com.thefear.my_notes.domain.Note;
import com.thefear.my_notes.domain.TestNotesRepository;

public class NotesInfoFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";

    Button saveButton;


    public NotesInfoFragment() {
        super(R.layout.fragment_notes_info);
    }

    public static NotesInfoFragment newInstance(Note note) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);

        NotesInfoFragment fragment = new NotesInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveButton = view.findViewById(R.id.save_note_button);

        if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {
            Note note = getArguments().getParcelable(ARG_NOTE);

            EditText nameNote = view.findViewById(R.id.note_name);
            nameNote.setText(note.getNameNote());

            EditText infoNote = view.findViewById(R.id.notes_info);
            infoNote.setText(note.getInfoNote());

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    note.setNameNote(nameNote.getText().toString());
                    note.setInfoNote(infoNote.getText().toString());
                }
            });
        }

        EditText nameNote = view.findViewById(R.id.note_name);
        EditText infoNote = view.findViewById(R.id.notes_info);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestNotesRepository.getInstance().addNote(new Note(nameNote.getText().toString(), infoNote.getText().toString()));
            }
        });
    }

}
