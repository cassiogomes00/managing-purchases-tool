--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3 (Ubuntu 13.3-1.pgdg20.10+1)
-- Dumped by pg_dump version 13.3 (Ubuntu 13.3-1.pgdg20.10+1)

-- Started on 2021-05-17 20:36:36 -03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 16453)
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    customer_id integer NOT NULL,
    customer_first_name character varying(50),
    customer_last_name character varying(50) NOT NULL,
    customer_bio character varying(250)
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16451)
-- Name: customer_customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.customer ALTER COLUMN customer_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.customer_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 203 (class 1259 OID 16460)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    product_id integer NOT NULL,
    product_description character varying(255) NOT NULL,
    product_base_price real DEFAULT 0,
    product_discount real DEFAULT 0
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16458)
-- Name: product_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.product ALTER COLUMN product_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.product_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 204 (class 1259 OID 16467)
-- Name: pucharse; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pucharse (
    customer_id integer NOT NULL,
    product_id integer NOT NULL,
    pucharse_product_quantity integer NOT NULL,
    pucharse_date date NOT NULL
);


ALTER TABLE public.pucharse OWNER TO postgres;

--
-- TOC entry 3164 (class 0 OID 16453)
-- Dependencies: 201
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (customer_id, customer_first_name, customer_last_name, customer_bio) FROM stdin;
\.


--
-- TOC entry 3166 (class 0 OID 16460)
-- Dependencies: 203
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (product_id, product_description, product_base_price, product_discount) FROM stdin;
\.


--
-- TOC entry 3167 (class 0 OID 16467)
-- Dependencies: 204
-- Data for Name: pucharse; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pucharse (customer_id, product_id, pucharse_product_quantity, pucharse_date) FROM stdin;
\.


--
-- TOC entry 3173 (class 0 OID 0)
-- Dependencies: 200
-- Name: customer_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_customer_id_seq', 6, true);


--
-- TOC entry 3174 (class 0 OID 0)
-- Dependencies: 202
-- Name: product_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_product_id_seq', 5, true);


--
-- TOC entry 3026 (class 2606 OID 16457)
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);


--
-- TOC entry 3028 (class 2606 OID 16466)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);


--
-- TOC entry 3030 (class 2606 OID 16471)
-- Name: pucharse pucharse_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pucharse
    ADD CONSTRAINT pucharse_pkey PRIMARY KEY (customer_id, product_id, pucharse_date);


--
-- TOC entry 3031 (class 2606 OID 16472)
-- Name: pucharse pk_customer_product; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pucharse
    ADD CONSTRAINT pk_customer_product FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- TOC entry 3032 (class 2606 OID 16477)
-- Name: pucharse pk_product_customer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pucharse
    ADD CONSTRAINT pk_product_customer FOREIGN KEY (product_id) REFERENCES public.product(product_id);


-- Completed on 2021-05-17 20:36:36 -03

--
-- PostgreSQL database dump complete
--

