package com.antonowicz.okna;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditTaskActivity extends AppCompatActivity {

    private EditText taskTitleEditText;
    private EditText taskDescriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        taskTitleEditText = findViewById(R.id.taskTitleEditText);
        taskDescriptionEditText = findViewById(R.id.taskDescriptionEditText);
        Button saveButton = findViewById(R.id.saveButton);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        int position = intent.getIntExtra("position", -1);

        if (title != null) {
            taskTitleEditText.setText(title);
            taskDescriptionEditText.setText(description);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = taskTitleEditText.getText().toString();
                String newDescription = taskDescriptionEditText.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("title", newTitle);
                resultIntent.putExtra("description", newDescription);
                resultIntent.putExtra("position", position);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
