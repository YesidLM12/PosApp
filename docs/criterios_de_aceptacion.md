# Criterios de Aceptación – Sistema POS para Restaurante

## RF-01. Gestión de usuarios y roles

### CA-01.1 Creación de usuario
- Dado que el usuario tiene rol de administrador
- Cuando registra un nuevo usuario con datos válidos
- Entonces el sistema guarda el usuario y lo deja activo

### CA-01.2 Control de permisos
- Dado un usuario autenticado
- Cuando intenta acceder a una funcionalidad no permitida por su rol
- Entonces el sistema debe denegar el acceso

---

## RF-02. Gestión de menú

### CA-02.1 Creación de producto
- Dado que el usuario tiene permisos de administración
- Cuando crea un producto con nombre, precio y categoría
- Entonces el producto debe aparecer disponible en el menú

### CA-02.2 Producto no disponible
- Dado un producto desactivado
- Cuando un mesero intenta agregarlo a una orden
- Entonces el sistema debe impedir la acción y mostrar un mensaje informativo

---

## RF-03. Gestión de mesas

### CA-03.1 Estado de mesa
- Dado el listado de mesas
- Cuando una mesa tiene una orden activa
- Entonces su estado debe mostrarse como ocupada

### CA-03.2 Cambio de mesa
- Dado una orden activa
- Cuando el usuario la transfiere a otra mesa disponible
- Entonces la orden debe quedar asociada a la nueva mesa

---

## RF-04. Gestión de órdenes

### CA-04.1 Creación de orden
- Dado un mesero autenticado
- Cuando crea una orden y agrega productos
- Entonces la orden debe guardarse con estado “abierta”

### CA-04.2 Cálculo automático
- Dado una orden con productos
- Cuando se agregan o eliminan ítems
- Entonces el sistema debe recalcular automáticamente el total

### CA-04.3 Observaciones
- Dado un producto agregado a la orden
- Cuando el usuario añade una observación
- Entonces esta debe enviarse a cocina junto con la orden

---

## RF-05. Gestión de pagos

### CA-05.1 Pago completo
- Dado una orden abierta
- Cuando el usuario registra un pago por el total
- Entonces la orden debe marcarse como pagada

### CA-05.2 Pago mixto
- Dado una orden con saldo pendiente
- Cuando el usuario registra múltiples métodos de pago
- Entonces el sistema debe validar que el total sea cubierto

---

## RF-06. Comunicación con cocina

### CA-06.1 Envío automático
- Dado una orden confirmada
- Cuando se guarda la orden
- Entonces esta debe enviarse automáticamente a cocina

### CA-06.2 Estado de preparación
- Dado una orden en cocina
- Cuando el personal marca el pedido como listo
- Entonces el estado debe actualizarse en el sistema principal

---

## RF-07. Gestión de inventario

### CA-07.1 Descuento automático
- Dado una venta confirmada
- Cuando se finaliza la orden
- Entonces el inventario debe descontarse automáticamente

### CA-07.2 Alerta de stock
- Dado un producto con stock mínimo configurado
- Cuando el inventario alcanza ese nivel
- Entonces el sistema debe generar una alerta

---

## RF-08. Reportes

### CA-08.1 Reporte de ventas
- Dado un rango de fechas
- Cuando el administrador solicita un reporte
- Entonces el sistema debe mostrar las ventas correspondientes

### CA-08.2 Exportación
- Dado un reporte generado
- Cuando el usuario selecciona exportar
- Entonces el sistema debe generar el archivo correctamente

---

## RF-09. Auditoría

### CA-09.1 Registro de acciones
- Dado un usuario autenticado
- Cuando realiza una acción crítica
- Entonces el sistema debe registrar usuario, acción y fecha

