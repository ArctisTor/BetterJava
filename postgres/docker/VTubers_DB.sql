PGDMP  )                
    |            VTubers    17.0    17.0     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    16388    VTubers    DATABASE     �   CREATE DATABASE "VTubers" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.932';
    DROP DATABASE "VTubers";
                     postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                     pg_database_owner    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                        pg_database_owner    false    4            �            1259    16389    talent    TABLE     �  CREATE TABLE public.talent (
    talent_id uuid DEFAULT gen_random_uuid(),
    talent_name character varying(200) NOT NULL,
    talent_debut date DEFAULT CURRENT_DATE,
    talent_birthday date NOT NULL,
    talent_organization character varying(200) DEFAULT 'Indie'::character varying,
    talent_unit character varying(200) DEFAULT 'Indie'::character varying,
    talent_height numeric DEFAULT 159.5,
    talent_fan_name character varying(200)
);
    DROP TABLE public.talent;
       public         heap r       postgres    false    4            �          0    16389    talent 
   TABLE DATA           �   COPY public.talent (talent_id, talent_name, talent_debut, talent_birthday, talent_organization, talent_unit, talent_height, talent_fan_name) FROM stdin;
    public               postgres    false    217   _       �   �   x�m��R�0Ek�+RB���#�`��4r�l<y���|=���h4G3ҽ�j�����@E`u� z�@��.�1�cQ�i���T��xIh�T	
o�y���e\���w(pۋ�O�x�:'�k�ݤe�Q�%�h<X�|�bW*�B��k�JC��������pC�;#^�6�oENN|�i��mK��HX�t�x�Q��h�!��i��OE}����@G��I�S�J{Z�B	,�M�c�?z�wZ���?ʷ�����d�     