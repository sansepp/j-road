package com.nortal.jroad.client.ariregv6.requests;

import com.nortal.jroad.client.ariregv6.types.eu.x_road.arireg.producer.DetailandmedV5Query;

/**
 * @author Priit Laht
 */
public class DetailandmedV1ValidGeneralJuridicalRequest extends DetailandmedV1Request {
  private final String juridicalCode;
  private final DetailAndmedV1Status[] statuses;

  public DetailandmedV1ValidGeneralJuridicalRequest(String juridicalCode, DetailAndmedV1Status... statuses) {
    this.juridicalCode = juridicalCode;
    this.statuses = statuses;
  }

  @Override
  protected void fillRequestBody(DetailandmedV5Query body) {
    body.setJurisikArk(juridicalCode);
    body.setStaatusedArray(getStatusNames(statuses));
    body.setYandmed(true);
    body.setIandmed(false);
    body.setDandmed(false);
    body.setKandmed(false);
    body.setMaarused(false);
    body.setAinultKehtivad(true);
  }
}