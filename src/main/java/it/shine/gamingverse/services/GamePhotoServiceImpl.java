package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.GamePhotoDto;
import it.shine.gamingverse.entities.GamePhoto;
import it.shine.gamingverse.exceptions.GamePhotoNotFoundException;
import it.shine.gamingverse.mappers.GamePhotoMapper;
import it.shine.gamingverse.repositories.GamePhotoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GamePhotoServiceImpl implements GamePhotoService {

    @Autowired
    private GamePhotoRepository gamePhotoRepository;

    @Autowired
    private GamePhotoMapper gamePhotoMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public GamePhotoDto addGamePhoto(GamePhotoDto gamePhotoDto) throws Exception {
        GamePhoto gamePhoto = gamePhotoMapper.gamePhotoDtoToGamePhoto(gamePhotoDto, entityManager);

        gamePhotoRepository.save(gamePhoto);

        return gamePhotoDto;
    }

    @Override
    public GamePhotoDto getGamePhotoById(Integer id) throws Exception {
        GamePhoto gamePhoto = gamePhotoRepository.findById(id)
                .orElseThrow(GamePhotoNotFoundException::new);

        return gamePhotoMapper.gamePhotoToGamePhotoDto(gamePhoto);
    }

    @Override
    public List<GamePhotoDto> getAllGamePhotos() {
        List<GamePhoto> gamePhotos = gamePhotoRepository.findAll();

        return gamePhotos
                .stream()
                .map(gamePhotoMapper::gamePhotoToGamePhotoDto)
                .collect(Collectors.toList());
    }

    @Override
    public GamePhotoDto updateGamePhoto(GamePhotoDto gamePhotoDto) throws Exception {
        GamePhoto existingGamePhoto = gamePhotoRepository
                .findById(gamePhotoDto.getId())
                .orElseThrow(GamePhotoNotFoundException::new);

        GamePhoto gamePhoto = gamePhotoMapper.gamePhotoDtoToGamePhoto(gamePhotoDto, entityManager);

        gamePhoto.setId(existingGamePhoto.getId());

        gamePhoto = gamePhotoRepository.save(gamePhoto);

        return gamePhotoMapper.gamePhotoToGamePhotoDto(gamePhoto);
    }

    @Override
    public void deleteGamePhoto(Integer id) throws GamePhotoNotFoundException {
        if (!gamePhotoRepository.existsById(id)) {
            throw new GamePhotoNotFoundException();
        }

        gamePhotoRepository.deleteById(id);
    }

}

