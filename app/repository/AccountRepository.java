package repository;

import models.Account;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, ObjectId>{

	Account findByEmail(String email); 
	
}
