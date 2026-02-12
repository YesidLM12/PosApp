# Fases de Desarrollo – Sistema POS para Restaurante

## Fase 0. Análisis y planificación
**Estado:** Completado  
**Objetivo:** Alinear expectativas, definir alcance y preparar el terreno técnico.

### Actividades
- Levantamiento y validación de requerimientos.
- Definición del MVP.
- Priorización del backlog inicial.
- Definición de arquitectura base.
- Selección del stack tecnológico.
- Configuración del repositorio y herramientas de trabajo.

### Entregables
- Documento de requerimientos.
- Definición del MVP.
- Backlog priorizado.
- Arquitectura inicial del sistema.

---

## Fase 1. Desarrollo del núcleo del sistema (MVP – Parte 1)
**Estado:** Completado
**Objetivo:** Habilitar el acceso al sistema y la operación básica.

### Alcance
- Autenticación de usuarios.
- Gestión básica de usuarios y roles.
- Gestión de menú (CRUD de productos).
- Visualización del menú.

### Entregables
- Sistema con login funcional.
- Menú administrable y visible para operación.
- Base de datos operativa.

---

## Fase 2. Órdenes y operación en salón (MVP – Parte 2)
**Objetivo:** Permitir la toma de pedidos en mesas.

### Alcance
- Visualización de mesas.
- Creación de órdenes por mesa.
- Agregar y eliminar productos de una orden.
- Observaciones en pedidos.
- Cálculo automático de totales.

### Entregables
- Flujo completo de creación y gestión de órdenes.
- Interfaz usable para meseros.

---

## Fase 3. Integración con cocina (MVP – Parte 3)
**Objetivo:** Conectar salón y cocina sin fricción.

### Alcance
- Envío automático de órdenes a cocina.
- Visualización de pedidos pendientes.
- Actualización de estado de pedidos (pendiente / listo).

### Entregables
- Módulo de cocina funcional.
- Comunicación en tiempo real o casi real entre roles.

---

## Fase 4. Pagos y cierre de órdenes (Cierre del MVP)
**Objetivo:** Completar el ciclo de venta.

### Alcance
- Registro de pagos con método único.
- Validación del pago completo.
- Cierre de órdenes.
- Bloqueo de modificaciones posteriores.

### Entregables
- Flujo de venta end-to-end completo.
- MVP listo para uso real.

---

## Fase 5. Estabilización y pruebas
**Objetivo:** Asegurar calidad antes de escalar.

### Actividades
- Pruebas funcionales.
- Pruebas de integración.
- Corrección de errores.
- Ajustes de rendimiento y usabilidad.

### Entregables
- MVP estable.
- Registro de incidencias resueltas.
- Versión candidata a producción.

---

## Fase 6. Post-MVP (Evolución del producto)
**Objetivo:** Escalar el sistema según necesidades del negocio.

### Funcionalidades
- Pagos mixtos y parciales.
- Control y alertas de inventario.
- Reportes avanzados.
- Auditoría detallada.
- Modo offline.
- Múltiples sucursales.
- Promociones y descuentos.

### Entregables
- Nuevas versiones incrementales.
- Mejora continua del sistema.

---

## Consideraciones generales
- Cada fase puede dividirse en uno o más sprints.
- No se inicia una fase sin cerrar la anterior.
- El MVP se considera completo al finalizar la Fase 4 y estabilizado en la Fase 5.
