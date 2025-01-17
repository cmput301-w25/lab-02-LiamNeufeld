package com.example.lab2_301;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Local variables for UI components
        EditText editText = findViewById(R.id.editText1);
        Button addButton = findViewById(R.id.button1);
        Button deleteButton = findViewById(R.id.buttonDelete);
        ListView cityList = findViewById(R.id.city_list);

        // Local variables for data
        ArrayList<String> dataList = new ArrayList<>();
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, dataList);
        cityList.setAdapter(cityAdapter);

        // Add city when addButton is clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editText.getText().toString().trim();
                if (!input.isEmpty()) {
                    dataList.add(input);
                    cityAdapter.notifyDataSetChanged();
                    editText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a valid city name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Delete selected cities when deleteButton is clicked
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the checked item positions
                int itemCount = cityList.getCount();
                for (int i = itemCount - 1; i >= 0; i--) {
                    if (cityList.isItemChecked(i)) {
                        dataList.remove(i);
                    }
                }
                // Refresh the ListView and clear selection
                cityAdapter.notifyDataSetChanged();
                cityList.clearChoices();
            }
        });
    }
}
