package com.jn.model.repository;


import com.jn.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInfoRepository extends JpaRepository<UserInfo, String>, JpaSpecificationExecutor<UserInfo> {

}