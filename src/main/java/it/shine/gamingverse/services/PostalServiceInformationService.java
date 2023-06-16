package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.PostalServiceInformationDto;
import it.shine.gamingverse.exceptions.listempty.PostalServiceInformationListEmptyException;
import it.shine.gamingverse.exceptions.isnull.PostalServiceInformationDtoNullException;

import java.util.List;

public interface PostalServiceInformationService {

    public PostalServiceInformationDto addPostalServiceInformation(PostalServiceInformationDto postalServiceInformationDto) throws PostalServiceInformationDtoNullException;

    PostalServiceInformationDto getPostalServiceInformationById(Integer id) throws Exception;

    List<PostalServiceInformationDto> getAllPostalServiceInformation() throws PostalServiceInformationListEmptyException;

    PostalServiceInformationDto updatePostalServiceInformation(Integer id, PostalServiceInformationDto postalServiceInformationDto) throws Exception;

    void deletePostalServiceInformation(Integer id) throws Exception;

}
