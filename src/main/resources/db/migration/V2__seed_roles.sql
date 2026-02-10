INSERT IGNORE INTO roles (codigo,nombre,descripcion,created_at)
VALUES
    ('ADMIN', 'Administrador', true, now()),
    ('MESERO', 'Mesero', true, now()),
    ('CAJERO', 'Cajero', true, now()),
    ('COCINA', 'Cocina', true, now())
