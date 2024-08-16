CREATE TABLE "medicos" (
    "id_medico" serial PRIMARY KEY,
    "nome_medico" varchar (50) NOT NULL,
    "telefone" varchar(15) NOT NULL,
    "email" varchar(100) NOT NULL UNIQUE,
    "especializacao" varchar(500),
    "experiencia" varchar(1000),
    "status" varchar(7) NOT NULL
);

CREATE TABLE "clientes" (
    "id_cliente" serial PRIMARY KEY,
    "nome_cliente" varchar(100) NOT NULL,
    "cpf" varchar(18) NOT NULL UNIQUE,
    "telefone" varchar(15) NOT NULL,
    "email" varchar(100) NOT NULL UNIQUE,
    "id_medico" int NOT NULL,
    CONSTRAINT fk_medico FOREIGN KEY ("id_medico") REFERENCES "medicos" ("id_medico") ON DELETE CASCADE,
    "status" varchar(7) NOT NULL
);

CREATE TABLE "consultas" (
    "id_consulta" serial PRIMARY KEY,
    "data_consulta" date NOT NULL,
    "hora_consulta" varchar(6) NOT NULL,
    "id_cliente" int NOT NULL,
    "id_medico" int NOT NULL,
    CONSTRAINT fk_cliente_consulta FOREIGN KEY ("id_cliente") REFERENCES "clientes" ("id_cliente") ON DELETE CASCADE,
    CONSTRAINT fk_consultor_consulta FOREIGN KEY ("id_medico") REFERENCES "medicos" ("id_medico") ON DELETE CASCADE
);
