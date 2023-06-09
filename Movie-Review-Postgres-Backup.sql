PGDMP                         {            Movie-Show-Review #   14.5 (Ubuntu 14.5-0ubuntu0.22.04.1) #   14.5 (Ubuntu 14.5-0ubuntu0.22.04.1) d    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    28636    Movie-Show-Review    DATABASE     b   CREATE DATABASE "Movie-Show-Review" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_IN';
 #   DROP DATABASE "Movie-Show-Review";
                postgres    false            �            1259    30111 
   collection    TABLE     �   CREATE TABLE public.collection (
    id integer NOT NULL,
    backdrop_path character varying(255),
    name character varying(255),
    poster_path character varying(255),
    movie_id integer,
    the_movie_db_id character varying(255)
);
    DROP TABLE public.collection;
       public         heap    postgres    false            �            1259    30110    collection_id_seq    SEQUENCE     �   CREATE SEQUENCE public.collection_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.collection_id_seq;
       public          postgres    false    211            �           0    0    collection_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.collection_id_seq OWNED BY public.collection.id;
          public          postgres    false    210            �            1259    30120    comments    TABLE     �   CREATE TABLE public.comments (
    id bigint NOT NULL,
    date_of_comment character varying(255),
    note character varying(255),
    user_name character varying(255),
    user_pic character varying(255),
    movie_id integer
);
    DROP TABLE public.comments;
       public         heap    postgres    false            �            1259    30119    comments_id_seq    SEQUENCE     x   CREATE SEQUENCE public.comments_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.comments_id_seq;
       public          postgres    false    213            �           0    0    comments_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.comments_id_seq OWNED BY public.comments.id;
          public          postgres    false    212            �            1259    30129    genre    TABLE     X   CREATE TABLE public.genre (
    id integer NOT NULL,
    name character varying(255)
);
    DROP TABLE public.genre;
       public         heap    postgres    false            �            1259    30128    genre_id_seq    SEQUENCE     �   CREATE SEQUENCE public.genre_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.genre_id_seq;
       public          postgres    false    215            �           0    0    genre_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.genre_id_seq OWNED BY public.genre.id;
          public          postgres    false    214            �            1259    28707    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            �            1259    30136    movie    TABLE     �  CREATE TABLE public.movie (
    id integer NOT NULL,
    adult boolean NOT NULL,
    backdrop_path character varying(255),
    budget integer NOT NULL,
    homepage character varying(255),
    imdb_id character varying(255),
    original_language character varying(255),
    original_title character varying(255),
    overview character varying(10000),
    popularity double precision NOT NULL,
    poster_path character varying(255),
    release_date character varying(255),
    revenue bigint NOT NULL,
    runtime integer NOT NULL,
    status character varying(255),
    tagline character varying(255),
    title character varying(255),
    video boolean NOT NULL,
    vote_average double precision NOT NULL,
    vote_count integer NOT NULL
);
    DROP TABLE public.movie;
       public         heap    postgres    false            �            1259    30263 
   movie_cast    TABLE     �   CREATE TABLE public.movie_cast (
    id integer NOT NULL,
    "character" character varying(255),
    name character varying(255),
    profile_path character varying(255),
    movie_id integer,
    person_id integer
);
    DROP TABLE public.movie_cast;
       public         heap    postgres    false            �            1259    30262    movie_cast_id_seq    SEQUENCE     �   CREATE SEQUENCE public.movie_cast_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.movie_cast_id_seq;
       public          postgres    false    226            �           0    0    movie_cast_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.movie_cast_id_seq OWNED BY public.movie_cast.id;
          public          postgres    false    225            �            1259    30153    movie_genre    TABLE     b   CREATE TABLE public.movie_genre (
    movie_id integer NOT NULL,
    genre_id integer NOT NULL
);
    DROP TABLE public.movie_genre;
       public         heap    postgres    false            �            1259    30135    movie_id_seq    SEQUENCE     �   CREATE SEQUENCE public.movie_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.movie_id_seq;
       public          postgres    false    217            �           0    0    movie_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.movie_id_seq OWNED BY public.movie.id;
          public          postgres    false    216            �            1259    30156    movie_production    TABLE     l   CREATE TABLE public.movie_production (
    movie_id integer NOT NULL,
    production_id integer NOT NULL
);
 $   DROP TABLE public.movie_production;
       public         heap    postgres    false            �            1259    30159    movie_spoken_lang    TABLE     g   CREATE TABLE public.movie_spoken_lang (
    movie_id integer NOT NULL,
    lang_id integer NOT NULL
);
 %   DROP TABLE public.movie_spoken_lang;
       public         heap    postgres    false            �            1259    30272    person    TABLE     Y   CREATE TABLE public.person (
    id integer NOT NULL,
    name character varying(255)
);
    DROP TABLE public.person;
       public         heap    postgres    false            �            1259    30271    person_id_seq    SEQUENCE     �   CREATE SEQUENCE public.person_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.person_id_seq;
       public          postgres    false    228            �           0    0    person_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;
          public          postgres    false    227            �            1259    30163    production_company    TABLE     �   CREATE TABLE public.production_company (
    id integer NOT NULL,
    logo_path character varying(255),
    name character varying(255),
    origin_country character varying(255)
);
 &   DROP TABLE public.production_company;
       public         heap    postgres    false            �            1259    30162    production_company_id_seq    SEQUENCE     �   CREATE SEQUENCE public.production_company_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.production_company_id_seq;
       public          postgres    false    222            �           0    0    production_company_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.production_company_id_seq OWNED BY public.production_company.id;
          public          postgres    false    221            �            1259    30528    review    TABLE     x  CREATE TABLE public.review (
    id bigint NOT NULL,
    best_actor character varying(255),
    movie_rating integer NOT NULL,
    other_comments character varying(255),
    plot_rating character varying(255),
    recommend character varying(255),
    sound_rating character varying(255),
    visual_ratings character varying(255),
    movie_id integer,
    user_id bigint
);
    DROP TABLE public.review;
       public         heap    postgres    false            �            1259    30527    review_id_seq    SEQUENCE     v   CREATE SEQUENCE public.review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.review_id_seq;
       public          postgres    false    232            �           0    0    review_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.review_id_seq OWNED BY public.review.id;
          public          postgres    false    231            �            1259    30179    spoken_language    TABLE     �   CREATE TABLE public.spoken_language (
    id integer NOT NULL,
    english_name character varying(255),
    iso_639_1 character varying(255),
    name character varying(255)
);
 #   DROP TABLE public.spoken_language;
       public         heap    postgres    false            �            1259    30178    spoken_language_id_seq    SEQUENCE     �   CREATE SEQUENCE public.spoken_language_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.spoken_language_id_seq;
       public          postgres    false    224            �           0    0    spoken_language_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.spoken_language_id_seq OWNED BY public.spoken_language.id;
          public          postgres    false    223            �            1259    30289 
   user_table    TABLE     8  CREATE TABLE public.user_table (
    id bigint NOT NULL,
    email character varying(255),
    is_verified boolean NOT NULL,
    password character varying(255),
    role character varying(255),
    user_pic character varying(255),
    username character varying(255),
    verify_token character varying(255)
);
    DROP TABLE public.user_table;
       public         heap    postgres    false            �            1259    30288    user_table_id_seq    SEQUENCE     z   CREATE SEQUENCE public.user_table_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.user_table_id_seq;
       public          postgres    false    230            �           0    0    user_table_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.user_table_id_seq OWNED BY public.user_table.id;
          public          postgres    false    229            �           2604    30114    collection id    DEFAULT     n   ALTER TABLE ONLY public.collection ALTER COLUMN id SET DEFAULT nextval('public.collection_id_seq'::regclass);
 <   ALTER TABLE public.collection ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    211    211            �           2604    30123    comments id    DEFAULT     j   ALTER TABLE ONLY public.comments ALTER COLUMN id SET DEFAULT nextval('public.comments_id_seq'::regclass);
 :   ALTER TABLE public.comments ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    213    212    213            �           2604    30132    genre id    DEFAULT     d   ALTER TABLE ONLY public.genre ALTER COLUMN id SET DEFAULT nextval('public.genre_id_seq'::regclass);
 7   ALTER TABLE public.genre ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215            �           2604    30139    movie id    DEFAULT     d   ALTER TABLE ONLY public.movie ALTER COLUMN id SET DEFAULT nextval('public.movie_id_seq'::regclass);
 7   ALTER TABLE public.movie ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            �           2604    30266    movie_cast id    DEFAULT     n   ALTER TABLE ONLY public.movie_cast ALTER COLUMN id SET DEFAULT nextval('public.movie_cast_id_seq'::regclass);
 <   ALTER TABLE public.movie_cast ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225    226            �           2604    30275 	   person id    DEFAULT     f   ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);
 8   ALTER TABLE public.person ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227    228            �           2604    30166    production_company id    DEFAULT     ~   ALTER TABLE ONLY public.production_company ALTER COLUMN id SET DEFAULT nextval('public.production_company_id_seq'::regclass);
 D   ALTER TABLE public.production_company ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    30531 	   review id    DEFAULT     f   ALTER TABLE ONLY public.review ALTER COLUMN id SET DEFAULT nextval('public.review_id_seq'::regclass);
 8   ALTER TABLE public.review ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    231    232    232            �           2604    30182    spoken_language id    DEFAULT     x   ALTER TABLE ONLY public.spoken_language ALTER COLUMN id SET DEFAULT nextval('public.spoken_language_id_seq'::regclass);
 A   ALTER TABLE public.spoken_language ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    224    224            �           2604    30292    user_table id    DEFAULT     n   ALTER TABLE ONLY public.user_table ALTER COLUMN id SET DEFAULT nextval('public.user_table_id_seq'::regclass);
 <   ALTER TABLE public.user_table ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    229    230    230            �           2613    29796    29796    BLOB     &   SELECT pg_catalog.lo_create('29796');
 &   SELECT pg_catalog.lo_unlink('29796');
                postgres    false            �           2613    29802    29802    BLOB     &   SELECT pg_catalog.lo_create('29802');
 &   SELECT pg_catalog.lo_unlink('29802');
                postgres    false            �           2613    29954    29954    BLOB     &   SELECT pg_catalog.lo_create('29954');
 &   SELECT pg_catalog.lo_unlink('29954');
                postgres    false            �           2613    29956    29956    BLOB     &   SELECT pg_catalog.lo_create('29956');
 &   SELECT pg_catalog.lo_unlink('29956');
                postgres    false            �           2613    30108    30108    BLOB     &   SELECT pg_catalog.lo_create('30108');
 &   SELECT pg_catalog.lo_unlink('30108');
                postgres    false            v          0    30111 
   collection 
   TABLE DATA           e   COPY public.collection (id, backdrop_path, name, poster_path, movie_id, the_movie_db_id) FROM stdin;
    public          postgres    false    211   �s       x          0    30120    comments 
   TABLE DATA           \   COPY public.comments (id, date_of_comment, note, user_name, user_pic, movie_id) FROM stdin;
    public          postgres    false    213   �w       z          0    30129    genre 
   TABLE DATA           )   COPY public.genre (id, name) FROM stdin;
    public          postgres    false    215   Wx       |          0    30136    movie 
   TABLE DATA           �   COPY public.movie (id, adult, backdrop_path, budget, homepage, imdb_id, original_language, original_title, overview, popularity, poster_path, release_date, revenue, runtime, status, tagline, title, video, vote_average, vote_count) FROM stdin;
    public          postgres    false    217   �x       �          0    30263 
   movie_cast 
   TABLE DATA           ^   COPY public.movie_cast (id, "character", name, profile_path, movie_id, person_id) FROM stdin;
    public          postgres    false    226   �       }          0    30153    movie_genre 
   TABLE DATA           9   COPY public.movie_genre (movie_id, genre_id) FROM stdin;
    public          postgres    false    218    �       ~          0    30156    movie_production 
   TABLE DATA           C   COPY public.movie_production (movie_id, production_id) FROM stdin;
    public          postgres    false    219   �                 0    30159    movie_spoken_lang 
   TABLE DATA           >   COPY public.movie_spoken_lang (movie_id, lang_id) FROM stdin;
    public          postgres    false    220   �       �          0    30272    person 
   TABLE DATA           *   COPY public.person (id, name) FROM stdin;
    public          postgres    false    228   Q�       �          0    30163    production_company 
   TABLE DATA           Q   COPY public.production_company (id, logo_path, name, origin_country) FROM stdin;
    public          postgres    false    222   ��       �          0    30528    review 
   TABLE DATA           �   COPY public.review (id, best_actor, movie_rating, other_comments, plot_rating, recommend, sound_rating, visual_ratings, movie_id, user_id) FROM stdin;
    public          postgres    false    232   ��       �          0    30179    spoken_language 
   TABLE DATA           L   COPY public.spoken_language (id, english_name, iso_639_1, name) FROM stdin;
    public          postgres    false    224   4�       �          0    30289 
   user_table 
   TABLE DATA           n   COPY public.user_table (id, email, is_verified, password, role, user_pic, username, verify_token) FROM stdin;
    public          postgres    false    230   ��       �           0    0    collection_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.collection_id_seq', 16, true);
          public          postgres    false    210            �           0    0    comments_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.comments_id_seq', 2, true);
          public          postgres    false    212            �           0    0    genre_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.genre_id_seq', 10, true);
          public          postgres    false    214            �           0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);
          public          postgres    false    209            �           0    0    movie_cast_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.movie_cast_id_seq', 135, true);
          public          postgres    false    225            �           0    0    movie_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.movie_id_seq', 16, true);
          public          postgres    false    216            �           0    0    person_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.person_id_seq', 124, true);
          public          postgres    false    227            �           0    0    production_company_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.production_company_id_seq', 37, true);
          public          postgres    false    221            �           0    0    review_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.review_id_seq', 3, true);
          public          postgres    false    231            �           0    0    spoken_language_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.spoken_language_id_seq', 5, true);
          public          postgres    false    223            �           0    0    user_table_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_table_id_seq', 3, true);
          public          postgres    false    229            �          0    0    BLOBS    BLOBS                             false   O�       �           2606    30118    collection collection_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.collection
    ADD CONSTRAINT collection_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.collection DROP CONSTRAINT collection_pkey;
       public            postgres    false    211            �           2606    30127    comments comments_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.comments DROP CONSTRAINT comments_pkey;
       public            postgres    false    213            �           2606    30134    genre genre_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.genre
    ADD CONSTRAINT genre_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.genre DROP CONSTRAINT genre_pkey;
       public            postgres    false    215            �           2606    30270    movie_cast movie_cast_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.movie_cast
    ADD CONSTRAINT movie_cast_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.movie_cast DROP CONSTRAINT movie_cast_pkey;
       public            postgres    false    226            �           2606    30143    movie movie_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.movie DROP CONSTRAINT movie_pkey;
       public            postgres    false    217            �           2606    30277    person person_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    228            �           2606    30170 *   production_company production_company_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.production_company
    ADD CONSTRAINT production_company_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.production_company DROP CONSTRAINT production_company_pkey;
       public            postgres    false    222            �           2606    30535    review review_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.review DROP CONSTRAINT review_pkey;
       public            postgres    false    232            �           2606    30186 $   spoken_language spoken_language_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.spoken_language
    ADD CONSTRAINT spoken_language_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.spoken_language DROP CONSTRAINT spoken_language_pkey;
       public            postgres    false    224            �           2606    30296    user_table user_table_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.user_table
    ADD CONSTRAINT user_table_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.user_table DROP CONSTRAINT user_table_pkey;
       public            postgres    false    230            �           2606    30231 +   movie_production fk3s8i9sfcv39w4hk9v6u7mv2r    FK CONSTRAINT     �   ALTER TABLE ONLY public.movie_production
    ADD CONSTRAINT fk3s8i9sfcv39w4hk9v6u7mv2r FOREIGN KEY (production_id) REFERENCES public.production_company(id);
 U   ALTER TABLE ONLY public.movie_production DROP CONSTRAINT fk3s8i9sfcv39w4hk9v6u7mv2r;
       public          postgres    false    3282    219    222            �           2606    30246 -   movie_spoken_lang fk446mevgwenf3c6gsj4xyxm5yj    FK CONSTRAINT     �   ALTER TABLE ONLY public.movie_spoken_lang
    ADD CONSTRAINT fk446mevgwenf3c6gsj4xyxm5yj FOREIGN KEY (movie_id) REFERENCES public.movie(id);
 W   ALTER TABLE ONLY public.movie_spoken_lang DROP CONSTRAINT fk446mevgwenf3c6gsj4xyxm5yj;
       public          postgres    false    217    220    3280            �           2606    30536 "   review fk8378dhlpvq0e9tnkn9mx0r64i    FK CONSTRAINT     �   ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk8378dhlpvq0e9tnkn9mx0r64i FOREIGN KEY (movie_id) REFERENCES public.movie(id);
 L   ALTER TABLE ONLY public.review DROP CONSTRAINT fk8378dhlpvq0e9tnkn9mx0r64i;
       public          postgres    false    217    3280    232            �           2606    30221 '   movie_genre fk86p3roa187k12avqfl28klp1q    FK CONSTRAINT     �   ALTER TABLE ONLY public.movie_genre
    ADD CONSTRAINT fk86p3roa187k12avqfl28klp1q FOREIGN KEY (genre_id) REFERENCES public.genre(id);
 Q   ALTER TABLE ONLY public.movie_genre DROP CONSTRAINT fk86p3roa187k12avqfl28klp1q;
       public          postgres    false    215    218    3278            �           2606    30201 &   collection fkc898vwt2w1hqw5qrnlsg2218k    FK CONSTRAINT     �   ALTER TABLE ONLY public.collection
    ADD CONSTRAINT fkc898vwt2w1hqw5qrnlsg2218k FOREIGN KEY (movie_id) REFERENCES public.movie(id);
 P   ALTER TABLE ONLY public.collection DROP CONSTRAINT fkc898vwt2w1hqw5qrnlsg2218k;
       public          postgres    false    3280    217    211            �           2606    30236 ,   movie_production fke0e8gj23po4cc905hysw1ky89    FK CONSTRAINT     �   ALTER TABLE ONLY public.movie_production
    ADD CONSTRAINT fke0e8gj23po4cc905hysw1ky89 FOREIGN KEY (movie_id) REFERENCES public.movie(id);
 V   ALTER TABLE ONLY public.movie_production DROP CONSTRAINT fke0e8gj23po4cc905hysw1ky89;
       public          postgres    false    219    217    3280            �           2606    30541 "   review fkei9p4h1e5mqypku3by3ytjdnl    FK CONSTRAINT     �   ALTER TABLE ONLY public.review
    ADD CONSTRAINT fkei9p4h1e5mqypku3by3ytjdnl FOREIGN KEY (user_id) REFERENCES public.user_table(id);
 L   ALTER TABLE ONLY public.review DROP CONSTRAINT fkei9p4h1e5mqypku3by3ytjdnl;
       public          postgres    false    230    232    3290            �           2606    30283 &   movie_cast fkfb50mdn8yhocmqfsiucsydj70    FK CONSTRAINT     �   ALTER TABLE ONLY public.movie_cast
    ADD CONSTRAINT fkfb50mdn8yhocmqfsiucsydj70 FOREIGN KEY (person_id) REFERENCES public.person(id);
 P   ALTER TABLE ONLY public.movie_cast DROP CONSTRAINT fkfb50mdn8yhocmqfsiucsydj70;
       public          postgres    false    226    3288    228            �           2606    30278 &   movie_cast fkiclxo4q0vtqn16tudwy9gg0i0    FK CONSTRAINT     �   ALTER TABLE ONLY public.movie_cast
    ADD CONSTRAINT fkiclxo4q0vtqn16tudwy9gg0i0 FOREIGN KEY (movie_id) REFERENCES public.movie(id);
 P   ALTER TABLE ONLY public.movie_cast DROP CONSTRAINT fkiclxo4q0vtqn16tudwy9gg0i0;
       public          postgres    false    3280    217    226            �           2606    30206 $   comments fkk4afod0bluaff04jkuj08n7lk    FK CONSTRAINT     �   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT fkk4afod0bluaff04jkuj08n7lk FOREIGN KEY (movie_id) REFERENCES public.movie(id);
 N   ALTER TABLE ONLY public.comments DROP CONSTRAINT fkk4afod0bluaff04jkuj08n7lk;
       public          postgres    false    213    217    3280            �           2606    30241 -   movie_spoken_lang fkkh4lxvuhxqlkfw4rofmarregt    FK CONSTRAINT     �   ALTER TABLE ONLY public.movie_spoken_lang
    ADD CONSTRAINT fkkh4lxvuhxqlkfw4rofmarregt FOREIGN KEY (lang_id) REFERENCES public.spoken_language(id);
 W   ALTER TABLE ONLY public.movie_spoken_lang DROP CONSTRAINT fkkh4lxvuhxqlkfw4rofmarregt;
       public          postgres    false    220    224    3284            �           2606    30226 '   movie_genre fkp6vjabv2e2435at1hnuxg64yv    FK CONSTRAINT     �   ALTER TABLE ONLY public.movie_genre
    ADD CONSTRAINT fkp6vjabv2e2435at1hnuxg64yv FOREIGN KEY (movie_id) REFERENCES public.movie(id);
 Q   ALTER TABLE ONLY public.movie_genre DROP CONSTRAINT fkp6vjabv2e2435at1hnuxg64yv;
       public          postgres    false    3280    217    218            v     x��TI��8]㯰W��	��;@@P���&B�'!�������UwEeq�|99!�y@�gr�����/��m/��T!�F` �_S��2衸,�9--�Ku����vU-9��I���6�oBbsh�+�9<p@��a�Y�Ce7�)���+��z/>�[Ti�65 4u��@@���O=�&����9��w]����Ŵ*�7�3qy��iF��Z^�����ʉ�~�Ѥ�ݖ��?�'�Qؼr����@#�x����jK/��/�
