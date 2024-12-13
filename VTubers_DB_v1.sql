--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2024-12-13 13:05:15

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4798 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16389)
-- Name: talent; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.talent (
    talent_id uuid DEFAULT gen_random_uuid() NOT NULL,
    talent_name character varying(200) NOT NULL,
    talent_debut date DEFAULT CURRENT_DATE,
    talent_birthday date NOT NULL,
    talent_organization character varying(200) DEFAULT 'Indie'::character varying,
    talent_unit character varying(200) DEFAULT 'Indie'::character varying,
    talent_height numeric DEFAULT 159.5,
    talent_fan_name character varying(200)
);


ALTER TABLE public.talent OWNER TO postgres;

--
-- TOC entry 4792 (class 0 OID 16389)
-- Dependencies: 217
-- Data for Name: talent; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.talent (talent_id, talent_name, talent_debut, talent_birthday, talent_organization, talent_unit, talent_height, talent_fan_name) FROM stdin;
c62d8056-165e-45b8-b5de-6a26256d8024	Shirakami Fubuki	2018-06-01	2018-10-05	Hololive	Hololive 1st Gen/Gamers	155	Friends
da54c15a-c61e-4f80-bab2-0aed30a75bf6	Nakia Sata	2021-10-09	2021-01-13	Indie	Indie	159.5	Satalis
ff7a52bd-2fc7-4d16-bf56-bc260cb1e682	Hoshimachi Suisei	2018-03-18	2018-03-22	Hololive	Hololive Generation 0	160	Hoshiyomi (Stargazers)
\.


--
-- TOC entry 4646 (class 2606 OID 24579)
-- Name: talent talent_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.talent
    ADD CONSTRAINT talent_pkey PRIMARY KEY (talent_id);


-- Completed on 2024-12-13 13:05:15

--
-- PostgreSQL database dump complete
--

