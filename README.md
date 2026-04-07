# POS Restaurant System

Sistema POS (Point of Sale) para restaurantes diseñado para gestionar la operación completa de un restaurante: usuarios, menú, mesas, órdenes, cocina y pagos.

El objetivo del proyecto es construir un sistema modular que permita gestionar el ciclo completo de una venta en un restaurante, desde la toma del pedido hasta el cierre de la orden.

Este proyecto está desarrollado como práctica profesional para aplicar principios de arquitectura backend, reglas de negocio, pruebas y diseño de sistemas.

---

# Características principales

El sistema permite gestionar los procesos clave de un restaurante:

- Autenticación de usuarios
- Gestión de roles (Administrador, Mesero, Cajero, Cocina)
- Administración del menú
- Gestión de mesas
- Creación y administración de órdenes
- Comunicación entre salón y cocina
- Registro de pagos
- Cierre de órdenes

El sistema sigue el flujo natural de operación de un restaurante:

Mesero → crea orden → cocina prepara → cajero cobra → orden se cierra.

---

# Alcance del MVP

La primera versión del sistema incluye:

- Gestión de usuarios y autenticación
- CRUD de productos y categorías
- Gestión de mesas
- Creación y modificación de órdenes
- Envío de órdenes a cocina
- Visualización de pedidos en cocina
- Registro de pagos
- Cierre de órdenes

El MVP cubre el flujo completo de venta dentro del restaurante.

---

# Reglas de negocio clave

El sistema implementa reglas para garantizar consistencia operativa:

- Una mesa solo puede tener **una orden activa**.
- Solo productos **activos** pueden agregarse a una orden.
- Una orden **no puede cerrarse sin productos**.
- El total de la orden se recalcula automáticamente al modificar productos.
- Una orden **solo se cierra cuando el pago cubre el total**.
- Las órdenes cerradas **no pueden modificarse**.

Estas reglas se encuentran protegidas mediante pruebas unitarias.

---

# Arquitectura del sistema

El backend sigue una arquitectura en capas:

Controller  
↓  
Service  
↓  
Repository  
↓  
Database

Principios utilizados:

- Separación de responsabilidades
- Reglas de negocio en capa de servicio
- Persistencia con JPA
- Validaciones de dominio
- Pruebas unitarias para reglas críticas

---

# Stack Tecnológico

Backend:

- Java
- Spring Boot
- Spring Security
- JPA / Hibernate
- MySQL
- Maven

Testing:

- JUnit
- Mockito

Herramientas:

- Git
- Postman

---

# Estructura del proyecto
├── usuarios    
├── productos   
├── mesas   
├── ordenes     
├── cocina  
├── pagos   
└── config  


Cada módulo contiene:

- controller
- service
- repository
- dto
- entity

---

# Flujo principal del sistema

1. Usuario inicia sesión.
2. Mesero selecciona una mesa.
3. Se crea una orden.
4. Se agregan productos a la orden.
5. La orden se envía a cocina.
6. Cocina prepara el pedido.
7. El cajero registra el pago.
8. La orden se cierra.
9. La mesa vuelve a estado disponible.

---

# Pruebas

El sistema incluye:

- pruebas unitarias para reglas de negocio
- pruebas de integración para operaciones críticas

Las pruebas cubren:

- creación de órdenes
- validaciones de mesa
- cálculo de totales
- registro de pagos
- control de estados de órdenes

---

# Roadmap del proyecto

Fases del desarrollo:

1. Autenticación y usuarios
2. Gestión de menú
3. Mesas y órdenes
4. Integración con cocina
5. Pagos y cierre
6. Estabilización

Futuras mejoras:

- pagos mixtos
- control de inventario
- reportes avanzados
- soporte para múltiples sucursales
- modo offline

---

# Estado del proyecto

En desarrollo.

Actualmente se encuentra en estabilización y desarrollo de pruebas de integración

---
