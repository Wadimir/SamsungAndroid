package com.example.emailapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.ViewHolden> {

    private List<EmailItem> emailPreviewList;
    private EmailItemClicked callback;
    private Context context;


    public EmailAdapter(List<EmailItem> emailPreviewList, EmailItemClicked callback, Context context) {
        this.emailPreviewList = emailPreviewList;
        this.callback = callback;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolden onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycle, viewGroup, false);
        final ViewHolden holden = new ViewHolden(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holden.getAdapterPosition();

                if (adapterPosition!= RecyclerView.NO_POSITION && callback != null){
                    callback.itemClickedCallback(adapterPosition);
                }
            }
        });
        return holden;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolden viewHolden, int i) {
        final EmailItem item = emailPreviewList.get(i);

        if (item.getUserImg() != null && !item.getUserImg().isEmpty()) {
            Glide.with(context) // our context variable where we will draw our items
                    .load(item.getUserImg())
                    .into(viewHolden.iv_avatar);
        } else {
            String userImgDefault = "http://simpleicon.com/wp-content/uploads/user1.png";
            Glide.with(context)
                    .load(userImgDefault)
                    .into(viewHolden.iv_avatar);
        }

        if (item.getTitle() != null && !item.getTitle().isEmpty()) {
            viewHolden.tv_first.setText(item.getTitle());
        } else {
            viewHolden.tv_first.setText(R.string.user_name);
        }

        if (item.getSubtitle() != null && !item.getSubtitle().isEmpty()) {
            viewHolden.tv_email.setText(item.getSubtitle());
        } else {
            viewHolden.tv_email.setText(R.string.email_subject);
        }

        if (item.getContent() != null && !item.getContent().isEmpty()) {
            viewHolden.tv_second.setText(item.getContent());
        } else {
            viewHolden.tv_second.setText(R.string.email_compose);
        }

        if (item.getData() != null && !item.getData().isEmpty()) {
            viewHolden.tv_time.setText(item.getData());
        } else {
            viewHolden.tv_time.setText(R.string.email_date);
        }
    }

    @Override
    public int getItemCount() {
        if (emailPreviewList == null) return 0;
        return emailPreviewList.size();
    }

    interface EmailItemClicked {
        void itemClickedCallback(int itemPosition);

    }


    public class ViewHolden extends RecyclerView.ViewHolder {
        TextView tv_email, tv_first, tv_second, tv_time;
        ImageView iv_avatar;


        public ViewHolden(@NonNull View itemView) {
            super(itemView);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_first = itemView.findViewById(R.id.tv_first);
            tv_second = itemView.findViewById(R.id.tv_second);
            tv_time = itemView.findViewById(R.id.tv_time);
            iv_avatar = itemView.findViewById(R.id.iv_avatar);
        }
    }
}
