package com.medium.music.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.medium.music.R;
import com.medium.music.adapter.ContactAdapter;
import com.medium.music.constant.AboutUsConfig;
import com.medium.music.constant.GlobalFunction;
import com.medium.music.databinding.FragmentContactBinding;
import com.medium.music.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment {

    private FragmentContactBinding mFragmentContactBinding;
    private ContactAdapter mContactAdapter;

    @Nullable
    @Override
    //Sử dụng FragmentContactBinding để liên kết các thành phần giao diện từ file XML tránh việc phải gọi findViewById
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentContactBinding = FragmentContactBinding.inflate(inflater, container, false);
        initUi();
        initListener();
        return mFragmentContactBinding.getRoot();
    }

    private void initUi() {
//Gán các thông tin như tiêu đề, nội dung và website từ các hằng số có sẵn trong lớp AboutUsConfig vào các thành phần giao diện tương ứng
        mFragmentContactBinding.tvAboutUsTitle.setText(AboutUsConfig.ABOUT_US_TITLE);
        mFragmentContactBinding.tvAboutUsContent.setText(AboutUsConfig.ABOUT_US_CONTENT);
        mFragmentContactBinding.tvAboutUsWebsite.setText(AboutUsConfig.ABOUT_US_WEBSITE_TITLE);
//Một danh sách các liên hệ (social media, hotline, email, v.v.) được hiển thị dưới dạng GridLayout ( dạng lưới 3 cột)
        mContactAdapter = new ContactAdapter(getActivity(), getListContact(),
                () -> GlobalFunction.callPhoneNumber(getActivity()));
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
// Sử dụng RecyclerView để hiển thị danh sách các liên hệ
        mFragmentContactBinding.rcvData.setNestedScrollingEnabled(false);
        mFragmentContactBinding.rcvData.setFocusable(false);
        mFragmentContactBinding.rcvData.setLayoutManager(layoutManager);
        mFragmentContactBinding.rcvData.setAdapter(mContactAdapter);
    }
// Phương thức này gắn các Listener cho các sự kiện click trong giao diện
    private void initListener() {
        mFragmentContactBinding.layoutWebsite.setOnClickListener(view
                -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(AboutUsConfig.WEBSITE))));
    }
//Phương thức này trả về một danh sách các đối tượng Contact: id, image
    public List<Contact> getListContact() {
        List<Contact> contactArrayList = new ArrayList<>();
        contactArrayList.add(new Contact(Contact.FACEBOOK, R.drawable.ic_facebook));
        contactArrayList.add(new Contact(Contact.HOTLINE, R.drawable.ic_hotline));
        contactArrayList.add(new Contact(Contact.GMAIL, R.drawable.ic_gmail));
        contactArrayList.add(new Contact(Contact.SKYPE, R.drawable.ic_skype));
        contactArrayList.add(new Contact(Contact.YOUTUBE, R.drawable.ic_youtube));
        contactArrayList.add(new Contact(Contact.ZALO, R.drawable.ic_zalo));

        return contactArrayList;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContactAdapter.release();
    }
}
