package org.techtown.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Api.ApiProvider;
import Api.ServerApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {
    public static ArrayList<Integer> deleteList;
    static ArrayList<MainAdapter.CustomViewHolder> holders;
    static ArrayList<MainData> arrayList;
    final String TAG = "Adapter";

    public MainAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
        deleteList = new ArrayList<>();
    }

    static public void startRemoveList() {
        Log.d("AdapterAdapter", "startRemoveList: ");
        for (int i = 0; i < deleteList.size(); i++) {
            Log.d("MainAdapter", "" + deleteList.get(i));
            removeList(deleteList.get(i));
        }
    }

    static void removeList(int position) {
        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);

        serverApi.WishDel("Bearer " + LoginActivity.accessToken, position).enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("TAG", "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    clearList();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public static void clearList() {
        arrayList.clear();
        MainActivity.fetchFeed();
    }

    public static void selectAll() {
        Log.d("Adapter", "holders : " + holders);
        for (int i = 0; i < arrayList.size(); i++) {
            deleteList.add(arrayList.get(i).getFeed_id());
            arrayList.get(i).setSelected(true);
            Log.d("Adapter ", "isSelected " + arrayList.get(i).isSelected());
        }
        for (int i = 0; i < holders.size(); i++) {
            holders.get(i).chk_item.setChecked(true);
        }
    }


    public static void unSelectAll() {
        for (int i = 0; i < holders.size(); i++) {
            holders.get(i).chk_item.setChecked(false);
        }
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).setSelected(false);
        }
        deleteList.clear();
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        holders = new ArrayList<>();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holders.add(holder);
        holder.title.setText(arrayList.get(position).getTiltle());
        holder.content.setText(arrayList.get(position).getContent());
        holder.chk_item.setChecked(arrayList.get(position).isSelected());
        Log.d(TAG, "id : " + arrayList.get(position).getFeed_id() + " title : " + arrayList.get(position).getTiltle() + " selected : " + arrayList.get(position).isSelected());

        holder.chk_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    deleteList.add(arrayList.get(position).getFeed_id());
                    Log.d(TAG, "onClick: " + holder.chk_item.isChecked());
                } else {
                    deleteList.remove(Integer.valueOf(arrayList.get(position).getFeed_id()));
                    deleteList.remove(Integer.valueOf(arrayList.get(position).getFeed_id()));
                    holder.chk_item.setChecked(false);
                }
                Log.d(TAG, "" + deleteList);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("Wish 달성");
                builder.setMessage("Wish를 달성했습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        holder.cardView.setBackgroundColor(Color.parseColor("#C7DEEC"));
                    }
                });


                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        holder.cardView.setBackgroundColor(Color.parseColor("#E3E3E3"));
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });

        if (arrayList.get(position).isSelected()) {
            holder.chk_item.setChecked(true);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditActivity.class);
                intent.putExtra("title", arrayList.get(position).getTiltle());
                intent.putExtra("content", arrayList.get(position).getContent());
                intent.putExtra("id", arrayList.get(position).getFeed_id());


                v.getContext().startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setTitle("Wish 삭제");
                builder.setMessage("Wish를 삭제하시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeList(arrayList.get(position).getFeed_id());
                        notifyDataSetChanged();

                    }
                }).setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {                 // 리스트 사이즈 반환
        return arrayList.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView content;
        public TextView delete;
        public CheckBox chk_item;
        public CardView cardView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);
            delete = itemView.findViewById(R.id.tv_delete);
            chk_item = itemView.findViewById(R.id.checkBox);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

}
