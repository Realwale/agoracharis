package com.agoracharis.customerservice.repository;

import com.agoracharis.customerservice.common.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseEntityRepository <T extends BaseEntity> extends MongoRepository<T, String> {
}
