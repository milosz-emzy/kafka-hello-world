package com.emzy.factory_service.model;

import java.time.LocalDateTime;

public record Item(
        Long id,
        String name,
        LocalDateTime timeStamp
) {

    public Item {
    }

}
