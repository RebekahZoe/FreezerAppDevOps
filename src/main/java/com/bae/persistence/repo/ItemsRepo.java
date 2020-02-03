
package com.bae.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.persistence.domain.Items;

@Repository
public interface ItemsRepo extends JpaRepository<Items, Long> {
	Items findByItemName(String name);

}
