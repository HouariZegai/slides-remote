package com.houarizegai.slidesremote;

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
                Intent intent = new Intent(getApplicationContext(), RemoteControlActivity.class);
                startActivity(intent);
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
