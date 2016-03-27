-- Table: public.message_log

-- DROP TABLE public.message_log;

CREATE TABLE public.message_log
(
  id serial NOT NULL,
  type text,
  user_id integer,
  subject text,
  content text,
  email_from text,
  email_to text,
  status integer,
  status_timestamp timestamp with time zone,
  err_message text,
  "timestamp" timestamp with time zone DEFAULT now(),
  uuid text,
  CONSTRAINT "PK_message_log" PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.message_log
  OWNER TO postgres;
