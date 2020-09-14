package thor.admin.car.svc;

import thor.admin.car.dao.CarDAO;

public class CarDeleteService {
	public int deleteCar(String c_name) {
		CarDAO carDAO = CarDAO.getInstance();
		int result    = carDAO.deleteCar(c_name);
		
		return result;
	}
	
}
