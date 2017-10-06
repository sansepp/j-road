package com.nortal.jroad.client.ariregv6.requests;

import com.nortal.jroad.client.ariregv6.types.eu.x_road.arireg.producer.DetailandmedV5Query;

/**
 * @author Priit Laht
 */
public class DetailandmedV1ValidGeneralPersonRequest extends DetailandmedV1Request {
  private final String personalCode;

  public DetailandmedV1ValidGeneralPersonRequest(String personalCode) {
    this.personalCode = personalCode;
  }

  @Override
  protected void fillRequestBody(DetailandmedV5Query body) {
    body.setFyysiliseIsikuKood(personalCode);
    body.setYandmed(true);
    body.setIandmed(false);
    body.setDandmed(false);
    body.setKandmed(false);
    body.setMaarused(false);
    body.setAinultKehtivad(true);
  }
}
