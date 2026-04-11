
🚔 App Policía de la Ciudad

Aplicación móvil desarrollada para el personal de la Policía de la Ciudad de Buenos Aires, orientada a optimizar la gestión de servicios (UBG), visualización de información operativa y geolocalización en tiempo real.

📌 Descripción

Esta aplicación permite a los efectivos:

Gestionar servicios asignados
Visualizar ubicaciones en tiempo real
Registrar recorridos y distancias
Consultar información de comunas, comisarías y hospitales
Acceder a su credencial digital

Los datos utilizados provienen de fuentes públicas del Gobierno de la Ciudad Autónoma de Buenos Aires.

🔐 Login
<p align="center"> <img src="https://github.com/user-attachments/assets/9c39ad6e-1ed3-46e9-8853-a227b223e94a" width="250"/> </p>

Ingreso mediante LP (legajo personal) y contraseña del efectivo.

🏠 Pantalla principal (Home)
<p align="center"> <img src="https://github.com/user-attachments/assets/c09b6556-6690-48cb-8404-0d77700240ac" width="250"/> </p>

Visualización de los servicios asignados al efectivo.

📍 Detalles del servicio
<p align="center"> <img src="https://github.com/user-attachments/assets/1d5ce0f7-3b6e-4457-8d1b-ebf00b3e820b" width="250"/> </p>

Incluye:

Mini mapa con ubicación exacta
Información relevante del servicio
▶️ Inicio de servicio
<p align="center"> <img src="https://github.com/user-attachments/assets/046f1d44-d695-4cab-b5a7-d2d83693e0f0" width="250"/> </p>
Activación de geolocalización en tiempo real
Registro de distancia recorrida (KM) mediante foreground service
⛔ Finalización de servicio
<p align="center"> <img src="https://github.com/user-attachments/assets/32ad86fb-319b-44b5-8137-2755605edc5b" width="250"/> </p>

Confirmación para evitar errores antes de finalizar.

📊 Servicios realizados
<p align="center"> <img src="https://github.com/user-attachments/assets/cc686107-281d-421e-87a9-8fe2ce7d3497" width="250"/> </p>
Persistencia en Firebase Firestore
Visualización en tiempo real
🗺️ Mapa de la ciudad
<p align="center"> <img src="https://github.com/user-attachments/assets/8d53f3d0-d54e-47f1-abb6-d7714313b86e" width="250"/> </p>

Mapa interactivo con:

Comunas delimitadas
Ubicación de comisarías
🏙️ Información por comuna
<p align="center"> <img width="190" height="402" alt="infoDeComunaComiYHosp" src="https://github.com/user-attachments/assets/1566c51b-5719-4f2b-831c-0cb2ce21277e" /> </p>

Al seleccionar una comuna:

Direcciones de comisarías
Hospitales
Cantidad de instituciones
🪪 Datos del efectivo
<p align="center"> <img src="https://github.com/user-attachments/assets/e20d44f2-1048-457f-b2d6-8761ae8ae1dd" width="250"/> </p>

Visualización de credencial digital del policía.

🔓 Cierre de sesión
<p align="center"> <img src="https://github.com/user-attachments/assets/62dfeb48-eb4f-447f-a677-0ee725f1bc8c" width="250"/> </p>

Confirmación segura para evitar cierres accidentales.

🛠️ Tecnologías utilizadas
Kotlin
Jetpack Compose
Arquitectura MVVM
Hilt (inyección de dependencias)
Firebase Authentication
Firebase Firestore (base de datos en tiempo real)
Google Maps API
Foreground Service (geolocalización en tiempo real)
Manejo de permisos (ubicación, background location, etc.)
Testing (unit tests y/o instrumentados)








