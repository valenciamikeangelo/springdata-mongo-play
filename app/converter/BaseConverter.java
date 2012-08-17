package converter;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class BaseConverter {

	protected List<ObjectId> resolveObjectIdsToObjectId(BasicDBList objectIds){
		List<ObjectId> pobjectIds = new ArrayList<ObjectId>();
		for(Object obj:objectIds){
			pobjectIds.add((ObjectId)((DBObject)obj).get("_id"));
		}
		return pobjectIds;
	}
	
	protected BasicDBList resolveObjectIdsToBasicDBList(List<ObjectId> objectIds){
		BasicDBList pobjectIds = new BasicDBList();
		for(ObjectId obj:objectIds){
			DBObject dbo = new BasicDBObject();
		    dbo.put("_id", obj);
		    pobjectIds.add(dbo);
		}
		return pobjectIds;
	}
		
}
