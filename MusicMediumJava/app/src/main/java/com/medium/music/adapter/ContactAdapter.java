package com.medium.music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.medium.music.R;
import com.medium.music.constant.GlobalFunction;
import com.medium.music.databinding.ItemContactBinding;
import com.medium.music.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context context;
    private final List<Contact> listContact;
    private final ICallPhone iCallPhone;
// cho phép xử lý hành động gọi điện khi nhấn vào liên hệ hotline.
    public interface ICallPhone {
        void onClickCallPhone();
    }
// Khởi tạo adapter với listContact, context, iCallPhone
    public ContactAdapter(Context context, List<Contact> listContact, ICallPhone iCallPhone) {
        this.context = context;
        this.listContact = listContact;
        this.iCallPhone = iCallPhone;
    }


//    liên kết dữ liệu của đối tượng Contact với các thành phần giao diện tương ứng (TextView, ImageView).
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContactBinding itemContactBinding = ItemContactBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ContactViewHolder(itemContactBinding);
    }


//    Nó xử lý việc hiển thị hình ảnh và tên của các liên hệ (Facebook, Gmail, Skype, v.v.)
//    cung cấp khả năng tương tác với các liên hệ đó (gọi điện, mở trang Facebook, gửi email, v.v.).
    @Override

    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = listContact.get(position);
        if (contact == null) {
            return;
        }
        //Gán tên và xử lý sự kiện tương ứng với từng loại liên lạc
        holder.mItemContactBinding.imgContact.setImageResource(contact.getImage());
        switch (contact.getId()) {
            case Contact.FACEBOOK:
                holder.mItemContactBinding.tvContact.setText(context.getString(R.string.label_facebook));
                break;

            case Contact.HOTLINE:
                holder.mItemContactBinding.tvContact.setText(context.getString(R.string.label_call));
                break;

            case Contact.GMAIL:
                holder.mItemContactBinding.tvContact.setText(context.getString(R.string.label_gmail));
                break;

            case Contact.SKYPE:
                holder.mItemContactBinding.tvContact.setText(context.getString(R.string.label_skype));
                break;

            case Contact.YOUTUBE:
                holder.mItemContactBinding.tvContact.setText(context.getString(R.string.label_youtube));
                break;

            case Contact.ZALO:
                holder.mItemContactBinding.tvContact.setText(context.getString(R.string.label_zalo));
                break;
        }
//Xử lý click vào từng loại liên lạc
        holder.mItemContactBinding.layoutItem.setOnClickListener(v -> {
            switch (contact.getId()) {
                case Contact.FACEBOOK:
                    GlobalFunction.onClickOpenFacebook(context);
                    break;

                case Contact.HOTLINE:
                    iCallPhone.onClickCallPhone();
                    break;

                case Contact.GMAIL:
                    GlobalFunction.onClickOpenGmail(context);
                    break;

                case Contact.SKYPE:
                    GlobalFunction.onClickOpenSkype(context);
                    break;

                case Contact.YOUTUBE:
                    GlobalFunction.onClickOpenYoutubeChannel(context);
                    break;

                case Contact.ZALO:
                    GlobalFunction.onClickOpenZalo(context);
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == listContact ? 0 : listContact.size();
    }
//Giải phóng context
    public void release() {
        context = null;
    }
// Lớp này giữ tham chiếu đến các thành phần giao diện của từng item trong RecyclerView
    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        private final ItemContactBinding mItemContactBinding;
        public ContactViewHolder(ItemContactBinding itemContactBinding) {
            super(itemContactBinding.getRoot());
            this.mItemContactBinding = itemContactBinding;
        }
    }
}