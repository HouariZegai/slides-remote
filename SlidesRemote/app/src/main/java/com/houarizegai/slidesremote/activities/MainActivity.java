package com.houarizegai.slidesremote.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.houarizegai.slidesremote.R;
import com.houarizegai.slidesremote.utils.RegexChecker;

public class MainActivity extends AppCompatActivity {

    EditText editServerAddress;
    CardView startBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editServerAddress = findViewById(R.id.editServerAddress);
        startBox = findViewById(R.id.startBox);

        Intent intent = getIntent();
        if(intent != null) {
            editServerAddress.setText(intent.getStringExtra("data"));
            startBox.setClickable(true);
        }

        startBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serverIP = String.valueOf(editServerAddress.getText());
                if(RegexChecker.isIP(serverIP)) {
                    Intent intent = new Intent(getApplicationContext(), RemoteControlActivity.class);
                    intent.putExtra("serverIP", serverIP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please Type the Server IP!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuItemConnect) {
            Intent intent = new Intent(this, QRScannerActivity.class);
            startActivity(intent);
        } else if(id == R.id.menuItemSettings) {
            Toast.makeText(this, "Comming Soon!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
