package com.medium.music.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.medium.music.databinding.ItemFeedbackBinding;
import com.medium.music.model.Feedback;

import java.util.List;

public class AdminFeedbackAdapter extends RecyclerView.Adapter<AdminFeedbackAdapter.AdminFeedbackViewHolder> {

//    danh sach doi tuong kieu feedback goi la mListfeedback
    private final List<Feedback> mListFeedback;

    public AdminFeedbackAdapter(List<Feedback> mListFeedback) {
        this.mListFeedback = mListFeedback;
    }


//    tạo mới một ViewHolder. Một ViewHolder đại diện cho một mục (item) trong danh sách.
    @NonNull
    @Override
    public AdminFeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedbackBinding itemFeedbackBinding = ItemFeedbackBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new AdminFeedbackViewHolder(itemFeedbackBinding);
    }


//    lấy dư liệu phản hồi trong danh sách ra bằng mListFeedback.get(position).
//    Kiểm tra đối tượng nếu là null thì k thực hiện bất kỳ thao tác nào
//    tvEmail: TextView để hiển thị email của người phản hồi.
//    tvFeedback: TextView để hiển thị nội dung phản hồi của người dùng
    @Override
    public void onBindViewHolder(@NonNull AdminFeedbackViewHolder holder, int position) {
        Feedback feedback = mListFeedback.get(position);
        if (feedback == null) {
            return;
        }
        holder.mItemFeedbackBinding.tvEmail.setText(feedback.getEmail());
        holder.mItemFeedbackBinding.tvFeedback.setText(feedback.getComment());
    }


//    trả về số lượng phần tử trong danh sách, nếu null trả về 0

    @Override
    public int getItemCount() {
        if (mListFeedback != null) {
            return mListFeedback.size();
        }
        return 0;
    }

    public static class AdminFeedbackViewHolder extends RecyclerView.ViewHolder {

        private final ItemFeedbackBinding mItemFeedbackBinding;

        public AdminFeedbackViewHolder(@NonNull ItemFeedbackBinding itemFeedbackBinding) {
            super(itemFeedbackBinding.getRoot());
            this.mItemFeedbackBinding = itemFeedbackBinding;
        }
    }
}
