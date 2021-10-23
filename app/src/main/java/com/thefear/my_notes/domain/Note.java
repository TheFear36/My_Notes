package com.thefear.my_notes.domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import androidx.annotation.StringRes;

public class Note implements Parcelable {

    private String nameNote;
    private String infoNote;


    public void setNameNote(String nameNote) {
        this.nameNote = nameNote;
    }

    public void setInfoNote(String infoNote) {
        this.infoNote = infoNote;
    }

    public String getNameNote() {
        return nameNote;
    }

    public String getInfoNote() {
        return infoNote;
    }

    public Note(String nameNote, String infoNote) {
        this.nameNote = nameNote;
        this.infoNote = infoNote;
    }

    protected Note(Parcel in) {
        nameNote = in.readString();
        infoNote = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameNote);
        dest.writeString(infoNote);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}


