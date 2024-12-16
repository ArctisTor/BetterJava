--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2024-11-08 14:07:39

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16408)
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


-- ALTER TABLE public.talent OWNER TO admin;

--
-- TOC entry 4791 (class 0 OID 16408)
-- Dependencies: 217
-- Data for Name: talent; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.talent (talent_id, talent_name, talent_debut, talent_birthday, talent_organization, talent_unit, talent_height, talent_fan_name) FROM stdin;
dffa7cc7-230a-42bf-b880-02be59133bfe	Shirakami Fubuki	2018-06-01	2018-10-05	Hololive	Hololive 1st Gen/Gamers	155	Friends
4e5fade6-8b38-4551-8de4-bf60e79b4482	Nakia Sata	2021-10-09	2021-01-13	Indie	Indie	159.5	Satalis
e246b6e8-41a7-4084-bb8c-8e4b059c2eb9	Hoshimachi Suisei	2018-03-18	2018-03-22	Hololive	Hololive Generation 0	160	Hoshiyomi (Stargazers)
\.


--
-- TOC entry 2043 (class 826 OID 16422)
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER TABLE ONLY public.talent
    ADD CONSTRAINT talent_pkey PRIMARY KEY (talent_id);

-- ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TABLES TO admin;


-- Completed on 2024-11-08 14:07:39

--
-- PostgreSQL database dump complete
--

