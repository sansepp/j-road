package com.nortal.jroad.client.kikcrosscheck;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.kikcrosscheck.types.ee.riik.xtee.rmviki.producers.producer.rmviki.Projekt;
import com.nortal.jroad.client.test.BaseXRoadServiceImplTest;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Priit Laht
 */
public class KikCrossCheckXRoadServiceImplTest extends BaseXRoadServiceImplTest {
  @Resource
  private KikCrossCheckXRoadService kikCrossCheckXRoadService;

  @Test
  public void testCheckApplicant() throws XRoadServiceConsumptionException {
    List<Projekt> projects = kikCrossCheckXRoadService.checkApplicant("NORTAL AS", "10391131");
    Assert.assertFalse(projects.isEmpty());
  }
}