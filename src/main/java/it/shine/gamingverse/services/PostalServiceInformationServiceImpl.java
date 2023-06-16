package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.PostalServiceInformationDto;
import it.shine.gamingverse.entities.PostalServiceInformation;
import it.shine.gamingverse.exceptions.listempty.PostalServiceInformationListEmptyException;
import it.shine.gamingverse.exceptions.isnull.PostalServiceInformationDtoNullException;
import it.shine.gamingverse.exceptions.notfound.PostalServiceInformationNotFoundException;
import it.shine.gamingverse.mappers.PostalServiceInformationMapper;
import it.shine.gamingverse.repositories.PostalServiceInformationRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PostalServiceInformationServiceImpl implements PostalServiceInformationService {

    @Autowired
    private PostalServiceInformationRepository postalServiceInformationRepository;

    @Autowired
    private PostalServiceInformationMapper postalServiceInformationMapper;

    @Autowired
    private Validator validator;

    @Override
    public PostalServiceInformationDto addPostalServiceInformation(
            PostalServiceInformationDto postalServiceInformationDto)
            throws PostalServiceInformationDtoNullException
    {
        if (ObjectUtils.isEmpty(postalServiceInformationDto)) {
            throw new PostalServiceInformationDtoNullException();
        }

        Set<ConstraintViolation<PostalServiceInformationDto>> violations = validator.validate(postalServiceInformationDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        PostalServiceInformation postalServiceInformation = postalServiceInformationMapper.postalServiceInformationDtoToPostalServiceInformation(postalServiceInformationDto);

        postalServiceInformationRepository.save(postalServiceInformation);

        return postalServiceInformationMapper.postalServiceInformationToPostalServiceInformationDto(postalServiceInformation);
    }

    @Override
    public PostalServiceInformationDto getPostalServiceInformationById(Integer id) throws PostalServiceInformationNotFoundException {
        PostalServiceInformation postalServiceInformation = postalServiceInformationRepository.findById(id)
                .orElseThrow(PostalServiceInformationNotFoundException::new);

        return postalServiceInformationMapper.postalServiceInformationToPostalServiceInformationDto(postalServiceInformation);
    }

    @Override
    public List<PostalServiceInformationDto> getAllPostalServiceInformation() throws PostalServiceInformationListEmptyException {
        List<PostalServiceInformation> postalServiceInformationList = postalServiceInformationRepository.findAll();

        if (postalServiceInformationList.isEmpty()) {
            throw new PostalServiceInformationListEmptyException();
        }

        List<PostalServiceInformationDto> postalServiceInformationDtoList = new ArrayList<>();

        for (PostalServiceInformation postalServiceInformation : postalServiceInformationList) {
            postalServiceInformationDtoList.add(postalServiceInformationMapper.postalServiceInformationToPostalServiceInformationDto(postalServiceInformation));
        }

        return postalServiceInformationDtoList;
    }

    @Override
    public PostalServiceInformationDto updatePostalServiceInformation(
            Integer id, PostalServiceInformationDto postalServiceInformationDto)
            throws PostalServiceInformationDtoNullException {
        if (ObjectUtils.isEmpty(postalServiceInformationDto)) {
            throw new PostalServiceInformationDtoNullException();
        }

        Set<ConstraintViolation<PostalServiceInformationDto>> violations = validator
                                                                            .validate(postalServiceInformationDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        postalServiceInformationDto.setId(id);
        PostalServiceInformation postalServiceInformation = postalServiceInformationMapper
                        .postalServiceInformationDtoToPostalServiceInformation(postalServiceInformationDto);

        postalServiceInformationRepository.save(postalServiceInformation);

        return postalServiceInformationMapper.postalServiceInformationToPostalServiceInformationDto(postalServiceInformation);
    }

    @Override
    public void deletePostalServiceInformation(Integer id) throws PostalServiceInformationNotFoundException {
        PostalServiceInformation postalServiceInformation = postalServiceInformationRepository.findById(id)
                .orElseThrow(PostalServiceInformationNotFoundException::new);

        postalServiceInformationRepository.delete(postalServiceInformation);
    }

}
