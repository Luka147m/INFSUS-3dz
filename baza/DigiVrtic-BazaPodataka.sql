--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2025-05-25 14:16:04

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
-- TOC entry 228 (class 1259 OID 55980)
-- Name: dijete; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dijete (
    id_dijete integer NOT NULL,
    id_skupina integer,
    ime character varying(255),
    prezime character varying(255),
    oib character varying(255),
    mjesto_rodenja character varying(255),
    adresa_stanovanja character varying(255),
    mbo character varying(255),
    datum_rodenja date
);


ALTER TABLE public.dijete OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 55979)
-- Name: dijete_id_dijete_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dijete_id_dijete_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.dijete_id_dijete_seq OWNER TO postgres;

--
-- TOC entry 5010 (class 0 OID 0)
-- Dependencies: 227
-- Name: dijete_id_dijete_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dijete_id_dijete_seq OWNED BY public.dijete.id_dijete;


--
-- TOC entry 245 (class 1259 OID 56130)
-- Name: dnevnikdana; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dnevnikdana (
    id_dnevnik_dana integer NOT NULL,
    id_plan_tjedna integer,
    brojdjece integer,
    zapazanja text,
    datum date,
    suradnja text,
    radno_vrijeme text,
    planirani_poticaji text,
    situacijski_poticaji text
);


ALTER TABLE public.dnevnikdana OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 56129)
-- Name: dnevnikdana_id_dnevnik_dana_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.dnevnikdana_id_dnevnik_dana_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.dnevnikdana_id_dnevnik_dana_seq OWNER TO postgres;

--
-- TOC entry 5011 (class 0 OID 0)
-- Dependencies: 244
-- Name: dnevnikdana_id_dnevnik_dana_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.dnevnikdana_id_dnevnik_dana_seq OWNED BY public.dnevnikdana.id_dnevnik_dana;


--
-- TOC entry 239 (class 1259 OID 56076)
-- Name: evidencijskalista; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.evidencijskalista (
    id_evidencijska_lista integer NOT NULL,
    id_dijete integer,
    id_odgojitelj integer,
    datum date,
    prisutan boolean,
    program character varying(255),
    napomena character varying(255)
);


ALTER TABLE public.evidencijskalista OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 56075)
-- Name: evidencijskalista_id_evidencijska_lista_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.evidencijskalista_id_evidencijska_lista_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.evidencijskalista_id_evidencijska_lista_seq OWNER TO postgres;

--
-- TOC entry 5012 (class 0 OID 0)
-- Dependencies: 238
-- Name: evidencijskalista_id_evidencijska_lista_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.evidencijskalista_id_evidencijska_lista_seq OWNED BY public.evidencijskalista.id_evidencijska_lista;


--
-- TOC entry 230 (class 1259 OID 55992)
-- Name: imenik; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.imenik (
    id_imenik integer NOT NULL,
    id_roditelj1 integer,
    id_roditelj2 integer,
    id_dijete integer NOT NULL
);


ALTER TABLE public.imenik OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 55991)
-- Name: imenik_id_imenik_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.imenik_id_imenik_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.imenik_id_imenik_seq OWNER TO postgres;

--
-- TOC entry 5013 (class 0 OID 0)
-- Dependencies: 229
-- Name: imenik_id_imenik_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.imenik_id_imenik_seq OWNED BY public.imenik.id_imenik;


--
-- TOC entry 216 (class 1259 OID 55916)
-- Name: korisnik; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.korisnik (
    id_korisnik integer NOT NULL,
    ime character varying(255) NOT NULL,
    prezime character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    broj_telefona character varying(255),
    lozinka character varying(255) NOT NULL,
    uloga character varying(255) NOT NULL
);


ALTER TABLE public.korisnik OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 55915)
-- Name: korisnik_id_korisnik_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.korisnik_id_korisnik_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.korisnik_id_korisnik_seq OWNER TO postgres;

--
-- TOC entry 5014 (class 0 OID 0)
-- Dependencies: 215
-- Name: korisnik_id_korisnik_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.korisnik_id_korisnik_seq OWNED BY public.korisnik.id_korisnik;


--
-- TOC entry 232 (class 1259 OID 56014)
-- Name: obavijest; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.obavijest (
    id_obavijest integer NOT NULL,
    tekst_obavijesti text
);


ALTER TABLE public.obavijest OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 56013)
-- Name: obavijest_id_obavijest_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.obavijest_id_obavijest_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.obavijest_id_obavijest_seq OWNER TO postgres;

--
-- TOC entry 5015 (class 0 OID 0)
-- Dependencies: 231
-- Name: obavijest_id_obavijest_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.obavijest_id_obavijest_seq OWNED BY public.obavijest.id_obavijest;


--
-- TOC entry 224 (class 1259 OID 55956)
-- Name: odgojitelj; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.odgojitelj (
    id_odgojitelj integer NOT NULL,
    id_korisnik integer
);


