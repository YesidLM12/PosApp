# Requerimientos del Sistema POS para Restaurante

## 1. Requerimientos Funcionales (RF)

### RF-01. Gestión de usuarios y roles
- El sistema deberá permitir la creación, modificación, desactivación y autenticación de usuarios.
- El sistema deberá manejar roles con permisos diferenciados (administrador, cajero, mesero y cocina).

### RF-02. Gestión de menú
- El sistema deberá permitir la creación, edición y eliminación de productos y categorías.
- El sistema deberá permitir definir precios, impuestos, variantes y disponibilidad de productos.
- El sistema deberá permitir activar o desactivar productos sin eliminarlos.

### RF-03. Gestión de mesas
- El sistema deberá permitir registrar mesas y su estado (disponible, ocupada, reservada).
- El sistema deberá permitir asignar órdenes a mesas y a un mesero responsable.
- El sistema deberá permitir transferir órdenes entre mesas.

### RF-04. Gestión de órdenes
- El sistema deberá permitir crear, modificar y cancelar órdenes.
- El sistema deberá permitir agregar observaciones por producto.
- El sistema deberá permitir dividir y combinar órdenes.
- El sistema deberá calcular automáticamente subtotales, impuestos, descuentos y total a pagar.

### RF-05. Gestión de pagos
- El sistema deberá permitir registrar pagos en efectivo, tarjeta y otros métodos electrónicos.
- El sistema deberá permitir pagos parciales y combinados.
- El sistema deberá generar comprobantes de pago.

### RF-06. Comunicación con cocina
- El sistema deberá enviar las órdenes a cocina de forma automática.
- El sistema deberá permitir visualizar el estado de preparación de cada orden.
- El sistema deberá permitir marcar órdenes como listas o entregadas.

### RF-07. Gestión de inventario
- El sistema deberá permitir registrar insumos y productos.
- El sistema deberá descontar inventario automáticamente según las ventas realizadas.
- El sistema deberá generar alertas cuando un producto alcance niveles mínimos de stock.

### RF-08. Reportes y estadísticas
- El sistema deberá generar reportes de ventas por fecha, producto y usuario.
- El sistema deberá mostrar productos más vendidos y ventas totales.
- El sistema deberá permitir exportar reportes.

### RF-09. Auditoría y trazabilidad
- El sistema deberá registrar las acciones realizadas por los usuarios.
- El sistema deberá permitir la consulta del historial de cambios y transacciones.

---

## 2. Requerimientos No Funcionales (RNF)

### RNF-01. Rendimiento
- El sistema deberá responder a cualquier operación crítica en un tiempo máximo de 2 segundos bajo condiciones normales de operación.

### RNF-02. Disponibilidad
- El sistema deberá estar disponible al menos el 99% del tiempo operativo del restaurante.
- El sistema deberá permitir operación en modo offline y sincronización posterior cuando aplique.

### RNF-03. Seguridad
- El sistema deberá implementar autenticación segura de usuarios.
- El sistema deberá proteger la información sensible mediante mecanismos de cifrado.
- El sistema deberá restringir el acceso a funcionalidades según el rol del usuario.

### RNF-04. Usabilidad
- El sistema deberá contar con una interfaz intuitiva y fácil de usar.
- El sistema deberá permitir que un usuario nuevo pueda operar funciones básicas sin capacitación extensa.

### RNF-05. Escalabilidad
- El sistema deberá soportar el crecimiento en número de usuarios, mesas y transacciones sin degradación significativa del rendimiento.
- El sistema deberá permitir la incorporación de múltiples sucursales.

### RNF-06. Mantenibilidad
- El sistema deberá estar desarrollado con una arquitectura modular.
- El sistema deberá permitir cambios de negocio sin afectar la estabilidad del sistema.

### RNF-07. Compatibilidad
- El sistema deberá ser compatible con dispositivos de escritorio, tablets y navegadores modernos.
- El sistema deberá permitir integración con impresoras y dispositivos POS.

### RNF-08. Confiabilidad
- El sistema deberá garantizar la integridad de los datos.
- El sistema deberá contar con mecanismos de respaldo y recuperación ante fallos.

---

## 3. Suposiciones y Restricciones

- El sistema será utilizado en un entorno de restaurante físico.
- Los usuarios contarán con dispositivos compatibles y conexión básica a red.
- El sistema cumplirá con la normativa fiscal vigente según el país.
