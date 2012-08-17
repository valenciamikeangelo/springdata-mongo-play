package converter;

import java.util.Date;

import models.Event;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

public class EventReadConverter extends BaseConverter implements Converter<DBObject, Event> {

	@Override
	public Event convert(DBObject source) {
		Event event = new Event((ObjectId) source.get("organizerId"), (String) source.get("title"), (String) source.get("description"), (Date) source.get("eventDate"));
		event.id=(ObjectId) source.get("_id");
		event.attendeesIds=resolveObjectIdsToObjectId((BasicDBList)source.get("attendeesIds"));
		return event;
	}

	
	
}
