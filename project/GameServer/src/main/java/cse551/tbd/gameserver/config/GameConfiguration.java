package cse551.tbd.gameserver.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import cse551.tbd.gameserver.domain.Game;
import cse551.tbd.gameserver.service.GameRepository;

@Configuration
public class GameConfiguration implements InitializingBean {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.gameRepository.save(Game.builder().gameId(0).build());
    }

}