ALTER TABLE public.odgojitelj OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 55955)
-- Name: odgojitelj_id_odgojitelj_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.odgojitelj_id_odgojitelj_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.odgojitelj_id_odgojitelj_seq OWNER TO postgres;

--
-- TOC entry 5016 (class 0 OID 0)
-- Dependencies: 223
-- Name: odgojitelj_id_odgojitelj_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.odgojitelj_id_odgojitelj_seq OWNED BY public.odgojitelj.id_odgojitelj;


--
-- TOC entry 243 (class 1259 OID 56116)
-- Name: plantjedna; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plantjedna (
    id_plan_tjedna integer NOT NULL,
    id_plan_tromj integer,
    broj_tjedna integer,
    aktivnosti text,
    poslovi text,
    raspon_datuma daterange
);


ALTER TABLE public.plantjedna OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 56115)
-- Name: plantjedna_id_plan_tjedna_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.plantjedna_id_plan_tjedna_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.plantjedna_id_plan_tjedna_seq OWNER TO postgres;

--
-- TOC entry 5017 (class 0 OID 0)
-- Dependencies: 242
-- Name: plantjedna_id_plan_tjedna_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.plantjedna_id_plan_tjedna_seq OWNED BY public.plantjedna.id_plan_tjedna;


--
-- TOC entry 241 (class 1259 OID 56097)
-- Name: plantromjesecja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plantromjesecja (
    id_plan_tromj integer NOT NULL,
    id_odgojitelj1 integer,
    id_odgojitelj2 integer,
    aktivnosti text,
    uvjeti text,
    razvojne_zadace text,
    suradnje text,
    raspon_datuma daterange
);


ALTER TABLE public.plantromjesecja OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 56096)
-- Name: plantromjesecja_id_plan_tromj_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.plantromjesecja_id_plan_tromj_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.plantromjesecja_id_plan_tromj_seq OWNER TO postgres;

--
-- TOC entry 5018 (class 0 OID 0)
-- Dependencies: 240
-- Name: plantromjesecja_id_plan_tromj_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.plantromjesecja_id_plan_tromj_seq OWNED BY public.plantromjesecja.id_plan_tromj;


--
-- TOC entry 222 (class 1259 OID 55944)
-- Name: ravnatelj; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ravnatelj (
    id_ravnatelj integer NOT NULL,
    id_korisnik integer
);


ALTER TABLE public.ravnatelj OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 55943)
-- Name: ravnatelj_id_ravnatelj_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ravnatelj_id_ravnatelj_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ravnatelj_id_ravnatelj_seq OWNER TO postgres;

--
-- TOC entry 5019 (class 0 OID 0)
-- Dependencies: 221
-- Name: ravnatelj_id_ravnatelj_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ravnatelj_id_ravnatelj_seq OWNED BY public.ravnatelj.id_ravnatelj;


--
-- TOC entry 235 (class 1259 OID 56038)
-- Name: razvojnamapa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.razvojnamapa (
    id_razvojne_mape integer NOT NULL,
    id_dijete integer,
    id_odgojitelj integer,
    datum date,
    biljeska text
);


ALTER TABLE public.razvojnamapa OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 56037)
-- Name: razvojnamapa_id_razvojne_mape_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.razvojnamapa_id_razvojne_mape_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.razvojnamapa_id_razvojne_mape_seq OWNER TO postgres;

--
-- TOC entry 5020 (class 0 OID 0)
-- Dependencies: 234
-- Name: razvojnamapa_id_razvojne_mape_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.razvojnamapa_id_razvojne_mape_seq OWNED BY public.razvojnamapa.id_razvojne_mape;


--
-- TOC entry 220 (class 1259 OID 55932)
-- Name: roditelj; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roditelj (
    id_roditelj integer NOT NULL,
    id_korisnik integer,
    zanimanje character varying(255),
    radno_mjesto character varying(255),
    oib character varying(255)
);


ALTER TABLE public.roditelj OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 55931)
-- Name: roditelj_id_roditelj_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roditelj_id_roditelj_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.roditelj_id_roditelj_seq OWNER TO postgres;

--
-- TOC entry 5021 (class 0 OID 0)
-- Dependencies: 219
-- Name: roditelj_id_roditelj_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roditelj_id_roditelj_seq OWNED BY public.roditelj.id_roditelj;


--
-- TOC entry 233 (class 1259 OID 56022)
-- Name: roditeljobavijest; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roditeljobavijest (
    id_roditelj integer NOT NULL,
    id_obavijest integer NOT NULL
);


ALTER TABLE public.roditeljobavijest OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 55925)
-- Name: skupina; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.skupina (
    id_skupina integer NOT NULL,
    naziv character varying(100),
    ime character varying(255)
);


ALTER TABLE public.skupina OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 55924)
-- Name: skupina_id_skupina_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.skupina_id_skupina_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.skupina_id_skupina_seq OWNER TO postgres;

