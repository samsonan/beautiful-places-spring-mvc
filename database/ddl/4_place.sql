-- Table: public.place

-- DROP TABLE public.place;

CREATE TABLE public.place
(
  title text,
  description text,
  id serial NOT NULL,
  created timestamp with time zone DEFAULT now(),
  updated timestamp with time zone,
  status integer NOT NULL,
  lat numeric,
  lon numeric,
  parent_id integer,
  is_unesco boolean NOT NULL,
  created_by integer,
  updated_by integer,
  location_id integer,
  unesco_url text,
  category integer,
  country_code character(2),
  CONSTRAINT "PK_place_id" PRIMARY KEY (id),
  CONSTRAINT "FK_country" FOREIGN KEY (country_code)
      REFERENCES public.country (iso_2) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_created_by" FOREIGN KEY (created_by)
      REFERENCES public.bplace_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_location_region" FOREIGN KEY (location_id)
      REFERENCES public.location (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "FK_updated_by" FOREIGN KEY (updated_by)
      REFERENCES public.bplace_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.place
  OWNER TO postgres;
