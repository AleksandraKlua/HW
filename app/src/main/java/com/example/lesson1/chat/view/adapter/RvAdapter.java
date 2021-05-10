package com.example.lesson1.chat.view.adapter;

import com.example.lesson1.R;
import com.example.lesson1.chat.model.Message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    ArrayList chatArray = new ArrayList<Message>();

    public void setData(ArrayList<Message> arr){
        chatArray = arr;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view;
        if(((Message) chatArray.get(position)).getMessageLabel() == 0){
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_interlocutor, parent, false);
        }else view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(((Message) chatArray.get(position)).getMessageText());
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    @Override
    public int getItemCount() {
        return chatArray.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        TextView textView = itemView.findViewById(R.id.message_view);

        void bind(String str){
            textView.setText(str);
        }
    }
}
