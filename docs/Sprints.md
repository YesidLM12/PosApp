# Plan de Sprints – Sistema POS para Restaurante

## Duración del Sprint
- 2 semanas por sprint
- Entregable funcional al final de cada sprint

---

## Sprint 0 – Preparación del proyecto
**Estado:** Completado  
**Objetivo:** Dejar el proyecto listo para desarrollo continuo.

### Historias incluidas
- Configuración del repositorio
- Definición de arquitectura base
- Configuración de base de datos
- Configuración de entornos (dev/test)

### Entregables
- Repositorio inicial configurado
- Estructura base del proyecto
- Documentación mínima del proyecto

---

## Sprint 1 – Autenticación y usuarios
**Estado:** Completado  
**Objetivo:** Controlar el acceso al sistema.

### Historias de Usuario
- HU-01 Autenticación de usuarios
- HU-02 Administración básica de usuarios

### Funcionalidades
- Login funcional
- Roles definidos
- Protección de rutas o funcionalidades por rol

### Entregables
- Usuarios pueden iniciar sesión
- Accesos restringidos por rol

---

## Sprint 2 – Gestión de menú
**Objetivo:** Permitir administrar y consultar el menú.

### Historias de Usuario
- HU-03 Gestión de menú
- HU-04 Visualización del menú

### Funcionalidades
- CRUD de productos
- Activar / desactivar productos
- Visualización del menú para meseros

### Entregables
- Menú administrable
- Menú visible para operación

---

## Sprint 3 – Mesas y órdenes
**Objetivo:** Registrar pedidos por mesa.

### Historias de Usuario
- HU-05 Visualización de mesas
- HU-06 Creación de órdenes
- HU-07 Modificación de órdenes
- HU-08 Observaciones en pedidos

### Funcionalidades
- Estado de mesas
- Órdenes por mesa
- Cálculo automático de totales

### Entregables
- Flujo completo de toma de pedidos
- Órdenes persistidas correctamente

---

## Sprint 4 – Cocina
**Objetivo:** Conectar salón y cocina.

### Historias de Usuario
- HU-09 Envío de órdenes a cocina
- HU-10 Visualización de pedidos en cocina
- HU-11 Actualización de estado de pedidos

### Funcionalidades
- Envío automático de órdenes
- Estados de pedidos (pendiente / listo)

### Entregables
- Módulo de cocina operativo
- Comunicación clara entre roles

---

## Sprint 5 – Pagos y cierre
**Objetivo:** Completar el ciclo de venta.

### Historias de Usuario
- HU-12 Registro de pagos
- HU-14 Cierre de órdenes

### Funcionalidades
- Registro de pagos
- Cierre y bloqueo de órdenes

### Entregables
- Flujo end-to-end completo
- MVP funcional

---

## Sprint 6 – Estabilización
**Objetivo:** Preparar el sistema para uso real.

### Actividades
- Pruebas funcionales
- Pruebas de integración
- Corrección de errores
- Ajustes de rendimiento y UX

### Entregables
- MVP estable
- Versión candidata a producción

---

## Definición de Done (DoD)
Una historia se considera completada cuando:
- Cumple criterios de aceptación
- No presenta errores críticos
- Está integrada al sistema
- Puede ser demostrada funcionalmente

---

## Resultado del Plan
Al finalizar el Sprint 6, el proyecto contará con:
- Un POS funcional
- Un MVP usable en un restaurante real
- Una base sólida para evolución post-MVP