--
-- TOC entry 5022 (class 0 OID 0)
-- Dependencies: 217
-- Name: skupina_id_skupina_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.skupina_id_skupina_seq OWNED BY public.skupina.id_skupina;


--
-- TOC entry 237 (class 1259 OID 56057)
-- Name: zdravstvenaevidencija; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.zdravstvenaevidencija (
    id_evidencija integer NOT NULL,
    id_dijete integer,
    id_zdravstveni_djelatnik integer,
    datum date,
    visina numeric(5,2),
    tezina numeric(5,2),
    alergije text,
    posebne_zdravstvene_biljeske text
);


ALTER TABLE public.zdravstvenaevidencija OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 56056)
-- Name: zdravstvenaevidencija_id_evidencija_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.zdravstvenaevidencija_id_evidencija_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.zdravstvenaevidencija_id_evidencija_seq OWNER TO postgres;

--
-- TOC entry 5023 (class 0 OID 0)
-- Dependencies: 236
-- Name: zdravstvenaevidencija_id_evidencija_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.zdravstvenaevidencija_id_evidencija_seq OWNED BY public.zdravstvenaevidencija.id_evidencija;


--
-- TOC entry 226 (class 1259 OID 55968)
-- Name: zdravstvenidjelatnik; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.zdravstvenidjelatnik (
    id_zdravstveni_djelatnik integer NOT NULL,
    id_korisnik integer
);


ALTER TABLE public.zdravstvenidjelatnik OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 55967)
-- Name: zdravstvenidjelatnik_id_zdravstveni_djelatnik_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.zdravstvenidjelatnik_id_zdravstveni_djelatnik_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.zdravstvenidjelatnik_id_zdravstveni_djelatnik_seq OWNER TO postgres;

--
-- TOC entry 5024 (class 0 OID 0)
-- Dependencies: 225
-- Name: zdravstvenidjelatnik_id_zdravstveni_djelatnik_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.zdravstvenidjelatnik_id_zdravstveni_djelatnik_seq OWNED BY public.zdravstvenidjelatnik.id_zdravstveni_djelatnik;


--
-- TOC entry 4768 (class 2604 OID 55983)
-- Name: dijete id_dijete; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dijete ALTER COLUMN id_dijete SET DEFAULT nextval('public.dijete_id_dijete_seq'::regclass);


--
-- TOC entry 4776 (class 2604 OID 56133)
-- Name: dnevnikdana id_dnevnik_dana; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dnevnikdana ALTER COLUMN id_dnevnik_dana SET DEFAULT nextval('public.dnevnikdana_id_dnevnik_dana_seq'::regclass);


--
-- TOC entry 4773 (class 2604 OID 56079)
-- Name: evidencijskalista id_evidencijska_lista; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evidencijskalista ALTER COLUMN id_evidencijska_lista SET DEFAULT nextval('public.evidencijskalista_id_evidencijska_lista_seq'::regclass);


--
-- TOC entry 4769 (class 2604 OID 55995)
-- Name: imenik id_imenik; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imenik ALTER COLUMN id_imenik SET DEFAULT nextval('public.imenik_id_imenik_seq'::regclass);


--
-- TOC entry 4762 (class 2604 OID 55919)
-- Name: korisnik id_korisnik; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.korisnik ALTER COLUMN id_korisnik SET DEFAULT nextval('public.korisnik_id_korisnik_seq'::regclass);


--
-- TOC entry 4770 (class 2604 OID 56017)
-- Name: obavijest id_obavijest; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.obavijest ALTER COLUMN id_obavijest SET DEFAULT nextval('public.obavijest_id_obavijest_seq'::regclass);


--
-- TOC entry 4766 (class 2604 OID 55959)
-- Name: odgojitelj id_odgojitelj; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.odgojitelj ALTER COLUMN id_odgojitelj SET DEFAULT nextval('public.odgojitelj_id_odgojitelj_seq'::regclass);


--
-- TOC entry 4775 (class 2604 OID 56119)
-- Name: plantjedna id_plan_tjedna; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plantjedna ALTER COLUMN id_plan_tjedna SET DEFAULT nextval('public.plantjedna_id_plan_tjedna_seq'::regclass);


--
-- TOC entry 4774 (class 2604 OID 56100)
-- Name: plantromjesecja id_plan_tromj; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plantromjesecja ALTER COLUMN id_plan_tromj SET DEFAULT nextval('public.plantromjesecja_id_plan_tromj_seq'::regclass);


--
-- TOC entry 4765 (class 2604 OID 55947)
-- Name: ravnatelj id_ravnatelj; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ravnatelj ALTER COLUMN id_ravnatelj SET DEFAULT nextval('public.ravnatelj_id_ravnatelj_seq'::regclass);


