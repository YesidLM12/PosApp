INSERT IGNORE INTO roles (codigo,nombre,descripcion,created_at)
VALUES
    ('ADMIN', 'ADMINISTRADOR', true, now()),
    ('MESERO', 'MESERO', true, now()),
    ('CAJERO', 'CAJERO', true, now()),
    ('COCINA', 'COCINA', true, now())
