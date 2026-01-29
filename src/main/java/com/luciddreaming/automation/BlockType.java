package com.luciddreaming.automation;

/**
 * 积木类型枚举
 */
public enum BlockType {
    // 事件积木（触发器）
    EVENT_SCOREBOARD_CHANGED("event_scoreboard_changed"),
    EVENT_SCOREBOARD_CHANGED_FROM_TO("event_scoreboard_changed_from_to"),
    EVENT_HEALTH_CHANGED("event_health_changed"),
    EVENT_HEALTH_CHANGED_FROM_TO("event_health_changed_from_to"),
    EVENT_ENTITY_NEARBY("event_entity_nearby"),

    // 运动积木
    MOTION_FACE_DIRECTION("motion_face_direction"),
    MOTION_TURN_LEFT("motion_turn_left"),
    MOTION_TURN_RIGHT("motion_turn_right"),
    MOTION_MOVE_FORWARD("motion_move_forward"),
    MOTION_MOVE_BACKWARD("motion_move_backward"),
    MOTION_MOVE_LEFT("motion_move_left"),
    MOTION_MOVE_RIGHT("motion_move_right"),
    MOTION_MOVE_TO("motion_move_to"),
    MOTION_INTERACT("motion_interact"),
    MOTION_INTERACT_NEAREST("motion_interact_nearest"),

    // 侦测积木
    SENSING_SCOREBOARD_CHANGED("sensing_scoreboard_changed"),
    SENSING_SCOREBOARD_CHANGED_FROM_TO("sensing_scoreboard_changed_from_to"),
    SENSING_HEALTH_CHANGED("sensing_health_changed"),
    SENSING_HEALTH_CHANGED_FROM_TO("sensing_health_changed_from_to"),
    SENSING_ENTITY_NEARBY("sensing_entity_nearby"),
    SENSING_GET_HEALTH("sensing_get_health"),
    SENSING_GET_SCOREBOARD_VALUE("sensing_get_scoreboard_value"),
    SENSING_GET_PLAYER_POS("sensing_get_player_pos"),
    SENSING_GET_PLAYER_YAW("sensing_get_player_yaw"),
    SENSING_GET_PLAYER_PITCH("sensing_get_player_pitch"),

    // 控制积木
    CONTROL_WAIT("control_wait"),
    CONTROL_IF("control_if"),
    CONTROL_IF_ELSE("control_if_else"),
    CONTROL_REPEAT("control_repeat"),
    CONTROL_FOREVER("control_forever"),
    CONTROL_STOP("control_stop"),
    CONTROL_COMMENT("control_comment");

    private final String value;

    BlockType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BlockType fromValue(String value) {
        for (BlockType type : BlockType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return value;
    }
}