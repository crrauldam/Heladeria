package com.crraul.heladeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Actividad2 extends AppCompatActivity {

    private String v, c, s, container;
    private LinearLayout generatedIceCream;
    private Button close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        v = intent.getStringExtra("v"); // vanilla amount
        c = intent.getStringExtra("c"); // chocolate amount
        s = intent.getStringExtra("s"); // strawberry amount
        container = intent.getStringExtra("container"); // container for the ice cream

        close = findViewById(R.id.close);
        generatedIceCream = findViewById(R.id.generatedIceCream);

        // add vanilla balls
        try {
            for (int i = 0; i < Integer.parseInt(v); i++) {
                addIceCreamBall(getResources().getColor(R.color.vanilla, null));
            }
        } catch (Exception ignore){}


        // add chocolate balls
        try {
            for (int i = 0; i < Integer.parseInt(c); i++) {
                addIceCreamBall(getResources().getColor(R.color.chocolate, null));
            }
        } catch (Exception ignore){}


        // add strawberry balls
        try {
            for (int i = 0; i < Integer.parseInt(s); i++) {
                addIceCreamBall(getResources().getColor(R.color.strawberry, null));
            }
        } catch (Exception ignore){}

        switch (container) {
            case "Cucurucho":
                addContainer("\\/", getResources().getColor(R.color.cone, null));
                break;
            case "Cucu. choco":
                addContainer("\\/", getResources().getColor(R.color.coneC, null));
                break;
            case "Tarrina":
                addContainer("U", getResources().getColor(R.color.black, null));
                break;
        }

        close.setOnClickListener(view -> {
            finish();
        });
    }

    public void addIceCreamBall(int color) {
        TextView newIceCreamBall = new TextView(this);
        newIceCreamBall.setGravity(Gravity.CENTER_HORIZONTAL);
        newIceCreamBall.setText("O");
        newIceCreamBall.setTextColor(color);
        newIceCreamBall.setTextSize(20);
        generatedIceCream.addView(newIceCreamBall);
    }

    public void addContainer(String containerSymbol, int color) {
        TextView container = new TextView(this);
        container.setGravity(Gravity.CENTER_HORIZONTAL);
        container.setText(containerSymbol);
        container.setTextColor(color);
        container.setTextSize(20);
        generatedIceCream.addView(container);
    }
}