# Control_taller
Controlar el tiempo de las reparaciones de un taller

---

## ⚙️ Funcionalidades principales

- Registro y autenticación de usuarios con **Spring Security** y **BCryptPasswordEncoder**
- Registro de vehiculos, reparaciones y materiales utilizados en sus reparaciones
- Te permite controlar el tiempo dedicado de las reparaciones y/o mantenimientos de cada vehiculo.
- También puedes calcular los costes y beneficios de la gestión compra-venta.
- Persistencia de datos usando **Spring Data JPA** y **Postgresql**


---

## 🛠️ Tecnologías empleadas

| Tecnología           | Uso                                  |
|----------------------|---------------------------------------|
| Spring Boot          | Framework principal                   |
| Spring Security      | Autenticación y autorización          |
| BCryptPasswordEncoder| Encriptación de contraseñas           |
| Postgresql Database  | Base de datos para desarrollo         |
| Maven                | Gestión de dependencias y compilación |

---

## ▶️ Cómo ejecutar el proyecto
Nos descargamos la aplicación.
En la pantalla de inicio podemos solicitar el registro.
### Una vez entramos en la página podemos registrar los vehiculos y sus reparaciones.


![Llegamos al Dashboard con un pequeño resumen de los datos que tenemos guardados.](img/Dashboard.jpg)

![Registramos un vehiculo](img/registro_vehiculo.JPG)

### En los informes podemos encontrar filtros de búsqueda por fecha

![Podemos ver desde la fecha que queramos](img/informes.jpg)

### Podemos extraer informes personalizados en PDF del mes que necesitemos

![Podemos descargar los informes en PDF](img/pdf.jpg)

---

## 📂 Estructura del proyecto

```plaintext
control-horas-app/
├── src/
│   ├── main/java/…       → Código fuente (controladores, servicios, entidades)
│   ├── main/resources/   → Configuración (`application.properties`)
│   └── test/java/…       → Tests automatizados
├── pom.xml               → Configuración del proyecto Maven
├── mvnw / mvnw.cmd       → Scripts para usar Maven sin instalación global
├── README.md             → Documentación del proyecto

```

---

## 🛡️ Licencia

Este proyecto se distribuye bajo la licencia **MIT**.  
Puedes usarlo, modificarlo y compartirlo libremente.

---

## 🤝 Contribuciones

¿Tienes ideas para mejorar esta app o quieres participar?  
Las pull requests son bienvenidas, y también puedes abrir un *issue* para reportar errores o proponer nuevas funcionalidades.

---

## 📫 Contacto

Desarrollado por **Abel Contreras**  
🔗 GitHub: [@Acoro47](https://github.com/Acoro47)

---

## ✨ Gracias

Gracias por visitar este repositorio.  
Espero que te sea útil o inspirador para tus propios proyectos.  
Si te ha gustado, deja una estrella ⭐ en GitHub. ¡Nos vemos en el código!