>�������kd�%��IkT�C�1{��[�~��2����c۵YhG�sm���=j9R����������nH��܈��j��V�:�"����ܵ[�>��n���A���;cP2�����Շ]@��[�f�e��͆��W��P�)]h���Du�.��/���:��i�������t=7�Yr'ju]/���(�ѽ�r�\�5�uQo�em)�(yICɢ�<�!��y�d��=;3I��B��	S���M���L��톝W��� ��7����(|S͏KԌHF�������4eV6^�����$�c���/R����Z&q��HlK�{��BJQ{QT�������hK�!��(uF���f|��V�y����ÚĘ#�-�j��4��2E��\�d6+�ETA6ϻrM��^ܗ��lR	m�w#x�ݣx�X��{��K,���)4+�Y�-4!�>�K8T��,�X����e��6^+X���)�vfJĉxC�c���W�[oӅv>|��CP��	fՓ?!�Z�̮|H���Q']3W�=�҂���f^�V��y�8�*-��f�U:K%o~#q��f�yB��F\f.������j�:�O�ʋ���t�?�z����r��"=2�O]�����8YvAs��ɟe�uB�ml	���z.�z��D/^�
u�6n1�-�Z���lz�0�z�?	�ɨq��s�ɜ�C��E��6�FF�\
�";-���S�/P���S�1|]!��a�_���|F�?�%H?W������B�h�V�߿&�ɿ���      x   ~   x�m̱�  �����lJ�+��8�ҵ�Ea�&�j��t1�}�3��]K����Kڹa�;r�#8TL%�+-W̷{��i`�֧,%�e��	�⟍Q�^�7{�����8_>��MZ)���)�      z   n   x�%˻
�@��ޯ��h|�!!�VK�a3�@v&���`N�.ˉ{�9�\y�(��Z�bp��#�����]y�=��������-*/x�ܖE�W�r�b��dk�^�ܒ�a�!`      |      x��Y�n#Iv]��"�6�$��$�w�$J�DI%R��7�� 3�|)L���7c/À��6�Qـm�����%>7��ԍۍ�*Ɍ�����s�:��؟~��=]?[�y�x����<�Z���aU��a�7�QU��w��MӴ d�~?����Ĩ*�ql����k�����W���l�%�J,��%�����FV��-�9����=�g3���|�W��l�����-OC&���e���Ǻ��{�e��W�&K6+F͊,a>b!U�uUY2�e��!]�:�/#5�N�S-�,+Z�@�e�ZN���HY�q�2��n��v��|��d�U�.�]{�\�N��z6.�&���g��L߰}�w-�4�r�kAo�1��Ь,Ŋ�f�PY�+l�b��j}����L�2��츔Xwvof���!<�7w�ͤw�.�_�כĺ��؆�(�EV�'�R�rʫ��{˽��EA�!oҽML�|�︮�V�Wcْ�7c�c�L1<݌ �2��t��R�fN�݋�B�g�	6�b1��� �B���8�"��@V����A�MI�!��b��1�c��s���QR�F�*�<�e�M2��KQ��:��ي�|)�b�5���Z�NIS �1�Rhm"^�2+*Z<%�I��� %�l�^�A�&*��m\�F��}�7�5��r<�൘��"�!d�(wY^W3,$J�"^!O���I��E�[���@h���� �<�����RT���E|?���^�������=��#�gw\�k������mM*�x��'�3�m��c���1w<�j��i���t2Y^������fd_ƺl:��h�%�Y�t�:(��Wn0��kB�iٞ�w��W*����ܮ��롇!L$�	�@�e]��]`xW2A����3z�9�,�LwY����_Q6c9�7�9qv����M����BE�pV	�'��ds?/�J�,*.#�ǃH���x�K{$z8L��Z��ۭ�g�Wi��w����{�ozG�½�N�^.�TeR�ͮ�6,�C���8Όn�2`���t(�U�V�����~X�B:��p�՛~BD���D2�i�C�H���j��]۲L%3��6� E�UƆ�+�A.c"L^��eI��aBJ�@��,��)U%�{0�*��8��� r!b�3}m��6i�[�N�ط��9Ds|鎣�`�������Ӡw���Qg�ݥ@���f�u����}�K��لvp.��673�V��B4\�KA��������?��o�Yd��Ezp��j�6��m�I����__~x}���o߿����|�ײ�L
	�q��m��<ڙR�aI��DB�A�e�7�j��\�`�+��49Φ_
֛�1J�Bed5TP�"96]�A)f�GJJ�ld� <C���p�r��P2T��sHx �Ts5��L�|�fs�gkd�b���~�ʧ�i�Ɋ,�[Ӽ�K��9�8�X\����e�ì)5�(Xq�JA�aJ�1` D�k��z��+z�c�8�f���?K�<`WTX��7�2��G�א�i�CWY](��Z
C�N!���x���	ugQ�����۫��b��,z�[_��o�P���a�a(��4�y��N������űHe]�Z-��՟��TIX
��8s*�:��A��䟮9�;k}b",�D�_�R�aJ�y��9�,�R����]��¨������W+� JwU:WOrz./	.H[Jv1ΐ�7�� �dQ&�!�3�i��[l���B�J�!�3�b\��P���V �'�,1���ְ��z�xD�3��	O5QQ�fS��TOj
�[�K���R<��S�!Z�z!�T;(�-����:U��4��'�I��O���: XQ������?z��\��2��2h:��;[+���L�������;Z{<f���݁/�&r�~���c�	�機�nҶ���qL��ϓ�?:�_ի��2�O�ǁ� �t,��_���5�E�Em��B'L��;b�#P���$!�2E��+�����M2)&�l�d�J��J��+0=�������K�ŕ��jT�٩�?�e��mI�{T�5,:�v;����]8?l�B�����cS`x�.˻�<�;=?O�i���@�nv�������o�dYBٿc�$�pI��c�0�aP��h@���LEj��x���OP�ăm'J��c)�7�eKv���I��՞��c�I)��!��L�	�Y�"N��)Q=r����S1'n��T�j���يǔ7*IlPi�&u�=(w4�Z��Tǒ�����9|
V�a��ДpP���Z�b��+r�Db�8�b���	^�m�:&�|q�%���w�E����Ə�y�:�.�����Lԇo��}���yO���M ��%��2d���"��A�y��E�e}U��GaVO�a�]>��������35X�w��]��U���w���������_���'{}��ח�!`��7��w^�����_^��l�+��{u����o�lSX�N���M
�;��ZRx	&���w�}��`"t���$�x�՞���ָ�9���
;q�{p��8���ۧ�?�xt�M�����,t�憟��dC���b�CI����*����Q�mV�d�,���s�O�g^�Y�>����m笢�6]:�^�#��ɯؠ����z���ї��5*��ڳL9&J5�X~0���A�j���DL�Ԗ*�~ڼ�Ӯ��0T��B]Û��ug�	_���p#!*=����J���9��h�Y�<W��LY�@�P!�w�h���[�1����y������M�-�`i�-�L�mca���5�m$M�C�.��Z*v�?Ur>�aY��Ei��[�aޝ���a�Rton���Ķ:��R����Tw�$)���=E�D�FQ��g��FO�]% p�eW�,@W�`��)XR/�K��o�{�����B˗��C!G�̄Y8;
	��B�#P���AoT�I�v�]v�f����ۅ���?��$�%z,�)� %�D�h)�H͏�M.��������ؕ��2��F������=�B{��D�E.�&�}V�7��)���m��}�:9Ho&k���C޿��yxov�Ǔ�#����N�۱��e�; �;��T�,�ds�V����_Hu���9fw�Tqx�?�O&��Z'Y���d�|��G�m� nj���O�y�ǮP����B$9���q݊�Y6t����@��(P�|����RC�/�l� 4Z7�`�0E?O~�$k(�$�D �R�ę�Rڴ��-4�p��_�����zpp\=�L}�i��/�q��T�w\�F��~4&6IQ3�PwL�K����}~���i�wR��̼l�n;�A��~���Q�@DF�$��z���T5�A!�B �������>kWF�W�嘧�t��P��ޏ^�Q�*����Ҟ)�a�����p�p&Od��UP;��7��n]��H�.��Fȣ\j��=��VY:����ikO��(���j���F��dYF(VM1ϕ>���Q�E\��S�
����`Y�%�I��M���:�>Ҟa � ���Z�f��T7����谡��6 U��"ʭ���I��'�YɎZ��p�v������6g�R�|�D�&EK��;Z�'�z�B���,ĺ��6VU��j��j`w����n�����[�gcV���}<�����s�����<l��Ӱ7��S1R�H`�B�S�U��V$�������a|�>�����I\��/���9�Xz�t;�&:@�,Si��^�����^�}���t���o�Jt�a'��F7F +r�[��+�#�Jt;��RV�E��)�I����(l�N���hu{�����3�ڪ�T�hT|�����՝`}�l/�继���������o��7���h�y���ۇ/g����n�3G�:fײ]���U�4M���'ⷯ/���������U�ߨp�o�x��������Pd�B0�h>�N����U��1�|��3^F�,��	~����.M��@�a�=��Q��yL�r\i��Tl�=�e4����F�
o� *  ְ��V�eE܆��ݖC-颍6=v&���������xx/��惸"��As|��Y�~��a�v:O�ڮw�VI7��F�N�c�i������ze��RV�w��?�Q��u���Ne����Z5���j��T���1u�UE,��-訃(�>�Z�Bq]$��}��͡�S�T۱(�V�B��~.���=�di�]d�.۾[��Fõ�k�VfP.�]�J�`����wZ�O	9~H��}���Sz|R����i�϶D�~TA�n��x��qd����X�+}����󧭝���~�	�      �      x��Zɲ��r�Ww���{����o@}xR@!�V*@��#�9��ԝpؓ�&[���Z��\�re!�s#�@wB~�h+���p��ٖVUn�hxn�t�⦪���3��9��x��4m���"��2�T��3N�_i8Ṉ9r��x��'�<;���^�W��2�p����g(�&�O�^��\R�����O�p�s�2v��y����@nrʤ7�«ܜ��C�,$8�_��/�������8]D�����ܯ�G'?��}2/YG�i�:n���*�q#\�����NH�ԡeP���F�v�?��j��k5^�(m/e��j"Kj��ʽ*��w���h)z��������t���R�SX�
?stÔ��|v�G8M�M�	]Z�s��Ӧ�r�i���Q�v���[s��9�7��u(
R�
?NQ��?�)#�ps��R� 6���e!;�ٓ��|v��vO��zC��ŭp�C�q��@��WBػE���"m�h�ҳp!�ˋi�y���o��D�2�F���m��������X5)ra����Ez��K�����yRs�lC�.���&��$=O:A	n��3�
�0�%lq��*����<H�塽�u4�i�Ț�'��9�m�a2�e,t]�� 6G))�_��i���E�dm�w�Qjk��N�%�uX��������B��e�r;�
S�f,��?$W��u��jE�C��f�@���N�%�yZ��0���N����hn�R
�:MYa��v!:���w�Ym�N�h�{jZ���K�ksn���g�hZ0�A���<0��͟���d�Ə�� I=='jY�����K:�/�!�Ӝ��yP@���Y�TF�
.JQT��4���#�d��ևk�e��Q�N?�t^2�G_�
�#�g8'�a�J��9�l�zP��oD����M�z�v�~<�w
�W����`��*���g�Z��Xف:�B?MY��Ierȕ�.�Ce{��N)�C�^}�L^��hs�\Vs�{E�#�	�z���C�Ԥ;���f~\fش�4U��+���2� Z�,�ΌI*�,3�aG����x�~Tg����,`�,��"^R�3��z���2�d���$� J~��h�hn�Q�k�j��^��H,޻�A��'`A,�E��!e��
�"au�%O���Nk)���B7� ����a~�d^V��z��u�������6>D�<�����4].w�h�m�����Q�h]�����[��9B�"�!�@k����=�ay!l��btƻih�jW� �x72_,z�������g������a�J%x���'�v���5Х��ؑ������ι��O�O0`�
Ps$L_�m�a,�ec�O}u���"I�f`}�t^68;�)A4x�¯9d�"@��|s��q� ��y��+�V����e�U=�(�)���%F_e��Io��ėF�U���7[e�ͧe��-�[dY��.�4(��K'@�h�E��VX
��`�W�-���m��x.���-^��]�Ʌ(7}�Y�]�]:Z��@�G�����|l�G�W���V�!&CvP�
]���y��V��Eqo���m.�Y��xE���d���PWe��p5
�G���S;X����C����`���q������{��ݵ�+`g0N�~~���L e6&v�b�kuc�uO�#O��v��	�������Lp�j̜�C�4�wwE>z"��@=iٔΛ�ep� h�TQ-�h1�3�8g������R=t�l��ե�w�=õz�WnJ2��5S���p3�-h2Â��;z� ��BT�Մe>NFW��"Z�{��+����������A�xU�0C �Pv3�y9���+^���*q�*b5���q[
�-Uƞ����i��j$�%��i<�����[v�^�\��m;BoU�Uƺb�ڭX��;�+�E�^���/��b�:�����p#����%�f��yU�>� � ޑ�WC@�]A�W���/����l������6\���R�|��ck�8} 8�`��^��1��bK.%ŝ���3�Qy����}�b~�����t��u�C�X�"�ґo�a�A�x���:��CxN��}~��CS���:�1��h�M���y��6�s�'Aр�ʡ\S*�C��89�����4�n�_[�x��-$��&5�>X�'�x�٭�jt�aP���|���>]�|ۚ���k��,�,�J�DR���!���Է�����qT��g��kw*�����X��H �'��J�v��J����-o��򠉼�L_�f�ԱJ�����1m��f�o]��$Gio��s��A�5`'����;B��zv���=:׋GiC�p��A���ǵ�,�yM�$-a�G�:������ ~���[bwoGKהJ��keԖ�|�?0
�i`-�P�`j`����7tfb���Y|���ks����d��߃�t��(�~cAJ����1Z�4[�t	�N�5����lfل�3��Xi�:xLgr4:�j�=��|_���GX����eb���+D�s�� B7�qF=j���z�X��<�&�~=�>�Y��q~�t&L�X#�h1���1Л���nx'F�@B�1i��z�}0L^���`��R��\&���R��$���+t�wg$�? �K A�4����
h����Ck`;Sh��CC��P94��n����q{�߀���2�)��*���gsXbC�e�2#���E��P1F�,��MO�l�ǉ^&�f0�<��6Ǧ$c�`>�.���H�ඟo��$U��䮢�g���*��0��	씀�=��c��-�����n���?�UY�L����:O9�b!�����0���m$�ܺd}�����KŊ�IK�8�D�'�?+LK�}�!r���:��}�"�ف<�������'�h$��ז�E��pw�_����g���i�$P��B������]#��%���c}�%n���:��ܰ��������YzD
e�h�/^��p���<q�p��?�)U&���_L��-�5�n��a:@)�B�ڦ��>6��<;�)L_�]���0yC�l����s���,�F��z�TUi}�.s1��[lm�OT,ސ�A��1.�2z��Q'����KE4j��z1���ş�7����yC���1��_�:�Z�C�q�u
���T����[�'�v�I��c�V�}<��I���D�8|�e
 ~Bl����ŖNw�b�W�v����d��*3ئ��^�⡖9ѨC�4ʝ�|W��+�6U�����D��74��@�C+�DO�V�%v�Q�K�f�(�'�L!��*o����9�3�p®cʨ�<[��uy{P��u�VQ=�xެoM�Ho����nG�Y�A��U��˵���Q��c�{Ihga�J�������/��&׫�J���T��E���t�l� ��U_�N���P�ߕoX,�M6A��F�����ƪ36p�^��f�GIw����a����M6_���ns�+ea�{8n��x5ѧm�a��hΚ����M��K`�������[�8XDjfA]�{�}7����,6Eޔ����d���3/ L�d��f\�e�Lqkw��Z�3%�T@uZd�9��u�5f>���W~Yl��׵M�J�AU��c�e�
=��f���*�ξT�D�E�a2{�^m��˯���&hFը����ꋦ�&�Y c
�F��t5y�B
R���[���J�n�զ-g�c�6g��&���`�iK�0�ōy�(��n~�m/�(�7�x���mw9���x� ����ʜ֩=��e�zP��[���d5=Yf�5��s���䒈��J�M�ckP�.�e&�j
�R��ES�kw���r�ū��OT�|�r���UXz-��L	9�dv
I"O�@B���x��ی�K�9�Aܐ	���|QLޒ@d�fNX/L�*hn��4g+���=��_T�T~,ޒ�m {�a���� �  �I��/Ӿ�ȗ�ij݉t,N7h���y�ig~��A(������;����R<^5��-���\��SFu~�E�w�0FZ0A��?��p �3�`�͟�B�f��o��:[ߚw��|���]�->���nƭ/��,�C�#�]g c�E�>٥=/jʮZ��8��c�T���_�Q��iM�[�f���_O�~W�j�B��U�2A]��6-:��&E�������	��M-��MѢ�6��"{���.&�t}a�����mpL�����@L��Ip����g 
��%�f�)���a5�VZ���h�eG�y���VK0��0CcaC	�ȵ�@A������p�ɜ�hf����˩{X��D�P@�O���z��"��|?t:+t7��XmD�r�|AǷ�,�b���@u�P"-�c[~du�<�Λ�
z�kʲn����I�ryyO��Y쉉��H
j��]�ꌰ���"�3��l�K뿡7:�I���%�DlI*�ud��.���p �=�n�w�S�?�!� ` ��(cD�;�Xg�XM8A�ij����#
��p�ǖt���������i�F7���g`X���,�q��NP�T������J�)K��]���NF����y	�V7*��)$�._�~''�I׹���i�֑W���Y����K8@�$�=�e�z���cy����1���\��E�g�
�enl���Q��U�����#��ui�a����⦛�O��a�N�M���B�4�S�t/�LP%�[E��^P�����h?Q���1�Cv,6��J��Z^��`A���L=%�	�c��uF�LL�Ѵw�M�jt�&�dn\\��*���d^�!!<s�à��"h��`yux4���<������QN̜�8��uA�)�(��`��C�
A�H�1��M��N���b�:W�=�:�cxu�]i�Ǚ���"���WLċ����:g�Ѹ,�~�4�}���w{g^�vt��2):�����Lk��=js�����`&;S��ך�Ÿ�|��h܎!��f�7���3J�����u��Mq�y��
4�G?��W�:���d��=+���l���)�{��ܐ5-��.�,���\�'4/���fO������w�P528Y
L��'�- ����P�0/�d�*Ù^K���])���ըN��r�E�*�e�]㷂��绿��������o��v4�����\�l��3I[��/���p;�W�y-�	�PJg��G��I,�-j��r}��r8q/�*G�jJV���^���mn�Wnun���k����B��8�0���J���ik��Y��eg9�ȥ����ޠ�e�_������|���6`Y~�H��ns)��ơ�:����~̬�w9��l1a��-	�7�����+{jWeu�k
����؊wƲ�� z�������khi��2�E{i�.�QUJ���5�(S�<6_ �	E�:){6�N�ȉA[�{��<I1)�׳����M��P1ed��o�2���Z�z���5�+�]8%��{*=DlTg8f����h���YlG��=Q��d���+
gWE&�z~}*1����
��-
	]!�q�����e������ްM�2Y��Fw�}AٗT�(��<�O<�*0���'���8�I]ύ|ݎ̩@E~Q������ ��9��/��<�)��I^=o�݆�KF��:�NtVl����Q�y���
CU���'����p��      }   o   x��A1��0�&w����,�"���-�Z�e[I�-m�M���˥&o�ik���{�u�������r���C��1�ZӝK�c,�r��5�b��I�}�g      ~   r   x�-��	0Dѳ��`m^zI�udF���Xn�`��b�6r:��Eq���y��]�HQ����+9���$��.B�]�Ƶ��+ޟ_�k��\:Ƅ�e�s��i�7��|? ~�~ p         @   x����@B�s�%��N.��0��(D�:%Z;j�'J�"�hl�>;�#�IS�͘%��y�      �   =  x�=U�V�8|n}��`N|��p`6C8Ù=�҉E��,���|�V;�/]ܪ���N�o�wg}oެJ�l�L�g�`���;�z=;��:�*'��7��cp��)F3��n�3^?X,�*�~�#��TEO��q��Q5=Xg�d�C��������csVɊ��o��U�еD݄w��$��'1�A%�b���)��޼�Ao&�z�*)���c�~���s:��wS�Zܫ�!x�g}�q��TR#�Y_:'�4�f�g�Ƚ��C��+�}7��o�	=,wp`�T��G���*�@�9�'��q�o��r4zc�G+�
z��E!�JK������[�I+���e�������q��/JR��
Z��=�Ve+:���z}�P�%��k�����fI>A����E��*� Y:l��lYN3�b���[�YA��HIe�9����t�-�zb+�G3�^�
���g�1VJ�U��-P�x������2�P�XīyJ�@�~�7��3�Z��7�pPyN�fg�(핉�I��m��O���K��`�Q�T�� �۸�U.���
 H�=CX�ƏNĿ��hZ�I�mGU$'*�ٵ�Z��o���*���`/"~:*rz�E�;;J��,zr�`E��N =~Cڎ�dYUR^��Uш��Z��5����j#�n#ʯʄ���Ŷ�Y�)��MP�U�!�����*s�N�TCoAy����:���]�8>ReI�ȣ�G$��I$a5N^�0���'�FD}�� ~ʆ~�Y��ס��� �7Cw �R�z4��[��J��PA�-���ʤ+�(�PnA���a������dݤ���b��w�X���Ͽ�U�6�g���U5� ��9E#TWm�8p�\�Z<�7���y%v��NA%z�!�%�:#xb��wp]�g7�����	���I�%�Mn�-���,4��+����k~�v)�}Qu�[�U�f�q�A�� �$��'�hD;�#�4�j�U�k����U-����>n
:C���3jeU��ю���j*\����W�Xl8X��I34h|;J�k��ӱ�Xz�8�/��*!�<�7����=�.&���d�E��
Ն��ɩ!�U�N;�����(�F<`z�� YUb�'~Q.���q����k}/�m�P�`tm&o�i�0�H��^^�G�S��knÈ�X�|��uN��2�d
�J�A�2�R����`iث@���Z���kw1fk����o��8��Z���C�s��K���7�=����;}k�x�4���b�I�XR���9a�-����2[������	�6      �   �  x��TK��H<���VyHsDQ�o.R
��_�eٳ�=Ӈ�p ��YC�/���.U�Z�%��w��$�c���*|�\x��C^��p�MO�~���A~R0��E����P4E;US���vp��1ݷ��D�M��3]��]n1p[ e�cjX�^�/�3��oΏ��� /?�'%�7����mHȃ�6>g�|�������-C�2�CK��[Õ�h���(�7�|��W�Y�\g��ޭ��ʯ&0�5n1���_�Q����*t�#e��*1�U�����1i	�����vq삭{ݺ��N
fF�5���j��cJ����p���V�3K���޷��Қ$t؍a�� ��aL�����Z���:�n���/��Cցv��dd����Aじ7pI.�k7C*���CO������x���"������(�_`Oy��2P.�:F��'��4ZY�QE�@krP��%s�B���2�\�܁�TR9�q��9+�Is�~�ۺ�������oP�?c7%u���X,�N�X�J�m�]�n�9ujc�(;F��8��OT�&-m�WL�N(��ic�+,+g�[g�P��,�G�7���PL}�K���� S���P���ݔ��[8����ڸ�ܝv?O�P�'X���n,���XpI	���7	��NB����*x$Da��#��������k��ꖳ���yZF\���F���������,=t��Q��p��轖��V�0J�3��/�&\]_�C�Lx=++><�d���s[ �������hJ������-�<�
X/gy7���?�.��=�|�t�t�P�M�&�|�P��la�h��.W2�;�tw�ւ] �׫���䀟�������{�5�p�PgP}R�J6��H�X����#�'2�(CuFe֎�PT^#ͫ	=%D��ٟUϖ=�^��i!]ђ�z��=vE$1����?�������|      �   �   x�����@�w_��xPX54��`l�s�M�3p���K,��g2cq��IG�]c��[��JUhE�����@O��$I��4rX0�宽igT2��rҶ�d�&Ù]��LG���Q�%�W���7��F����c>�m>&      �   s   x�3�t�K��,��L̓1��8݊R�38ӊ��ļ��3���9���J�9]�o���2����K�����|�d�%�,Y�`i�%�,m�2�N�+�.�,�,N������ qP+w      �   �  x�M�Ko�0���9�jb�5�nR��Ү�VZ�^ᑄo_���=�4����P��� e��)4P��;U@��*P����LW�Y�Է�`��f���;&雫�W���Ӑ{����}��y��N���
>
wM�d���R}.'�[��A�p��I9�&��%<��'���D,4��4��;���ߜ�g�9��>��"�]�� �X�a��Z�����;�3�r��m�E��OeuZ�����ލ���XH�Ȇ�p<��h$;�ؒ&s#�)�����Έ2-5�;���D��RI]���w�|w���n)q��=��S[g�]rnp��'n.�kW�Mw�!���]�j�N�o����S_@�\aZ`�0Jc�� 	v l��$��G�4�>�      �   dt   �   x�%�An�0��򈜂��LK+��L�\��/�\w���[gŬ��5n�:P)�a�Zꔡ��������ÒG; ��82e�^A{�Z���1��xgp��@韝�����Cݎ1Q��(��Z�	�_����'{9�5K��}��?��!��W7�L�#�Eؕ�G���9�I8\K      jt   �   x�5�K�0��`���p�q��4�lGU8=�{�y3�\JǕܤ�j@ʆ(欰<�\;aOW�EW��kkb6LF}�8�=i=O �!ҙ��c���=���׋yIO��1Yq�B�p⎵��\6D���5�δ�'r��J@`s��1�%�_��R(      u   �   x�%�An�0��򈜂��LK+��L�\��/�\w���[gŬ��5n�:P)�a�Zꔡ��������ÒG; ��82e�^A{�Z���1��xgp��@韝�����Cݎ1Q��(��Z�	�_����'{9�5K��}��?��!��W7�L�#�Eؕ�G���9�I8\K      u   �   x�5�K�0��`���p�q��4�lGU8=�{�y3�\JǕܤ�j@ʆ(欰<�\;aOW�EW��kkb6LF}�8�=i=O �!ҙ��c���=���׋yIO��1Yq�B�p⎵��\6D���5�δ�'r��J@`s��1�%�_��R(      �u   �   x�5�K�0��`���p�q��4�lGU8=�{�y3�\JǕܤ�j@ʆ(欰<�\;aOW�EW��kkb6LF}�8�=i=O �!ҙ��c���=���׋yIO��1Yq�B�p⎵��\6D���5�δ�'r��J@`s��1�%�_��R(          