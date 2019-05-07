package com.assign.pratilipi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ContactDetaisActivity extends AppCompatActivity {

    private Contact contact = null;

    private TextView mName, mEmail, mMobile;
    private ImageView mNameIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detais);
        initViews();
        if (getIntent() != null && getIntent().getSerializableExtra("contact_details") != null) {
            contact = (Contact) getIntent().getSerializableExtra("contact_details");
            if (contact != null)
                updateUI(contact);
            else showToast(1);
        }


    }

    private void updateUI(Contact contact) {
        setData(contact.getContactName(), mName,2);
        setData(contact.getContactNumber(), mMobile,3);
        setData(contact.getEmail(), mEmail,4);
        if (contact.getPhotoUri() != null)
            Glide.with(this)
                    .load(contact.getPhotoUri())
                    .apply(RequestOptions.circleCropTransform())
                    .into(mNameIv);
    }

    private void setData(String value, TextView tv,int i) {
        Log.d("ContactDetails", "value is" + value);
        if (value != null)
            tv.setText(value);
    }

    private void showToast(int i) {
        Toast.makeText(this, "Something went wrong!"+i+" is null", Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mMobile = findViewById(R.id.mobile);
        mNameIv = findViewById(R.id.image);
    }
}
