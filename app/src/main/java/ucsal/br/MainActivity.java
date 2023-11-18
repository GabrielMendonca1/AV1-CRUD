package ucsal.br;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button createButton, readButton, updateButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        createButton = findViewById(R.id.createButton);
        readButton = findViewById(R.id.readButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);


        createButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateActivity.class);
            startActivity(intent);
        });

        readButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReadActivity.class);
            startActivity(intent);
        });

        updateButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UpdateSelectionActivity.class);
            startActivity(intent);
        });

        deleteButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DeleteSelectionActivity.class);
            startActivity(intent);
        });


    }
}
