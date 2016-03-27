-- Table: public.place_type

-- DROP TABLE public.place_type;

CREATE TABLE public.place_type
(
  place_id integer NOT NULL,
  type text NOT NULL,
  CONSTRAINT "PK_place_type" PRIMARY KEY (place_id, type),
  CONSTRAINT "FK_place_type_place_id" FOREIGN KEY (place_id)
      REFERENCES public.place (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.place_type
  OWNER TO postgres;
