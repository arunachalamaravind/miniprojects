package com.example.bookingsystem.serviceImp;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookingsystem.dto.AdminfetchUserDto;
import com.example.bookingsystem.dto.DefaultUserDto;
import com.example.bookingsystem.dto.DetailsGroundDto;
import com.example.bookingsystem.dto.GroundDeleteDto;
import com.example.bookingsystem.dto.SetStatusByAdminDto;
import com.example.bookingsystem.dto.ShowStatusRequestDto;
import com.example.bookingsystem.entity.BookStatus;
import com.example.bookingsystem.entity.Booking;
import com.example.bookingsystem.entity.DefaultUser;
import com.example.bookingsystem.entity.SportField;
import com.example.bookingsystem.exception.InvalidException;
import com.example.bookingsystem.groundinfromation.DetailsGround;
import com.example.bookingsystem.groundinfromation.GroundDetails;
import com.example.bookingsystem.respositary.BookStatusRespositary;
import com.example.bookingsystem.respositary.BookingRespositary;
import com.example.bookingsystem.respositary.DefaultUserRespositary;
import com.example.bookingsystem.respositary.DetailsRepositary;
import com.example.bookingsystem.respositary.SportFieldRespositary;
import com.example.bookingsystem.service.AdminService;

@Service
public class AdminServiceImp implements AdminService {

	@Autowired
	private BookStatusRespositary bookStatusRespositary;
	@Autowired
	private BookingRespositary bookingRespositary;
	@Autowired
	private SportFieldRespositary fieldRespositary;
	@Autowired
	private DefaultUserRespositary defaultUserRespositary;

	private Map<Integer, ShowStatusRequestDto> map;
	
	@Autowired
	private DetailsRepositary detailsRepositary;
	
	@Autowired
	private DetailsGround ground;

	@Override
	public Collection<ShowStatusRequestDto> showStatusRequest() {

		List<BookStatus> findAll = bookStatusRespositary.findAll();
		Map<Integer, ShowStatusRequestDto> map = new HashMap<Integer, ShowStatusRequestDto>();
		for (BookStatus bookStatus : findAll) {
			Booking booking = bookingRespositary.findById(bookStatus.getBookstatusId()).orElse(null);
			SportField field = fieldRespositary.findById(bookStatus.getBookstatusId()).orElse(null);
			DefaultUser defaultUser = bookStatus.getDefaultUser();
			map.put(bookStatus.getBookstatusId(),
					new ShowStatusRequestDto(field.getSportName(), field.getStartHour(), field.getEndingHour(),
							booking.getBookingId(), booking.getStartDate(), booking.getEndDATE(),
							bookStatus.getBookstatusId(), defaultUser.getUserId()));
		}
		return map.values();
	}

	@Override
	public BookStatus setStatusbyAdmin(SetStatusByAdminDto adminDto) {

		Optional<BookStatus> findById = bookStatusRespositary.findById(adminDto.getBookstatusId());
		if (findById.isPresent()) {
			BookStatus bookStatus = findById.get();
			bookStatus.setName(adminDto.getName());
			return bookStatusRespositary.save(bookStatus);
		} else {
			throw new InvalidException("Invalid statusId");
		}
	}

	@Override
	public List<AdminfetchUserDto> getUserDetails() {
		List<DefaultUser> findAll = defaultUserRespositary.findAll();
		List<AdminfetchUserDto> list = new ArrayList<>();
		if (findAll != null) {
		for (DefaultUser defaultUser : findAll) {
			list.add(new AdminfetchUserDto(defaultUser.getEmail(),
						defaultUser.getCreatedOn(), defaultUser.getUserName(),
						defaultUser.getPhoneNumber(), defaultUser.getGender(), defaultUser.getUserId()));
		}
		return list;
		}else {
			throw new InvalidException("something went wrong");
		}
	}

	@Override
	public AdminfetchUserDto getUserID(Integer userId) {
	
		Optional<DefaultUser> id = defaultUserRespositary.findById(userId);
		if (id.isPresent()) {
			DefaultUser defaultUser=id.get();
			AdminfetchUserDto adminfetchUserDto=new AdminfetchUserDto();
			BeanUtils.copyProperties(defaultUser, adminfetchUserDto);	
			return adminfetchUserDto;
		}else {
			throw new InvalidException("something went wrong");
		}
	}

	@Override
	public DetailsGround createGround(DetailsGroundDto detailsGround) {
		BeanUtils.copyProperties(detailsGround, ground);
		ground.setInput(Duration.between(ground.getStartHour(), ground.getEndingHour()));
		
		return detailsRepositary.save(ground); 
	}

	@Override
	public DetailsGround updateGround(DetailsGroundDto detailsGround) {
	Optional<DetailsGround> optional = detailsRepositary.findById(detailsGround.getGroundName());
	if (optional.isPresent()) {
		DetailsGround inputGound =optional.get();
		BeanUtils.copyProperties(detailsGround, inputGound);
		return detailsRepositary.save(inputGound);
	}else {
		throw new InvalidException("The Field Is Not Available");
	}
	}

	@Override
	public boolean deleteGround(GroundDeleteDto deleteDto) {
		Optional<DetailsGround> optional = detailsRepositary.findById(deleteDto.getGroundName());
		if (optional.isPresent()) {
			DetailsGround detailsGround=optional.get();
			 detailsRepositary.delete(detailsGround);
			 return true;
			 
		}else {
			throw new InvalidException("The Ground Name is Invalid");
		}
	}

}
