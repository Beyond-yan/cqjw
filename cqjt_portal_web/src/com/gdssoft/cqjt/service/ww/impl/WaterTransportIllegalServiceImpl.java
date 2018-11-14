package com.gdssoft.cqjt.service.ww.impl;

import com.gdssoft.cqjt.dao.ww.WaterTransportIllegalDao;
import com.gdssoft.cqjt.pojo.ww.WaterTransportIllegal;
import com.gdssoft.cqjt.service.ww.WaterTransportIllegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("waterTransportIllegalServiceImpl")
public class WaterTransportIllegalServiceImpl implements WaterTransportIllegalService {

	@Autowired
	@Resource(name = "waterTransportIllegalDao")
	private WaterTransportIllegalDao waterTransportIllegalDao;

	@Override
	public List<WaterTransportIllegal> SelectWaterTransportIllegal(Map<String, String> map) {
		// TODO Auto-generated method stub
		return waterTransportIllegalDao.SelectWaterTransportIllegal(map);
	}

	@Override
	public void InsertWaterTransportIllegal(WaterTransportIllegal illegal) {
		// TODO Auto-generated method stub
		waterTransportIllegalDao.InsertWaterTransportIllegal(illegal);
	}

	@Override
	public void UpdateWaterTransportIllegal(WaterTransportIllegal illegal) {
		// TODO Auto-generated method stub
		waterTransportIllegalDao.UpdateWaterTransportIllegal(illegal);
	}

	@Override
	public void DeleteWaterTransportIllegal(WaterTransportIllegal illegal) {
		// TODO Auto-generated method stub
		waterTransportIllegalDao.DeleteWaterTransportIllegal(illegal);
	}
	
}
