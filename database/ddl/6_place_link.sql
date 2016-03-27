-- Table: public.place_link

-- DROP TABLE public.place_link;

CREATE TABLE public.place_link
(
  id serial NOT NULL,
  site_name text,
  url text,
  place_id integer,
  CONSTRAINT "PK_links_id" PRIMARY KEY (id),
  CONSTRAINT "FK_links_place" FOREIGN KEY (place_id)
      REFERENCES public.place (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.place_link
  OWNER TO postgres;
