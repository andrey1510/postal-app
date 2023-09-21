DROP TABLE IF EXISTS packing_element;
CREATE TABLE packing_element (
   postal_item_id UUID,
   element_number INT,
   title VARCHAR(255),
   price NUMERIC,
   weight NUMERIC,
   PRIMARY KEY (postal_item_id, element_number)
);