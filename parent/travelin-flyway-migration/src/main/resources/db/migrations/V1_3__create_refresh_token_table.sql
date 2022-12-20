CREATE TABLE travelin."refresh_token"
(
    id               integer                  NOT NULL,
    database_version integer                  NOT NULL,
    create_date      timestamp with time zone NOT NULL,
    update_date      timestamp with time zone,
    user_id          integer                  NOT NULL,
    token            character varying(64)    NOT NULL,
    expiry_date      timestamp with time zone NOT NULL,

    CONSTRAINT refresh_token_pkey PRIMARY KEY (id),
    CONSTRAINT refresh_token_user_fk FOREIGN KEY (user_id)
        REFERENCES travelin."user" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)