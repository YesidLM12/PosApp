package com.enterprise.posapp.cocina.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TicketResponse(
        LocalDateTime fecha,
        int mesa,
        String mesero,
        List<ItemTicketResponse> items
) {}
