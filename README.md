# apiRestEmployee

1. Web service que permita agregar un nuevo empleado POST /employees createEmployee.
	Se debe validar que el nombre y apellido del empleado no existan, que el empleado sea mayor de edad y que el género y puesto existan en sus tablas correspondientes.

2. Web service que permita agregar horas trabajadas de un empleado POST /employees/hours createEmployeeWorkedHour.
	Se debe validar que el empleado exista, que el total de horas trabajadas no sea mayor a 20 horas y que la fecha de trabajo sea menor o igual a la actual y no se duplique por empleado (un empleado sólo puede tener un registro de horas trabajadas por día).

3. Web service que permita consultar los empleados por puesto GET /employees/jobs/{jobs_id} getEmployeesByJobsId.
	Se debe validar que el puesto exista.

4. Web service que permita consultar el total de horas trabajadas de un empleado por rango de fechas POST /employees/hours/hoursWorked getEmployeeHoursWorked.
	Se debe validar que el empleado exista y que la fecha de inicio sea menor a la fecha de fin.

5. Realiza un web service que permita consultar cuanto se le pagó a un empleado en un rango de fechas POST /employees/hours/hoursWorked/payments getEmployeeHoursWorkedPayed.
	Se debe validar que el empleado exista y que la fecha de inicio sea menor a la fecha de fin.

![image](https://user-images.githubusercontent.com/18336092/213841016-48c3a90b-c9c7-4ca8-bef5-1d643a1ac669.png)

Para correr los microservicios, poner en consola (en la carpeta apiRestEmployee/employee-app/target): java -jar employee-app-0.0.1-SNAPSHOT.jar

Con este comando será posible levantar los microservicios y la base de datos embebida H2 que cuenta con las tablas de Genders y Jobs previamente llenadas.
