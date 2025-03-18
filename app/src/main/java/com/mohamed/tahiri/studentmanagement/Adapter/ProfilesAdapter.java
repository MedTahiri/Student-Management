package com.mohamed.tahiri.studentmanagement.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamed.tahiri.studentmanagement.ProfileActivity;
import com.mohamed.tahiri.studentmanagement.ProfilesActivity;
import com.mohamed.tahiri.studentmanagement.R;
import com.mohamed.tahiri.studentmanagement.Utils.ImageUtils;
import com.mohamed.tahiri.studentmanagement.models.Student;

import java.io.Serializable;
import java.util.List;

public class ProfilesAdapter extends RecyclerView.Adapter<ProfilesAdapter.ViewHolder> {

    private List<Student> data;
    private Context context;

    public ProfilesAdapter(List<Student> data,Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_profile, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.firstname.setText(data.get(position).firstname);
        holder.lastname.setText(data.get(position).lastname);
        holder.classname.setText(data.get(position).email);
        ImageUtils.loadImageFromUrl(data.get(position).image, holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Student clickedStudent = data.get(position);
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra("student_data", clickedStudent);
            intent.putExtra("nb_student",data.size());
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView firstname,lastname,classname;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.itemfirstname);
            lastname = itemView.findViewById(R.id.itemlastname);
            classname = itemView.findViewById(R.id.itemclass_name);
            imageView = itemView.findViewById(R.id.itemimage);
        }


    }
}
