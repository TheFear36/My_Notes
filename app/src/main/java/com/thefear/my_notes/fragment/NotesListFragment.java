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

import com.thefear.my_notes.R;
import com.thefear.my_notes.domain.Note;
import com.thefear.my_notes.domain.TestNotesRepository;
import com.thefear.my_notes.fragment.info.NotesInfoFragment;
import com.thefear.my_notes.present.NotesListPresenter;
import com.thefear.my_notes.view.NotesListView;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {

    public static final String KEY_NOTES_LIST = "KEY_NOTES_LIST";
    public static final String ARG_NOTE = "ARG_NOTE";

    private LinearLayout notesContainer;

    private NotesListPresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotesListPresenter(this, TestNotesRepository.getInstance());
    }

    public NotesListFragment() {
        super(R.layout.fragment_notes_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.requestNotes();

        notesContainer = view.findViewById(R.id.notes_root);

        Button createNewNoteBtn = view.findViewById(R.id.create_new_note_btn);

        createNewNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isLandscape = getResources().getBoolean(R.bool.is_landscape);

                FragmentManager fManager = getParentFragmentManager();

                if (isLandscape){
                    fManager.beginTransaction()
                            .replace(R.id.fragment_container_info, new NotesInfoFragment())
                            .commit();
                } else {
                    fManager.beginTransaction()
                            .replace(R.id.fragment_container, new NotesInfoFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

    }

    @Override
    public void showNotes(List<Note> notes) {

        for (Note note : notes) {
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, notesContainer, false);

            TextView noteName = itemView.findViewById(R.id.note_name);
            noteName.setText(note.getNameNote());

            notesContainer.addView(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putParcelable(ARG_NOTE, note);

                    getParentFragmentManager()
                            .setFragmentResult(KEY_NOTES_LIST, bundle);
                }
            });
        }
    }
}
