# Mapeo de Reglas de Negocio a Casos de Prueba – POS Restaurante

## 1. Gestión de Mesas

### Reglas Asociadas
- RN-01 a RN-05

### Pruebas Unitarias
- Debe permitir crear una orden si la mesa está disponible
- Debe rechazar la creación de una orden si la mesa está ocupada
- Debe cambiar el estado de la mesa a ocupada al crear una orden
- Debe impedir asignar una orden a una mesa en proceso de pago

### Pruebas de Integración
- Crear orden y persistir cambio de estado de mesa
- Cerrar orden y verificar que la mesa vuelva a estado disponible
- Consultar mesas y validar estado correcto desde la base de datos

---

## 2. Gestión de Órdenes

### Reglas Asociadas
- RN-06 a RN-10

### Pruebas Unitarias
- No permitir crear orden sin mesa
- No permitir crear orden sin mesero
- No permitir cerrar una orden sin productos
- No permitir modificar una orden en estado cerrada
- No permitir reabrir una orden cancelada
- Validar transición correcta de estados

### Pruebas de Integración
- Persistir una orden con estado inicial “Abierta”
- Actualizar estado de la orden y verificar persistencia
- Consultar órdenes por estado
- Validar auditoría (fechas y usuario)

---

## 3. Gestión de Productos y Menú

### Reglas Asociadas
- RN-11 a RN-15

### Pruebas Unitarias
- No permitir agregar productos inactivos a una orden
- Al agregar un producto, fijar el precio en la orden
- Modificar el precio del producto no afecta órdenes existentes
- Permitir visualizar productos inactivos en órdenes históricas

### Pruebas de Integración
- Persistir productos activos e inactivos
- Agregar productos a una orden y validar relación
- Consultar órdenes históricas con productos inactivos

---

## 4. Cálculo de Totales

### Reglas Asociadas
- RN-16 a RN-18

### Pruebas Unitarias
- Calcular correctamente el total de la orden
- Recalcular total al agregar productos
- Recalcular total al eliminar productos
- Recalcular total al modificar cantidad
- Evitar totales negativos

### Pruebas de Integración
- Persistir una orden con total correcto
- Recuperar una orden y validar total calculado

---

## 5. Pagos

### Reglas Asociadas
- RN-19 a RN-23

### Pruebas Unitarias
- No permitir pago si la orden no está en estado válido
- Rechazar pago insuficiente
- Calcular correctamente el cambio
- No permitir cerrar orden sin pago completo
- No permitir modificaciones después del cierre

### Pruebas de Integración
- Registrar pago y persistir relación con la orden
- Cerrar orden y verificar estado final
- Consultar pagos asociados a una orden

---

## 6. Usuarios y Roles

### Reglas Asociadas
- RN-24 a RN-28

### Pruebas Unitarias
- Validar permisos por rol
- Impedir que un mesero gestione órdenes de otro mesero
- Validar capacidades del rol administrador
- Validar capacidades del rol cajero

### Pruebas de Integración
- Acceso a endpoints según rol
- Respuesta correcta ante acceso no autorizado
- Persistencia de usuarios y roles

---

## 7. Auditoría y Trazabilidad

### Reglas Asociadas
- RN-29 a RN-32

### Pruebas Unitarias
- Registrar correctamente usuario creador
- Registrar fecha y hora de creación
- Impedir eliminación lógica de órdenes cerradas
- Mantener historial de órdenes canceladas

### Pruebas de Integración
- Consultar órdenes históricas
- Validar campos de auditoría en base de datos
- Verificar integridad de datos históricos

---

## 8. Restricciones Operativas

### Reglas Asociadas
- RN-33 a RN-35

### Pruebas Unitarias
- Validar atomicidad de operaciones críticas
- Manejo de errores de negocio controlados

### Pruebas de Integración
- Rollback de transacciones ante fallos
- Simular error en persistencia y validar consistencia
- Verificar que no se persistan estados parciales

---

## 9. Reglas del MVP (Scope Control)

### Reglas Asociadas
- RN-36 a RN-39

### Pruebas Unitarias
- Validar que no existan lógicas de propinas
- Validar ausencia de facturación electrónica
- Validar configuración single-restaurant

### Pruebas de Integración
- Verificar que el sistema funcione sin integraciones externas
- Validar configuración por entorno

---

## 10. Beneficio del Mapeo

Este mapeo permite:
- Trazabilidad directa regla → test
- Cobertura real de reglas de negocio
- Priorización de tests críticos
- Documentación viva del sistema

Cada regla de negocio queda protegida por al menos un test.
