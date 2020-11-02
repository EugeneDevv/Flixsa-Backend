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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CampusBuzzAdapter extends RecyclerView.Adapter<CampusBuzzAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<UploadsModel> mUploads;

    public CampusBuzzAdapter(Context mContext, ArrayList<UploadsModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.announcement_item, parent, false);
        return new CampusBuzzAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UploadsModel uploadCurrent = mUploads.get(position);
        holder.title.setText(uploadCurrent.getTitle());
        holder.description.setText(uploadCurrent.getDescription());
        Glide.with(mContext)
                .load(uploadCurrent.getImageURL())
                .placeholder(R.drawable.ic_image_broken)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
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
