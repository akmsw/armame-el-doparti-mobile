package armameeldopartimobile.utils.common;

import java.util.List;
import java.util.Map;

import armameeldopartimobile.models.Player;
import armameeldopartimobile.models.enums.Distribution;
import armameeldopartimobile.models.enums.Position;

/**
 * Common-use fields class.
 *
 * @author Bonino, Francisco Ignacio.
 *
 * @version 1.0.0
 *
 * @since 1.0.0
 */
public final class CommonFields {

    // ---------- Private fields ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private static boolean anchoragesEnabled;

    private static Distribution distribution;

    private static Map<Position, Integer> playerLimitPerPosition;
    private static Map<Position, List<Player>> playersSets;

    // ---------- Constructor -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Empty, private constructor.
     */
    private CommonFields() {
        // Body not needed
    }

    // ---------- Getters -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static boolean isAnchoragesEnabled() {
        return anchoragesEnabled;
    }

    public static Distribution getDistribution() {
        return distribution;
    }

    public static Map<Position, Integer> getPlayerLimitPerPosition() {
        return playerLimitPerPosition;
    }

    public static Map<Position, List<Player>> getPlayersSets() {
        return playersSets;
    }

    // ---------- Setters -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void setAnchoragesEnabled(boolean anchoragesEnabled) {
        CommonFields.anchoragesEnabled = anchoragesEnabled;
    }

    public static void setDistribution(Distribution distribution) {
        CommonFields.distribution = distribution;
    }

    public static void setPlayerLimitPerPosition(Map<Position, Integer> playerLimitPerPosition) {
        CommonFields.playerLimitPerPosition = playerLimitPerPosition;
    }

    public static void setPlayersSets(Map<Position, List<Player>> playersSets) {
        CommonFields.playersSets = playersSets;
    }
}