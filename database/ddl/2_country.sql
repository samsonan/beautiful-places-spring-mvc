-- Table: public.country

-- DROP TABLE public.country;

CREATE TABLE public.country
(
  iso_2 character(2) NOT NULL, -- ISO 3166-1 alpha-2 country code
  name text,
  region text,
  iso_3 character(3),
  subregion text,
  capital text,
  subregion_code text,
  CONSTRAINT "PK_id" PRIMARY KEY (iso_2)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.country
  OWNER TO postgres;
COMMENT ON COLUMN public.country.iso_2 IS 'ISO 3166-1 alpha-2 country code';

