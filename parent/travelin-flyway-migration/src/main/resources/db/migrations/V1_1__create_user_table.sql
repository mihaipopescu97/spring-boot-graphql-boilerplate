CREATE TABLE IF NOT EXISTS travelin."user"
(
    id                     integer                  NOT NULL,
    database_version       integer                  NOT NULL,
    create_date            timestamp with time zone NOT NULL,
    update_date            timestamp with time zone,
    name                   character varying(64)    NOT NULL,
    username               character varying(64)    NOT NULL,
    password               character varying(64)    NOT NULL,
    email                  character varying(64)    NOT NULL,
    role                   character varying(10)    NOT NULL,
    is_expired             boolean                  NOT NULL,
    is_locked              boolean                  NOT NULL,
    is_credentials_expired boolean                  NOT NULL,
    is_enabled             boolean                  NOT NULL,

    CONSTRAINT user_pkey PRIMARY KEY (id)
)