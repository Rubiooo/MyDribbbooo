package com.example.rubiooo.mydribbbooo.view.bucket_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rubioo.mydribbbooo.R;
import com.example.rubiooo.mydribbbooo.model.Bucket;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by Jimmy on 2018/1/25.
 */

public class BucketListAdapter extends RecyclerView.Adapter {

    private List<Bucket> data;

    public BucketListAdapter(List<Bucket> data) { this.data = data; }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_bucket, parent, false);
        return new BucketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Bucket bucket = data.get(position);
        // 0 -> 0 shot
        // 1 -> 1 shot;  if 0 or 1, "shot"; if 2+, "shots"
        // 2 -> 2 shots
        String bucketShotCountString = MessageFormat.format(
                holder.itemView.getContext().getResources().getString(R.string.shot_count),
                bucket.shots_count);

        BucketViewHolder bucketViewHolder = (BucketViewHolder) holder;
        bucketViewHolder.bucketName.setText(bucket.name);
        bucketViewHolder.bucketShotCount.setText(bucketShotCountString);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
