package ucsal.br;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateSelectionActivity extends AppCompatActivity {

    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextAge;
    private Button buttonUpdate;
    private Button backButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_selection);

        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        backButton = findViewById(R.id.backButton);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        databaseHelper = new DatabaseHelper(this);


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close this activity
                finish();
            }
        });

    }

    private void updateData() {
        String id = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String ageString = editTextAge.getText().toString();

        if (!id.isEmpty() && !name.isEmpty() && !ageString.isEmpty()) {
            int age = Integer.parseInt(ageString);
            boolean result = databaseHelper.updateData(id, name, age);

            if (result) {
                Toast.makeText(this, "Data updated successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Update failed!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
        }
    }
}