--
-- TOC entry 4771 (class 2604 OID 56041)
-- Name: razvojnamapa id_razvojne_mape; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.razvojnamapa ALTER COLUMN id_razvojne_mape SET DEFAULT nextval('public.razvojnamapa_id_razvojne_mape_seq'::regclass);


--
-- TOC entry 4764 (class 2604 OID 55935)
-- Name: roditelj id_roditelj; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roditelj ALTER COLUMN id_roditelj SET DEFAULT nextval('public.roditelj_id_roditelj_seq'::regclass);


--
-- TOC entry 4763 (class 2604 OID 55928)
-- Name: skupina id_skupina; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.skupina ALTER COLUMN id_skupina SET DEFAULT nextval('public.skupina_id_skupina_seq'::regclass);


--
-- TOC entry 4772 (class 2604 OID 56060)
-- Name: zdravstvenaevidencija id_evidencija; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zdravstvenaevidencija ALTER COLUMN id_evidencija SET DEFAULT nextval('public.zdravstvenaevidencija_id_evidencija_seq'::regclass);


--
-- TOC entry 4767 (class 2604 OID 55971)
-- Name: zdravstvenidjelatnik id_zdravstveni_djelatnik; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zdravstvenidjelatnik ALTER COLUMN id_zdravstveni_djelatnik SET DEFAULT nextval('public.zdravstvenidjelatnik_id_zdravstveni_djelatnik_seq'::regclass);


--
-- TOC entry 4987 (class 0 OID 55980)
-- Dependencies: 228
-- Data for Name: dijete; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dijete (id_dijete, id_skupina, ime, prezime, oib, mjesto_rodenja, adresa_stanovanja, mbo, datum_rodenja) FROM stdin;
1	1	Borna	Novak	12345238912	Požega	Školska 123a	546325896	2019-04-10
2	1	Bruno	Petrović	12345233412	Slavonski brod	Domovinska 43	986532569	2019-09-08
3	1	Tina	Babić	12345255412	Slavonski brod	Petrova ulica 4	863263514	2019-12-11
18	1	Petar	Novak	12342238912	Zagreb	Petrovska 123a	986325628	2019-04-10
19	1	Leon	Novak	12342238912	Zagreb	Petrovska 123a	986325749	2019-04-10
\.


--
-- TOC entry 5004 (class 0 OID 56130)
-- Dependencies: 245
-- Data for Name: dnevnikdana; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dnevnikdana (id_dnevnik_dana, id_plan_tjedna, brojdjece, zapazanja, datum, suradnja, radno_vrijeme, planirani_poticaji, situacijski_poticaji) FROM stdin;
\.


--
-- TOC entry 4998 (class 0 OID 56076)
-- Dependencies: 239
-- Data for Name: evidencijskalista; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.evidencijskalista (id_evidencijska_lista, id_dijete, id_odgojitelj, datum, prisutan, program, napomena) FROM stdin;
9	1	2	2025-05-06	f	-	bolestan (prijavio Roditelj)
7	1	5	2024-05-22	t	poludnevni	kikiriki alergija
2	2	1	2025-04-11	t	poludnevni	-
3	3	1	2025-04-11	f	-	Roditelj javio da dijete ima vodene kozice
27	18	5	2025-05-13	t	cjelodnevni	Baka će pokupiti dijete zbog odsutnosti roditelja
28	19	4	2025-05-05	t	poludnevni	Izgubio je zub tijekom boravka u vrtiću
\.


--
-- TOC entry 4989 (class 0 OID 55992)
-- Dependencies: 230
-- Data for Name: imenik; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.imenik (id_imenik, id_roditelj1, id_roditelj2, id_dijete) FROM stdin;
1	1	2	1
2	3	4	2
3	5	6	3
10	5	6	18
11	3	4	19
\.


