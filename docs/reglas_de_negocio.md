# Reglas de Negocio – POS para Restaurante (Versión Inicial)

## 1. Gestión de Mesas

1. Una mesa solo puede tener **una orden activa** a la vez.
2. Una mesa puede estar en uno de los siguientes estados:
    - Disponible
    - Ocupada
    - En proceso de pago
    - Cerrada
3. Una mesa pasa a estado **Ocupada** cuando se crea una orden asociada.
4. Una mesa pasa a estado **Disponible** solo cuando la orden asociada ha sido cerrada y pagada.
5. No se puede asignar una orden a una mesa marcada como **Ocupada** o **En proceso de pago**.

---

## 2. Gestión de Órdenes

6. Toda orden debe estar asociada obligatoriamente a:
    - Una mesa
    - Un mesero (usuario del sistema)
    - Una fecha y hora de apertura
7. Una orden puede estar en los siguientes estados:
    - Abierta
    - En preparación
    - Servida
    - Cerrada
    - Cancelada
8. Solo las órdenes en estado **Abierta** pueden ser modificadas.
9. Una orden no puede cerrarse si no contiene al menos un producto.
10. Una orden cancelada no puede ser reabierta ni modificada.

---

## 3. Gestión de Productos y Menú

11. Solo los productos **activos** pueden ser agregados a una orden.
12. Cada producto debe tener:
- Nombre
- Precio
- Categoría
- Estado (Activo / Inactivo)
13. El precio del producto se fija al momento de agregarlo a la orden.
14. Cambios posteriores en el precio del producto **no afectan órdenes ya creadas**.
15. Un producto inactivo no puede ser agregado a nuevas órdenes, pero sí visualizarse en órdenes históricas.

---

## 4. Cálculo de Totales

16. El total de la orden se calcula como la suma de:
- (Precio del producto × Cantidad) por cada ítem
17. El sistema debe recalcular automáticamente el total cuando:
- Se agregan productos
- Se eliminan productos
- Se modifica la cantidad
18. El total de una orden no puede ser negativo.

---

## 5. Pagos

19. Una orden solo puede ser pagada si está en estado **Servida** o **Abierta**.
20. El pago puede realizarse por uno o varios métodos (según alcance del MVP).
21. Una orden solo puede cerrarse cuando el monto pagado sea **igual o superior** al total.
22. Si el monto pagado es mayor al total, el sistema debe calcular el cambio.
23. Una vez la orden esté en estado **Cerrada**, no se permiten modificaciones.

---

## 6. Usuarios y Roles

24. Todo usuario debe autenticarse para operar el sistema.
25. El sistema debe manejar roles mínimos:
- Administrador
- Mesero
- Cajero
26. Un mesero solo puede ver y gestionar sus propias órdenes.
27. El administrador puede:
- Gestionar productos
- Gestionar usuarios
- Visualizar reportes
28. El cajero puede:
- Procesar pagos
- Cerrar órdenes

---

## 7. Auditoría y Trazabilidad

29. Toda orden debe registrar:
- Usuario que la creó
- Fecha y hora de creación
- Fecha y hora de cierre
30. No se permite la eliminación física de órdenes cerradas.
31. Las órdenes canceladas deben conservar su historial para auditoría.
32. El sistema debe permitir la consulta de órdenes históricas.

---

## 8. Restricciones Operativas

33. El sistema no debe permitir operaciones críticas sin conexión a la base de datos.
34. Ante fallos del sistema, las órdenes abiertas no deben perderse.
35. Toda transacción crítica debe ejecutarse de forma atómica.

---

## 9. Reglas Iniciales del MVP (Scope Control)

36. No se incluye manejo de propinas en la primera versión.
37. No se incluye facturación electrónica en el MVP.
38. No se incluye integración con plataformas externas (delivery, pagos en línea).
39. El sistema estará orientado inicialmente a un solo restaurante (single-tenant).
