package org.techtown.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Api.ApiProvider;
import Api.ServerApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ArrayList<MainData> arrayList;

    public MainAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView content;
        public TextView delete;

        public CustomViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.tv_title);
            this.content = (TextView) itemView.findViewById(R.id.tv_content);
            this.delete = (TextView) itemView.findViewById(R.id.tv_delete);

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
                        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);

                        serverApi.WishDel("Bearer " + LoginActivity.accessToken, (Integer) arrayList.get(position).getFeed_id()).enqueue(new Callback<Void>() {

                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Log.d("TAG", "onResponse: " + response.code());
                                if (response.isSuccessful()) {
                                    arrayList.remove(position);
                                    notifyItemRemoved(position);
                                    Toast.makeText(view.getContext(), "Wish가 삭제됐습니다!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(view.getContext(), "죄송합니다..", Toast.LENGTH_SHORT).show();
                            }
                        });
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

}
