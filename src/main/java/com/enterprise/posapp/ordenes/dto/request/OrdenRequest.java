package com.enterprise.posapp.ordenes.dto.request;

import java.util.List;

public record OrdenRequest(
        int mesa,
        String  mesero,
        List<OrdenItemRequest> items
) {
}
