package com.doanhtq.note.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.doanhtq.note.Note;
import com.doanhtq.note.R;

import java.util.LinkedList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{

    private final LinkedList<Note> mNoteList;
    private LayoutInflater mInflater;

    public NoteAdapter(Context context, LinkedList<Note> mNoteList) {
        this.mNoteList = mNoteList;
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.note_view, parent, false);

        return new NoteViewHolder(mView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note mNote = mNoteList.get(position);
        holder.noteID.setText(mNote.getNoteID() + "");
        holder.noteTitle.setText(mNote.getNoteTitle());
        holder.noteDescription.setText(mNote.getNoteDescription());
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        public final TextView noteID, noteTitle, noteDescription;
        final NoteAdapter mNoteAdapter;


        public NoteViewHolder(@NonNull View itemView, NoteAdapter mNoteAdapter) {
            super(itemView);
            noteID = itemView.findViewById(R.id.note_id);
            noteTitle = itemView.findViewById(R.id.note_title);
            noteDescription = itemView.findViewById(R.id.note_description);
            this.mNoteAdapter = mNoteAdapter;
        }
    }
}
