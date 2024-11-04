package com.antonowicz.asynctask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> items;
    private ArrayAdapter<String> adapter;
    private ListView itemList;
    private UpdateListTask updateListTask;

    private final ActivityResultLauncher<Intent> addItemLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String name = data.getStringExtra("name");
                    String price = data.getStringExtra("price");
                    String item = name + " - " + price + " PLN";
                    items.add(item);
                    adapter.notifyDataSetChanged();
                }
            }
    );

    private final ActivityResultLauncher<Intent> editItemLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String updatedName = data.getStringExtra("name");
                    String updatedPrice = data.getStringExtra("price");
                    int position = data.getIntExtra("position", -1);

                    if (position != -1) {
                        String updatedItem = updatedName + " - " + updatedPrice + " PLN";
                        items.set(position, updatedItem);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Error updating item", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemList = findViewById(R.id.item_list_view);
        itemList.setAdapter(adapter);

        Button addItemButton = findViewById(R.id.add_item_button);
        addItemButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            addItemLauncher.launch(intent);
        });

        itemList.setOnItemClickListener((parent, view, position, id) -> {
            String item = items.get(position);
            String[] parts = item.split(" - ");
            String name = parts[0];
            String price = parts[1].replace(" PLN", "");

            Intent editIntent = new Intent(MainActivity.this, EditItemActivity.class);
            editIntent.putExtra("name", name);
            editIntent.putExtra("price", price);
            editIntent.putExtra("position", position);
            editItemLauncher.launch(editIntent);
        });

        updateListTask = new UpdateListTask();
        updateListTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (updateListTask != null) {
            updateListTask.cancel(true);
        }
    }

    @SuppressLint("StaticFieldLeak")
    @SuppressWarnings("deprecation")
    private class UpdateListTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                while (!isCancelled()) {
                    Thread.sleep(5000);
                    publishProgress();
                }
            } catch (InterruptedException e) {
                Log.e("UpdateListTask", "Thread interrupted", e);
                Thread.currentThread().interrupt();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            adapter.notifyDataSetChanged();
        }
    }
}
