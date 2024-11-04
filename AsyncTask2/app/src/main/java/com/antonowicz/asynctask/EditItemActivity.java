package com.antonowicz.asynctask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditItemActivity extends AppCompatActivity {

    private EditText productNameInput, priceInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        productNameInput = findViewById(R.id.product_name_input);
        priceInput = findViewById(R.id.price_input);

        // Retrieve data from the intent
        Intent intent = getIntent();
        String currentName = intent.getStringExtra("name");
        String currentPrice = intent.getStringExtra("price");

        // Set current item data
        productNameInput.setText(currentName);
        priceInput.setText(currentPrice);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> {
            String updatedName = productNameInput.getText().toString().trim();
            String updatedPrice = priceInput.getText().toString().trim();

            if (!updatedName.isEmpty() && !updatedPrice.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", updatedName);
                resultIntent.putExtra("price", updatedPrice);
                resultIntent.putExtra("position", intent.getIntExtra("position", -1));
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
