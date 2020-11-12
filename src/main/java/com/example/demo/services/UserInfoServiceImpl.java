package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserInfoCreateRequestDto;
import com.example.demo.dto.UserInfoResponseDto;
import com.example.demo.dto.UserInfoUpdateRequestDto;
import com.example.demo.entities.UserInfoEntity;
import com.example.demo.repositories.UserInfoRepository;
import com.example.demo.utils.LocalDateTimeAttributeConverter;

/**
 * 
 * @author Gilbert Renegado
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
	private final LocalDateTimeAttributeConverter localDateTimeConverter = new LocalDateTimeAttributeConverter();

	@Autowired
	UserInfoRepository repository;
	
	@Autowired
	private Environment env;

	@Override
	public UserInfoResponseDto createUser(UserInfoCreateRequestDto request) {

		// Create user entity for create
		UserInfoEntity user = new UserInfoEntity();
		user.setGivenName(request.getGivenName());
		user.setFamilyName(request.getFamilyName());
		user.setFullName(request.getFullName());
		user.setEmail(request.getEmail());
		user.setImageUrl(request.getImageUrl());
		user.setCreatedDatetime(localDateTimeConverter.convertToDatabaseColumn(request.getCreatedDatetime()));
		user.setModifiedDatetime(localDateTimeConverter.convertToDatabaseColumn(request.getModifiedDatetime()));

		// Insert data and create instance for the response
		UserInfoResponseDto createdUser = new UserInfoResponseDto(repository.save(user));

		return createdUser;
	}

	@Override
	public UserInfoResponseDto updateUser(UserInfoUpdateRequestDto request) {
		
		
		
		// Create user entity for update
		UserInfoEntity user = repository.findByUserId(request.getId());
		if (user != null) {
			user.setGivenName(request.getGivenName());
			user.setFamilyName(request.getFamilyName());
			user.setFullName(request.getFullName());
			user.setEmail(request.getEmail());
			user.setImageUrl(request.getImageUrl());
			user.setModifiedDatetime(localDateTimeConverter.convertToDatabaseColumn(request.getModifiedDatetime()));
		} else {
			String error = env.getProperty("error.resource.not_found.user");
			throw new ResourceNotFoundException(error);
		}
		
		UserInfoResponseDto updatedUser = new UserInfoResponseDto(repository.save(user));
		return updatedUser;
	}

	@Override
	public UserInfoEntity removeUser(UserInfoEntity user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfoEntity findUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserInfoEntity findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UserInfoEntity> listAllUsers(int pageNumber) {
		PageRequest pageable = PageRequest.of(pageNumber, 10, Sort.by(UserInfoEntity.ID).ascending());
		return repository.findAll(pageable);
	}

	@Override
	public List<UserInfoEntity> findUserByName(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
