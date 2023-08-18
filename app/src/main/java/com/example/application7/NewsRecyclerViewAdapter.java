package com.example.application7;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.application7.databinding.ListItemBinding;

import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private List<News> newsData;

    @Nullable
    private ItemClickListener listener;

    public NewsRecyclerViewAdapter(List<News> data) {
        this.newsData = data;
    }

    public interface ItemClickListener {
        void onClick(int index, News news);
    }

    public void setOnItemClick(ItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemBinding binding = ListItemBinding.inflate(inflater, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        binding.item.setOnClickListener(v -> {
            int p = viewHolder.getAdapterPosition();
            News news = newsData.get(p);
            if (listener != null) {
                listener.onClick(p, news);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = newsData.get(position);

        holder.binding.tvTitle.setText(news.getTitle());
        holder.binding.tvSubtitle.setText(news.getAuthor());

        if (news.getImageUrl() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(news.getImageUrl())
                    .placeholder(R.drawable.img_01)
                    .into(holder.binding.ivImage);
        } else {
            holder.binding.ivImage.setImageResource(news.getImageId());
        }
    }

    @Override
    public int getItemCount() {
        return newsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ListItemBinding binding;

        public ViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}