package com.example.bookingsystem.serviceImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookingsystem.exception.InvalidException;
import com.example.bookingsystem.groundinfromation.DetailsGround;
import com.example.bookingsystem.groundinfromation.GroundDetails;
import com.example.bookingsystem.groundinfromation.GroundInformationForUser;
import com.example.bookingsystem.respositary.DetailsRepositary;
import com.example.bookingsystem.respositary.GroundDetailsRepositary;
import com.example.bookingsystem.respositary.GroundRepositary;
import com.example.bookingsystem.service.GroundService;
@Service
public class GroundServiceImp implements GroundService{

	@Autowired
	private GroundRepositary groundRepositary;
	
	@Autowired
	private GroundDetails details;
	
	@Autowired
	private GroundDetailsRepositary repositary;
	
	@Autowired
	private DetailsRepositary detailsRepositary;
	
	private Map<String,GroundDetails> map;
	
	public GroundServiceImp() {
		this.map=new HashMap<String,GroundDetails>();
		this.map.put("Cricket",new GroundDetails("Cricket",30,250.0));
		this.map.put("VolleyBall",new GroundDetails("VolleyBall",15,200.0));
		this.map.put("BasketBall",new GroundDetails("BasketBall",18,200.0));
		this.map.put("Tennis",new GroundDetails("Tennis",8,200.0));
		this.map.put("Badmitan",new GroundDetails("Badmitan",10,200.0));
		this.map.put("Kabaddi",new GroundDetails("Kabaddi",20,200.0));
		this.map.put("FootBall",new GroundDetails("FootBall",30,250.0));
		
		
	}
	
	
	
	@Override
	public List<DetailsGround> getGroundDetails() {
		
		/* using Hahmap tech*/
		//return new GroundInformationForUser(new ArrayList<GroundDetails>(map.values()),"7.00-Am","10.00-Pm",map.size());
		List<DetailsGround> findAll = detailsRepositary.findAll();
//		findAll.stream().sorted().
		
		return findAll;
		
	}

	@Override
	public DetailsGround getByName(String groundName) {
		Optional<DetailsGround> optional = detailsRepositary.findById(groundName);
		if (optional.isPresent()) {
			DetailsGround detailsGround=optional.get();
			return detailsGround;
		}
		throw new InvalidException("ENTER INCORRECT GROUND NAME");
	}






//	@Override
//	public void registerGround() {
//		//GroundInformationForUser groundInformationForUser = new GroundInformationForUser(new ArrayList<GroundDetails>(map.values()),"7.00-Am","10.00-Pm",map.size());
//		//groundRepositary.saveAndFlush(groundInformationForUser);
//		GroundDetails details= new GroundDetails();
//		details.setGroundName("Cricket");
//		details.setCapacity(5);
//		details.setPriceperhour(200.0);
//		detailsRepositary.save(details);
//		
//	}

}
