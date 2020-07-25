package dev.xmuu.recyclerviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NO_IMAGE = 1;
    public static final int TYPE_SMALL_IMAGE = 2;
    public static final int TYPE_BIG_IMAGE = 3;
    public static final int TYPE_FOOTER = 4;

    private Context mContext;
    private List<ListItem> mData;

    public ListAdapter(Context context, List<ListItem> mData) {
        this.mContext = context;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else {
            return mData.get(position - 1).type;
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_list_header, parent, false), viewType);
            case TYPE_NO_IMAGE:
                return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_text_only, parent, false), viewType);
            case TYPE_SMALL_IMAGE:
                return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_text_with_small_image, parent, false), viewType);
            case TYPE_BIG_IMAGE:
                return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_text_with_big_image, parent, false), viewType);
            default:
                return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_list_footer, parent, false), viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (holder.type == TYPE_HEADER) {
            String imgURL1 = "file:///android_asset/1.jpg";
            String imgURL2 = "file:///android_asset/2.jpg";
            String imgURL3 = "file:///android_asset/3.jpg";
            Glide.with(mContext)
                    .load(imgURL1)
                    .centerCrop()
                    .into(holder.headerImage1);
            Glide.with(mContext)
                    .load(imgURL2)
                    .centerCrop()
                    .into(holder.headerImage2);
            Glide.with(mContext)
                    .load(imgURL3)
                    .centerCrop()
                    .into(holder.headerImage3);
        } else if (holder.type != TYPE_FOOTER) {
            holder.textTitle.setText(mData.get(position - 1).title);
            holder.textBrief.setText(mData.get(position - 1).brief);
            if (holder.type == 2 || holder.type == 3) {
                String imgURL = "file:///android_asset/" + mData.get(position - 1).image + ".jpg";
                Glide.with(mContext)
                        .load(imgURL)
                        .centerCrop()
                        .into(holder.imageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 2;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        int type;
        ImageView headerImage1, headerImage2, headerImage3;
        TextView textTitle, textBrief;
        ImageView imageView;

        public ItemViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            this.type = viewType;
            textTitle = itemView.findViewById(R.id.item_title);
            textBrief = itemView.findViewById(R.id.item_brief);
            if (viewType == 0) {
                headerImage1 = itemView.findViewById(R.id.header_image_1);
                headerImage2 = itemView.findViewById(R.id.header_image_2);
                headerImage3 = itemView.findViewById(R.id.header_image_3);
            }
            if (viewType == 2 || viewType == 3) {
                imageView = itemView.findViewById(R.id.item_image);
            }
        }
    }
}
