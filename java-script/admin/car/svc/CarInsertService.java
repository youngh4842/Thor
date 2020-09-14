package thor.admin.car.svc;

import thor.admin.car.dao.CarDAO;
import thor.vo.CarVO;

public class CarInsertService {
	public int insertCar(CarVO cVo) {
		CarDAO carDAO = CarDAO.getInstance();
		int result    = carDAO.insertCar(cVo);
		
		return result;
	}
}
	

