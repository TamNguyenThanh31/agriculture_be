//package com.CRUD_Agriculture.CRUD_Agriculture.entity.enums;
//
//public enum SeasonStatus {
//    GROWING("Đang gieo trồng"),
//    NEAR_HARVEST("Sắp thu hoạch"),
//    COMPLETED("Đã hoàn thành");
//
//    private final String description;
//
//    SeasonStatus(String description) {
//        this.description = description;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//}
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

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static SeasonStatus forValue(String value) {
        for (SeasonStatus status : SeasonStatus.values()) {
            if (status.getDescription().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}

