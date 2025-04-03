package armameeldopartimobile.utils.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.armameeldopartimobile.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import armameeldopartimobile.models.Player;
import armameeldopartimobile.models.Team;
import armameeldopartimobile.models.enums.Error;
import armameeldopartimobile.models.enums.Position;

/**
 * Common-use functions class.
 *
 * @since 1.0.0
 *
 * @author Bonino, Francisco Ignacio.
 *
 * @version 1.0.0
 */
public final class CommonFunctions {

    // ---------- Constructor -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Empty, private constructor.
     */
    private CommonFunctions() {
        // Body not needed
    }

    // ---------- Public methods ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Exits the program with the corresponding error message and error code according to the occurred exception.
     *
     * @param error The error that caused the program to end.
     */
    public static void exitProgram(Error error, Exception exception) {
        System.exit(Constants.MAP_ERROR_CODE.get(error));
    }

    /**
     * Shows a basic custom dialog with the given title and message.
     *
     * @param dialogTitle   The dialog title.
     * @param dialogMessage The dialog message.
     * @param context       The context for the dialog.
     */
    public static void showBasicBottomSheetDialog(String dialogTitle, String dialogMessage, Context context) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);

        View view = LayoutInflater.from(context).inflate(R.layout.basic_dialog_bottom_sheet, null);

        ((TextView) view.findViewById(R.id.dialogTitle)).setText(dialogTitle);
        ((TextView) view.findViewById(R.id.dialogMessage)).setText(dialogMessage);

        view.findViewById(R.id.okButton).setOnClickListener(v -> bottomSheetDialog.dismiss());

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    /**
     * @param teams Teams to calculate the skill difference.
     *
     * @return The difference between the skill points of the given teams.
     */
    public static int getTeamsSkillDifference(List<Team> teams) {
        return Math.abs(teams.get(0).getTeamSkill() - teams.get(1).getTeamSkill());
    }

    /**
     * Checks if the skill points of the given teams are equal.
     *
     * @param teams Teams to check if their skill points are equal.
     *
     * @return Whether the skill points of the given teams are equal.
     */
    public static boolean teamsSkillPointsAreEqual(List<Team> teams) {
        return getTeamsSkillDifference(teams) == 0;
    }

    /**
     * @param string The string to validate.
     *
     * @return Whether the given string contains only numbers.
     */
    public static boolean isNumericString(String string) {
        return Pattern.matches(Constants.REGEX_NUMERIC_STRING, string);
    }

    /**
     * @param string The string to validate.
     *
     * @return Whether the given string contains special characters.
     */
    public static boolean containsSpecialCharacters(String string) {
        return Pattern.matches(Constants.REGEX_SPECIAL_CHARACTERS, string);
    }

    /**
     * @param input The string to capitalize.
     *
     * @return The given string with the first letter uppercase and the rest lowercase.
     */
    public static String capitalize(String input) {
        return input.isBlank() ? input : (input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase());
    }

    /**
     * @return A list containing the anchored players grouped by their anchorage number.
     */
    public static List<List<Player>> getAnchorages() {
        return new ArrayList<>(CommonFields.getPlayersSets()
                                           .values()
                                           .stream()
                                           .flatMap(List::stream)
                                           .filter(Player::isAnchored)
                                           .collect(Collectors.groupingBy(Player::getAnchorageNumber))
                                           .values());
    }

    /**
     * Checks if an optional that should not be null has a value present. If so, that value is retrieved. If the optional has no value, then the program exits with a fatal internal error code.
     *
     * @param <T>      Generic optional type.
     * @param optional The optional to be checked.
     *
     * @return The optional value, if present.
     */
    public static <T> T retrieveOptional(Optional<T> optional) {
        if (optional.isEmpty()) {
            exitProgram(Error.ERROR_INTERNAL, new IllegalArgumentException(Constants.MSG_ERROR_NO_OPTIONAL_CONTENT));
        }

        return optional.get();
    }

    /**
     * Gets the search-corresponding position in a generic map received.
     *
     * @param <T>    Generic value type.
     * @param map    Generic map with positions as keys.
     * @param search Value to search in the map.
     *
     * @return The search-corresponding position.
     */
    public static <T> Position getCorrespondingPosition(Map<Position, T> map, T search) {
        return retrieveOptional(map.entrySet()
                                   .stream()
                                   .filter(entry -> entry.getValue().equals(search))
                                   .map(Map.Entry::getKey)
                                   .findFirst());
    }
}