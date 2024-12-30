--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2024-12-30 14:30:55

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
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

-- CREATE SCHEMA public;


-- ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4819 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 24594)
-- Name: organization; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization (
    id character varying(255) DEFAULT gen_random_uuid() NOT NULL,
    name character varying(255) NOT NULL,
    owner character varying(255),
    website character varying(255),
    founded date,
    industry character varying(255),
    brands character varying(255)[] DEFAULT '{}'::character varying[],
    founder character varying(255)
);


-- ALTER TABLE public.organization OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16389)
-- Name: talent; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.talent (
    talent_id character varying(255) DEFAULT gen_random_uuid() NOT NULL,
    talent_name character varying(255) NOT NULL,
    talent_debut character varying(255) DEFAULT CURRENT_DATE,
    talent_birthday character varying(255) NOT NULL,
    talent_organization character varying(255) DEFAULT 'fed7d4bf-2264-4aa5-a43a-9f2e9949e4f8'::uuid,
    talent_unit character varying(255) DEFAULT 'Indie'::character varying,
    talent_height double precision DEFAULT 159.5,
    talent_fan_name character varying(255)
);


-- ALTER TABLE public.talent OWNER TO postgres;

--
-- TOC entry 4813 (class 0 OID 24594)
-- Dependencies: 219
-- Data for Name: organization; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organization (id, name, owner, website, founded, industry, brands, founder) FROM stdin;
8fc96e59-ff45-4f17-851f-9f0b1c86424b	Hololive Production	Cover Corporation	https://hololivepro.com/en/	2016-06-13	Virtual YouTuber agency	{"Hololive JP","Hololive ID","Hololive EN","Holostars JP","Hololive EN"}	Motoaki Tanigo
fed7d4bf-2264-4aa5-a43a-9f2e9949e4f8	Independent VTuber	Independent	No single website	\N	Virtual YouTuber agency	{}	Usually the talent themselves
\.


--
-- TOC entry 4812 (class 0 OID 16389)
-- Dependencies: 218
-- Data for Name: talent; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.talent (talent_id, talent_name, talent_debut, talent_birthday, talent_organization, talent_unit, talent_height, talent_fan_name) FROM stdin;
c62d8056-165e-45b8-b5de-6a26256d8024	Shirakami Fubuki	2018-06-01	2018-10-05	8fc96e59-ff45-4f17-851f-9f0b1c86424b	Hololive 1st Gen/Gamers	155	Friends
da54c15a-c61e-4f80-bab2-0aed30a75bf6	Nakia Sata	2021-10-09	2021-01-13	fed7d4bf-2264-4aa5-a43a-9f2e9949e4f8	Indie	159.5	Satalis
ff7a52bd-2fc7-4d16-bf56-bc260cb1e682	Hoshimachi Suisei	2018-03-18	2018-03-22	8fc96e59-ff45-4f17-851f-9f0b1c86424b	Hololive Generation 0	160	Hoshiyomi (Stargazers)
\.


--
-- TOC entry 4665 (class 2606 OID 24665)
-- Name: organization organization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (id);


--
-- TOC entry 4663 (class 2606 OID 24622)
-- Name: talent talent_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.talent
    ADD CONSTRAINT talent_pkey PRIMARY KEY (talent_id);


--
-- TOC entry 4666 (class 2606 OID 24679)
-- Name: talent fk_talent_organization; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.talent
    ADD CONSTRAINT fk_talent_organization FOREIGN KEY (talent_organization) REFERENCES public.organization(id);


-- Completed on 2024-12-30 14:30:55

--
-- PostgreSQL database dump complete
--

