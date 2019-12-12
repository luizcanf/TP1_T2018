
CREATE TABLE endereco (
    id_endereco numeric NOT NULL,
    rua character varying,
    numero character varying,
    bairro character varying,
    estado character(2),
    municipio character varying,
    cep numeric(8,0),
    usua_id_usuario numeric NOT NULL
);


CREATE TABLE usuario (
    id_usuario numeric NOT NULL,
    nome character varying,
    senha character varying,
    doc_rg character varying(20),
    doc_cpf numeric(11,0)
);


ALTER TABLE ONLY usuario
    ADD CONSTRAINT chave PRIMARY KEY (id_usuario);

ALTER TABLE ONLY endereco
    ADD CONSTRAINT pk_end PRIMARY KEY (id_endereco);

CREATE INDEX fki_pk_usuario_endereco ON public.endereco USING btree (usua_id_usuario);


ALTER TABLE ONLY endereco
    ADD CONSTRAINT pk_usuario_endereco FOREIGN KEY (usua_id_usuario) REFERENCES usuario(id_usuario);
