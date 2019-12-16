CREATE TABLE public.usuario
(
  id_usuario numeric NOT NULL,
  nome character varying,
  senha character varying,
  doc_rg character varying(20),
  doc_cpf numeric(11,0),
  CONSTRAINT chave PRIMARY KEY (id_usuario)
);

CREATE TABLE public.endereco
(
  id_endereco numeric NOT NULL,
  rua character varying,
  numero character varying,
  bairro character varying,
  estado character(2),
  municipio character varying,
  cep numeric(8,0),
  usua_id_usuario numeric NOT NULL,
  CONSTRAINT pk_end PRIMARY KEY (id_endereco),
  CONSTRAINT pk_usuario_endereco FOREIGN KEY (usua_id_usuario)
      REFERENCES public.usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.telefone
(
  id_tel numeric NOT NULL,
  id_usuario_tel numeric NOT NULL,
  ddd integer,
  numero integer,
  operadora character varying(30),
  CONSTRAINT pk_tel PRIMARY KEY (id_tel),
  CONSTRAINT fk_usuario_tel FOREIGN KEY (id_usuario_tel)
      REFERENCES public.usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
