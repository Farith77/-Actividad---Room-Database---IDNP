# Bodega Delivery App - Gestión de Tienda con Room

Este proyecto es una aplicación de Android nativa desarrollada en Kotlin para la gestión de una pequeña tienda o "bodega". La aplicación permite administrar clientes, productos, categorías y pedidos, utilizando una arquitectura moderna y persistencia de datos local a través de Room Database.

## ✨ Características Principales

- **Gestión de Clientes:** CRUD completo (Crear, Leer, Actualizar, Eliminar) para los clientes de la tienda.
- **Gestión de Catálogo:** Administración de productos y sus categorías correspondientes.
- **Registro de Pedidos:** Creación de nuevos pedidos asociando un cliente y una lista de productos.
- **Persistencia Local:** Todos los datos se almacenan localmente en el dispositivo utilizando **Room Database**.
- **Carga Inicial de Datos:** La base de datos se puebla con datos de ejemplo desde archivos JSON la primera vez que se inicia la aplicación.
- **Interfaz de Usuario Intuitiva:** UI limpia construida con Vistas XML, Material Design, y navegación fluida a través de `Navigation Component`.
- **Arquitectura Moderna:** El proyecto sigue los principios de la arquitectura MVVM (Model-View-ViewModel) para una clara separación de responsabilidades y un código mantenible.

## 🛠️ Stack Tecnológico y Dependencias

- **Lenguaje:** [Kotlin](https://kotlinlang.org/)
- **Arquitectura:** MVVM (Model-View-ViewModel)
- **Persistencia de Datos:** [Android Room](https://developer.android.com/training/data-storage/room) - Abstracción de SQLite para una base de datos robusta.
- **Componentes de Arquitectura de Android (Android Jetpack):**
    - **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel):** Para gestionar datos relacionados con la UI de manera consciente del ciclo de vida.
    - **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) / [Flow](https://developer.android.com/kotlin/flow):** Para observar cambios en los datos y actualizar la UI de forma reactiva.
    - **[Navigation Component](https://developer.android.com/guide/navigation):** Para manejar toda la navegación entre fragmentos de la aplicación.
- **Asincronía:** [Coroutines de Kotlin](https://kotlinlang.org/docs/coroutines-overview.html) para gestionar operaciones en segundo plano de forma eficiente.
- **UI:** Vistas XML con `RecyclerView`, `Fragments`, `BottomNavigationView` y [Material Design Components](https://material.io/develop/android/docs/getting-started).
- **Inyección de Dependencias (Manual):** La gestión de dependencias se realiza de forma manual para una mayor claridad en este proyecto.
- **Serialización JSON:** [Gson](https://github.com/google/gson) para parsear los datos iniciales desde archivos `.json`.

## 📂 Estructura del Proyecto

El código está organizado siguiendo una arquitectura limpia, separando las responsabilidades en diferentes capas:

- `com.bodega.delivery.data`: Contiene la lógica de acceso a datos.
    - `entities`: Clases de datos (tablas) para Room.
    - `dao`: Interfaces de Acceso a Datos (Data Access Objects) para las consultas a la base de datos.
    - `database`: La clase `AppDatabase` que configura y une toda la base de datos Room.
    - `relations`: Clases que definen las relaciones entre entidades.
- `com.bodega.delivery.repository`: Repositorios que abstraen el origen de datos (en este caso, Room) del resto de la app.
- `com.bodega.delivery.viewmodel`: ViewModels que contienen la lógica de negocio y exponen el estado a la UI.
- `com.bodega.delivery.ui`: Contiene los componentes de la interfaz de usuario.
    - `fragments`: Fragmentos que representan cada pantalla de la aplicación.
    - `adapters`: Adaptadores para los `RecyclerView`.
    - `dialogs`: Diálogos personalizados para la entrada de datos.

## 🚀 Cómo Empezar

1.  Clona este repositorio en tu máquina local.
2.  Abre el proyecto en Android Studio.
3.  Asegúrate de tener un emulador configurado o un dispositivo físico conectado.
4.  Ejecuta la aplicación. La primera vez, Gradle descargará todas las dependencias necesarias. Al iniciar, la base de datos se llenará automáticamente con los datos de los archivos en `res/raw/`.
