package com.CRUD_Agriculture.CRUD_Agriculture.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SeasonStatus {
    GROWING("Đang gieo trồng"),
    NEAR_HARVEST("Sắp thu hoạch"),
    COMPLETED("Đã hoàn thành");

    private final String description;

    SeasonStatus(String description) {
        this.description = description;
    }

    // Khi serialize, trả về description
    @JsonValue
    public String getDescription() {
        return description;
    }

    // Khi deserialize, chấp nhận cả name của enum hoặc description
    @JsonCreator
    public static SeasonStatus fromValue(String value) {
        // Thử tìm theo tên enum trước
        try {
            return SeasonStatus.valueOf(value);
        } catch (IllegalArgumentException e) {
            // Nếu không tìm thấy theo tên, tìm theo description
            for (SeasonStatus status : SeasonStatus.values()) {
                if (status.getDescription().equalsIgnoreCase(value)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Unknown status: " + value);
        }
    }
}