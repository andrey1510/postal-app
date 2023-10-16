DROP TABLE IF EXISTS postal_updates;
CREATE TABLE postal_updates (
   id UUID,
   postal_item_id UUID,
   status VARCHAR(255),
   timestamp TIMESTAMP,
   PRIMARY KEY (id)
);