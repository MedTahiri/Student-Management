package com.mohamed.tahiri.studentmanagement.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamed.tahiri.studentmanagement.R;
import com.mohamed.tahiri.studentmanagement.models.Note;
import com.mohamed.tahiri.studentmanagement.models.Student;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> data;
    private Context context;

    public NotesAdapter(List<Note> data, Context context) {
        this.data = data;
        this.context = context;
    }



    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NotesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.matiere.setText(data.get(position).matiere);
        holder.score.setText(data.get(position).score);
        if (data.get(position).status) {
            holder.status.setImageResource(R.drawable.like);
        } else {
            holder.status.setImageResource(R.drawable.dislike);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView matiere,score;
        ImageView status;
        public ViewHolder(View itemView) {
            super(itemView);
            matiere = itemView.findViewById(R.id.item_matiere);
            score = itemView.findViewById(R.id.item_score);
            status = itemView.findViewById(R.id.item_status);
        }
    }
}
