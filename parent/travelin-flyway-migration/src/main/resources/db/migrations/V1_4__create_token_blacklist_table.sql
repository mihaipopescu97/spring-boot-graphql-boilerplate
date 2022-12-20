CREATE TABLE travelin."token_blacklist"
(
    token       character varying(256)   NOT NULL,
    expiry_date timestamp with time zone NOT NULL,

    CONSTRAINT token_blacklist_pkey PRIMARY KEY (token)
)