package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.PostalServiceInformationDto;
import it.shine.gamingverse.entities.PostalServiceInformation;
import it.shine.gamingverse.exceptions.PostalServiceInformationNotFoundException;
import it.shine.gamingverse.mappers.PostalServiceInformationMapper;
import it.shine.gamingverse.repositories.PostalServiceInformationRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PostalServiceInformationDto addPostalServiceInformation(PostalServiceInformationDto postalServiceInformationDto) {
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
    public List<PostalServiceInformationDto> getAllPostalServiceInformation() {
        List<PostalServiceInformationDto> postalServiceInformations = new ArrayList<>();

        for (PostalServiceInformation postalServiceInformation : postalServiceInformationRepository.findAll()) {
            postalServiceInformations.add(postalServiceInformationMapper.postalServiceInformationToPostalServiceInformationDto(postalServiceInformation));
        }

        return postalServiceInformations;
    }

    @Override
    public PostalServiceInformationDto updatePostalServiceInformation(Integer id, PostalServiceInformationDto postalServiceInformationDto) throws PostalServiceInformationNotFoundException {
        Set<ConstraintViolation<PostalServiceInformationDto>> violations = validator.validate(postalServiceInformationDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        PostalServiceInformation postalServiceInformation = postalServiceInformationRepository.findById(id)
                .orElseThrow(PostalServiceInformationNotFoundException::new);

        postalServiceInformation.setPostalServiceCompany(postalServiceInformationDto.getPostalServiceCompany());
        postalServiceInformation.setTrackingNumber(postalServiceInformationDto.getTrackingNumber());
        postalServiceInformation.setTrackingLink(postalServiceInformationDto.getTrackingLink());
        postalServiceInformation.setEstimatedDelivery(postalServiceInformationDto.getEstimatedDelivery());
        postalServiceInformation.setServiceType(postalServiceInformationDto.getServiceType());
        postalServiceInformation.setDeliveryStatus(postalServiceInformationDto.getDeliveryStatus());
        postalServiceInformation.setAdditionalInformation(postalServiceInformationDto.getAdditionalInformation());

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
