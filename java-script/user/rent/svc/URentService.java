package thor.user.rent.svc;

import java.util.ArrayList;

import thor.user.rent.dao.URentDAO;
import thor.vo.CarVO;
import thor.vo.PointVO;
import thor.vo.RentVO;

public class URentService {
	public ArrayList<CarVO> rentcarList(){
		
		URentDAO urentDAO 			= URentDAO.getInstance();
		ArrayList<CarVO> carList	= urentDAO.rentcarLists();
		
		return carList;		
	}
	
	public CarVO carinfo (String car_n) {
		
		URentDAO urentDAO		= URentDAO.getInstance();
		CarVO	carVO			= urentDAO.rentCar(car_n);
		
		return carVO;
	}
	
	public int pCheck (String id) {
		
		URentDAO urentDAO		= URentDAO.getInstance();
		int nowpoint 			= urentDAO.pCheck(id);
		
		return nowpoint;
	}
	
	public int addPContent(PointVO pVO) {
		
		URentDAO urentDAO		= URentDAO.getInstance();
		int result				= urentDAO.addPointContent(pVO);
		
		return result;
	}
	
	public int addRent(RentVO rVO) {
		
		URentDAO urentDAO 		= URentDAO.getInstance();
		int result				= urentDAO.addRent(rVO);
		
		return result;
	}
}
