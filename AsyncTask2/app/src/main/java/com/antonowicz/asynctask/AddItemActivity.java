package com.antonowicz.asynctask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    private EditText productNameInput, priceInput, descriptionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        productNameInput = findViewById(R.id.product_name_input);
        priceInput = findViewById(R.id.price_input);
        descriptionInput = findViewById(R.id.description_input);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> {
            String name = productNameInput.getText().toString().trim();
            String price = priceInput.getText().toString().trim();

            if (!name.isEmpty() && !price.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("price", price);
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(this, "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
