package org.techtown.Activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ArrayList<MainData> arrayList;

    public MainAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {

        holder.title.setText(arrayList.get(position).getTiltle());
        holder.content.setText(arrayList.get(position).getContent());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),EditActivity.class);
                intent.putExtra("title",arrayList.get(position).getTiltle());
                intent.putExtra("content", arrayList.get(position).getContent());

                v.getContext().startActivity(intent);
//                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), EditActivity.class));
//                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), EditActivity.class));

            }
        });
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView content;


        public CustomViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.tv_title);
            this.content = (TextView) itemView.findViewById(R.id.tv_ccontent);


        }

    }

    @Override
    public int getItemCount() {                 // 리스트 사이즈 반환
        return arrayList.size();
    }
}
