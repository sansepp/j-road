package com.nortal.jroad.client.priacrosscheck;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.Projekt;
import java.util.List;

/**
 * @author Priit Laht
 */
public interface PriaCrossCheckXRoadService {
  List<Projekt> checkApplicantByRegNumber(String regNumber) throws XRoadServiceConsumptionException;

  List<Projekt> checkApplicantByRegNumber(long regNumber) throws XRoadServiceConsumptionException;

  List<Projekt> checkApplicantByName(String name) throws XRoadServiceConsumptionException;
}
