package com.nortal.jroad.client.priacrosscheck;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.Kulurida;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.Projekt;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Priit Laht
 */
public interface PriaCrossCheckXRoadService {
  List<Projekt> checkApplicant(long applicantRegNumber) throws XRoadServiceConsumptionException;

  List<Projekt> checkApplicant(String applicantName) throws XRoadServiceConsumptionException;

  List<Kulurida> checkExpense(Date expenseDate, BigDecimal expenseSum) throws XRoadServiceConsumptionException;
}
