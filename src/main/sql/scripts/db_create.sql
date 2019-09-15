CREATE TABLE IF NOT EXISTS courier
(
    id    INTEGER PRIMARY KEY AUTOINCREMENT,
    name  TEXT NOT NULL,
    image TEXT
);

CREATE TABLE IF NOT EXISTS product
(
    id            INTEGER PRIMARY KEY AUTOINCREMENT,
    state_id      INTEGER,
    courier_id    INTEGER,
    name          TEXT    NOT NULL,
    url           TEXT    NOT NULL,
    quantity      INTEGER NOT NULL,
    with_box      INTEGER NOT NULL,
    delivery_date DATE    NOT NULL,
    detail        TEXT,
    image         TEXT,

    FOREIGN KEY (state_id) REFERENCES product_state (id),
    FOREIGN KEY (courier_id) REFERENCES courier (id)
);

CREATE TABLE IF NOT EXISTS product_state
(
    id   INTEGER PRIMARY KEY,
    name TEXT NOT NULL
)