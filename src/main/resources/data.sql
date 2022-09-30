INSERT INTO public.users (id, created, updated, password, username)
    VALUES ('081e3968-786a-4fb9-b5f3-930222ff029b', '2022-09-29 22:33:16.000000', '2022-09-29 22:33:16.000000', '$2a$04$EGgpzdS/3afy8q6cyTPUsO35vwVfyfqmkKhcMIrM5khoaXBAcylwm', 'user');

INSERT INTO public.subject_categories (id, created, updated, name)
    VALUES ('29a2356c-e12a-415b-9f69-ffb02b869435', '2022-09-30 06:12:44.000000', '2022-09-30 06:12:46.000000', 'Кошки'),
            ('5b1de31f-7b9d-48f6-b827-e01e44695260', '2022-09-30 06:12:44.000000', '2022-09-30 06:12:46.000000', 'Собаки'),
            ('5c7da8ba-cde2-471b-a801-915ea9bf5853', '2022-09-30 06:12:44.000000', '2022-09-30 06:12:46.000000', 'Пони');

INSERT INTO public.user_category (id, created, updated, subject_category_id, user_id)
    VALUES ('7eba01f9-6e04-4f1c-b92e-8a56e19000b4', '2022-09-30 06:22:47.000000', '2022-09-30 06:22:47.000000', '5b1de31f-7b9d-48f6-b827-e01e44695260', '081e3968-786a-4fb9-b5f3-930222ff029b');

INSERT INTO public.subject (id, created, updated, image_url, likes, name, subject_category_id)
    VALUES ('1b8b05de-9fa6-4f1d-bcea-319e9ec6a43d', '2022-09-30 09:09:14.000000', '2022-09-30 09:09:16.000000', 'https://www.eg.ru/wp-content/uploads/2020/09/koshka110456.jpg', 0, 'Кошка 1', '29a2356c-e12a-415b-9f69-ffb02b869435'),
            ('3d09ea44-3b0f-40fc-8018-f72c56db3c84', '2022-09-30 09:09:14.000000', '2022-09-30 09:09:16.000000', 'https://icdn.lenta.ru/images/2022/03/03/09/20220303095556170/square_320_d815f16fa1aad007a5cb7d1ab3611903.jpg', 0, 'Кошка 2', '29a2356c-e12a-415b-9f69-ffb02b869435'),
            ('f67a7d40-7343-4ad4-b8de-ddad96594895', '2022-09-30 09:09:14.000000', '2022-09-30 09:09:16.000000', 'https://cdn.iz.ru/sites/default/files/styles/1920x1080/public/article-2021-04/_AM18250%208.jpg', 0, 'Кошка 3', '29a2356c-e12a-415b-9f69-ffb02b869435'),
            ('56d022da-50a1-439b-92fb-60ad57150563', '2022-09-30 09:09:14.000000', '2022-09-30 09:09:16.000000', 'https://www.bethowen.ru/upload/iblock/63f/63f2f01ca6828d9574995f549d89a2e0.jpeg', 0, 'Кошка 4', '29a2356c-e12a-415b-9f69-ffb02b869435'),
            ('0d97f5ea-6a28-47fd-ad7c-3e94b38c8ca2', '2022-09-30 09:09:14.000000', '2022-09-30 09:09:16.000000', 'https://goodhands.vet/upload/iblock/48d/48dfdcd8590e901e50e0d986b6953a13.jpg', 0, 'Кошка 5', '29a2356c-e12a-415b-9f69-ffb02b869435');
