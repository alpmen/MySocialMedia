package com.orhunalpyamen.mysocialmedia;

import android.app.Application;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileRcyclerAdapter extends RecyclerView.Adapter<ProfileRcyclerAdapter.PostHolder2> {
    private ArrayList<String> userEmailList;
    private ArrayList<String> userCommentList;
    private ArrayList<String> userImageist;
    private ArrayList<String> userIDlist;

    public ProfileRcyclerAdapter(ArrayList<String> userEmailList, ArrayList<String> userCommentList, ArrayList<String> userImageist,ArrayList<String> userIDlist) {
        this.userEmailList = userEmailList;
        this.userCommentList = userCommentList;
        this.userImageist = userImageist;
        this.userIDlist=userIDlist;
    }

    @NonNull
    @Override
    public PostHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.view_profile_rcycler,parent,false);

        return new PostHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder2 holder, int position) {
        holder.textUserProfile.setText(userEmailList.get(position));
        holder.textCommentProfile.setText(userCommentList.get(position));
        holder.textView9.setText(userIDlist.get(position));
        Picasso.get().load(userImageist.get(position)).into(holder.imageURLProfile);


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                delete(holder.textView9.getText().toString(),position);
                notifyDataSetChanged();
                Intent intent=new Intent(view.getContext().getApplicationContext(),feedActivity.class);
                view.getContext().startActivity(intent);
            }
        });

    }


    public void delete(String id,int position){
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();
        firestore.collection("Posts").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                notifyItemRemoved(position);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return userEmailList.size();
    }

    class PostHolder2 extends RecyclerView.ViewHolder{
        TextView textUserProfile;
        TextView textView9;
        TextView textCommentProfile;
        ImageView imageURLProfile;
        Button button;

        public PostHolder2(@NonNull View itemView) {
            super(itemView);

            textUserProfile=itemView.findViewById(R.id.textUserProfile);
            textCommentProfile=itemView.findViewById(R.id.textCommentProfile);
            imageURLProfile=itemView.findViewById(R.id.imageURLProfile);
            button=itemView.findViewById(R.id.button7);
            textView9=itemView.findViewById(R.id.textView9);
        }
    }
}
