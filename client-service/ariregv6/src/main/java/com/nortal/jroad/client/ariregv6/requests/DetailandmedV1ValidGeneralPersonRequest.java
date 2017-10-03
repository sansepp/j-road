package com.nortal.jroad.client.ariregv6.requests;

import com.nortal.jroad.client.ariregv6.types.eu.x_road.arireg.producer.DetailandmedV5Query;

/**
 * @author Priit Laht
 */
public class DetailandmedV1ValidGeneralPersonRequest extends DetailandmedV1Request {
  private final String personalCode;
  private final DetailAndmedV1Status[] statuses;

  public DetailandmedV1ValidGeneralPersonRequest(String personalCode, DetailAndmedV1Status... statuses) {
    this.personalCode = personalCode;
    this.statuses = statuses;
  }

  @Override
  protected void fillRequestBody(DetailandmedV5Query body) {
    body.setFyysiliseIsikuKood(personalCode);
    body.setFyysiliseIsikuRollJadaArray(getStatusNames(statuses));
    body.setYandmed(true);
    body.setIandmed(false);
    body.setDandmed(false);
    body.setKandmed(false);
    body.setMaarused(false);
    body.setAinultKehtivad(true);
  }
}
