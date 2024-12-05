package com.antonowicz.sortowanie;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private EditText inputSize;
    private Button startButton;
    private ProgressBar progressBar;
    private SortingView sortingView;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler uiHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupStartButtonListener();
    }

    private void initializeViews() {
        inputSize = findViewById(R.id.inputSize);
        startButton = findViewById(R.id.startButton);
        progressBar = findViewById(R.id.progressBar);
        sortingView = findViewById(R.id.sortingView);
    }

    private void setupStartButtonListener() {
        startButton.setOnClickListener(v -> {
            String input = inputSize.getText().toString();
            if (input.isEmpty()) {
                showToast("Podaj ilość elementów");
                return;
            }

            try {
                int size = Integer.parseInt(input);
                if (size <= 0) {
                    showToast("Liczba musi być dodatnia i różna od zera");
                } else {
                    startSorting(size);
                }
            } catch (NumberFormatException e) {
                showToast("Wprowadź poprawną liczbę");
            }
        });
    }

    private void startSorting(int size) {
        int[] array = generateArray(size);
        progressBar.setProgress(0);
        sortingView.setArray(array);

        executorService.execute(() -> {
            performBubbleSort(array);
            uiHandler.post(() -> {
                showToast("Sortowanie zakończone!");
                progressBar.setProgress(100);
                sortingView.setArray(array);
            });
        });
    }

    private int[] generateArray(int size) {
        Random random = new Random();
        return random.ints(size, 1, 501).toArray();
    }

    private void performBubbleSort(int[] array) {
        int size = array.length;

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
            updateProgress(i, size, array);
            sleepThread(50);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void updateProgress(int currentStep, int totalSteps, int[] array) {
        int progress = (int) (((currentStep + 1) / (float) totalSteps) * 100);
        uiHandler.post(() -> {
            progressBar.setProgress(progress);
            sortingView.setArray(Arrays.copyOf(array, array.length));
        });
    }

    private void sleepThread(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
