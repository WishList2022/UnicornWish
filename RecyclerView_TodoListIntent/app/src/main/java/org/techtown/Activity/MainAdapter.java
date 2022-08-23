package org.techtown.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ArrayList<MainData> arrayList;

    public MainAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView content;


        public CustomViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.tv_title);
            this.content = (TextView) itemView.findViewById(R.id.tv_content);


        }
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new CustomViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(arrayList.get(position).getTiltle());
        holder.content.setText(arrayList.get(position).getContent());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditActivity.class);
                intent.putExtra("title", arrayList.get(position).getTiltle());
                intent.putExtra("content", arrayList.get(position).getContent());

                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {                 // 리스트 사이즈 반환
        return arrayList.size();
    }

}
