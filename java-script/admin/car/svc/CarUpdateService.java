package thor.admin.car.svc;

import thor.admin.car.dao.CarDAO;
import thor.vo.CarVO;

public class CarUpdateService {
	public CarVO updateCar(String c_name) {
		CarDAO carDAO = CarDAO.getInstance();
		CarVO cVo     = carDAO.updateCar(c_name);
		
		return cVo;
	}
}
