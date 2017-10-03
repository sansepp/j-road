package com.nortal.jroad.client.kikcrosscheck;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.kikcrosscheck.types.ee.riik.xtee.rmviki.producers.producer.rmviki.Projekt;
import java.util.List;

/**
 * @author Priit Laht
 */
public interface KikCrossCheckXRoadService {
  List<Projekt> checkApplicant(String applicantName, String applicantRegNumber) throws XRoadServiceConsumptionException;
}