--
-- TOC entry 4975 (class 0 OID 55916)
-- Dependencies: 216
-- Data for Name: korisnik; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.korisnik (id_korisnik, ime, prezime, email, broj_telefona, lozinka, uloga) FROM stdin;
1	Marko	Marković	m.markovic@vrtic.com	098 900 5432	$2b$10$qVWTbJQNvuRcw/EPREH1Gep.cqY.emlJioaBTn7BxnWwTNrRGy76.	ravnatelj
2	Ana	Barić	a.baric@vrtic.com	098 900 541	$2b$10$JPpFC9OBQOEho5coZE0Cz.xpQ/aSOkZxD5tG.UNjWcIFJ4MqjsLBG	odgojitelj
3	Ivana	Kovač	i.kovac@vrtic.com	098 900 542	$2b$10$wtfurherAAQJHHMnHj8Xd.1dRR4ac2dr2hUJMVQf6kRddHBVgMWSu	odgojitelj
4	Petar	Jurić	p.juric@vrtic.com	098 900 543	$2b$10$lqw4DpbIfAMAuxJOCqR7QeZuJrg8awBREPDiRzq37HFzng.aYFHXK	odgojitelj
6	Lana	Perković	l.perkovic@vrtic.com	098 900 544	$2b$10$hkn01yiVNf8hSeq5hrt3QeRe34vR/wZrojUEkmCF.t1DOfCd1bxA6	odgojitelj
7	Lucija	Markov	l.markov@vrtic.com	098 900 545	$2b$10$FwsDOrbtpfYhUSYk5LHoieX39zWk1Ahtl39NL7AjVpz2rQg21DhbK	odgojitelj
8	Ema	Vuković	e.vukovic@vrtic.com	098 900 546	$2b$10$SCNgG34gPtk74gtJn83h8u2ARk1YAKwNyxorpwplUgUgmqUACuNwu	odgojitelj
9	Marko	Novak	marko.novak@gmail.com	098 900 551	$2b$10$nHqVqnkdV34tj8UGD3rkw.Y5HLYSNhLWSWFQpL8/IVOxC9IRQ0nJW	roditelj
10	Sara	Matić	sara.matic@gmail.com	098 900 552	$2b$10$59ET2h8AqaFgrIwjGHJMzuVGGj58n/GZTIcJLdN6/M5Te2D6fuYOS	roditelj
11	Tomislav	Petrović	tomislav.petrovic@gmail.com	098 900 553	$2b$10$qDpsq6oj/fNRKFC/7VyMwegun.KAizXZCj8p1l6gErC2gql4/UbLG	roditelj
12	Marija	Tomić	marija.tomic@gmail.com	098 900 554	$2b$10$cqNP77Ae4aBMlUn6/2zLBuzq6dS5AURcudYlcKBp43nsJLNay0iVK	roditelj
13	Jana	Kovačić	jana.kovacic@gmail.com	098 900 555	$2b$10$2MGD1SBDNzj7XYqVECKp..RUiQVrFA32Pm2HapoR7Hdm.S.AvQZ7i	roditelj
15	Renata	Matić	renata.matic@zdravlje.com	098 900 546	$2b$10$sCbRF4HiL2R3vQYbXMpR7.5/t6vv7kF88PldFUdqbkGbkeVVZbaHa	zdravstveni djelatnik
14	Marko	Babić	marko.babić@example.com	+385912345673	tajnaLozinka123	RODITELJ
29	Petar	Horvat	petar.horvat@gmail.com	+385962354125	supertajnaloyinka	Roditelj
\.


--
-- TOC entry 4991 (class 0 OID 56014)
-- Dependencies: 232
-- Data for Name: obavijest; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.obavijest (id_obavijest, tekst_obavijesti) FROM stdin;
\.


--
-- TOC entry 4983 (class 0 OID 55956)
-- Dependencies: 224
-- Data for Name: odgojitelj; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.odgojitelj (id_odgojitelj, id_korisnik) FROM stdin;
1	2
2	3
3	4
4	6
5	7
6	8
\.


--
-- TOC entry 5002 (class 0 OID 56116)
-- Dependencies: 243
-- Data for Name: plantjedna; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.plantjedna (id_plan_tjedna, id_plan_tromj, broj_tjedna, aktivnosti, poslovi, raspon_datuma) FROM stdin;
\.


--
-- TOC entry 5000 (class 0 OID 56097)
-- Dependencies: 241
-- Data for Name: plantromjesecja; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.plantromjesecja (id_plan_tromj, id_odgojitelj1, id_odgojitelj2, aktivnosti, uvjeti, razvojne_zadace, suradnje, raspon_datuma) FROM stdin;
\.


--
-- TOC entry 4981 (class 0 OID 55944)
-- Dependencies: 222
-- Data for Name: ravnatelj; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ravnatelj (id_ravnatelj, id_korisnik) FROM stdin;
1	1
\.


--
-- TOC entry 4994 (class 0 OID 56038)
-- Dependencies: 235
-- Data for Name: razvojnamapa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.razvojnamapa (id_razvojne_mape, id_dijete, id_odgojitelj, datum, biljeska) FROM stdin;
\.


--
-- TOC entry 4979 (class 0 OID 55932)
-- Dependencies: 220
-- Data for Name: roditelj; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roditelj (id_roditelj, id_korisnik, zanimanje, radno_mjesto, oib) FROM stdin;
1	9	Automehaničar	Mehaničarska radnja čekić	12345678912
2	10	Pedijatar	Opća županijska bolnica Požega	14345678912
3	11	Građevinski radnik	Firma beton Sava	14375678912
4	12	Prodavačica	Studenac	14375678982
5	13	Prodavačica	Konzum	14375678985
6	14	Inženjer	Zagrebačka tvrtka d.o.o.	12345678901
20	29	Stolar	Drva Dabar	56231245862
\.


--
-- TOC entry 4992 (class 0 OID 56022)
-- Dependencies: 233
-- Data for Name: roditeljobavijest; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roditeljobavijest (id_roditelj, id_obavijest) FROM stdin;
\.


