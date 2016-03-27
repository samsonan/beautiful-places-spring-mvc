-- Table: public.image

-- DROP TABLE public.image;

CREATE TABLE public.image
(
  id serial NOT NULL,
  place_id integer,
  title text,
  description text,
  created date,
  content bytea,
  updated timestamp without time zone,
  content_type text,
  file_name text NOT NULL,
  path text,
  thumb_file_name text,
  author_txt text,
  src_url text,
  CONSTRAINT "PK_photo_id" PRIMARY KEY (id),
  CONSTRAINT "FK_photo_place" FOREIGN KEY (place_id)
      REFERENCES public.place (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.image
  OWNER TO postgres;
