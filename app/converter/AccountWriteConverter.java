package converter;

import models.Account;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class AccountWriteConverter extends BaseConverter implements	Converter<Account, DBObject> {

	@Override
	public DBObject convert(Account source) {
		DBObject dbo = new BasicDBObject();
		dbo.put("_id", source.id);
		dbo.put("name", source.name);
		dbo.put("email", source.email);
		dbo.put("colleaguesIds",
				resolveObjectIdsToBasicDBList(source.colleaguesIds));
		dbo.put("participatedPostIds",
				resolveObjectIdsToBasicDBList(source.participatedPostIds));
		return dbo;
	}

}