--
-- TOC entry 4977 (class 0 OID 55925)
-- Dependencies: 218
-- Data for Name: skupina; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.skupina (id_skupina, naziv, ime) FROM stdin;
1	Žute pčelice	\N
2	Pospani medeki	\N
\.


--
-- TOC entry 4996 (class 0 OID 56057)
-- Dependencies: 237
-- Data for Name: zdravstvenaevidencija; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.zdravstvenaevidencija (id_evidencija, id_dijete, id_zdravstveni_djelatnik, datum, visina, tezina, alergije, posebne_zdravstvene_biljeske) FROM stdin;
\.


--
-- TOC entry 4985 (class 0 OID 55968)
-- Dependencies: 226
-- Data for Name: zdravstvenidjelatnik; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.zdravstvenidjelatnik (id_zdravstveni_djelatnik, id_korisnik) FROM stdin;
1	15
\.


--
-- TOC entry 5025 (class 0 OID 0)
-- Dependencies: 227
-- Name: dijete_id_dijete_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dijete_id_dijete_seq', 21, true);


--
-- TOC entry 5026 (class 0 OID 0)
-- Dependencies: 244
-- Name: dnevnikdana_id_dnevnik_dana_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dnevnikdana_id_dnevnik_dana_seq', 1, false);


--
-- TOC entry 5027 (class 0 OID 0)
-- Dependencies: 238
-- Name: evidencijskalista_id_evidencijska_lista_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.evidencijskalista_id_evidencijska_lista_seq', 28, true);


--
-- TOC entry 5028 (class 0 OID 0)
-- Dependencies: 229
-- Name: imenik_id_imenik_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.imenik_id_imenik_seq', 13, true);


--
-- TOC entry 5029 (class 0 OID 0)
-- Dependencies: 215
-- Name: korisnik_id_korisnik_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.korisnik_id_korisnik_seq', 29, true);


--
-- TOC entry 5030 (class 0 OID 0)
-- Dependencies: 231
-- Name: obavijest_id_obavijest_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.obavijest_id_obavijest_seq', 1, false);


--
-- TOC entry 5031 (class 0 OID 0)
-- Dependencies: 223
-- Name: odgojitelj_id_odgojitelj_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.odgojitelj_id_odgojitelj_seq', 6, true);


--
-- TOC entry 5032 (class 0 OID 0)
-- Dependencies: 242
-- Name: plantjedna_id_plan_tjedna_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.plantjedna_id_plan_tjedna_seq', 1, false);


--
-- TOC entry 5033 (class 0 OID 0)
-- Dependencies: 240
-- Name: plantromjesecja_id_plan_tromj_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.plantromjesecja_id_plan_tromj_seq', 1, false);


--
-- TOC entry 5034 (class 0 OID 0)
-- Dependencies: 221
-- Name: ravnatelj_id_ravnatelj_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ravnatelj_id_ravnatelj_seq', 1, true);


--
-- TOC entry 5035 (class 0 OID 0)
-- Dependencies: 234
-- Name: razvojnamapa_id_razvojne_mape_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.razvojnamapa_id_razvojne_mape_seq', 1, false);


--
-- TOC entry 5036 (class 0 OID 0)
-- Dependencies: 219
-- Name: roditelj_id_roditelj_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roditelj_id_roditelj_seq', 20, true);


--
-- TOC entry 5037 (class 0 OID 0)
-- Dependencies: 217
-- Name: skupina_id_skupina_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.skupina_id_skupina_seq', 2, true);


--
-- TOC entry 5038 (class 0 OID 0)
-- Dependencies: 236
-- Name: zdravstvenaevidencija_id_evidencija_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.zdravstvenaevidencija_id_evidencija_seq', 1, false);


--
-- TOC entry 5039 (class 0 OID 0)
-- Dependencies: 225
-- Name: zdravstvenidjelatnik_id_zdravstveni_djelatnik_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.zdravstvenidjelatnik_id_zdravstveni_djelatnik_seq', 1, true);


--
-- TOC entry 4790 (class 2606 OID 55985)
-- Name: dijete dijete_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dijete
    ADD CONSTRAINT dijete_pkey PRIMARY KEY (id_dijete);


--
-- TOC entry 4810 (class 2606 OID 56137)
-- Name: dnevnikdana dnevnikdana_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dnevnikdana
    ADD CONSTRAINT dnevnikdana_pkey PRIMARY KEY (id_dnevnik_dana);


--
-- TOC entry 4804 (class 2606 OID 56083)
-- Name: evidencijskalista evidencijskalista_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evidencijskalista
    ADD CONSTRAINT evidencijskalista_pkey PRIMARY KEY (id_evidencijska_lista);


--
-- TOC entry 4792 (class 2606 OID 55997)
-- Name: imenik imenik_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imenik
    ADD CONSTRAINT imenik_pkey PRIMARY KEY (id_imenik);


