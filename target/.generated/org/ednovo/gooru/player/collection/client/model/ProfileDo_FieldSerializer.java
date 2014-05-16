package org.ednovo.gooru.player.collection.client.model;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.impl.ReflectionHelper;

@SuppressWarnings("deprecation")
public class ProfileDo_FieldSerializer implements com.google.gwt.user.client.rpc.impl.TypeHandler {
  public static void deserialize(SerializationStreamReader streamReader, org.ednovo.gooru.player.collection.client.model.ProfileDo instance) throws SerializationException {
    instance.aboutMe = streamReader.readString();
    instance.firstName = streamReader.readString();
    instance.lastName = streamReader.readString();
    instance.userProfileImageUrl = streamReader.readString();
    instance.username = streamReader.readString();
    
  }
  
  public static org.ednovo.gooru.player.collection.client.model.ProfileDo instantiate(SerializationStreamReader streamReader) throws SerializationException {
    return new org.ednovo.gooru.player.collection.client.model.ProfileDo();
  }
  
  public static void serialize(SerializationStreamWriter streamWriter, org.ednovo.gooru.player.collection.client.model.ProfileDo instance) throws SerializationException {
    streamWriter.writeString(instance.aboutMe);
    streamWriter.writeString(instance.firstName);
    streamWriter.writeString(instance.lastName);
    streamWriter.writeString(instance.userProfileImageUrl);
    streamWriter.writeString(instance.username);
    
  }
  
  public Object create(SerializationStreamReader reader) throws SerializationException {
    return org.ednovo.gooru.player.collection.client.model.ProfileDo_FieldSerializer.instantiate(reader);
  }
  
  public void deserial(SerializationStreamReader reader, Object object) throws SerializationException {
    org.ednovo.gooru.player.collection.client.model.ProfileDo_FieldSerializer.deserialize(reader, (org.ednovo.gooru.player.collection.client.model.ProfileDo)object);
  }
  
  public void serial(SerializationStreamWriter writer, Object object) throws SerializationException {
    org.ednovo.gooru.player.collection.client.model.ProfileDo_FieldSerializer.serialize(writer, (org.ednovo.gooru.player.collection.client.model.ProfileDo)object);
  }
  
}
