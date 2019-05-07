package com.assign.pratilipi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.Serializable;
import java.util.List;

import static com.assign.pratilipi.R.drawable.ic_user;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private List<Contact> userList;
    private Context context;
    public ContactAdapter(List<Contact> userList, Context context) {
        this.context=context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_layout,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(userList.get(i).getContactName());
        if (userList.get(i).getPhotoUri()!=null && !userList.get(i).getPhotoUri().equals(""))
            Glide.with(context)
                    .load(userList.get(i).getPhotoUri())
                    .apply(RequestOptions.circleCropTransform())
                    .into(myViewHolder.image);
        else
            myViewHolder.image.setImageDrawable(context.getDrawable(R.drawable.ic_user));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ContactDetaisActivity.class);
                    intent.putExtra("contact_details",(Serializable)userList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
