package armameeldopartimobile.utils.common;

import armameeldopartimobile.models.enums.Error;
import armameeldopartimobile.models.enums.Position;

import java.util.Map;

/**
 * Common-use constants class.
 *
 * @author Bonino, Francisco Ignacio.
 *
 * @version 1.0.0
 *
 * @since 1.0.0
 */
public class Constants {

    // ---------- Private constants -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private static final int EXIT_CODE_ERROR_BROWSER = -1;
    private static final int EXIT_CODE_ERROR_GUI = -2;
    private static final int EXIT_CODE_ERROR_INTERNAL = -3;
    private static final int EXIT_CODE_ERROR_FILES = -4;

    // ---------- Public constants --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static final int PLAYER_NO_ANCHORAGE_ASSIGNED = 0;
    public static final int PLAYER_NO_SKILL_POINTS_ASSIGNED = 0;
    public static final int PLAYER_NO_TEAM_ASSIGNED = 0;

    public static final String MSG_ERROR_NO_OPTIONAL_CONTENT = "No available content to retrieve in Optional object.";
    public static final String PLAYER_NO_NAME_ASSIGNED = "";
    public static final String POSITION_CENTRAL_DEFENDERS = "DEFENSORES CENTRALES";
    public static final String POSITION_FORWARDS = "DELANTEROS";
    public static final String POSITION_GOALKEEPERS = "ARQUEROS";
    public static final String POSITION_LATERAL_DEFENDERS = "DEFENSORES LATERALES";
    public static final String POSITION_MIDFIELDERS = "MEDIOCAMPISTAS";

    /**
     * Positions to show in the names input view spinner.
     */
    public static final Map<Position, String> MAP_POSITIONS = Map.of(
        Position.CENTRAL_DEFENDER, POSITION_CENTRAL_DEFENDERS,
        Position.LATERAL_DEFENDER, POSITION_LATERAL_DEFENDERS,
        Position.MIDFIELDER, POSITION_MIDFIELDERS,
        Position.FORWARD, POSITION_FORWARDS,
        Position.GOALKEEPER, POSITION_GOALKEEPERS
    );

    /**
     * Map of errors and their corresponding exit code.
     */
    public static final Map<Error, Integer> MAP_ERROR_CODE = Map.of(
        Error.ERROR_BROWSER, EXIT_CODE_ERROR_BROWSER,
        Error.ERROR_FILES, EXIT_CODE_ERROR_FILES,
        Error.ERROR_GUI, EXIT_CODE_ERROR_GUI,
        Error.ERROR_INTERNAL, EXIT_CODE_ERROR_INTERNAL
    );
}