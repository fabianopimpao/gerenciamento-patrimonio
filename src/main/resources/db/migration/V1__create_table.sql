create table marca (
	id uuid default uuid_generate_v4(),
	nome varchar(255) not null,
	primary key(id)
);

create table patrimonio (
	id uuid default uuid_generate_v4(),
	marca_id uuid not null,
	nome varchar(255) not null,
	descricao varchar(255),
	numero_tombo uuid,
	primary key(id),
	foreign key (marca_id) references marca (id)
);

create table usuario (
	id uuid default uuid_generate_v4(),
	nome varchar(255) not null,
	email varchar(255) not null,
	senha varchar(255) not null,
	primary key (id)
);

create table perfil (
	id uuid default uuid_generate_v4(),
	nome varchar(255) not null,
	primary key (id)
);