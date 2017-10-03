package com.nortal.jroad.client.ariregv6;

import com.nortal.jroad.client.ariregv6.database.AriregXRoadDatabase;
import com.nortal.jroad.client.ariregv6.requests.DetailandmedV1Request;
import com.nortal.jroad.client.ariregv6.types.eu.x_road.arireg.producer.DetailandmedV5Ettevotja;
import com.nortal.jroad.client.exception.XRoadServiceConsumptionException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Priit Laht
 */
@Service("ariregXRoadService")
public class AriregXRoadServiceImpl implements AriregXRoadService {
  @Resource
  private AriregXRoadDatabase ariregXRoadDatabase;

  @Override
  public List<DetailandmedV5Ettevotja> findDetailandmedV1(DetailandmedV1Request request) throws XRoadServiceConsumptionException {
    return ariregXRoadDatabase.detailandmedV1V1(request.get()).getKeha().getEttevotjad().getItemList();
  }
}
