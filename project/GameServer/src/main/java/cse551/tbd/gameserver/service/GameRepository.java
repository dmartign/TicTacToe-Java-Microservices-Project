package cse551.tbd.gameserver.service;

import org.springframework.data.repository.CrudRepository;

import cse551.tbd.gameserver.domain.Game;

public interface GameRepository extends CrudRepository<Game, String> {

    public Game findByGameId(Integer gameId);

}
