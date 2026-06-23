# Trabajo Práctico Integrador – Programación II

## Alumno

**Guzmán Mauricio Gabriel**

**Comisión:** A25 C2-07

**Carrera:** Tecnicatura Universitaria en Programación – Modalidad a Distancia

**Universidad Tecnológica Nacional (UTN)**

---

# Descripción del Proyecto

Este proyecto consiste en una aplicación de consola desarrollada en Java para la gestión integral de Categorías, Productos, Usuarios y Pedidos.

El sistema fue construido aplicando los principios de la Programación Orientada a Objetos (POO), implementando funcionalidades CRUD (Crear, Listar, Modificar y Eliminar), validaciones de datos, manejo de excepciones personalizadas y bajas lógicas para preservar la integridad de la información.

La aplicación permite administrar un catálogo de productos organizados por categorías, registrar usuarios, generar pedidos con múltiples detalles y calcular automáticamente los importes totales de cada compra.

---

# Funcionalidades Principales

## Gestión de Categorías

* Crear categorías.
* Listar categorías activas.
* Modificar categorías existentes.
* Eliminar categorías mediante baja lógica.

## Gestión de Productos

* Crear productos asociados a una categoría.
* Listar productos activos.
* Modificar información de productos.
* Eliminar productos mediante baja lógica.
* Validación de precio y stock.

## Gestión de Usuarios

* Crear usuarios.
* Validar correos electrónicos duplicados.
* Modificar datos de usuarios.
* Eliminar usuarios mediante baja lógica.

## Gestión de Pedidos

* Crear pedidos asociados a usuarios.
* Agregar uno o varios productos por pedido.
* Calcular automáticamente subtotales y total general.
* Actualizar estado y forma de pago.
* Eliminar pedidos mediante baja lógica.
* Validar cantidades y stock disponible.

---

# Conceptos Aplicados

Durante el desarrollo se utilizaron diversos conceptos vistos en la materia:

* Programación Orientada a Objetos.
* Encapsulamiento.
* Herencia.
* Polimorfismo.
* Abstracción.
* Interfaces.
* Enumeraciones (Enums).
* Colecciones (ArrayList).
* Excepciones personalizadas.
* Arquitectura por capas.
* Patrón CRUD.
* Separación de responsabilidades.

---

# Arquitectura del Proyecto

El proyecto se encuentra organizado en paquetes para facilitar el mantenimiento y la escalabilidad.

## Entities

Contiene las entidades principales del dominio:

* Categoria
* Producto
* Usuario
* Pedido
* DetallePedido
* Base

## Services

Contiene la lógica de negocio y validaciones:

* CategoriaService
* ProductoService
* UsuarioService
* PedidoService

## Menu

Gestiona toda la interacción con el usuario mediante consola:

* Menu
* MenuCategorias
* MenuProductos
* MenuUsuarios
* MenuPedidos

## Interfaces

Interfaces utilizadas en el sistema:

* MenuPantalla
* Calculable

## Enums

Enumeraciones utilizadas:

* Estado
* FormaPago
* Rol

## Exception

Excepciones personalizadas para el control de errores:

* EntidadNoEncontradaException
* MailDuplicadoException
* PrecioInvalidoException
* StockInvalidoException
* DatoInvalidoException

---

# Tecnologías Utilizadas

* Java 21
* Apache NetBeans
* Windows PowerShell
* GitHub

---

# Ejecución del Proyecto

## Desde NetBeans

1. Abrir el proyecto en Apache NetBeans.
2. Ejecutar la clase Main.java.
3. Utilizar los menús disponibles para interactuar con el sistema.

## Desde PowerShell

Ubicarse en la carpeta raíz del proyecto y ejecutar:

```powershell
java -cp build\classes Integrador.prog2.Main
```

---

# Video Demostración

https://youtu.be/hXi0bXyVrmA

---

# Documentación

El informe académico se encuentra incluido dentro del repositorio en formato PDF.

Archivo:

Trabajo Practico Integrador Programación 2.pdf

---

# Autor

Guzmán Mauricio Gabriel

Tecnicatura Universitaria en Programación

Universidad Tecnológica Nacional
