# Sistema de Gestión de Préstamos

## Descripción General
El **Sistema de Gestión de Préstamos** es una aplicación de escritorio desarrollada en **Java (NetBeans)** con conexión a **MySQL**, diseñada para administrar clientes, registrar préstamos con interés mensual, gestionar pagos semanales, generar reportes en PDF y mantener control del estado de las operaciones financieras.  

Su objetivo principal es automatizar el proceso de registro, cálculo de intereses y seguimiento de pagos en pequeñas y medianas entidades de crédito.

---

## Requisitos del Sistema

### Software necesario
- **Java JDK 22 o superior**  
- **NetBeans IDE 22 o superior**  
- **MySQL Server 8.0 o superior**  
- **Conector JDBC para MySQL (mysql-connector-java.jar)** 
- **Git (opcional, para clonar el proyecto)**

###  Librerías externas
Asegúrate de agregar al proyecto las siguientes librerías dentro de NetBeans:

1. `mysql-connector-java-x.x.x.jar`  

---

## ️ Estructura del Proyecto

```
SistemaPrestamos/
├── src/
│   ├── Controladores/
│   ├── Modelos/
│   ├── Ventanas/
│   ├── Conexion/
│   ├── Helper/
│   │   └── Environment.java
│   └── Recursos/
├── base_datos/
│   └── prestamos.sql
├── lib/
│   ├── mysql-connector-java.jar
│   └── itextpdf.jar
└── README.md
```

---

##  Configuración de la Base de Datos

1. Abre **MySQL Workbench** o **phpMyAdmin**.  
2. Crea una base de datos llamada:
   ```sql
   CREATE DATABASE prestamos_1;
   ```
3. Importa el archivo `prestamos.sql` ubicado en la carpeta `base_datos`.  
4. Verifica que las tablas principales se hayan creado correctamente:
   - `clientes`
   - `creditos`
   - `pagos`
   - `usuarios`
   - `tipo_credito`

5. En el archivo de conexionmy del proyecto (por ejemplo, `ConexionBD.java`), actualiza las credenciales según tu entorno:
   ```java
   Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/prestamos_1?useSSL=false&allowPublicKeyRetrieval=true", "tu_usuario","tu_contraseña");
   ```

---

## Configuración del Paquete Helper

Dentro del proyecto existe un paquete llamado **`Helper`** que contiene la clase **`Environment.java`**, encargada de manejar las variables de entorno necesarias para integraciones externas (como el envío de notificaciones o mensajes mediante Meta).

Ejemplo del contenido de la clase:

```java
package helper;

public class Environment {
    public static String phoneNumberId = "numerodecelular";
    public static String accessToken = "token de meta";
}
```

> ⚠️ **Importante:**  
> - Reemplaza `"numerodecelular"` y `"token de meta"` con los valores reales de tu cuenta o entorno de producción.  
> - No compartas estos datos públicamente, ya que son credenciales privadas.  
> - Se recomienda almacenarlas en variables de entorno del sistema operativo o en un archivo `.env` seguro si el proyecto evoluciona a un entorno de despliegue profesional.

---

## Ejecución del Proyecto

1. Abre **NetBeans IDE**.  
2. Importa el proyecto desde la opción **“Abrir Proyecto”**.  
3. Asegúrate de que las librerías (`lib/`) estén correctamente vinculadas.  
4. Ejecuta el archivo principal (`Main.java` o `Login.java`).  
5. Inicia sesión con un usuario existente o crea uno nuevo desde la base de datos.

---

## Funcionalidades Principales

- 👤 **Gestión de clientes** (registro, edición y eliminación)  
- 💰 **Registro de préstamos** con cálculo automático de interés mensual (20%)  
- 📆 **Control de pagos semanales**  
- 📄 **Generación de reportes en la ventana de clientes en riesgo** de clientes, préstamos y pagos  
- 👨‍💼 **Gestión de usuarios** con roles y permisos  
- 💬 **Integración con Meta** *(usando variables del paquete `Helper`)* para futuras notificaciones automáticas  

---

##  Descarga del Proyecto

**[Descargar Sistema de Préstamos (versión completa en github)](https://github.com/bryan200103/ProyectoGrado)**  

---

## 💻 Desarrollador

**Bryan Soledispa - Erick Murillo**  
Estudiante de Tecnología de la Información  
Universidad Estatal de Milagro – Ecuador  
📧 **Bryansole@outlook.es**  
📱 **0984055114**

---

## Licencia

Este proyecto es de uso académico y puede ser modificado o mejorado libremente con fines educativos o de investigación.  
Se prohíbe su uso comercial sin la autorización del autor.
