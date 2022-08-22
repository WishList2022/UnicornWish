package org.techtown.Activity;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Response.GetResponse;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private MainActivity mainActivity;
    private ArrayList<GetResponse> arrayList;

    public MainAdapter(MainActivity mainActivity, ArrayList<GetResponse> arrayList) {
        this.mainActivity = mainActivity;
        this.arrayList = arrayList;
    }

    public class MainViewHoder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_contnet;

        public MainViewHoder(@NonNull View view) {
            super(view);

            tv_title = view.findViewById(R.id.tv_title);
            tv_contnet = view.findViewById(R.id.tv_ccontent);
        }
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
        holder.title.setText(arrayList.get(position).getTitle());
        holder.content.setText(arrayList.get(position).getContent());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                mainActivity.Edit();

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
