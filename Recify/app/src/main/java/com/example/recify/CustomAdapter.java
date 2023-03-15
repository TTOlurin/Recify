package com.example.recify;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList r_id, r_name, r_type, r_ingredients, r_method;

    int position;

    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList r_id,
                  ArrayList r_name,
                  ArrayList r_type,
                  ArrayList r_ingredients,
                  ArrayList r_method){
        this.activity = activity;
        this.context = context;
        this.r_id = r_id;
        this.r_name = r_name;
        this.r_type = r_type;
        this.r_ingredients = r_ingredients;
        this.r_method = r_method;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recipe_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.r_id_txt.setText(String.valueOf(r_id.get(position)));
        holder.r_name_txt.setText(String.valueOf(r_name.get(position)));
        holder.r_type_txt.setText(String.valueOf(r_type.get(position)));
        holder.r_ingredients_txt.setText(String.valueOf(r_ingredients.get(position)));
        holder.r_method_txt.setText(String.valueOf(r_method.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditRecipe.class);
                intent.putExtra("id", String.valueOf(r_id.get(position)));
                intent.putExtra("name", String.valueOf(r_name.get(position)));
                intent.putExtra("type", String.valueOf(r_type.get(position)));
                intent.putExtra("ingredients", String.valueOf(r_ingredients.get(position)));
                intent.putExtra("method", String.valueOf(r_method.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return r_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView r_name_txt, r_type_txt, r_ingredients_txt, r_method_txt, r_id_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            r_name_txt = itemView.findViewById(R.id.r_name_txt);
            r_type_txt = itemView.findViewById(R.id.r_type_txt);
            r_ingredients_txt = itemView.findViewById(R.id.r_ingredients_txt);
            r_method_txt = itemView.findViewById(R.id.r_method_txt);
            r_id_txt = itemView.findViewById(R.id.r_id_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
