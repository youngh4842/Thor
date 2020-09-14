package thor.admin.car.svc;

import java.util.ArrayList;

import thor.admin.car.dao.CarDAO;
import thor.vo.CarVO;

public class CarManageService {
	public ArrayList<CarVO> manageCar() {
		CarDAO carDAO        = CarDAO.getInstance();
		ArrayList<CarVO> car = carDAO.manageCar();
		
		return car;
	}
}
