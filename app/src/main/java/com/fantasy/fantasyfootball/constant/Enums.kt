package com.fantasy.fantasyfootball.constant

class Enums {
    enum class FormSuccess {
        LOGIN_SUCCESSFUL,
        REGISTER_SUCCESSFUL,
        LOGOUT_SUCCESSFUL
    }

    enum class FormErrors {
        EMPTY_FIELD,
        WRONG_CREDENTIALS,
        MISSING_NAME,
        MISSING_TEAM_NAME,
        INVALID_USERNAME,
        INVALID_PASSWORD,
        PASSWORDS_NOT_MATCHING,
        LOGIN_UNSUCCESSFUL
    }

    enum class Result {
        REFRESH,
        EDIT_PROFILE_RESULT,
        EDIT_IMAGE_RESULT
    }

    enum class SortOrder {
        Ascending,
        Descending
    }

    enum class SortBy {
        Name,
        Price
    }

    enum class ShirtColor {
        DARKBLUE, DARKRED, LIGHTBLUE, LIGHTRED, ORANGE, TRANSLUCENT, TURQOISE, WHITE, YELLOW, PURPLE
    }

    enum class Position {
        GK, LB, LCB, RCB, RB, LM, LCM, RCM, RM, LS, RS
    }

    enum class Area {
        Goalkeeper, Defender, Midfielder, Striker
    }
}