--
-- TOC entry 4778 (class 2606 OID 55923)
-- Name: korisnik korisnik_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.korisnik
    ADD CONSTRAINT korisnik_pkey PRIMARY KEY (id_korisnik);


--
-- TOC entry 4796 (class 2606 OID 56021)
-- Name: obavijest obavijest_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.obavijest
    ADD CONSTRAINT obavijest_pkey PRIMARY KEY (id_obavijest);


--
-- TOC entry 4786 (class 2606 OID 55961)
-- Name: odgojitelj odgojitelj_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.odgojitelj
    ADD CONSTRAINT odgojitelj_pkey PRIMARY KEY (id_odgojitelj);


--
-- TOC entry 4808 (class 2606 OID 56123)
-- Name: plantjedna plantjedna_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plantjedna
    ADD CONSTRAINT plantjedna_pkey PRIMARY KEY (id_plan_tjedna);


--
-- TOC entry 4806 (class 2606 OID 56104)
-- Name: plantromjesecja plantromjesecja_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plantromjesecja
    ADD CONSTRAINT plantromjesecja_pkey PRIMARY KEY (id_plan_tromj);


--
-- TOC entry 4784 (class 2606 OID 55949)
-- Name: ravnatelj ravnatelj_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ravnatelj
    ADD CONSTRAINT ravnatelj_pkey PRIMARY KEY (id_ravnatelj);


--
-- TOC entry 4800 (class 2606 OID 56045)
-- Name: razvojnamapa razvojnamapa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.razvojnamapa
    ADD CONSTRAINT razvojnamapa_pkey PRIMARY KEY (id_razvojne_mape);


--
-- TOC entry 4782 (class 2606 OID 55937)
-- Name: roditelj roditelj_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roditelj
    ADD CONSTRAINT roditelj_pkey PRIMARY KEY (id_roditelj);


--
-- TOC entry 4798 (class 2606 OID 56026)
-- Name: roditeljobavijest roditeljobavijest_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roditeljobavijest
    ADD CONSTRAINT roditeljobavijest_pkey PRIMARY KEY (id_roditelj, id_obavijest);


--
-- TOC entry 4780 (class 2606 OID 55930)
-- Name: skupina skupina_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.skupina
    ADD CONSTRAINT skupina_pkey PRIMARY KEY (id_skupina);


--
-- TOC entry 4794 (class 2606 OID 56485)
-- Name: imenik unique_id_dijete; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imenik
    ADD CONSTRAINT unique_id_dijete UNIQUE (id_dijete);


--
-- TOC entry 4802 (class 2606 OID 56064)
-- Name: zdravstvenaevidencija zdravstvenaevidencija_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zdravstvenaevidencija
    ADD CONSTRAINT zdravstvenaevidencija_pkey PRIMARY KEY (id_evidencija);


--
-- TOC entry 4788 (class 2606 OID 55973)
-- Name: zdravstvenidjelatnik zdravstvenidjelatnik_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zdravstvenidjelatnik
    ADD CONSTRAINT zdravstvenidjelatnik_pkey PRIMARY KEY (id_zdravstveni_djelatnik);


--
-- TOC entry 4815 (class 2606 OID 55986)
-- Name: dijete dijete_id_skupina_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dijete
    ADD CONSTRAINT dijete_id_skupina_fkey FOREIGN KEY (id_skupina) REFERENCES public.skupina(id_skupina);


--
-- TOC entry 4830 (class 2606 OID 56138)
-- Name: dnevnikdana dnevnikdana_id_plan_tjedna_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dnevnikdana
    ADD CONSTRAINT dnevnikdana_id_plan_tjedna_fkey FOREIGN KEY (id_plan_tjedna) REFERENCES public.plantjedna(id_plan_tjedna);


--
-- TOC entry 4825 (class 2606 OID 56086)
-- Name: evidencijskalista evidencijskalista_id_dijete_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evidencijskalista
    ADD CONSTRAINT evidencijskalista_id_dijete_fkey FOREIGN KEY (id_dijete) REFERENCES public.dijete(id_dijete);


--
-- TOC entry 4826 (class 2606 OID 56091)
-- Name: evidencijskalista evidencijskalista_id_odgojitelj_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evidencijskalista
    ADD CONSTRAINT evidencijskalista_id_odgojitelj_fkey FOREIGN KEY (id_odgojitelj) REFERENCES public.odgojitelj(id_odgojitelj);


--
-- TOC entry 4816 (class 2606 OID 56008)
-- Name: imenik imenik_id_dijete_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imenik
    ADD CONSTRAINT imenik_id_dijete_fkey FOREIGN KEY (id_dijete) REFERENCES public.dijete(id_dijete);


--
-- TOC entry 4817 (class 2606 OID 55998)
-- Name: imenik imenik_id_roditelj1_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imenik
    ADD CONSTRAINT imenik_id_roditelj1_fkey FOREIGN KEY (id_roditelj1) REFERENCES public.roditelj(id_roditelj);


