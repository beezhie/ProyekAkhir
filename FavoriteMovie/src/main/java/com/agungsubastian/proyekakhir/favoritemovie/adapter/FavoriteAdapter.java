package com.agungsubastian.proyekakhir.favoritemovie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agungsubastian.proyekakhir.favoritemovie.BuildConfig;
import com.agungsubastian.proyekakhir.favoritemovie.R;
import com.agungsubastian.proyekakhir.favoritemovie.model.FavoriteModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {
    private List<FavoriteModel> listGetFavoriteModel = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;

    public FavoriteAdapter() {}

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void replaceAll(List<FavoriteModel> items) {
        listGetFavoriteModel.clear();
        listGetFavoriteModel = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv, viewGroup, false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteHolder holder, int position) {
        holder.bind(listGetFavoriteModel.get(position));
    }

    @Override
    public int getItemCount() {
        return listGetFavoriteModel.size();
    }

    class FavoriteHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;
        private TextView txtScore;
        private TextView txtDate;
        private TextView txtDescription;
        private ImageView imgData;

        FavoriteHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtScore = itemView.findViewById(R.id.txt_score);
            txtDate = itemView.findViewById(R.id.txt_date);
            txtDescription = itemView.findViewById(R.id.txt_description);
            imgData = itemView.findViewById(R.id.img_photo);
        }

        void bind(final FavoriteModel item) {
            txtTitle.setText(item.getName());
            txtScore.setText(item.getVote());
            txtDate.setText(item.getDate());
            txtDescription.setText(item.getDescription());
            Glide.with(itemView.getContext())
                    .load(BuildConfig.BASE_URL_IMG +"/w92/"+item.getImage())
                    .error(R.drawable.ic_error)
                    .centerInside()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imgData);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickCallback.onItemClicked(listGetFavoriteModel.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(FavoriteModel data);
    }
}
