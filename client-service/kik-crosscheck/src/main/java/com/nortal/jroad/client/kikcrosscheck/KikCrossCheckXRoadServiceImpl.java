package com.nortal.jroad.client.kikcrosscheck;


import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import com.nortal.jroad.client.kikcrosscheck.database.KikasXRoadDatabase;
import com.nortal.jroad.client.kikcrosscheck.types.ee.riik.xtee.rmviki.producers.producer.rmviki.Projekt;
import com.nortal.jroad.client.kikcrosscheck.types.ee.riik.xtee.rmviki.producers.producer.rmviki.ProjektRistkontrollRequest;
import com.nortal.jroad.client.kikcrosscheck.types.ee.riik.xtee.rmviki.producers.producer.rmviki.Taotleja;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Priit Laht
 */
@Service("kikCrossCheckXRoadService")
public class KikCrossCheckXRoadServiceImpl implements KikCrossCheckXRoadService {
  @Resource
  private KikasXRoadDatabase kikasXRoadDatabase;

  @Override
  public List<Projekt> checkApplicant(String applicantName, String applicantRegNumber) throws XRoadServiceConsumptionException {
    ProjektRistkontrollRequest projektRistkontroll = ProjektRistkontrollRequest.Factory.newInstance();
    Taotleja taotleja = projektRistkontroll.addNewTaotleja();
    taotleja.setNimi(applicantName);
    taotleja.setKood(applicantRegNumber);
    return kikasXRoadDatabase.projektRistkontrollV1(projektRistkontroll).getProjektList();
  }
}
