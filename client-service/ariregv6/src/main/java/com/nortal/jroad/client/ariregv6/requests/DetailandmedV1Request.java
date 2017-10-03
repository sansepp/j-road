package com.nortal.jroad.client.ariregv6.requests;

import com.nortal.jroad.client.ariregv6.types.eu.x_road.arireg.producer.DetailandmedV1;
import com.nortal.jroad.client.ariregv6.types.eu.x_road.arireg.producer.DetailandmedV5Query;

/**
 * @author Priit Laht
 */
public abstract class DetailandmedV1Request {
  public DetailandmedV1 get() {
    DetailandmedV1 request = DetailandmedV1.Factory.newInstance();
    DetailandmedV5Query body = request.addNewKeha();
    fillRequestBody(body);
    return request;
  }

  protected abstract void fillRequestBody(DetailandmedV5Query body);

  String[] getStatusNames(DetailAndmedV1Status[] statuses) {
    String[] result = new String[statuses.length];
    for (int i = 0; i < statuses.length; i++) {
      DetailAndmedV1Status status = statuses[i];
      result[i] = statuses[i].name();
    }
    return result;
  }
}
