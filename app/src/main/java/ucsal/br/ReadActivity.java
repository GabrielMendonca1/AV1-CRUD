package ucsal.br;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ReadActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseHelper databaseHelper;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        listView = findViewById(R.id.listView);
        backButton = findViewById(R.id.backButton); // Initialize the backButton
        databaseHelper = new DatabaseHelper(this);

        loadData();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close this activity
                finish();
            }
        });
    }
    private void loadData() {
        List<DatabaseHelper.MyTableEntry> dataList = databaseHelper.getAllData();
        ArrayAdapter<DatabaseHelper.MyTableEntry> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dataList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(); // Refresh data when the activity resumes
    }


}

