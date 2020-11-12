package com.example.demo.dto;

import com.example.demo.entities.UserInfoEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Gilbert Renegado
 *
 */
public class UserInfoCreateResponseDto {

	@JsonProperty("user")
	UserInfoEntity createdUser;

	public UserInfoCreateResponseDto(UserInfoEntity createdUser) {
		this.createdUser = createdUser;
	}
}
