package thor.admin.car.svc;

import thor.admin.car.dao.CarDAO;
import thor.vo.CarVO;

public class CarUpdateOKService {
	public int updateOKCar(CarVO cVo, String oldName) {
		CarDAO carDAO = CarDAO.getInstance();
		int result 	  = carDAO.updateOKCar(cVo, oldName);
		
		return result;
	}
}
