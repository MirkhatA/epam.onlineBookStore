CREATE SCHEMA `book-store_online` ;


CREATE TABLE `book-store_online`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `book-store_online`.`users` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `mobile` VARCHAR(45) NULL,
  `registered_at` DATE NULL,
  `role_id` INT NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `book-store_online`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `book-store_online`.`users` 
CHANGE COLUMN `id` `id` INT NOT NULL AUTO_INCREMENT ;

INSERT INTO `book-store_online`.`users` (`id`, `first_name`, `email`, `password`, `role_id`, `status`) VALUES ('2', 'Admin', 'admin@gmail.ru', 'admin', '2', 'Active');




CREATE TABLE `book-store_online`.`language` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `short_name` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `book-store_online`.`genres` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `language_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `language_id`));

ALTER TABLE `book-store_online`.`genres` 
CHANGE COLUMN `language_id` `language_id` INT NOT NULL ,
ADD INDEX `genres_language_id_idx` (`language_id` ASC) VISIBLE;
;
ALTER TABLE `book-store_online`.`genres` 
ADD CONSTRAINT `genres_language_id`
  FOREIGN KEY (`language_id`)
  REFERENCES `book-store_online`.`language` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;  


CREATE TABLE `book-store_online`.`publishers` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `book-store_online`.`authors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(255) NOT NULL,
  `language_id` INT NOT NULL,
  PRIMARY KEY (`id`, `language_id`));

ALTER TABLE `book-store_online`.`authors` 
ADD INDEX `author_language_id_idx` (`language_id` ASC) VISIBLE;
;
ALTER TABLE `book-store_online`.`authors` 
ADD CONSTRAINT `author_language_id`
  FOREIGN KEY (`language_id`)
  REFERENCES `book-store_online`.`language` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


CREATE TABLE `book-store_online`.`books` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NULL,
  `image` TEXT NULL,
  `quantity` INT NULL,
  `price` DECIMAL(10,2) NULL,
  `author_id` INT NULL,
  `publisher_id` INT NULL,
  `language_id` INT NULL,
  `genre_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `author_id_idx` (`author_id` ASC) VISIBLE,
  INDEX `book_publisher_id_idx` (`publisher_id` ASC) VISIBLE,
  INDEX `book_language_id_idx` (`language_id` ASC) VISIBLE,
  INDEX `book_genre_id_idx` (`genre_id` ASC) VISIBLE,
  CONSTRAINT `book_author_id`
    FOREIGN KEY (`author_id`)
    REFERENCES `book-store_online`.`authors` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `book_publisher_id`
    FOREIGN KEY (`publisher_id`)
    REFERENCES `book-store_online`.`publishers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `book_language_id`
    FOREIGN KEY (`language_id`)
    REFERENCES `book-store_online`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `book_genre_id`
    FOREIGN KEY (`genre_id`)
    REFERENCES `book-store_online`.`genres` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `book-store_online`.`books` 
DROP FOREIGN KEY `book_language_id`;
ALTER TABLE `book-store_online`.`books` 
CHANGE COLUMN `language_id` `language_id` INT NOT NULL ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`id`, `language_id`);
;
ALTER TABLE `book-store_online`.`books` 
ADD CONSTRAINT `book_language_id`
  FOREIGN KEY (`language_id`)
  REFERENCES `book-store_online`.`language` (`id`);




CREATE TABLE `book-store_online`.`paid_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `language_id` INT NOT NULL,
  PRIMARY KEY (`id`, `language_id`),
  INDEX `language_id_idx` (`language_id` ASC) VISIBLE,
  CONSTRAINT `language_id`
    FOREIGN KEY (`language_id`)
    REFERENCES `book-store_online`.`language` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);





CREATE TABLE `book-store_online`.`cart` (
  `user_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `quantity` INT NULL,
  PRIMARY KEY (`user_id`, `book_id`));

ALTER TABLE `book-store_online`.`cart` 
ADD INDEX `cart_book_id_idx` (`book_id` ASC) VISIBLE;
;
ALTER TABLE `book-store_online`.`cart` 
ADD CONSTRAINT `cart_user_id`
  FOREIGN KEY (`user_id`)
  REFERENCES `book-store_online`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `cart_book_id`
  FOREIGN KEY (`book_id`)
  REFERENCES `book-store_online`.`books` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;





CREATE TABLE `book-store_online`.`order_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `language_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `language_id`));

