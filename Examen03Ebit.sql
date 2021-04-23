/*Creacion tablas*/
Create table cajeros(
cajero  integer primary key,
NomApels varchar(255));


create table maquinas_registradoras(
  maquina  integer primary key,
  piso integer
);


create table productos(
producto  integer primary key,
nombre varchar(255),
precio money);


create table Venta(
cajero integer,
maquina integer,
producto integer
);

alter table Venta
add constraint FK_cajero
foreign key (cajero)
references cajeros(cajero);


alter table Venta
add constraint FK_maquina
foreign key (maquina)
references maquinas_registradoras(maquina);

alter table Venta
add constraint FK_producto
foreign key (producto)
references productos(producto);







/*1.-LLENAR TABLAS*/

/*9 CAJEROS*/
insert into  cajeros(cajero,NomApels)values(1,'Gibran Matías Jacinto');
insert into  cajeros(cajero,NomApels)values(2,'Cirilo Torres Guzman');
insert into  cajeros(cajero,NomApels)values(3,'Ana Rodriguez Alaniz');
insert into  cajeros(cajero,NomApels)values(4,'Alejandra Guzman');
insert into  cajeros(cajero,NomApels)values(5,'Raymundo Cervantes');
insert into  cajeros(cajero,NomApels)values(6,'Miranda Juarez');
insert into  cajeros(cajero,NomApels)values(7,'Maribel Albino');
insert into  cajeros(cajero,NomApels)values(8,'Juan Escutia');
insert into  cajeros(cajero,NomApels)values(9,'Ignacio Cervantes');



/*9 maquinas registradoras, 3 en cada piso*/
insert into  maquinas_registradoras(maquina,piso) values (1,1);
insert into  maquinas_registradoras(maquina,piso) values (2,1);
insert into  maquinas_registradoras(maquina,piso) values (3,1);

insert into  maquinas_registradoras(maquina,piso) values (4,2);
insert into  maquinas_registradoras(maquina,piso) values (5,2);
insert into  maquinas_registradoras(maquina,piso) values (6,2);

insert into  maquinas_registradoras(maquina,piso) values (7,3);
insert into  maquinas_registradoras(maquina,piso) values (8,3);
insert into  maquinas_registradoras(maquina,piso) values (9,3);


/*6 productos*/
insert into productos (producto,nombre,precio) values (1,'Cemento',300)
insert into productos (producto,nombre,precio) values (2,'ladrillos',20);
insert into productos (producto,nombre,precio) values (3,'arena',500);
insert into productos (producto,nombre,precio) values (4,'varillas',200);
insert into productos (producto,nombre,precio) values (5,'grava',400);
insert into productos (producto,nombre,precio) values (6,'tabicones',30);

/*ventas de cajero1*/
insert into Venta(cajero,maquina,producto) values (1,1,1);
insert into Venta(cajero,maquina,producto) values (1,1,1);
insert into Venta(cajero,maquina,producto) values (1,1,3);
insert into Venta(cajero,maquina,producto) values (1,1,3);
insert into Venta(cajero,maquina,producto) values (1,1,6);

/*ventas de cajero2*/
insert into Venta(cajero,maquina,producto) values (2,2,1);
insert into Venta(cajero,maquina,producto) values (2,2,4);
insert into Venta(cajero,maquina,producto) values (2,2,5);

/*ventas de cajero3*/
insert into Venta(cajero,maquina,producto) values (3,3,5);
insert into Venta(cajero,maquina,producto) values (3,3,5);
insert into Venta(cajero,maquina,producto) values (3,3,6);
insert into Venta(cajero,maquina,producto) values (3,3,6);


/*ventas de cajero4*/

insert into Venta(cajero,maquina,producto) values (4,4,1);
insert into Venta(cajero,maquina,producto) values (4,4,4);


/*ventas de cajero5*/

insert into Venta(cajero,maquina,producto) values (5,5,1);
insert into Venta(cajero,maquina,producto) values (5,5,2);
insert into Venta(cajero,maquina,producto) values (5,5,2);


/*ventas de cajero6*/
insert into Venta(cajero,maquina,producto) values (6,6,1);
insert into Venta(cajero,maquina,producto) values (6,6,2);
insert into Venta(cajero,maquina,producto) values (6,6,2);
insert into Venta(cajero,maquina,producto) values (6,6,4);
insert into Venta(cajero,maquina,producto) values (6,6,6);
insert into Venta(cajero,maquina,producto) values (6,6,2);

/*ventas de cajero7*/
insert into Venta(cajero,maquina,producto) values (7,7,4);


/*ventas de cajero8*/

insert into Venta(cajero,maquina,producto) values (8,8,1);
insert into Venta(cajero,maquina,producto) values (8,8,3);
insert into Venta(cajero,maquina,producto) values (8,8,3);
insert into Venta(cajero,maquina,producto) values (8,8,4);

/*ventas de cajero9*/
insert into Venta(cajero,maquina,producto) values (9,9,1);
insert into Venta(cajero,maquina,producto) values (9,9,3);



/* numero de ventas de cada producto, ordenado de mas a menos ventas*/

select p.producto,count(v.producto)as cantidadVentas from productos p left join venta v on p.producto=v.producto
group by p.producto
order by cantidadVentas desc;



/*obtener informe completo de ventas, indicando el nombre del cajero que realizó la venta, nombre y precios de los productos vendidos
, y el piso donde se encuentra la maquina registradora que realizo la venta*/
select c.NomApels as cajeroEncargado,p.nombre as productoVendido,p.precio,maq.piso
from Venta v
left join cajeros c on c.cajero=v.cajero
left join productos p on p.producto=v.producto
left join maquinas_registradoras maq on maq.maquina=v.maquina
order by maq.piso;


/*obtener las ventas totales realizadas en cada piso*/
select maq.piso,count(v.producto)as cantidadVentas from 
maquinas_registradoras maq left join Venta v
on maq.maquina=v.maquina
group by maq.piso
order by maq.piso;


/*obtener el codigo y el nombre de cada cajero junto con el importe total de sus ventas*/
select c.cajero,c.NomApels,sum(p.precio) as importeTotal
from cajeros c left join Venta v on c.cajero=v.cajero
left join productos p on v.producto=p.producto
group by c.cajero
order by c.cajero;

/*obtener el codigo y nombre de aquellos cajeros que hayan realizado ventas en pisos cuyas ventas totales
sean inferiores a 5000 pesos*/
select c.cajero,c.NomApels from cajeros c
left join venta v on c.cajero=v.cajero
left join maquinas_registradoras maq on v.maquina=maq.maquina
left join
(select maq.piso,sum(p.precio)as totalPiso
	from productos p left join Venta v on v.producto=p.producto
	left join maquinas_registradoras maq on maq.maquina=v.maquina
	Group by maq.piso
	) as tabla1  on tabla1.piso=maq.piso 
	where tabla1.totalPiso::numeric::int < 5000;


