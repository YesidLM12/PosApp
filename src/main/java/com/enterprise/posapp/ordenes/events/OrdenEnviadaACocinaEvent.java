package com.enterprise.posapp.ordenes.events;

import com.enterprise.posapp.ordenes.model.entity.Orden;

import java.util.List;

public record OrdenEnviadaACocinaEvent(Orden orden) {
}
