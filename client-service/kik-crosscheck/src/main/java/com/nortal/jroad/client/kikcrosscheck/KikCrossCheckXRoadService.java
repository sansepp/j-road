package com.nortal.jroad.client.kikcrosscheck;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.kikcrosscheck.types.ee.x_road.kikas.Projekt;
import java.util.List;

/**
 * @author Priit Laht
 */
public interface KikCrossCheckXRoadService {
  List<Projekt> checkApplicantByRegNumber(String regNumber) throws XRoadServiceConsumptionException;

  List<Projekt> checkApplicantByName(String name) throws XRoadServiceConsumptionException;
}
