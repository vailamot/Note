package com.doanhtq.note;

public class Note {
    public Note(int noteID, String noteTitle, String noteDescription) {
        this.noteID = noteID;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    int noteID;

    public Note() {
    }

    String noteTitle;
    String noteDescription;

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Note(String noteTitle, String noteDescription) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }
}
