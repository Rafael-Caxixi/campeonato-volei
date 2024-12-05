CREATE TABLE contratacoes(
    id int not null auto_increment,
    data_compra datetime not null,
    time_id int not null,
    jogador_id int not null,
    primary key(id),
    constraint fk_time_id foreign key(time_id) references times(id),
    constraint fk_jogador_id foreign key(jogador_id) references jogadores(id)
);