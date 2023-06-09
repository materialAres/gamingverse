package it.shine.gamingverse.repositories;

import it.shine.gamingverse.entities.GamePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamePhotoRepository extends JpaRepository<GamePhoto, Integer> {

    List<GamePhoto> findByGameId(Integer gameId);

    @Query("SELECT gp.id FROM GamePhoto gp WHERE gp.game.id = :gameId")
    List<Integer> findIdsByGameId(@Param("gameId") Integer gameId);

}
