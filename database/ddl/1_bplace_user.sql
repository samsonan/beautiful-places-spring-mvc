-- Table: public.bplace_user

-- DROP TABLE public.bplace_user;

CREATE TABLE public.bplace_user
(
  id serial NOT NULL,
  email text,
  name text,
  first_name text,
  last_name text,
  password text,
  role text,
  status integer NOT NULL,
  CONSTRAINT "PK_user_id" PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.bplace_user
  OWNER TO postgres;
