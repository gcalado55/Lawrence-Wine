package com.wine.Repositories;

import com.wine.Domain.Wine.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wine, String> {
}
