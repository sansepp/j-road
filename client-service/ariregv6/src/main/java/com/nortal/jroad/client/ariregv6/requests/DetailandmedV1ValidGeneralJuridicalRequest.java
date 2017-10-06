package com.nortal.jroad.client.ariregv6.requests;

import com.nortal.jroad.client.ariregv6.types.eu.x_road.arireg.producer.DetailandmedV5Query;

/**
 * @author Priit Laht
 */
public class DetailandmedV1ValidGeneralJuridicalRequest extends DetailandmedV1Request {
  private final String juridicalRegCode;
  private final DetailAndmedV1Status[] statuses;

  public DetailandmedV1ValidGeneralJuridicalRequest(String juridicalRegCode, DetailAndmedV1Status... statuses) {
    this.juridicalRegCode = juridicalRegCode;
    this.statuses = statuses;
  }

  @Override
  protected void fillRequestBody(DetailandmedV5Query body) {
    body.setJurisikArk(juridicalRegCode);
    body.setStaatusedArray(getStatusNames(statuses));
    body.setYandmed(true);
    body.setIandmed(false);
    body.setDandmed(false);
    body.setKandmed(false);
    body.setMaarused(false);
    body.setAinultKehtivad(true);
  }
}