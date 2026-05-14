# Revisión de código (Main y clases relacionadas)

Fecha: 2026-05-14

Este documento resume **qué problemas estaban ocurriendo** (principalmente inconsistencias de variables/llamados y comportamiento raro en consola) y **qué correcciones menores** se aplicaron, sin aumentar la complejidad del proyecto.

## 1) Problema: comparación incorrecta de `String` en Java (`==`)

**Dónde pasaba:** `Vehiculo.java` en `generarPlaca(String ultimaPlaca)`.

**Qué ocurría:**
- Se usaba `if (ultimaPlaca == "0")`.
- En Java, `==` compara **referencias** (si es el mismo objeto), no el contenido del texto.
- Resultado: a veces **no entraba** al caso base aunque `ultimaPlaca` “pareciera” ser `"0"`, y eso podía disparar errores al hacer `substring` / `parseInt` sobre una placa que no tenía el formato esperado.

**Arreglo aplicado:**
- Se cambió a comparación por contenido: `"0".equals(ultimaPlaca)`.
- Además se agregó tolerancia a `null`: `ultimaPlaca == null || "0".equals(ultimaPlaca)`.

**Efecto:**
- La primera placa se genera de forma consistente como `AAA000` cuando la flota está vacía.

## 2) Problema: ciudades con formato inconsistente (mayúsculas/espacios)

**Dónde pasaba:** `Ubicacion.java` y el flujo de entrada en `Main.java`.

**Qué ocurría:**
- El mapa `ubicaciones` tiene claves exactas: `Medellin`, `Bogota`, etc.
- Si el usuario ingresaba `bogota`, `BOGOTA`, ` Bogota `, etc., entonces:
  - `esUbicacionValida(...)` podía fallar (por espacios) o funcionar a medias,
  - y `obtenerDistancia(origen, destino)` fallaba porque estaba usando el texto tal cual fue ingresado.

**Arreglo aplicado (menor, sin cambiar lógica de negocio):**
- Se agregó `Ubicacion.normalizarCiudad(String)` para:
  - hacer `trim()`,
  - capitalizar (`"bogota"` → `"Bogota"`).
- `Ubicacion.obtenerDistancia(...)` ahora normaliza `origen` y `destino` antes de buscar en el `Map`.
- En `Main.crearVehiculo()` si la ubicación es válida, se guarda normalizada para mantener consistencia en toda la app.

**Efecto:**
- El usuario puede escribir ciudades con diferentes mayúsculas o espacios sin romper el cálculo de distancias.

## 3) Problema: `Scanner` duplicado en búsqueda (comportamiento raro al leer)

**Dónde pasaba:** `Buscador.java`.

**Qué ocurría:**
- `Buscador.buscarVehiculo(...)` creaba un `new Scanner(System.in)` aparte.
- `Main` ya tenía su propio `Scanner` global.
- Tener **dos `Scanner`** leyendo de `System.in` puede producir:
  - lecturas “saltadas”,
  - entradas vacías inesperadas,
  - comportamiento inconsistente, especialmente cuando se mezcla `nextInt()` y `nextLine()` en el flujo.

**Arreglo aplicado:**
- `Buscador.buscarVehiculo(...)` ahora recibe el mismo `Scanner` del `Main`:
  - Firma nueva: `buscarVehiculo(ArrayList<Vehiculo> flota, Scanner scanner)`
  - Llamado actualizado desde `Main`: `Buscador.buscarVehiculo(flota, scanner)`
- También se aplica `trim()` a la placa ingresada para evitar fallos por espacios.

**Efecto:**
- Entrada por consola más estable y consistente (un solo origen de lectura).

## 4) Detalles menores en `Main` (mensajes y validación simple)

**Dónde pasaba:** `Main.java`.

**Qué ocurría / mejoras aplicadas:**
- Mensajes en consola tenían caracteres mal codificados (tildes/“Ã³”, “Â¡”, etc.). Se reemplazaron por textos correctos en el archivo.
- Se agregó `trim()` a entradas de conductor/marca/tipo/origen/destino para evitar espacios accidentales.
- Si el usuario ingresa un tipo distinto a `Furgon` o `Camioneta`, antes no había feedback claro; ahora se muestra un error simple.

> Nota: no se cambió el flujo del menú ni se agregó complejidad (persistencia, submenús, etc.). Sólo se mejoró robustez de entrada y mensajes.

## 5) Limpieza menor: import no usado

**Dónde pasaba:** `Archivo.java`.

**Qué ocurría:**
- Se importaba `java.io.File` pero no se usaba.

**Arreglo aplicado:**
- Se eliminó el import no usado.

## Limitación del entorno (compilación)

En este entorno no fue posible compilar porque no existe `javac` disponible en el PATH (no hay JDK configurado).

En tu máquina, cuando tengas Java instalado, deberías poder compilar desde la carpeta del proyecto con:

```bash
javac *.java
```

y ejecutar con:

```bash
java Main
```

