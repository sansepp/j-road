package com.nortal.jroad.client.ariregv6.requests;

import com.nortal.jroad.client.ariregv6.types.eu.x_road.arireg.producer.DetailandmedV5Query;
import java.math.BigInteger;

/**
 * @author Priit Laht
 */
public class DetailandmedV1ValidGeneralRegCodeRequest extends DetailandmedV1Request {
  private final long registryCode;
  private final DetailAndmedV1Status[] statuses;

  public DetailandmedV1ValidGeneralRegCodeRequest(long registryCode, DetailAndmedV1Status... statuses) {
    this.registryCode = registryCode;
    this.statuses = statuses;
  }

  @Override
  protected void fillRequestBody(DetailandmedV5Query body) {
    body.setAriregistriKood(BigInteger.valueOf(registryCode));
    body.setStaatusedArray(getStatusNames(statuses));
    body.setYandmed(true);
    body.setIandmed(false);
    body.setDandmed(false);
    body.setKandmed(false);
    body.setMaarused(false);
    body.setAinultKehtivad(true);
  }
}
