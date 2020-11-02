package com.eujoh.uoeadminapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eujoh.uoeadminapp.R;
import com.eujoh.uoeadminapp.models.UploadsModel;

import java.util.ArrayList;

public class AnnouncementsAdapter extends RecyclerView.Adapter<AnnouncementsAdapter.ViewHolder>{
    private static final String Tag = "RecyclerView";

    private Context mContext;
    private ArrayList<UploadsModel> announcementsList;

    public AnnouncementsAdapter(Context mContext, ArrayList<UploadsModel> announcementsList) {
        this.mContext = mContext;
        this.announcementsList = announcementsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.announcement_item, parent, false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(announcementsList.get(position).getTitle());
        holder.description.setText(announcementsList.get(position).getDescription());

//        ImageView: Glide library
        Glide.with(mContext)
                .load(announcementsList.get(position).getImageURL())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return announcementsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //        widgets
        ImageView imageView;
        TextView title, description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.announcement_item_image_v);
            title = itemView.findViewById(R.id.announcement_title_tv);
            description = itemView.findViewById(R.id.announcement_desc_tv);
        }
    }
}
