CREATE TABLE departamento
(
  id SERIAL PRIMARY KEY,
  sigla varchar(10) NOT NULL,
  nome text NOT NULL,
  CONSTRAINT departamento_unique_sigla UNIQUE (sigla)
);

CREATE TABLE curso
(
  id SERIAL PRIMARY KEY,
  sigla text NOT NULL,
  nome text NOT NULL,
  departamento_id integer NOT NULL,
  CONSTRAINT curso_unique_sigla UNIQUE (sigla),
  CONSTRAINT fk_departamento FOREIGN KEY (departamento_id) REFERENCES departamento (id)
);

CREATE TABLE consumidor
(
  id SERIAL PRIMARY KEY,
  cpf character(11) NOT NULL,
  tipo text NOT NULL,
  nome text NOT NULL,
  sexo text NOT NULL,
  matricula character(10) NOT NULL,
  ano_ingresso integer NOT NULL,
  titulo text NOT NULL,
  CONSTRAINT consumidor_unique_cpf UNIQUE (cpf),
  CONSTRAINT consumidor_unique_matricula UNIQUE (matricula),
  CONSTRAINT sexos_permitidos CHECK (sexo = 'MASCULINO' OR sexo = 'FEMININO'),
  CONSTRAINT tipos_consumidor_permitidos CHECK (tipo = 'ALUNO' OR tipo = 'FUNCIONARIO'),
  CONSTRAINT titulos_permitidos CHECK (titulo = 'ESPECIALIZACAO' OR titulo = 'MESTRADO' OR titulo = 'DOUTORADO')
);

CREATE TABLE aluno
(
  id SERIAL PRIMARY KEY,
  cpf character(11) NOT NULL,
  curso_id integer NOT NULL,
  CONSTRAINT aluno_unique_cpf UNIQUE(cpf),
  CONSTRAINT fk_id_consumidor FOREIGN KEY (id) REFERENCES consumidor (id),
  CONSTRAINT fk_id_curso FOREIGN KEY (curso_id) REFERENCES curso (id)
);

CREATE TABLE funcionario
(
  id SERIAL PRIMARY KEY,
  cpf character(11) NOT NULL,
  departamento_id integer NOT NULL,
  CONSTRAINT fk_id_consumidor FOREIGN KEY (id) REFERENCES consumidor (id),
  CONSTRAINT funcionario_unique_cpf UNIQUE(cpf),
  CONSTRAINT fk_id_departamento FOREIGN KEY (departamento_id) REFERENCES departamento (id)
);

CREATE TABLE refeicao
(
	id SERIAL PRIMARY KEY,
	tipo varchar(10) NOT NULL,
	turno varchar(10) NOT NULL,
	descricao varchar(100) NOT NULL,
	opcao_vegetariana varchar(100) NOT NULL,
	data date NOT NULL
);

CREATE TABLE ticket (
	id SERIAL PRIMARY KEY,
	refeicao_id INTEGER,
	consumidor_id INTEGER,
	preco decimal(5,2) NOT NULL,
	data_compra date NOT NULL,
	situacao_compra boolean,
	CONSTRAINT refeicao_fkey FOREIGN KEY (refeicao_id) REFERENCES refeicao(id),
	CONSTRAINT consumidor_fkey FOREIGN KEY (consumidor_id) REFERENCES consumidor(id)
);