package com.simbiose.repository;

import com.simbiose.model.TokenResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercadoLivreRepository extends MongoRepository<TokenResponse, Long> {

    TokenResponse findFirstByOrderByCreatedOnDesc();
}
