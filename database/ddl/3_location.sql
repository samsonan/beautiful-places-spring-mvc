-- Table: public.location

-- DROP TABLE public.location;

CREATE TABLE public.location
(
  id serial NOT NULL,
  name text,
  country_code text,
  CONSTRAINT "PK_regions" PRIMARY KEY (id),
  CONSTRAINT "FK_region_country" FOREIGN KEY (country_code)
      REFERENCES public.country (iso_2) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.location
  OWNER TO postgres;
