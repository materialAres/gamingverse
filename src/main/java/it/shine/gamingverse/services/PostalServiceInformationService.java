package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.PostalServiceInformationDto;

import java.util.List;

public interface PostalServiceInformationService {

    public PostalServiceInformationDto addPostalServiceInformation(PostalServiceInformationDto postalServiceInformationDto);

    PostalServiceInformationDto getPostalServiceInformationById(Integer id) throws Exception;

    List<PostalServiceInformationDto> getAllPostalServiceInformation();

    PostalServiceInformationDto updatePostalServiceInformation(Integer id, PostalServiceInformationDto postalServiceInformationDto) throws Exception;

    void deletePostalServiceInformation(Integer id) throws Exception;

}
