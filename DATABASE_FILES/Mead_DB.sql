--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2025-11-10 11:51:19

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

\connect Mead

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

-- CREATE SCHEMA public;


-- ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4793 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 24732)
-- Name: mead_recipes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mead_recipes (
    recipe_id character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    abv double precision,
    batch_size_gallons numeric(5,2),
    flavor_notes text,
    ingredients text,
    steps text,
    batchsizegallons double precision,
    flavornotes character varying(255)
);


-- ALTER TABLE public.mead_recipes OWNER TO postgres;

--
-- TOC entry 4787 (class 0 OID 24732)
-- Dependencies: 217
-- Data for Name: mead_recipes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mead_recipes (recipe_id, name, abv, batch_size_gallons, flavor_notes, ingredients, steps, batchsizegallons, flavornotes) FROM stdin;
9a0f1848-fa54-4d1e-8120-477e94d67e8b	Dry Butterscotch Blood Orange Mead (2 Gallon Batch)	13.5	2.00	Rich amber mead with smooth caramel/butterscotch aroma, blood orange brightness, and a crisp dry finish (FG ~0.998–1.004)	[\n        { "section": "Base", "items": ["6.4 lbs Blood Orange Honey","2 gallons total volume (including all ingredients)","Filtered water (non-chlorinated)"] },\n        { "section": "Yeast & Nutrients", "items": ["1 packet Lalvin 71B-1122 or D47","2.5 g Fermaid-O (≈1 tsp total) = up front","2.5 g Fermaid-K (≈1 tsp total) = staggered","Add over 3 stages (24h, 48h, ⅓ sugar break)"] },\n        { "section": "Butterscotch Flavor Layer", "items": ["½ cup light brown sugar","2 Tbsp unsalted butter (for caramelization only)","2 tsp vanilla extract","2–4 Werther’s Originals or 2 Tbsp butterscotch syrup (optional)"] },\n        { "section": "Optional Enhancements", "items": ["2 small cinnamon sticks (or 1 large)","Zest of 1 blood orange (added in secondary)","½ oz medium toast oak cubes"] }\n    ]	[\n        { "title": "Caramel/Butterscotch Base", "steps": ["Melt brown sugar and butter over medium heat until caramelized","Deglaze with ~1 cup water","Let cool, then add vanilla extract","Add to sanitized fermenter"] },\n        { "title": "Mix the Must", "steps": ["Dissolve honey in ~1 gallon warm water","Add butterscotch mix, top to 2 gallons total","OG should be around 1.100–1.110"] },\n        { "title": "Yeast Prep & Pitch", "steps": ["Rehydrate yeast with Go-Ferm (15 min)","Temper with must, then pitch into fermenter","Add ⅓ of nutrients"] },\n        { "title": "Fermentation", "steps": ["Keep at 62–68°F (17–20°C)","Add nutrients at 24h, 48h, and at the ⅓ sugar break (~1.070 SG)","Gently degas daily during first few days","Primary fermentation lasts 3–4 weeks"] },\n        { "title": "Secondary Fermentation / Aging", "steps": ["Rack off lees at ~1.000 SG","Optional: Add zest or oak cubes for 2–4 weeks","Age 2–4 months minimum"] },\n        { "title": "Stabilization & Sweetness", "steps": ["For dry: stabilize and bottle as-is","For slight sweetness: back-sweeten with 1–2 oz butterscotch syrup or honey (after stabilization)"] },\n        { "title": "Enjoy", "steps": ["Serve slightly chilled in a wine glass or mead goblet","Savor the butterscotch aroma and citrus brightness","Share with friends and celebrate your brewing skill!"] }\n    ]	\N	\N
6c76cb4b-4e69-4b33-ad2f-646d746cc185	Dry Blackberry Mead (2 Gallon Batch)	16.5	2.00	Rich, fruity mead with bright blackberry aroma, subtle orange blossom honey notes, and a crisp dry finish. Optional vanilla or spicy cinnamon aftertaste for added depth (FG ~1.010).	[\n        { "section": "Base", "items": ["3 lbs fresh blackberries","6 lbs (≈9 cups) orange blossom honey","2 gallons filtered water (non-chlorinated)"] },\n        { "section": "Yeast & Nutrients", "items": ["1 packet Lalvin 71B-1122 or D47","2.5 g Fermaid-O (≈1 tsp total) = added up front","2.5 g Fermaid-K (≈1 tsp total) = staggered over 3 stages (24h, 48h, ⅓ sugar break)"] },\n        { "section": "Optional Flavor Enhancements", "items": ["1–2 tsp vanilla extract (add during secondary for vanilla aftertaste)","2 small cinnamon sticks or 1 large (add during secondary for spicy aftertaste)","1–2 oz medium toast oak chips (add during secondary for oak character)"] }\n    ]	[\n        { "title": "Prepare Must", "steps": ["Dissolve honey in ~1 gallon warm water","Add water to reach 2 gallons total","OG expected ~1.120–1.125"] },\n        { "title": "Yeast Prep & Pitch", "steps": ["Rehydrate yeast with Go-Ferm for 15 minutes","Temper with must, then pitch into fermenter","Add ⅓ of nutrients"] },\n        { "title": "Primary Fermentation", "steps": ["Keep at 62–68°F (17–20°C)","Add remaining nutrients at 24h, 48h, and at ⅓ sugar break (~1.070 SG)","Gently degas daily during first few days","Primary fermentation lasts 3–4 weeks"] },\n        { "title": "Secondary Fermentation / Aging", "steps": ["Rack off lees from primary fermentation","Add fresh blackberries (do not crush too aggressively to avoid bitterness)","Add optional oak chips, vanilla, or cinnamon sticks","Age 2–4 months minimum, tasting periodically for oak and spice balance"] },\n        { "title": "Stabilization & Bottling", "steps": ["For dry mead: stabilize and bottle as-is","For slight sweetness: back-sweeten with 1–2 oz honey after stabilization"] },\n        { "title": "Enjoy", "steps": ["Serve slightly chilled in a wine glass or mead goblet","Savor the blackberry aroma and bright fruity flavor","Share with friends and celebrate your brewing skill!"]}\n    ]	\N	\N
\.


--
-- TOC entry 4641 (class 2606 OID 24742)
-- Name: mead_recipes mead_recipes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mead_recipes
    ADD CONSTRAINT mead_recipes_pkey PRIMARY KEY (recipe_id);


-- Completed on 2025-11-10 11:51:19

--
-- PostgreSQL database dump complete
--

