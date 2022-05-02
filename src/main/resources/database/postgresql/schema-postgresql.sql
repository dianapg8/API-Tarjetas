CREATE SCHEMA tarjetas_schema;
create table tarjetas_schema.tarjetas
(
    id_tarjeta     bigint           not null
        primary key,
    nombre_tarjeta varchar(255)     not null,
    preferencia    varchar(255)     not null,
    edad_min       integer          not null,
    edad_max       integer          not null,
    salario_min    double precision not null,
    salario_max    double precision
);

alter table tarjetas_schema.tarjetas
    owner to postgres;