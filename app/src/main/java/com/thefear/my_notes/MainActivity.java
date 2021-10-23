package com.thefear.my_notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

import android.os.Bundle;

import com.thefear.my_notes.domain.Note;
import com.thefear.my_notes.fragment.NotesListFragment;
import com.thefear.my_notes.fragment.info.NotesInfoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().setFragmentResultListener(NotesListFragment.KEY_NOTES_LIST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                Note note = result.getParcelable(NotesListFragment.ARG_NOTE);

                NotesInfoFragment infoFragment = NotesInfoFragment.newInstance(note);

                FragmentManager fragmentManager = getSupportFragmentManager();

                boolean isLandscape = getResources().getBoolean(R.bool.is_landscape);

                if (isLandscape) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container_info, infoFragment)
                            .commit();
                } else {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, infoFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
}