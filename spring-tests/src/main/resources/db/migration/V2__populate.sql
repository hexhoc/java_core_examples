INSERT INTO customers (id, first_name, last_name, email, phone, is_deleted, created_date, modified_date, version)
VALUES
    (1, 'John', 'Doe', 'johndoe@example.com', '555-1234', false, '2022-10-01 10:00:00', '2022-10-01 10:00:00', 1),
    (2, 'Jane', 'Doe', 'janedoe@example.com', '555-5678', false, '2022-10-02 10:00:00', '2022-10-02 10:00:00', 1),
    (3, 'Bob', 'Smith', 'bobsmith@example.com', '555-9012', false, '2022-10-03 10:00:00', '2022-10-03 10:00:00', 1),
    (4, 'Alice', 'Johnson', 'alicejohnson@example.com', '555-3456', false, '2022-10-04 10:00:00', '2022-10-04 10:00:00', 1),
    (5, 'Tom', 'Jones', 'tomjones@example.com', '555-7890', false, '2022-10-05 10:00:00', '2022-10-05 10:00:00', 1);

INSERT INTO customer_contacts (customer_id, address)
VALUES
    (1, 'address 1'),
    (2, 'address 2'),
    (3, 'address 3'),
    (4, 'address 4'),
    (5, 'address 5');