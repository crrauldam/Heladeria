package com.crraul.heladeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Actividad1 extends AppCompatActivity {
    private EditText vanilla;
    private EditText chocolate;
    private EditText strawberry;
    private Button generate;
    private Spinner spinner;
    private Boolean userInteracting = false;
    private String selectedContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userInteracting = true;
        vanilla = findViewById(R.id.vanilla);
        chocolate = findViewById(R.id.chocolate);
        strawberry = findViewById(R.id.strawberry);
        generate = findViewById(R.id.generate);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones_spinner, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!userInteracting) {return;}
                if (i != 0) {
                    selectedContainer = adapterView.getItemAtPosition(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        generate.setOnClickListener(view -> {
            if (selectedContainer != null) {
                Intent intent = new Intent(this, Actividad2.class);
                intent.putExtra("v", vanilla.getText().toString());
                intent.putExtra("c", chocolate.getText().toString());
                intent.putExtra("s", strawberry.getText().toString());
                intent.putExtra("container", selectedContainer);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Selecciona un recipiente para el helado.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}