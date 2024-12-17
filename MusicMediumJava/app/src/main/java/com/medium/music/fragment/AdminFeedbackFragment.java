package com.medium.music.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.medium.music.MyApplication;
import com.medium.music.adapter.AdminFeedbackAdapter;
import com.medium.music.databinding.FragmentAdminFeedbackBinding;
import com.medium.music.model.Feedback;

import java.util.ArrayList;
import java.util.List;

public class AdminFeedbackFragment extends Fragment {

    private FragmentAdminFeedbackBinding mFragmentAdminFeedbackBinding;
    private List<Feedback> mListFeedback;
    private AdminFeedbackAdapter mFeedbackAdapter;
//Tạo và hiển thị giao diện cho AdminFeedbackFragment.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentAdminFeedbackBinding = FragmentAdminFeedbackBinding.inflate(inflater, container, false);
        initView();
        getListFeedback();
        return mFragmentAdminFeedbackBinding.getRoot();
    }
//Thiết lập giao diện ban đầu cho RecyclerView
    private void initView() {
        if (getActivity() == null) return;
//Quản lý cách hiển thị các phần tử trong RecyclerView theo dạng danh sách dọc.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mFragmentAdminFeedbackBinding.rcvFeedback.setLayoutManager(linearLayoutManager);
    }
// lấy danh sách phản hồi từ Firebase và cập nhật dữ liệu lên RecyclerView
    public void getListFeedback() {
        if (getActivity() == null) return;
// Lấy tham chiếu đến cơ sở dữ liệu, sự kiện lắng nghe addValueEventListener Mỗi khi có thay đổi phương thức onDataChange được gọi
        MyApplication.get(getActivity()).getFeedbackDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
            //làm trống trước khi thêm dữ liệu mới.
                        if (mListFeedback != null) {
                            mListFeedback.clear();
                        } else {
                            mListFeedback = new ArrayList<>();
                        }
            //Lặp qua từng phản hồi trong cơ sở dữ liệu.
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Feedback feedback = dataSnapshot.getValue(Feedback.class);
            //Thêm phản hồi mới vào danh sách. Phản hồi mới nhất được thêm vào đầu danh sách.
                            if (feedback != null) {
                                mListFeedback.add(0, feedback);
                            }
                        }
            // Tạo mới adapter với danh sách phản hồi, Gán adapter cho RecyclerView để hiển thị danh sách phản hồi.
                        mFeedbackAdapter = new AdminFeedbackAdapter(mListFeedback);
                        mFragmentAdminFeedbackBinding.rcvFeedback.setAdapter(mFeedbackAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }
}
