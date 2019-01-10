/* DB : springbook  */

ALTER TABLE users ADD level tinyint not null;
ALTER TABLE users ADD login int not null;
ALTER TABLE users ADD recommend int not null;
ALTER TABLE users ADD email varchar(255) not null;