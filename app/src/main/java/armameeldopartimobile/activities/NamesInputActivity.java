package armameeldopartimobile.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import armameeldopartimobile.models.Player;
import armameeldopartimobile.models.enums.Position;
import armameeldopartimobile.utils.common.CommonFields;
import armameeldopartimobile.utils.common.CommonFunctions;
import armameeldopartimobile.utils.common.Constants;

import com.example.armameeldopartimobile.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class NamesInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_names_input);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });

        (findViewById(R.id.back_button)).setOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
    }

    public void launchNextActivity(View view) {
        savePlayersNames();

        if (!playerNamesAreValid()) {
            return;
        }

        showDistributionConfigDialog();
    }

    private void savePlayersNames() {
        List<Player> centralDefenders = CommonFields.getPlayersSets().get(Position.CENTRAL_DEFENDER);
        List<Player> lateralDefenders = CommonFields.getPlayersSets().get(Position.LATERAL_DEFENDER);
        List<Player> midfielders = CommonFields.getPlayersSets().get(Position.MIDFIELDER);
        List<Player> forwards = CommonFields.getPlayersSets().get(Position.FORWARD);
        List<Player> goalkeepers = CommonFields.getPlayersSets().get(Position.GOALKEEPER);

        centralDefenders.get(0).setName(((EditText) findViewById(R.id.edit_text_cd_1)).getText().toString().trim());
        centralDefenders.get(1).setName(((EditText) findViewById(R.id.edit_text_cd_2)).getText().toString().trim());

        lateralDefenders.get(0).setName(((EditText) findViewById(R.id.edit_text_ld_1)).getText().toString().trim());
        lateralDefenders.get(1).setName(((EditText) findViewById(R.id.edit_text_ld_2)).getText().toString().trim());
        lateralDefenders.get(2).setName(((EditText) findViewById(R.id.edit_text_ld_3)).getText().toString().trim());
        lateralDefenders.get(3).setName(((EditText) findViewById(R.id.edit_text_ld_4)).getText().toString().trim());

        midfielders.get(0).setName(((EditText) findViewById(R.id.edit_text_mf_1)).getText().toString().trim());
        midfielders.get(1).setName(((EditText) findViewById(R.id.edit_text_mf_2)).getText().toString().trim());
        midfielders.get(2).setName(((EditText) findViewById(R.id.edit_text_mf_3)).getText().toString().trim());
        midfielders.get(3).setName(((EditText) findViewById(R.id.edit_text_mf_4)).getText().toString().trim());

        forwards.get(0).setName(((EditText) findViewById(R.id.edit_text_fw_1)).getText().toString().trim());
        forwards.get(1).setName(((EditText) findViewById(R.id.edit_text_fw_2)).getText().toString().trim());

        goalkeepers.get(0).setName(((EditText) findViewById(R.id.edit_text_gk_1)).getText().toString().trim());
        goalkeepers.get(1).setName(((EditText) findViewById(R.id.edit_text_gk_2)).getText().toString().trim());
    }

    /**
     * Validates the player names given that they cannot be blank, contain only numbers or be repeated.
     *
     * @return Whether the player names are valid.
     */
    private boolean playerNamesAreValid() {
        List<Player> players = CommonFields.getPlayersSets().values().stream().flatMap(List::stream).collect(Collectors.toList());

        if (players.stream().anyMatch(player -> player.getName().equalsIgnoreCase(Constants.PLAYER_NO_NAME_ASSIGNED))) {
            CommonFunctions.showBasicBottomSheetDialog(getResources().getString(R.string.title_dialog_warning), getResources().getString(R.string.dialog_name_empty), this);

            return false;
        }

        String dialogMessage = "";

        for (Player player : players) {
            String playerName = player.getName();

            if (CommonFunctions.isNumericString(playerName)) {
                dialogMessage = getResources().getString(R.string.dialog_name_numeric_string);

                break;
            }

            if (CommonFunctions.containsSpecialCharacters(playerName)) {
                dialogMessage = getResources().getString(R.string.dialog_name_special_characters);

                break;
            }

            if (nameAlreadyExists(playerName)) {
                dialogMessage = getResources().getString(R.string.dialog_name_already_exists);

                break;
            }
        }

        if (!dialogMessage.isEmpty()) {
            CommonFunctions.showBasicBottomSheetDialog(getResources().getString(R.string.title_dialog_warning), dialogMessage, this);

            return false;
        }

        return true;
    }

    /**
     * @param name Name to validate.
     *
     * @return Whether there is already a player with the specified name.
     */
    public static boolean nameAlreadyExists(String name) {
        return CommonFields.getPlayersSets()
                           .values()
                           .stream()
                           .flatMap(Collection::stream)
                           .filter(player -> player.getName().equalsIgnoreCase(name)).count() > 1;
    }

    private void showDistributionConfigDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        View view = LayoutInflater.from(this).inflate(R.layout.distribution_settings_dialog_bottom_sheet, null);

        ((TextView) view.findViewById(R.id.dialogTitle)).setText(R.string.title_dialog_distribution_settings);
        ((TextView) view.findViewById(R.id.dialogMessage)).setText(R.string.description_dialog_distribution_settings);

        view.findViewById(R.id.okButton).setOnClickListener(v -> bottomSheetDialog.dismiss());

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }
}