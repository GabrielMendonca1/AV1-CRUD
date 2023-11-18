package ucsal.br;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteSelectionActivity extends AppCompatActivity {

    private EditText editTextId;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_selection);

        editTextId = findViewById(R.id.editTextId);
        databaseHelper = new DatabaseHelper(this);

        // Initialize and set the delete button
        Button buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEntry();
            }
        });

        // Initialize and set the back button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity
            }
        });
    }

    private void deleteEntry() {
        String id = editTextId.getText().toString();
        if (!id.isEmpty()) {
            int result = databaseHelper.deleteData(id);
            if (result > 0) {
                Toast.makeText(DeleteSelectionActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DeleteSelectionActivity.this, "Deletion Failed", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(DeleteSelectionActivity.this, "Please enter an ID", Toast.LENGTH_SHORT).show();
        }
    }
}
