package com.example.droidcafeinput;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        // Get the intent and its data.
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(message);

        // Create the spinner.
        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        // Create an ArrayAdapter using the string array and default spinner
        // layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.labels_array,
                android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        Context View = view.getContext();
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked.
        if(view.getId() == R.id.sameday && checked)
            displayToast(getString(R.string.same_day_messenger_service));
        else if (view.getId() == R.id.nextday && checked) {
            displayToast(getString(R.string.next_day_ground_delivery));
        } else if (view.getId() == R.id.pickup && checked) {
            displayToast(getString(R.string.pick_up));
        }

    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    // Interface callback for when any spinner item is selected.
    @Override
    public void onItemSelected(AdapterView<?> adapterView,
                               View view, int i, long l) {
        String spinnerLabel = adapterView.getItemAtPosition(i).toString();
        displayToast(spinnerLabel);
    }

    // Interface callback for when no spinner item is selected.
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Do nothing.
    }
}