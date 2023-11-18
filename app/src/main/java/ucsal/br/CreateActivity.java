package ucsal.br;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    EditText editTextName, editTextAge;
    Button saveButton, backButton; // Declare the backButton
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton); // Initialize the backButton

        databaseHelper = new DatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                int age = 0;
                try {
                    age = Integer.parseInt(editTextAge.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(CreateActivity.this, "Idade inválida", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (databaseHelper.insertData(name, age)) {
                    Toast.makeText(CreateActivity.this, "Dados inseridos com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateActivity.this, "Erro ao inserir dados", Toast.LENGTH_SHORT).show();
                }
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
}
