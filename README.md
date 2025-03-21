# Gutendex Spring Boot Application

Gutendex-Springboot es una aplicación diseñada para interactuar con la API de Gutendex y administrar información sobre libros y autores. Permite buscar libros por título, guardar autores y libros en una base de datos relacional y manejar las relaciones entre estas entidades (uno-a-muchos).

## Funcionalidades principales

1. **Búsqueda de libros por título:**
   - La aplicación utiliza un método de entrada para buscar libros por su título mediante la API de Gutendex.
   - Los resultados incluyen información como el nombre del autor, idioma del libro y número de descargas.

2. **Gestión de libros y autores:**
   - Inserta libros y autores en la base de datos, asegurando que no haya duplicados.
   - Asocia un autor a múltiples libros utilizando una relación uno-a-muchos.

3. **Persistencia de datos:**
   - Utiliza JPA (Java Persistence API) para manejar entidades persistentes y realizar operaciones sobre la base de datos.

4. **Validaciones automáticas:**
   - Comprueba si un libro o autor ya existe antes de intentar insertarlos en la base de datos.
   - Evita duplicados y mantiene la integridad referencial entre las tablas.


## Cómo funciona

1. **Flujo principal:**
   - El usuario ingresa el título de un libro que desea buscar.
   - La aplicación consulta la API de Gutendex y devuelve el resultado más relevante.
   - Si el libro ya existe, muestra un mensaje indicando que ya está registrado.
   - Si el autor ya existe, asocia el nuevo libro con el autor existente.
   - Si el autor no existe, crea un nuevo registro del autor junto con su libro.

2. **Clases principales:**
   - **`Book`**: Representa la entidad del libro, incluye título, idioma, descargas y una referencia al autor.
   - **`Author`**: Representa la entidad del autor, incluye nombre, años de nacimiento y fallecimiento, y una lista de libros.

3. **Repositorio:**
   - **`BookRepository`** y **`AuthorRepository`** se utilizan para interactuar con la base de datos mediante JPA.

4. **Método destacado:**
   - `findBookByTitle()`: Maneja la lógica de búsqueda, inserción y validación de duplicados para libros y autores.

## Requisitos

- **Java 17+**
- **Maven**
- **Base de datos relacional (ejemplo: PostgreSQL)**


## Uso

- Al iniciar la aplicación, utiliza la consola para buscar libros e interactuar con la base de datos.

Ejemplo de búsqueda de un libro:
```plaintext
Enter the book title you want to find:
"Cien años de soledad"
``` 

La aplicación verificará si el libro ya existe. Si no, se registrará junto con su autor.

## Tecnologías utilizadas

- **Spring Boot**: Framework principal para la aplicación.
- **JPA/Hibernate**: Manejo de la persistencia de datos.
- **PostgreSQL**: Base de datos relacional utilizada.
- **Gutendex API**: Fuente de datos para libros y autores.
