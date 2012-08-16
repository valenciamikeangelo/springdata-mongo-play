package converter;

import java.util.List;

import models.Account;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class AccountWriteConverter implements Converter<Account, DBObject> {

	@Override
	public DBObject convert(Account source) {
		DBObject dbo = new BasicDBObject();
	    dbo.put("_id", source.id);
	    dbo.put("name", source.name);
	    dbo.put("email", source.email);
	    dbo.put("colleaguesIds", resolveObjectIds(source.colleaguesIds));
	    dbo.put("participatedPostIds", resolveObjectIds(source.participatedPostIds));
	    return dbo;
	}

	
	private BasicDBList resolveObjectIds(List<ObjectId> objectIds){
		BasicDBList pobjectIds = new BasicDBList();
		for(ObjectId obj:objectIds){
			DBObject dbo = new BasicDBObject();
		    dbo.put("_id", obj);
		    pobjectIds.add(dbo);
		}
		return pobjectIds;
	}
	
}
