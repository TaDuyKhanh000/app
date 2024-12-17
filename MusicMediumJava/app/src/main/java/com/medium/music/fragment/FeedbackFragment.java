package com.medium.music.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.medium.music.MyApplication;
import com.medium.music.R;
import com.medium.music.activity.MainActivity;
import com.medium.music.constant.GlobalFunction;
import com.medium.music.databinding.FragmentFeedbackBinding;
import com.medium.music.model.Feedback;
import com.medium.music.prefs.DataStoreManager;
import com.medium.music.utils.StringUtil;

public class FeedbackFragment extends Fragment {

    private FragmentFeedbackBinding mFragmentFeedbackBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
// tạo và liên kết giao diện từ XML
        mFragmentFeedbackBinding = FragmentFeedbackBinding.inflate(inflater, container, false);
// Điền sẵn địa chỉ email của người dùng đã đăng nhập được lấy từ DataStoreManager.getUser().getEmail()
        mFragmentFeedbackBinding.edtEmail.setText(DataStoreManager.getUser().getEmail());
// sự kiện lắng nghe khi người dùng gửi phản hồi sẽ gọi ra phương thức onClickSendFeedback
        mFragmentFeedbackBinding.tvSendFeedback.setOnClickListener(v -> onClickSendFeedback());

        return mFragmentFeedbackBinding.getRoot();
    }

    private void onClickSendFeedback() {
        if (getActivity() == null) {
            return;
        }
        MainActivity activity = (MainActivity) getActivity();
// Sử dụng getText().toString() để lấy thông tin từ các ô nhập
        String strName = mFragmentFeedbackBinding.edtName.getText().toString();
        String strPhone = mFragmentFeedbackBinding.edtPhone.getText().toString();
        String strEmail = mFragmentFeedbackBinding.edtEmail.getText().toString();
        String strComment = mFragmentFeedbackBinding.edtComment.getText().toString();
// Nếu tên hoặc nhận xét để trống, nó sẽ hiển thị thông báo yêu cầu người dùng nhập đầy đủ.
        if (StringUtil.isEmpty(strName)) {
            GlobalFunction.showToastMessage(activity, getString(R.string.name_require));
        } else if (StringUtil.isEmpty(strComment)) {
            GlobalFunction.showToastMessage(activity, getString(R.string.comment_require));
        } else {
            activity.showProgressDialog(true);
            Feedback feedback = new Feedback(strName, strPhone, strEmail, strComment);
            MyApplication.get(getActivity()).getFeedbackDatabaseReference()
                    .child(String.valueOf(System.currentTimeMillis()))
                    .setValue(feedback, (databaseError, databaseReference) -> {
                        activity.showProgressDialog(false);
                        sendFeedbackSuccess();
                    });
        }
    }

    //Được gọi sau khi phản hồi được gửi thành công.
    //
    public void sendFeedbackSuccess() {
// Ẩn bàn phím.
        GlobalFunction.hideSoftKeyboard(getActivity());
//Hiển thị thông báo "Phản hồi đã gửi thành công"
        GlobalFunction.showToastMessage(getActivity(), getString(R.string.msg_send_feedback_success));
//Xóa dữ liệu đã nhập (tên, SĐT, comment) để làm trống các trường sau khi gửi phản hồi thành công.
        mFragmentFeedbackBinding.edtName.setText("");
        mFragmentFeedbackBinding.edtPhone.setText("");
        mFragmentFeedbackBinding.edtComment.setText("");
    }
}
