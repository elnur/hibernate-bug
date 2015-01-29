CREATE TABLE person
(
    id uuid NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE employee
(
    id uuid NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES person
);

CREATE TABLE "group"
(
    id uuid NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE membership
(
    employee_id uuid NOT NULL,
    group_id    uuid NOT NULL,

    PRIMARY KEY (employee_id, group_id),
    FOREIGN KEY (employee_id) REFERENCES employee,
    FOREIGN KEY (group_id) REFERENCES "group"
);
