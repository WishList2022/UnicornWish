package org.techtown.Activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private MainActivity mainActivity;
    private ArrayList<MainData> arrayList;

    public MainAdapter(ArrayList<MainData> arrayList, MainActivity mainActivity) {
        this.arrayList = arrayList;
        this.mainActivity = mainActivity;

    }

    public class  MainViewHoder extends RecyclerView.ViewHolder{

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
    public void onBindViewHolder(@NonNull final MainAdapter.CustomViewHolder holder, int position) {

      holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                mainActivity.moveSee();

            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position) {
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView tv_name;


        public CustomViewHolder(View itemView) {
            super(itemView);

            this.tv_name = (TextView) itemView.findViewById(R.id.tv_title);


        }

    }
}
