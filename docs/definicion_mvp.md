# Definición del MVP – Sistema POS para Restaurante

## Objetivo del MVP
Desarrollar un sistema POS funcional que permita al restaurante registrar pedidos, enviarlos a cocina y procesar pagos, garantizando la operación básica del negocio de forma eficiente y confiable.

El MVP se enfocará únicamente en el flujo crítico de operación, excluyendo funcionalidades avanzadas que no sean indispensables en la primera versión.

---

## Flujo Operativo Cubierto por el MVP
1. Autenticación de usuarios.
2. Creación de órdenes por mesa.
3. Registro de productos en la orden.
4. Envío automático de órdenes a cocina.
5. Gestión del estado de pedidos en cocina.
6. Registro del pago de la orden.
7. Cierre de la orden.

---

## Alcance Funcional del MVP

### 1. Gestión de usuarios
- Autenticación de usuarios.
- Creación y administración básica de usuarios.
- Asignación de roles (administrador, mesero, cocina, cajero).

---

### 2. Gestión de menú
- Creación, edición y desactivación de productos.
- Visualización del menú actualizado.
- Manejo de precios simples por producto.

---

### 3. Gestión de mesas y órdenes
- Visualización del estado de las mesas.
- Creación de una orden por mesa.
- Agregar y eliminar productos de la orden.
- Inclusión de observaciones en los pedidos.

---

### 4. Comunicación con cocina
- Envío automático de órdenes a cocina.
- Visualización de pedidos pendientes.
- Actualización del estado de pedidos (pendiente / listo).

---

### 5. Pagos y cierre de órdenes
- Registro de pagos con método único.
- Cierre de órdenes una vez completado el pago.
- Impedimento de modificaciones en órdenes cerradas.

---

## Funcionalidades Excluidas del MVP (Post-MVP)
- Pagos mixtos o parciales.
- Control avanzado de inventario.
- Alertas de stock.
- Reportes avanzados y exportación de datos.
- Auditoría detallada de acciones.
- Modo offline.
- Gestión de múltiples sucursales.
- Promociones, descuentos y combos.

---

## Requerimientos No Funcionales del MVP
- El sistema debe responder a operaciones críticas en menos de 2 segundos.
- El sistema debe garantizar la persistencia e integridad de los datos.
- El sistema debe contar con autenticación segura.
- La interfaz debe ser intuitiva y de fácil uso.

---

## Resultado Esperado
Al finalizar el MVP, el restaurante contará con un sistema POS operativo que permitirá gestionar pedidos y pagos de forma digital, reduciendo errores operativos y mejorando la eficiencia del servicio.
