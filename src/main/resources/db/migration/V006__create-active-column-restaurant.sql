alter table restaurant add active tinyint(1) not null;
update algafood.restaurant set active=true;