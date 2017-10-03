package com.nortal.jroad.client.priacrosscheck;

import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.priacrosscheck.database.PriaMaitXRoadDatabase;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.Projekt;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.ProjektRistkontrollRequest;
import com.nortal.jroad.client.priacrosscheck.types.ee.riik.xtee.mait.producers.producer.mait.Taotleja;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Priit Laht
 */
@Service("priaCrossCheckXRoadService")
public class PriaCrossCheckXRoadServiceImpl implements PriaCrossCheckXRoadService {
  @Resource
  private PriaMaitXRoadDatabase priaMaitXRoadDatabase;

  @Override
  public List<Projekt> checkApplicantByRegNumber(String regNumber) throws XRoadServiceConsumptionException {
    if (!StringUtils.isNumeric(regNumber)) {
      throw new IllegalArgumentException("Registry number must be numeric!");
    }
    return checkApplicantByRegNumber(Long.valueOf(regNumber));
  }

  @Override
  public List<Projekt> checkApplicantByRegNumber(long regNumber) throws XRoadServiceConsumptionException {
    ProjektRistkontrollRequest request = ProjektRistkontrollRequest.Factory.newInstance();
    Taotleja applicant = request.addNewTaotleja();
    applicant.setKood(regNumber);
    return priaMaitXRoadDatabase.projektRistkontrollV1(request).getProjektList();
  }

  @Override
  public List<Projekt> checkApplicantByName(String name) throws XRoadServiceConsumptionException {
    ProjektRistkontrollRequest request = ProjektRistkontrollRequest.Factory.newInstance();
    Taotleja applicant = request.addNewTaotleja();
    applicant.setNimi(name);
    return priaMaitXRoadDatabase.projektRistkontrollV1(request).getProjektList();
  }
}
