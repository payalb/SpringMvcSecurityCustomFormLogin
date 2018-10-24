insert into users ( username, password)
values ( 'payal', '{bcrypt}$2a$12$dlm6tmvHvP1ObrL02vE3A.9qB8Uq.Fm/TIQrs6Vt/277YQNES4/UG');

insert into users ( username, password)
values ( 'admin', '{bcrypt}$2a$12$Vz1z1Zv9vOvsG43k1/3touiUf0HZaIYxJaP3luFPgxz/H4B1SCogy');

insert into authorities ( username, authority)
values (  'payal', 'ROLE_USER');

insert into authorities ( username, authority)
values ( 'admin', 'ROLE_ADMIN');

