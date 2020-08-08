package com.houarizegai.slidesremote.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.houarizegai.slidesremote.R;

public class AboutActivity extends AppCompatActivity {

    private ClipboardManager clipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public void onCopyEmail(View view) {
        String email = getString(R.string.company_email);

        ClipData clip = ClipData.newPlainText("Email", email);
        clipboard.setPrimaryClip(clip);
    }

    public void onCopyPhone(View view) {
        String phone = getString(R.string.company_phone);

        ClipData clip = ClipData.newPlainText("Phone", phone);
        clipboard.setPrimaryClip(clip);
    }

    public void onVisitWebsite(View view) {
        String website = getString(R.string.company_website);

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
        startActivity(browserIntent);
    }
}
