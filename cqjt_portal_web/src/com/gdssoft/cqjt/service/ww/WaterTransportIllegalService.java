package com.gdssoft.cqjt.service.ww;

import com.gdssoft.cqjt.pojo.ww.WaterTransportIllegal;

import java.util.List;
import java.util.Map;

public interface WaterTransportIllegalService {

	public List<WaterTransportIllegal> SelectWaterTransportIllegal(Map<String, String> map);

	public void InsertWaterTransportIllegal(WaterTransportIllegal illegal);

	public void UpdateWaterTransportIllegal(WaterTransportIllegal illegal);

	public void DeleteWaterTransportIllegal(WaterTransportIllegal illegal);
	
}
