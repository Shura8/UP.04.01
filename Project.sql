
CREATE TABLE car
(
    _id bigint NOT NULL,
    _brand character varying(20) COLLATE pg_catalog."default",
    _color character varying(20) COLLATE pg_catalog."default",
    _deposit integer NOT NULL,
    _issue_year integer NOT NULL,
    _passport character varying(10) COLLATE pg_catalog."default",
    _state_number character varying(9) COLLATE pg_catalog."default",
    _class_car__id bigint,
    _status_car_id bigint,
    CONSTRAINT car_pkey PRIMARY KEY (_id),
    CONSTRAINT fkawky8ku5boly91fqw69dfrcgg FOREIGN KEY (_class_car__id)
        REFERENCES public.class_car (_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fki7mmnb6coxmg519av4w25shtw FOREIGN KEY (_status_car_id)
        REFERENCES public.status_car (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT car__deposit_check CHECK (_deposit >= 0),
    CONSTRAINT car__issue_year_check CHECK (_issue_year >= 0)
);
CREATE TABLE class_car
(
    _id bigint NOT NULL,
    _day_cost integer NOT NULL,
    _name_class character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT class_car_pkey PRIMARY KEY (_id),
    CONSTRAINT class_car__day_cost_check CHECK (_day_cost >= 0)
);
CREATE TABLE fine
(
    _id bigint NOT NULL,
    _cost_fine integer NOT NULL,
    _name_fine character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT fine_pkey PRIMARY KEY (_id),
    CONSTRAINT fine__cost_fine_check CHECK (_cost_fine >= 0)
);
CREATE TABLE maintenance
(
    _id bigint NOT NULL,
    _date date,
    _service_cost integer NOT NULL,
    _car__id bigint,
    CONSTRAINT maintenance_pkey PRIMARY KEY (_id),
    CONSTRAINT fkdi30qm2l72y6at3bcvtyukqua FOREIGN KEY (_car__id)
        REFERENCES public.car (_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT maintenance__service_cost_check CHECK (_service_cost >= 0)
);
CREATE TABLE rental
(
    _id bigint NOT NULL,
    _cost_rental integer NOT NULL,
    _date_rental date,
    _car__id bigint,
    _user__id bigint,
    CONSTRAINT rental_pkey PRIMARY KEY (_id),
    CONSTRAINT fk1njisdsb2kh8t2x97d2422a7g FOREIGN KEY (_user__id)
        REFERENCES public.users (_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkexwi2htwid7xmo5mrlwimp03c FOREIGN KEY (_car__id)
        REFERENCES public.car (_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rental__cost_rental_check CHECK (_cost_rental >= 0)
);
CREATE TABLE role
(
    id bigint NOT NULL,
    name character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT role_pkey PRIMARY KEY (id)
);
CREATE TABLE status_car
(
    id bigint NOT NULL,
    name character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT status_car_pkey PRIMARY KEY (id)
);
CREATE TABLE users
(
    _id bigint NOT NULL,
    age integer NOT NULL,
    fio character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    _role_id bigint,
    CONSTRAINT users_pkey PRIMARY KEY (_id),
    CONSTRAINT fkewjf24qe6pxqfa7xukg3w75m0 FOREIGN KEY (_role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT users_age_check CHECK (age >= 0)
);