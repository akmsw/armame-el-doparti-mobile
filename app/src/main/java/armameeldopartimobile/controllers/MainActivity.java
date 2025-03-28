package armameeldopartimobile.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import armameeldopartimobile.models.Player;
import armameeldopartimobile.models.enums.Position;
import armameeldopartimobile.utils.common.CommonFields;
import armameeldopartimobile.utils.common.Constants;

import com.example.armameeldopartimobile.R;

import java.util.EnumMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    // ---------- Public methods ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Launches the activity that allows the user to input the players names.
     *
     * @param v Intentionally unused view.
     */
    public void launchNamesInputActivity(View v) {
        startActivity(new Intent(this, NamesInputActivity.class));
    }

    // ---------- Protected methods -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });

        initializeCommonMaps();
    }

    // ---------- Private methods ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Initializes common maps.
     */
    private void initializeCommonMaps() {
        CommonFields.setPlayerLimitPerPosition(new EnumMap<>(Position.class));
        CommonFields.setPlayersSets(new TreeMap<>());

        setPlayersDistribution();
        populatePlayersSets();
    }

    private void setPlayersDistribution() {
        CommonFields.getPlayerLimitPerPosition().put(Position.CENTRAL_DEFENDER, 1);
        CommonFields.getPlayerLimitPerPosition().put(Position.LATERAL_DEFENDER, 2);
        CommonFields.getPlayerLimitPerPosition().put(Position.MIDFIELDER, 2);
        CommonFields.getPlayerLimitPerPosition().put(Position.FORWARD, 1);
        CommonFields.getPlayerLimitPerPosition().put(Position.GOALKEEPER, 1);
    }

    /**
     * Populates the players sets with empty players.
     */
    private void populatePlayersSets() {
        for (Position position : Position.values()) {
            CommonFields.getPlayersSets()
                        .put(position, IntStream.range(0, CommonFields.getPlayerLimitPerPosition().get(position) * 2)
                                                .mapToObj(i -> new Player(Constants.PLAYER_NO_NAME_ASSIGNED, position))
                                                .collect(Collectors.toList()));
        }
    }
}