# challenge-sysone-ventas
La solución propuesta cuenta con dos servicios, el servicio servicio-ventas, es el encargado de manejar las ventas de los vehículos en el punto de venta y generar las facturas de las mismas.

Para cumplir con el propósito expone una API Rest con los siguientes métodos y se ha deployado en la nube con el servicio de ***Heroku***

&nbsp;
Si lo ejecutamos localmente el servicio va a escuchar en el puerto :8082

&nbsp;
***Crear una venta***

&nbsp;
Se debe llamar al servicio con el verbo POST
```
https://servicio-ventas.herokuapp.com/api/ventas/
```
Pasando como parámetro en el body un objeto ***JSON*** con el siguiente formato
```
   {
    "nombre" : "Dario",
    "apellido" : "Jolo",
    "modelo" : "Sedan",
    "precio" : 2233,
    "descripcionGarantia" : "Garantia extendida"
   } 
```
Y obtendremos como respuesta el objeto JSON creado en la base de datos, si el precio del vehículo está dentro del rango de precios permitidos
```
   {
    "id": 2,
    "nombre": "Dario",
    "apellido": "Jolo",
    "modelo": "Sedan",
    "precio": 2233.0,
    "descripcionGarantia": "Garantia extendida"
}
```
***Modificar una venta***

&nbsp;
Para modificar una venta (solo se puede modificar el modelo y precio del vehículo, ya que pedia actualizacion parcial) se debe llamar al siguiente método con el verbo PUT.

&nbsp;
Pasando como parámetro un ID de venta válido y un objeto JSON con los datos a modificar
```
https://servicio-ventas.herokuapp.com/api/ventas/2
```

```
   {
     "modelo" : "Sedan II",
     "precio" : 2232
   } 
```
Y obtendremos como resultado el objeto modificado
```
{
    "id": 2,
    "nombre": "Dario",
    "apellido": "Jolo",
    "modelo": "Sedan II",
    "precio": 2232.0,
    "descripcionGarantia": "Garantia extendida"
}
```
***Obtener información de una venta***

&nbsp;
Se debe llamar al siguiente método con el verbo GET y pasando como parámetro el ID de la venta a visualizar
```
https://servicio-ventas.herokuapp.com/api/ventas/2
```
Y obtendremos el siguiente resultado
```
{
    "id": 2,
    "nombre": "Dario",
    "apellido": "Jolo",
    "modelo": "Sedan",
    "precio": 2231.0,
    "descripcionGarantia": "Garantia extendida"
}
```

***Obtener un listado de las ventas / facturas***

&nbsp;
Se debe llamar al siguiente método con el verbo GET 
```
GET https://servicio-ventas.herokuapp.com/api/ventas/
```
Y obtendremos como resultado un listado de todas las ventas realizadas por el punto de venta
```
[
    {
        "id": 1,
        "nombre": "Dario",
        "apellido": "Jolo",
        "modelo": "Sedan",
        "precio": 33333.0,
        "descripcionGarantia": "Garantia extendida"
    },
    {
        "id": 2,
        "nombre": "Dario",
        "apellido": "Jolo",
        "modelo": "Sedan",
        "precio": 2231.0,
        "descripcionGarantia": "Garantia extendida"
    }
]
```
***Eliminar una venta de la BBDD**

&nbsp;
Si queremos eliminar una venta realizada, debemos llamar al siguiente método con el verbo DELETE pasando el ID de la factura a realizar
```
https://servicio-ventas.herokuapp.com/api/ventas/1
```