--
-- TOC entry 4818 (class 2606 OID 56003)
-- Name: imenik imenik_id_roditelj2_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imenik
    ADD CONSTRAINT imenik_id_roditelj2_fkey FOREIGN KEY (id_roditelj2) REFERENCES public.roditelj(id_roditelj);


--
-- TOC entry 4813 (class 2606 OID 55962)
-- Name: odgojitelj odgojitelj_id_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.odgojitelj
    ADD CONSTRAINT odgojitelj_id_korisnik_fkey FOREIGN KEY (id_korisnik) REFERENCES public.korisnik(id_korisnik);


--
-- TOC entry 4829 (class 2606 OID 56124)
-- Name: plantjedna plantjedna_id_plan_tromj_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plantjedna
    ADD CONSTRAINT plantjedna_id_plan_tromj_fkey FOREIGN KEY (id_plan_tromj) REFERENCES public.plantromjesecja(id_plan_tromj);


--
-- TOC entry 4827 (class 2606 OID 56105)
-- Name: plantromjesecja plantromjesecja_id_odgojitelj1_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plantromjesecja
    ADD CONSTRAINT plantromjesecja_id_odgojitelj1_fkey FOREIGN KEY (id_odgojitelj1) REFERENCES public.odgojitelj(id_odgojitelj);


--
-- TOC entry 4828 (class 2606 OID 56110)
-- Name: plantromjesecja plantromjesecja_id_odgojitelj2_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plantromjesecja
    ADD CONSTRAINT plantromjesecja_id_odgojitelj2_fkey FOREIGN KEY (id_odgojitelj2) REFERENCES public.odgojitelj(id_odgojitelj);


--
-- TOC entry 4812 (class 2606 OID 55950)
-- Name: ravnatelj ravnatelj_id_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ravnatelj
    ADD CONSTRAINT ravnatelj_id_korisnik_fkey FOREIGN KEY (id_korisnik) REFERENCES public.korisnik(id_korisnik);


--
-- TOC entry 4821 (class 2606 OID 56046)
-- Name: razvojnamapa razvojnamapa_id_dijete_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.razvojnamapa
    ADD CONSTRAINT razvojnamapa_id_dijete_fkey FOREIGN KEY (id_dijete) REFERENCES public.dijete(id_dijete);


--
-- TOC entry 4822 (class 2606 OID 56051)
-- Name: razvojnamapa razvojnamapa_id_odgojitelj_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.razvojnamapa
    ADD CONSTRAINT razvojnamapa_id_odgojitelj_fkey FOREIGN KEY (id_odgojitelj) REFERENCES public.odgojitelj(id_odgojitelj);


--
-- TOC entry 4811 (class 2606 OID 55938)
-- Name: roditelj roditelj_id_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roditelj
    ADD CONSTRAINT roditelj_id_korisnik_fkey FOREIGN KEY (id_korisnik) REFERENCES public.korisnik(id_korisnik);


--
-- TOC entry 4819 (class 2606 OID 56032)
-- Name: roditeljobavijest roditeljobavijest_id_obavijest_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roditeljobavijest
    ADD CONSTRAINT roditeljobavijest_id_obavijest_fkey FOREIGN KEY (id_obavijest) REFERENCES public.obavijest(id_obavijest);


--
-- TOC entry 4820 (class 2606 OID 56027)
-- Name: roditeljobavijest roditeljobavijest_id_roditelj_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roditeljobavijest
    ADD CONSTRAINT roditeljobavijest_id_roditelj_fkey FOREIGN KEY (id_roditelj) REFERENCES public.roditelj(id_roditelj);


--
-- TOC entry 4823 (class 2606 OID 56065)
-- Name: zdravstvenaevidencija zdravstvenaevidencija_id_dijete_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zdravstvenaevidencija
    ADD CONSTRAINT zdravstvenaevidencija_id_dijete_fkey FOREIGN KEY (id_dijete) REFERENCES public.dijete(id_dijete);


--
-- TOC entry 4824 (class 2606 OID 56070)
-- Name: zdravstvenaevidencija zdravstvenaevidencija_id_zdravstveni_djelatnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zdravstvenaevidencija
    ADD CONSTRAINT zdravstvenaevidencija_id_zdravstveni_djelatnik_fkey FOREIGN KEY (id_zdravstveni_djelatnik) REFERENCES public.zdravstvenidjelatnik(id_zdravstveni_djelatnik);


--
-- TOC entry 4814 (class 2606 OID 55974)
-- Name: zdravstvenidjelatnik zdravstvenidjelatnik_id_korisnik_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zdravstvenidjelatnik
    ADD CONSTRAINT zdravstvenidjelatnik_id_korisnik_fkey FOREIGN KEY (id_korisnik) REFERENCES public.korisnik(id_korisnik);


-- Completed on 2025-05-25 14:16:06

--
-- PostgreSQL database dump complete
--

