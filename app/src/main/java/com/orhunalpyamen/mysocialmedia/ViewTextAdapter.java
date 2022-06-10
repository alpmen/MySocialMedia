package com.orhunalpyamen.mysocialmedia;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class ViewTextAdapter extends RecyclerView.Adapter<ViewTextAdapter.ViewHolder>{

    public Context context;
    public ArrayList<Notes> mList;
    public View.OnClickListener mOnItemClickListener;


    public ViewTextAdapter(Context context, ArrayList<Notes> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewTextAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_text_recycler, parent, false);
        return new ViewTextAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewTextAdapter.ViewHolder holder, int position) {
        holder.txtmail.setText(mList.get(position).getNote_mail());
        holder.txttext.setText(mList.get(position).getNote_text());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtmail;
        TextView txttext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtmail = itemView.findViewById(R.id.txtmail);
            txttext = itemView.findViewById(R.id.txttext);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

}
