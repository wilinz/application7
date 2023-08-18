package com.example.application7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewsListViewAdapter extends ArrayAdapter<News> {

    // ViewHolder类，用于缓存每个子项的视图
    static class ViewHolder {
        TextView tvTitle;
        TextView tvSubtitle;
        ImageView ivImage;
    }

    public NewsListViewAdapter(Context context, int resourceId, List<News> data) {
        super(context, resourceId, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            // 如果convertView为空，说明当前子项还未被绘制，需要创建新的ViewHolder并关联到convertView
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
            viewHolder.tvSubtitle = convertView.findViewById(R.id.tv_subtitle);
            viewHolder.ivImage = convertView.findViewById(R.id.iv_image);
            convertView.setTag(viewHolder);
        } else {
            // 如果convertView不为空，说明当前子项已经被绘制过了，直接从convertView中获取ViewHolder即可
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 获取当前位置的新闻数据对象
        News news = getItem(position);

        // 将新闻数据对象的标题、作者和图片资源ID设置到对应的视图控件中
        viewHolder.tvTitle.setText(news.getTitle());
        viewHolder.tvSubtitle.setText(news.getAuthor());
        viewHolder.ivImage.setImageResource(news.getImageId());

        return convertView; // 返回当前子项的视图
    }
}