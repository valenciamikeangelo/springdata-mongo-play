package repository;

import java.util.List;

import models.Event;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRepository extends PagingAndSortingRepository<Event, ObjectId>{

	@Query("{'organizerId' : ?0 }")
	public List<Event> findByOrganizer(ObjectId  eventCreatorId);
}
