package armameeldopartimobile.controllers;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.armameeldopartimobile.R;

import java.util.ArrayList;
import java.util.List;

import armameeldopartimobile.models.enums.Position;
import armameeldopartimobile.utils.common.CommonFunctions;
import armameeldopartimobile.utils.common.Constants;

public class NamesInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_names_input);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });

        populatePositionsSpinner();
    }

    private void populatePositionsSpinner() {
        List<String> positions = new ArrayList<>();

        for (Position position : Position.values()) {
            positions.add(CommonFunctions.capitalize(Constants.MAP_POSITIONS.get(position)));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ((Spinner) findViewById(R.id.positions_spinner)).setAdapter(adapter);
    }
}