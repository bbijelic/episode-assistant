PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: episode
DROP TABLE IF EXISTS episode;

CREATE TABLE episode (
    id           BIGINT CONSTRAINT episode_pk_constraint PRIMARY KEY ASC ON CONFLICT ROLLBACK,
    show_name    STRING CONSTRAINT show_name_null_constraint NOT NULL,
    episode_name STRING CONSTRAINT episode_name_null_constraint NOT NULL,
    season       INT    CONSTRAINT season_null_constraint NOT NULL,
    episode      INT    CONSTRAINT episode_null_constraint NOT NULL,
    status       INT    CONSTRAINT status_null_constraint NOT NULL
                        DEFAULT (0) 
);


-- Table: torrent
DROP TABLE IF EXISTS torrent;

CREATE TABLE torrent (
    id       BIGINT PRIMARY KEY
                    CONSTRAINT torrent_id_null_constraint NOT NULL,
    infohash STRING CONSTRAINT torrent_infohash_uq UNIQUE
                    CONSTRAINT torrent_infohash_null_constraint NOT NULL,
    file     BLOB   CONSTRAINT torrent_file_null_constraint NOT NULL,
    episode  BIGINT CONSTRAINT torrent_episode_fk REFERENCES episode (id) ON DELETE CASCADE
                                                                          ON UPDATE CASCADE
                    CONSTRAINT torrent_episode_null_constraint NOT NULL
);


COMMIT TRANSACTION;
PRAGMA foreign_keys = on;