ALTER TABLE `book-store_online`.`order_status` 
CHANGE COLUMN `language_id` `language_id` INT NOT NULL ,
ADD INDEX `order_status_language_id_idx` (`language_id` ASC) VISIBLE;
;
ALTER TABLE `book-store_online`.`order_status` 
ADD CONSTRAINT `order_status_language_id`
  FOREIGN KEY (`language_id`)
  REFERENCES `book-store_online`.`language` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;



CREATE TABLE `book-store_online`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `total` DECIMAL(10,2) NULL,
  `address` VARCHAR(255) NULL,
  `created_at` DATE NULL,
  `comment` TEXT NULL,
  `mobile` VARCHAR(255) NULL,
  `reciever_name` VARCHAR(255) NULL,
  `payment_way` VARCHAR(255) NULL,
  `paid_id` INT NULL,
  `user_id` INT NULL,
  `status_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `order_paid_id_idx` (`paid_id` ASC) VISIBLE,
  INDEX `order_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `order_paid_id`
    FOREIGN KEY (`paid_id`)
    REFERENCES `book-store_online`.`paid_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `order_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `book-store_online`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


ALTER TABLE `book-store_online`.`order` 
ADD INDEX `order_status_id_idx` (`status_id` ASC) VISIBLE;
;
ALTER TABLE `book-store_online`.`order` 
ADD CONSTRAINT `order_status_id`
  FOREIGN KEY (`status_id`)
  REFERENCES `book-store_online`.`order_status` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `book-store_online`.`order` 
CHANGE COLUMN `reciever_name` `receiver_name` VARCHAR(255) NULL DEFAULT NULL ;




INSERT INTO `book-store_online`.`language` (`id`, `short_name`, `name`) VALUES ('1', 'en', 'English');
INSERT INTO `book-store_online`.`language` (`id`, `short_name`, `name`) VALUES ('2', 'ru', 'Russian');


INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('1', 'Fantasy', '1');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('1', 'Фэнтези', '2');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('2', 'Psychology', '1');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('2', 'Психология ', '2');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('3', 'Education', '1');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('3', 'Образование', '2');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('4', 'Comics', '1');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('4', 'Комиксы', '2');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('5', 'Cookery', '1');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('5', 'Кулинария ', '2');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('6', 'Literature', '1');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('6', 'Литература', '2');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('7', 'Horror', '1');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('7', 'Ужасы', '2');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('8', 'Detectives', '1');
INSERT INTO `book-store_online`.`genres` (`id`, `name`, `language_id`) VALUES ('8', 'Детективы', '2');


INSERT INTO `book-store_online`.`authors` (`id`, `full_name`, `language_id`) VALUES ('1', 'Tolkien J. R. R', '1');
INSERT INTO `book-store_online`.`authors` (`id`, `full_name`, `language_id`) VALUES ('1', 'Толкин Дж. Р. Р.', '2');
INSERT INTO `book-store_online`.`authors` (`id`, `full_name`, `language_id`) VALUES ('2', 'King S.', '1');
INSERT INTO `book-store_online`.`authors` (`id`, `full_name`, `language_id`) VALUES ('2', 'Кинг. С', '2');
INSERT INTO `book-store_online`.`authors` (`id`, `full_name`, `language_id`) VALUES ('3', 'Palahniuk Ch', '1');
INSERT INTO `book-store_online`.`authors` (`id`, `full_name`, `language_id`) VALUES ('3', 'Паланик Ч', '2');
INSERT INTO `book-store_online`.`authors` (`id`, `full_name`, `language_id`) VALUES ('4', 'Christie A.', '1');
INSERT INTO `book-store_online`.`authors` (`id`, `full_name`, `language_id`) VALUES ('4', 'Кристи А', '2');
INSERT INTO `book-store_online`.`authors` (`id`, `full_name`, `language_id`) VALUES ('5', 'Murakami Р', '1');
INSERT INTO `book-store_online`.`authors` (`id`, `full_name`, `language_id`) VALUES ('5', 'Мураками Х', '2');


INSERT INTO `book-store_online`.`publishers` (`id`, `name`) VALUES ('1', 'Эксмо');
INSERT INTO `book-store_online`.`publishers` (`id`, `name`) VALUES ('2', 'Азбука');
INSERT INTO `book-store_online`.`publishers` (`id`, `name`) VALUES ('3', 'МИФ');
INSERT INTO `book-store_online`.`publishers` (`id`, `name`) VALUES ('4', 'Альпина');
INSERT INTO `book-store_online`.`publishers` (`id`, `name`) VALUES ('5', 'Поляндрия');
INSERT INTO `book-store_online`.`publishers` (`id`, `name`) VALUES ('6', 'АСТ');
INSERT INTO `book-store_online`.`publishers` (`id`, `name`) VALUES ('7', 'Фолиант');


INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('1', 'Lord of the Rings. Two Towers', 'John Ronald Reuel Tolkien (01/3/1892 - 09/2/1973) - writer, poet, philologist, professor at Oxford University, the founder of modern fantasy. In 1937, The Hobbit was written, and in the mid-1950s, three books of The Lord of the Rings were published, telling about Middle-earth - a world inhabited by representatives of magical races with a complex culture, history and mythology. In subsequent years, these novels were translated into all world languages, adapted for cinema, animation, audio plays, theater, computer games, comics and gave rise to a lot of imitations and parodies. Alan Lee (b. 08/20/1947) is an illustrator of dozens of books in the fantasy genre. The most famous were his covers and illustrations for the works of John R. R. Tolkien: The Hobbit, The Lord of the Rings, The Children of Hurin. He also illustrated the trilogy \"Gormenghast\" by Mervyn Peake, the cycle of medieval Welsh stories \"Mabinogion\" and much more.', 'https://images-na.ssl-images-amazon.com/images/I/919-2hQNB6L.jpg', '50', '7450', '1', '1', '1', '1');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('1', 'Властелин колец. Две крепости', 'Джон Рональд Руэл Толкин (3.01.1892—2.09.1973) — писатель, поэт, филолог, профессор Оксфордского университета, родоначальник современной фэнтези. В 1937 году был написан \"Хоббит\", а в середине 1950-х годов увидели свет три книги \"Властелина Колец\", повествующие о Средиземье — мире, населенном представителями волшебных рас со сложной культурой, историей и мифологией.В последующие годы эти романы были переведены на все мировые языки, адаптированы для кино, мультипликации, аудиопьес, театра, компьютерных игр, комиксов и породили массу подражаний и пародий. Алан Ли (р. 20.08.1947) — художник-иллюстратор десятков книг в жанре фэнтези. Наибольшую известность приобрели его обложки и иллюстрации к произведениям Джона Р. Р. Толкина: \"Хоббит\", \"Властелин Колец\", \"Дети Хурина\". Также иллюстрировал трилогию \"Горменгаст\" Мервина Пика, цикл средневековых валлийских повестей \"Мабиногион\" и многое другое.', 'https://images-na.ssl-images-amazon.com/images/I/919-2hQNB6L.jpg', '50', '7450', '1', '1', '2', '1');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('2', 'Lord of the Rings. Ring keepers', 'John Ronald Reuel Tolkien (01/3/1892 - 09/2/1973) - writer, poet, philologist, professor at Oxford University, the founder of modern fantasy. In 1937, The Hobbit was written, and in the mid-1950s, three books of The Lord of the Rings were published, telling about Middle-earth - a world inhabited by representatives of magical races with a complex culture, history and mythology. In subsequent years, these novels were translated into all world languages, adapted for cinema, animation, audio plays, theater, computer games, comics and gave rise to a lot of imitations and parodies. Alan Lee (b. 08/20/1947) is an illustrator of dozens of books in the fantasy genre. The most famous were his covers and illustrations for the works of John R. R. Tolkien: The Hobbit, The Lord of the Rings, The Children of Hurin. He also illustrated the trilogy \"Gormenghast\" by Mervyn Peake, the cycle of medieval Welsh stories \"Mabinogion\" and much more.', 'https://upload.wikimedia.org/wikipedia/ru/thumb/8/82/%D0%91%D1%80%D0%B0%D1%82%D1%81%D1%82%D0%B2%D0%BE_%D0%9A%D0%BE%D0%BB%D1%8C%D1%86%D0%B0.gif/253px-%D0%91%D1%80%D0%B0%D1%82%D1%81%D1%82%D0%B2%D0%BE_%D0%9A%D0%BE%D0%BB%D1%8C%D1%86%D0%B0.gif', '40', '7450', '1', '1', '1', '1');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('2', 'Властелин колец. Хранители кольца', 'Джон Рональд Руэл Толкин (3.01.1892—2.09.1973) — писатель, поэт, филолог, профессор Оксфордского университета, родоначальник современной фэнтези. В 1937 году был написан \"Хоббит\", а в середине 1950-х годов увидели свет три книги \"Властелина Колец\", повествующие о Средиземье — мире, населенном представителями волшебных рас со сложной культурой, историей и мифологией. В последующие годы эти романы были переведены на все мировые языки, адаптированы для кино, мультипликации, аудиопьес, театра, компьютерных игр, комиксов и породили массу подражаний и пародий. Алан Ли (р. 20.08.1947) — художник-иллюстратор десятков книг в жанре фэнтези. Наибольшую известность приобрели его обложки и иллюстрации к произведениям Джона Р. Р. Толкина: \"Хоббит\", \"Властелин Колец\", \"Дети Хурина\". Также иллюстрировал трилогию \"Горменгаст\" Мервина Пика, цикл средневековых валлийских повестей \"Мабиногион\" и многое другое.', 'https://upload.wikimedia.org/wikipedia/ru/thumb/8/82/%D0%91%D1%80%D0%B0%D1%82%D1%81%D1%82%D0%B2%D0%BE_%D0%9A%D0%BE%D0%BB%D1%8C%D1%86%D0%B0.gif/253px-%D0%91%D1%80%D0%B0%D1%82%D1%81%D1%82%D0%B2%D0%BE_%D0%9A%D0%BE%D0%BB%D1%8C%D1%86%D0%B0.gif', '40', '7450', '1', '1', '2', '1');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('3', 'Lord of the Rings. Return of the King', 'John Ronald Reuel Tolkien (01/3/1892 - 09/2/1973) - writer, poet, philologist, professor at Oxford University, the founder of modern fantasy. In 1937, The Hobbit was written, and in the mid-1950s, three books of The Lord of the Rings were published, telling about Middle-earth - a world inhabited by representatives of magical races with a complex culture, history and mythology. In subsequent years, these novels were translated into all world languages, adapted for cinema, animation, audio plays, theater, computer games, comics and gave rise to a lot of imitations and parodies. Alan Lee (b. 08/20/1947) is an illustrator of dozens of books in the fantasy genre. The most famous were his covers and illustrations for the works of John R. R. Tolkien: The Hobbit, The Lord of the Rings, The Children of Hurin. He also illustrated the trilogy \"Gormenghast\" by Mervyn Peake, the cycle of medieval Welsh stories \"Mabinogion\" and much more.', 'https://images-na.ssl-images-amazon.com/images/I/61YLlCTnWtL.jpg', '40', '7450', '1', '1', '1', '1');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('3', 'Властелин колец. Возвращение короля', 'Джон Рональд Руэл Толкин (3.01.1892—2.09.1973) — писатель, поэт, филолог, профессор Оксфордского университета, родоначальник современной фэнтези. В 1937 году был написан \"Хоббит\", а в середине 1950-х годов увидели свет три книги \"Властелина Колец\", повествующие о Средиземье — мире, населенном представителями волшебных рас со сложной культурой, историей и мифологией.В последующие годы эти романы были переведены на все мировые языки, адаптированы для кино, мультипликации, аудиопьес, театра, компьютерных игр, комиксов и породили массу подражаний и пародий. Алан Ли (р. 20.08.1947) — художник-иллюстратор десятков книг в жанре фэнтези. Наибольшую известность приобрели его обложки и иллюстрации к произведениям Джона Р. Р. Толкина: \"Хоббит\", \"Властелин Колец\", \"Дети Хурина\". Также иллюстрировал трилогию \"Горменгаст\" Мервина Пика, цикл средневековых валлийских повестей \"Мабиногион\" и многое другое.', 'https://images-na.ssl-images-amazon.com/images/I/61YLlCTnWtL.jpg', '40', '7450', '1', '1', '2', '1');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('4', 'The Mysterious Incident at Stiles', 'The Curious Affair at Styles is Agatha Christie\'s first novel. In this book, published in 1920, the reader meets for the first time the most famous detective of the 20th century, the mustachioed Belgian Hercule Poirot, as well as his friend and assistant Hastings. It is here that Poirot is first given the opportunity to demonstrate his deductive abilities and solve a mysterious crime (the poisoning of Mrs. Inglethorp, the mistress of the Stiles estate), based on well-known facts.', 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1585632445l/52843028._SY475_.jpg', '70', '1390', '4', '2', '1', '8');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('4', 'Загадочное происшествие в Стайлзе', '\"Загадочное происшествие в Стайлзе\" – это самый первый роман Агаты Кристи. В этой книге, вышедшей в 1920 году, читатель в первый раз встречается с самым знаменитым сыщиком XX столетия — усатым бельгийцем Эркюлем Пуаро, а также с его другом и помощником Гастингсом. Именно здесь Пуаро впервые предоставлена возможность продемонстрировать свои дедуктивные способности и раскрыть загадочное преступление (отравление миссис Инглторп, хозяйки поместья Стайлз), опираясь на всем известные факты.', 'https://cdn.f.kz/prod/1405/1404112_550.jpg', '70', '1390', '4', '2', '2', '8');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('5', 'Silent Witness', 'When Lady Arundell suddenly fell down the stairs of her own house, everyone around her saw it as just an unfortunate accident - after all, she stepped on a rubber ball left there by her pet, a fox terrier named Bob. But the more the victim herself thought about it, the more it seemed to her that one of her relatives had arranged the fall. She wrote a letter to the famous detective Hercule Poirot asking him to look into this case. But by the time the letter reached the addressee, Lady Arundell had already died, and her considerable inheritance had passed to her companion. Poirot is unable to resurrect the unfortunate lady, but he can find the killer. And the same dog named Bob will help him in this ...', 'https://cv5.litres.ru/pub/c/elektronnaya-kniga/cover_max1500/45221351-agata-kristi-nemoy-svidetel-45221351.jpg', '70', '1390', '4', '2', '1', '8');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('5', 'Немой свидетель', 'Когда леди Аранделл неожиданно упала с лестницы своего собственного дома, все вокруг увидели в этом лишь несчастливую случайность – ведь она наступила на резиновый мячик, оставленный там ее любимцем, фокстерьером по кличке Боб. Но чем больше думала об этом сама пострадавшая, тем больше ей казалось, что падение подстроил кто-то из ее родственников. Она написала письмо знаменитому детективу Эркюлю Пуаро с просьбой разобраться в этом деле. Но за то время, пока письмо шло до адресата, леди Аранделл уже умерла, а ее немаленькое наследство перешло к компаньонке. Пуаро не в силах воскресить несчастную леди, но он может найти убийцу. А поможет ему в этом тот самый пес по кличке Боб…', 'https://cv5.litres.ru/pub/c/elektronnaya-kniga/cover_max1500/45221351-agata-kristi-nemoy-svidetel-45221351.jpg', '70', '1390', '4', '2', '2', '8');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('6', 'Green Mile', 'Stephen King invites readers to the terrible world of the prison block of death row, from where they leave in order not to return, opens the door of the last refuge of those who have transgressed not only human, but also God\'s law. There is no more deadly place this side of the electric chair! Nothing you\'ve read before compares to Stephen King\'s most audacious horror experience, a story that begins on Death Road and descends into the depths of the most monstrous mysteries of the human soul.', 'https://images-na.ssl-images-amazon.com/images/I/41647mOz0IL._SX324_BO1,204,203,200_.jpg', '30', '6450', '2', '3', '1', '7');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('6', 'Зеленая миля', 'Стивен Кинг приглашает читателей в жуткий мир тюремного блока смертников, откуда уходят, чтобы не вернуться, приоткрывает дверь последнего пристанища тех, кто преступил не только человеческий, но и Божий закон. По эту сторону электрического стула нет более смертоносного местечка! Ничто из того, что вы читали раньше, не сравнится с самым дерзким из ужасных опытов Стивена Кинга - с историей, что начинается на Дороге Смерти и уходит в глубины самых чудовищных тайн человеческой души...', 'https://images-na.ssl-images-amazon.com/images/I/41647mOz0IL._SX324_BO1,204,203,200_.jpg', '30', '6450', '2', '3', '2', '7');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('7', 'Misery', 'Can salvation from certain death turn into such a nightmare that even death seems like a merciful gift of fate? Maybe - because that\'s exactly what happened to Paul Sheldon, author of an endless series of books about the misadventures of Misery. The wounded writer ended up in the hands of Annie Wilkes, a woman who lost her mind because of his novels. The secluded house of a demon-possessed fury has turned into a torture chamber, and Paul\'s existence has become a hell full of pain and horror...', 'http://prodimage.images-bn.com/pimages/9781501143106_p0_v2_s1200x630.jpg', '35', '5650', '2', '3', '1', '7');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('7', 'Мизери', 'Может ли спасение от верной гибели обернуться таким кошмаром, что даже смерть покажется милосердным даром судьбы? Может - ибо именно так случилось с Полом Шелдоном, автором бесконечного сериала книг о злоключениях Мизери. Раненый писатель оказался в руках Энни Уилкс - женщины, потерявшей рассудок на почве его романов. Уединенный домик одержимой бесами фурии превратился в камеру пыток, а существование Пола - в ад, полный боли и ужаса...', 'http://prodimage.images-bn.com/pimages/9781501143106_p0_v2_s1200x630.jpg', '35', '5650', '2', '3', '2', '7');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('8', 'Fight club', 'This is the most amazing and most scandalous book of the 1990s. A book in which Chuck Palahniuk spoke not just \"Generation X\", but - \"Generation X\" already embittered, already lost its last illusions. Have you seen the movie \"Fight Club\"? Then - read the book on which it was filmed!', 'https://images-na.ssl-images-amazon.com/images/I/91MBNyp411L.jpg', '60', '2300', '3', '4', '1', '6');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('8', 'Бойцовский клуб', 'Это - самая потрясающая и самая скандальная книга 1990-х. Книга, в которой устами Чака Паланика заговорило не просто \"Поколение Икс\", но - \"Поколение Икс\" уже озлобленное, уже растерявшее свои последние иллюзии. Вы смотрели фильм \"Бойцовский клуб\"?', 'https://images-na.ssl-images-amazon.com/images/I/91MBNyp411L.jpg', '60', '2300', '3', '4', '2', '6');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('9', 'Suffocation', 'Chuck Palahniuk. \"Crazy Genius\" is not \"Generation X\" but Generation NEXT. The author whose novels \"Suffocation\", \"Lullaby\", \"Survivor\" and \"The Invisibles\" have earned fame as a cult classic of modern alternative prose. The author who REALLY \"blew up the world\" with his legendary \"Fight Club\". Chuck Palahniuk. The writer who is already called the William Burroughs of our time. The book is about a young swindler who plays choking attacks in expensive restaurants every day - and earns good money on it... The book is about sexaholics, alcoholics and clothesholics. About love, friendship and philosophy. About the dubious \"second coming\" - and the undoubted \"unbearable lightness of being\" of our days. However, Palahniuk himself says about his novel: \"Are you going to read it? And in vain!\" However, it is still worth reading - at your own peril and risk!', 'https://www.moscowbooks.ru/image/book/723/orig/i723482.jpg?cu=20210629110504', '40', '2300', '3', '4', '1', '6');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('9', 'Удушье', 'Чак Паланик. \"Сумасшедший гений\" не \"Поколения Икс\", но – поколения СЛЕДУЮЩЕГО. Автор, чьи романы \"Удушье\", \"Колыбельная\", \"Уцелевший\" и \"Невидимки\" снискали славу КУЛЬТОВОЙ классики современной альтернативной прозы. Автор, который ДЕЙСТВИТЕЛЬНО \"взорвал мир\" своим легендарным \"Бойцовским клубом\". Чак Паланик. Писатель, которого уже называют Уильямом Берроузом нашего времени. Книга о молодом мошеннике, который каждодневно разыгрывает в дорогих ресторанах приступы удушья – и зарабатывает на этом неплохие деньги... Книга о сексоголиках, алкоголиках и шмоткаголиках. О любви, о дружбе и философии. О сомнительном \"втором пришествии\" – и несомненной \"невыносимой легкости бытия\" наших дней. Впрочем, сам Паланик говорит о своем романе: \"Собираетесь прочитать? И зря!\" Однако прочитать все же стоит – на свой страх и риск!', 'https://www.moscowbooks.ru/image/book/723/orig/i723482.jpg?cu=20210629110504', '40', '2300', '3', '4', '2', '6');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('10', 'Haunted', 'An incredible, scary and funny story that everyone tells in their own way. Seventeen people accept a tempting offer to renounce the worldly bustle for three months in order to create literary masterpieces. But the creative competition turns their life into a real nightmare! A dilapidated underground gothic theater with no electricity or heating... Food is running out... Help is nowhere to be found... The strongest survive!', 'https://s1.livelib.ru/boocover/1000864120/200/cf74/boocover.jpg', '70', '1340', '3', '4', '1', '6');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('10', 'Призраки', 'Невероятная, страшная и смешная история, которую каждый рассказывает по-своему. Семнадцать человек принимают заманчивое предложение отрешиться на три месяца от мирской суеты, чтобы создать литературные шедевры. Но творческий конкурс превращает их жизнь в настоящий кошмар! Полуразрушенный подземный готический театр, в котором нет ни электричества, ни отопления… Еда на исходе… Помощи ждать неоткуда… Выживает сильнейший!', 'https://s1.livelib.ru/boocover/1000864120/200/cf74/boocover.jpg', '70', '1340', '3', '4', '2', '6');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('11', 'Kafka on the beach', '“I noticed that something black had stuck to the chest of the white T-shirt, shaped like a large butterfly with open wings ... In the flickering light of a fluorescent lamp, it became clear: this is a dark red bloody stain. The blood is fresh, not yet dried. Quite a lot. I tilted my head and sniffed the stain. No smell. Spatters of blood - very little - turned out to be on a dark blue shirt, where it was not so noticeable. And on a white T-shirt - so bright, fresh ... \"A nightmare journey through the labyrinths of the soul of modern Oedipus - in Haruki Murakami\'s Kafka on the Beach.', 'https://cv7.litres.ru/pub/c/elektronnaya-kniga/cover_330/125379-haruki-murakami-kafka-na-plyazhe.jpg', '45', '4670', '5', '5', '1', '6');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('11', 'Кафка на пляже', '\"Я заметил, что на груди белой майки налипло что-то черное, по форме — вроде большой бабочки с раскрытыми крыльями… В мерцающем свете люминесцентной лампы стало понятно: это темно-красное кровавое пятно. Кровь свежая, еще не засохла. Довольно много. Я наклонил голову и понюхал пятно. Никакого запаха. Брызги крови — совсем немного — оказались и на темно-синей рубашке, где она была не так заметна. А на белой майке — такая яркая, свежая…\" Кошмарное странствие по лабиринтам души современного Эдипа — в романе Харуки Мураками \"Кафка на пляже\".', 'https://cv7.litres.ru/pub/c/elektronnaya-kniga/cover_330/125379-haruki-murakami-kafka-na-plyazhe.jpg', '45', '4670', '5', '5', '2', '6');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('12', 'Fight Club 3', 'Marla is about to give birth to her second child, but his father will not be her husband, now calling himself Balthazar, but Tyler Durden, who is very interested in his child inheriting the whole world. In the previous installment, Tyler turned \"Project Mayhem\" into \"Rise or Die\", and with the gates of heaven so close, the new faction implements a ruthless plan to set up humanity, prompting Balthazar to form a dubious alliance... with Tyler Durden.', 'https://simg.marwin.kz/media/catalog/product/cache/41deb699a7fea062a8915debbbb0442c/c/o/palanik_ch_boycovskiy_klub_3_kniga_2.jpg', '15', '4550', '3', '3', '1', '4');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('12', 'Бойцовский клуб 3', 'Марла вот-вот родит второго ребенка, но его отцом будет не её муж, теперь называющий себя Бальтазаром, а Тайлер Дёрден, который очень заинтересован в том, чтобы его ребёнок унаследовал весь мир. В предыдущей части Тайлер превратил ≪Проект разгром≫ в ≪Восстань или умри≫, и, когда райские врата были так близки, новая группировка реализует безжалостный план по настройке человечества, побуждая Бальтазара на сомнительный союз… с Тайлером Дёрденом.', 'https://simg.marwin.kz/media/catalog/product/cache/41deb699a7fea062a8915debbbb0442c/c/o/palanik_ch_boycovskiy_klub_3_kniga_2.jpg', '15', '4550', '3', '3', '2', '4');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('13', 'friends. Official cookbook', 'More than 100 recipes for delicious dishes inspired by the popular series will impress any fan of Friends. It doesn\'t matter if you\'re a seasoned chef like Monica Geller or a food freak like Joe Tribianni. Our book offers recipes for cooks of all levels. Here you\'ll find recipes for iconic gastronomic delights such as Rachel\'s Meat Trifle, Something Grilled with Cheese, Sandwich Humidifier, and, of course, an excellent cheesecake that\'s best eaten off the plate, not off the floor. From starters and appetizers to entrees and desserts, now you can cook your favorite dishes from the series in your own kitchen.', 'https://simg.marwin.kz/media/catalog/product/cache/41deb699a7fea062a8915debbbb0442c/c/o/yi_a_friends_oficialnaya_kulinarnaya_kniga.jpg', '10', '25750', '3', '6', '1', '5');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('13', 'Friends. Официальная кулинарная книга', 'Более 100 рецептов восхитительных блюд, вдохновленных популярным сериалом не оставят равнодушным ни одного поклонника \"Друзей\". Неважно, опытный ли ты повар, как Моника Геллер или без ума от еды как Джо Трибианни. Наша книга предлагает рецепты для поваров всех уровней. Здесь ты найдешь рецепты культовых гастрономических шедевров, таких как \"Трайфл с мясом\" от Рэйчел, \"Что-то жареное с сыром\", \"Увлажнитель сэндвичей\" и, конечно же, превосходный чизкейк, который лучше есть с тарелки, а не с пола. От закусок и аппетайзеров до основных блюд и десертов – теперь любимые блюда сериала ты можешь приготовить у себя на кухне.', 'https://simg.marwin.kz/media/catalog/product/cache/41deb699a7fea062a8915debbbb0442c/c/o/yi_a_friends_oficialnaya_kulinarnaya_kniga.jpg', '10', '25750', '3', '6', '2', '5');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('14', 'Grokaem Algorithms', 'Algorithms are just a sequence of solving problems, and most of these problems have already been solved, tested and verified by someone. You can, of course, dive into the deep philosophy of the ingenious Knuth, study multi-page tomes with proofs and justifications, but do you want to spend your time on this? Open a beautifully illustrated book and you will immediately understand that algorithms are simple. And groping algorithms is a fun and exciting activity.', 'https://simg.marwin.kz/media/catalog/product/cache/41deb699a7fea062a8915debbbb0442c/1/0/1037901676.jpg', '35', '7250', '5', '7', '1', '3');
INSERT INTO `book-store_online`.`books` (`id`, `title`, `description`, `image`, `quantity`, `price`, `author_id`, `publisher_id`, `language_id`, `genre_id`) VALUES ('14', 'Грокаем алгоритмы', 'Алгоритмы - это всего лишь последовательность решения задач, и большинство таких задач уже были кем-то решены, протестированы и проверены. Можно, конечно, погрузиться в глубокую философию гениального Кнута, изучить многостраничные фолианты с доказательствами и обоснованиями, но хотите ли вы тратить на это свое время? Откройте великолепно иллюстрированную книгу и вы сразу поймете, что алгоритмы - это просто. А грокать алгоритмы - это веселое и увлекательное занятие.', 'https://simg.marwin.kz/media/catalog/product/cache/41deb699a7fea062a8915debbbb0442c/1/0/1037901676.jpg', '35', '7250', '5', '7', '2', '3');


INSERT INTO `book-store_online`.`paid_status` (`id`, `name`, `language_id`) VALUES ('1', 'Unpaid', '1');
INSERT INTO `book-store_online`.`paid_status` (`id`, `name`, `language_id`) VALUES ('1', 'Не оплачен', '2');
INSERT INTO `book-store_online`.`paid_status` (`id`, `name`, `language_id`) VALUES ('2', 'Paid', '1');
INSERT INTO `book-store_online`.`paid_status` (`id`, `name`, `language_id`) VALUES ('2', 'Оплачен', '2');


INSERT INTO `book-store_online`.`role` (`id`, `name`) VALUES ('1', 'User');
INSERT INTO `book-store_online`.`role` (`id`, `name`) VALUES ('2', 'Admin');


INSERT INTO `book-store_online`.`order_status` (`id`, `name`, `language_id`) VALUES ('1', 'Processing', '1');
INSERT INTO `book-store_online`.`order_status` (`id`, `name`, `language_id`) VALUES ('1', 'В обработке', '2');
INSERT INTO `book-store_online`.`order_status` (`id`, `name`, `language_id`) VALUES ('2', 'On the way', '1');
INSERT INTO `book-store_online`.`order_status` (`id`, `name`, `language_id`) VALUES ('2', 'В пути', '2');
INSERT INTO `book-store_online`.`order_status` (`id`, `name`, `language_id`) VALUES ('3', 'Delivered', '1');
INSERT INTO `book-store_online`.`order_status` (`id`, `name`, `language_id`) VALUES ('3', 'Завершен', '2');
INSERT INTO `book-store_online`.`order_status` (`id`, `name`, `language_id`) VALUES ('4', 'Canceled', '1');
INSERT INTO `book-store_online`.`order_status` (`id`, `name`, `language_id`) VALUES ('4', 'Отменен', '2');
INSERT INTO `book-store_online`.`order_status` (`id`, `name`, `language_id`) VALUES ('5', 'Not placed', '1');
INSERT INTO `book-store_online`.`order_status` (`id`, `name`, `language_id`) VALUES ('5', 'Не оформлен', '2');
