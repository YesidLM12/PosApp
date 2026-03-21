-- =========================
-- ROLES
-- =========================
CREATE TABLE roles
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo      VARCHAR(50) UNIQUE NOT NULL,
    nombre      VARCHAR(50)        NOT NULL UNIQUE,
    descripcion VARCHAR(255),
    activo      BOOLEAN            NOT NULL,
    created_at  TIMESTAMP
);

-- =========================
-- USUARIOS
-- =========================
CREATE TABLE usuarios
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    activo     BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE usuario_roles
(
    usuario_id BIGINT NOT NULL,
    rol_id     BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, rol_id),
    CONSTRAINT fk_usuario_roles_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
    CONSTRAINT fk_usuario_roles_rol
        FOREIGN KEY (rol_id) REFERENCES roles (id)
);

-- =========================
-- MESAS
-- =========================
CREATE TABLE mesas
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero     INT         NOT NULL UNIQUE,
    estado     VARCHAR(30) NOT NULL,
    created_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- CATEGORÍAS
-- =========================
CREATE TABLE categorias
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    activa      BOOLEAN      NOT NULL DEFAULT TRUE
);

-- =========================
-- PRODUCTOS
-- =========================
CREATE TABLE productos
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(150)   NOT NULL,
    precio       NUMERIC(10, 2) NOT NULL,
    activo       BOOLEAN        NOT NULL DEFAULT TRUE,
    categoria_id BIGINT         NOT NULL,
    CONSTRAINT fk_producto_categoria
        FOREIGN KEY (categoria_id) REFERENCES categorias (id)
);

-- =========================
-- ORDENES
-- =========================
CREATE TABLE ordenes
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    mesa_id    BIGINT         NOT NULL,
    mesero_id  BIGINT         NOT NULL,
    estado     VARCHAR(30)    NOT NULL,
    total      NUMERIC(10, 2) NOT NULL DEFAULT 0,
    created_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    closed_at  TIMESTAMP,
    CONSTRAINT fk_orden_mesa
        FOREIGN KEY (mesa_id) REFERENCES mesas (id),
    CONSTRAINT fk_orden_mesero
        FOREIGN KEY (mesero_id) REFERENCES usuarios (id)
);

-- =========================
-- ITEMS DE ORDEN
-- =========================
CREATE TABLE orden_items
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    orden_id    BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    observacion VARCHAR(50),
    cantidad    INT    NOT NULL,
    CONSTRAINT fk_item_orden
        FOREIGN KEY (orden_id) REFERENCES ordenes (id),
    CONSTRAINT fk_item_producto
        FOREIGN KEY (producto_id) REFERENCES productos (id)
);

-- =========================
-- PAGOS
-- =========================
CREATE TABLE pagos
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    orden_id   BIGINT         NOT NULL,
    metodo     VARCHAR(30)    NOT NULL,
    monto      NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pago_orden
        FOREIGN KEY (orden_id) REFERENCES ordenes (id)
);

-- ===========================
-- COCINA
-- ===========================
CREATE TABLE ticket
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    orden_id   BIGINT      NOT NULL,
    estado     VARCHAR(50) NOT NULL,
    created_at DATETIME    NOT NULL,
    CONSTRAINT fk_orden
        FOREIGN KEY (orden_id) REFERENCES ordenes (id)
);
