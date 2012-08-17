package converter;

import models.Event;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class EventWriteConverter extends BaseConverter implements	Converter<Event, DBObject> {

	@Override
	public DBObject convert(Event source) {
		DBObject dbo = new BasicDBObject();
		dbo.put("_id", source.id);
		dbo.put("title", source.title);
		dbo.put("description", source.description);
		dbo.put("eventDate", source.eventDate);
		dbo.put("organizerId", source.organizerId);
		dbo.put("attendeesIds",resolveObjectIdsToBasicDBList(source.attendeesIds));
		return dbo;
	}

}
