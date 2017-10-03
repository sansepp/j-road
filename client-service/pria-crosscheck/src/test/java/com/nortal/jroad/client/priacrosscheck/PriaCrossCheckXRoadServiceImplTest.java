package com.nortal.jroad.client.priacrosscheck;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.Kulurida;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.Projekt;
import com.nortal.jroad.client.test.BaseXRoadServiceImplTest;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Priit Laht
 */
public class PriaCrossCheckXRoadServiceImplTest extends BaseXRoadServiceImplTest {
  @Resource
  private PriaCrossCheckXRoadService priaCrossCheckXRoadService;

  @Test
  public void testCheckApplicantByRegNumber() throws XRoadServiceConsumptionException {
    List<Projekt> projects = priaCrossCheckXRoadService.checkApplicant(10391131);
    Assert.assertFalse(projects.isEmpty());
  }

  @Test
  public void testCheckApplicantByName() throws XRoadServiceConsumptionException {
    List<Projekt> projects = priaCrossCheckXRoadService.checkApplicant("NORTAL AS");
    Assert.assertFalse(projects.isEmpty());
  }

  @Test
  public void testCheckExpense() throws XRoadServiceConsumptionException, ParseException {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    List<Kulurida> expenses = priaCrossCheckXRoadService.checkExpense(dateFormat.parse("01/01/2000"), BigDecimal.valueOf(100));
    Assert.assertTrue(expenses.isEmpty());
  }
}