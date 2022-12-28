package com.example.bookingsystem.serviceImp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bookingsystem.dto.BookingDto;
import com.example.bookingsystem.dto.DefaultUserDto;
import com.example.bookingsystem.dto.SportFieldDeleteDto;
import com.example.bookingsystem.dto.SportFieldUpdateDto;
import com.example.bookingsystem.dto.UserDeleteDto;
import com.example.bookingsystem.dto.UserUpdateDto;
import com.example.bookingsystem.entity.AvailabiltyCheckingDate;
import com.example.bookingsystem.entity.BookStatus;
import com.example.bookingsystem.entity.Booking;
import com.example.bookingsystem.entity.ConfirmationToken;
import com.example.bookingsystem.entity.DefaultUser;
import com.example.bookingsystem.entity.SportField;
import com.example.bookingsystem.exception.InvalidException;
import com.example.bookingsystem.groundinfromation.DetailsGround;
import com.example.bookingsystem.respositary.AvailabityRepositary;
import com.example.bookingsystem.respositary.BookStatusRespositary;
import com.example.bookingsystem.respositary.BookingRespositary;
import com.example.bookingsystem.respositary.ConfirmationTokenRepository;
import com.example.bookingsystem.respositary.DefaultUserRespositary;
import com.example.bookingsystem.respositary.DetailsRepositary;
import com.example.bookingsystem.respositary.SportFieldRespositary;
import com.example.bookingsystem.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

	int diffHour;
	double totalPricePerHour;
	int startHour;
	int endHour;
	double totalAmount;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private BookingRespositary bookingRespositary;

	@Autowired
	private DefaultUserRespositary defaultUserRespositary;

	@Autowired
	private SportFieldRespositary fieldRespositary;

	@Autowired
	private BookStatusRespositary bookStatusRespositary;

	@Autowired
	private DefaultUser defaultUser;

	@Autowired
	private Booking booking;

	@Autowired
	private SportField field;

	@Autowired
	private BookStatus bookStatus;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private DetailsRepositary detailsRepositary;

	@Autowired
	private AvailabityRepositary availabityRepositary;

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImp(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}

	UUID uuid = UUID.randomUUID();
	String uuidAsString = uuid.toString();

//	@Override
//	public DefaultUser register(DefaultUserDto defaultUserDto) {
//		BeanUtils.copyProperties(defaultUserDto, defaultUser);
//		defaultUser.setPassword(passwordEncoder.encode(uuidAsString)); 
//
//		return defaultUserRespositary.save(defaultUser);
//	}

	@Override
	public DefaultUser register(DefaultUserDto defaultUserDto) {
		BeanUtils.copyProperties(defaultUserDto, defaultUser);

		DefaultUser existingUser = defaultUserRespositary.findByEmail(defaultUser.getEmail());
		if (existingUser == null) {
			defaultUser.setPassword(uuidAsString);
			defaultUserRespositary.save(defaultUser);
			ConfirmationToken confirmationToken = new ConfirmationToken(defaultUser);
			// confirmationTokenRepository.save(confirmationToken);
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(defaultUser.getEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setText("To confirm your account, please click here : "
					+ "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
			sendEmail(mailMessage);
			return defaultUser;
		} else {
			throw new InvalidException("This email already exists!");
		}

	}

	@Override
	public boolean getAuthicationToken(String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		if (token != null) {
			DefaultUser user = defaultUserRespositary.findByEmail(token.getUserEntity().getEmail());
			user.setActive(1);
			defaultUserRespositary.save(user);
			return true;
		} else {
			throw new InvalidException("The link is invalid or broken!");
		}

	}

	@Override
	public Booking sportFieldRegister(Integer userId, BookingDto bookingDto) {

		/*
		 * Stream<LocalDate> mapStartDate = listBooking.stream().filter(a ->
		 * a.getStartDate()== bookingDto.getStartDate()).map(m -> m.getStartDate());
		 * Stream<LocalDate> mapEndDate = listBooking.stream().filter(a ->
		 * a.getEndDATE()== bookingDto.getEndDATE()).map(m ->m.getEndDATE());
		 * Stream<LocalTime> mapStartHour =
		 * listSport.stream().filter(a->a.getStartHour() ==
		 * bookingDto.getStartHour()).map(m->m.getStartHour()); Stream<LocalTime>
		 * mapEndHour = listSport.stream().filter(a->a.getEndingHour() ==
		 * bookingDto.getEndingHour()).map(m->m.getEndingHour());
		 */


		Optional<DefaultUser> optionalUser = defaultUserRespositary.findById(userId);
		List<Booking> listBooking = bookingRespositary.findAll();
		List<SportField> listSport = fieldRespositary.findAll();

		LocalDate dateFrom = bookingDto.getStartDate();
		LocalDate dateTo = bookingDto.getEndDATE();
		Long intervalDays = ChronoUnit.DAYS.between(dateFrom, dateTo);

		List<AvailabiltyCheckingDate> findAll = availabityRepositary.findAll();

		for (long i = 0; i <= intervalDays; i++) {
			for (AvailabiltyCheckingDate availabiltyCheckingDate : findAll) {
				if (dateFrom.plusDays(i) != availabiltyCheckingDate.getBookedDate()) {
					if ((bookingDto.getStartHour() != availabiltyCheckingDate.getStartHour())
							&& (bookingDto.getEndingHour() != availabiltyCheckingDate.getEndingHour())) {
					} else {
						throw new InvalidException("TIME SLOT IS ALREADY BOOKED");
					}

				} else {
					throw new InvalidException("Day IS ALREADY BOOKED");
				}

			}

		}

		if (optionalUser.isPresent()) {
			DefaultUser defaultUser = optionalUser.get();

			LocalTime time = bookingDto.getStartHour();
			startHour = time.getHour();
			LocalTime localTime = bookingDto.getEndingHour();
			endHour = localTime.getHour();

			DetailsGround byId = detailsRepositary.getById(bookingDto.getSportName());

			if (bookingDto.getSportName().equalsIgnoreCase(byId.getGroundName())) {
				diffHour = endHour - startHour;
				totalPricePerHour = diffHour * byId.getPriceperhour();
			}
			
			totalAmount=totalPricePerHour*intervalDays;

			BeanUtils.copyProperties(bookingDto, booking);
			BeanUtils.copyProperties(bookingDto, field);
			BeanUtils.copyProperties(bookingDto, bookStatus);

			/*
			 * if ((bookingDto.getSportName().equalsIgnoreCase("cricket")) ||
			 * (bookingDto.getSportName().equalsIgnoreCase("football"))) {
			 * 
			 * diffHour = endHour - startHour; totalPricePerHour = diffHour * 250;
			 * 
			 * } else if ((bookingDto.getSportName().equalsIgnoreCase("volleyball") ||
			 * (bookingDto.getSportName().equalsIgnoreCase("kabaddi")) ||
			 * (bookingDto.getSportName().equalsIgnoreCase("badmitan")) ||
			 * (bookingDto.getSportName().equalsIgnoreCase("basketball")) ||
			 * (bookingDto.getSportName().equalsIgnoreCase("tennis")))) {
			 * 
			 * diffHour = endHour -startHour; totalPricePerHour = diffHour * 200; } else {
			 * throw new InvalidException("Invalid data"); }
			 */

			/* LocalDate plusDays = dateFrom.plusDays(intervalDays); */

			/*
			 * LocalDate dateFrom=bookingDto.getStartDate(); LocalDate
			 * dateTo=bookingDto.getEndDATE(); Long
			 * intervalDays=ChronoUnit.DAYS.between(dateFrom, dateTo);
			 * totalAmount=totalPricePerHour*intervalDays;
			 */

			/* LocalDate plusDays = dateFrom.plusDays(intervalDays); */

			
			for (long i = 0; i <= intervalDays; i++) {
				AvailabiltyCheckingDate checkingDate = new AvailabiltyCheckingDate();
				checkingDate.setBookedDate(dateFrom.plusDays(i));
				checkingDate.setStartHour(bookingDto.getStartHour());
				checkingDate.setEndingHour(bookingDto.getEndingHour());
				availabityRepositary.save(checkingDate);
			}

			field.setPricePerHour(totalPricePerHour);
			booking.setTotalAmount(totalAmount);

			booking.getDefaultUser().add(defaultUser);
			defaultUser.getBooking().add(booking);
			field.setDefaultUser(defaultUser);
			bookStatus.setDefaultUser(defaultUser);
			booking.setBookStatus(bookStatus);
			booking.setField(field);
			bookingRespositary.save(booking);

			return booking;
		} else {
			throw new InvalidException("something went wrong");
		}

	}

	@Override
	public DefaultUser updateUser(UserUpdateDto dto) {
		Optional<DefaultUser> findById = defaultUserRespositary.findById(dto.getUserId());
		if (findById.isPresent()) {
			DefaultUser user = findById.get();
			BeanUtils.copyProperties(dto, user);
			return defaultUserRespositary.save(user);
		} else {
			throw new InvalidException("The userId is Not Available");
		}
	}

	@Override
	public boolean deleteUser(UserDeleteDto deleteDto) {
		Optional<DefaultUser> findById = defaultUserRespositary.findById(deleteDto.getUserId());
		if (findById.isPresent()) {
			DefaultUser user = findById.get();
			defaultUserRespositary.delete(user);
			return true;
		}
		throw new InvalidException("The userId is Not Available");
	}

	@Override
	public Booking updateSportField(SportFieldUpdateDto fieldUpdateDto) {

		Optional<SportField> findById = fieldRespositary.findById(fieldUpdateDto.getSportfieldId());
		Optional<Booking> byId = bookingRespositary.findById(fieldUpdateDto.getSportfieldId());
		if (findById.isPresent() && byId.isPresent()) {
			SportField field = findById.get();
			Booking booking = byId.get();
			BeanUtils.copyProperties(fieldUpdateDto, booking);
			BeanUtils.copyProperties(fieldUpdateDto, field);
			booking.setField(field);
			return bookingRespositary.save(booking);
		} else {
			throw new InvalidException("The SportFieldId is Not Available");
		}
	}

	@Override
	public boolean deleteUser(SportFieldDeleteDto deleteDto) {
		Optional<SportField> findById = fieldRespositary.findById(deleteDto.getSportfieldId());
		Optional<Booking> byId = bookingRespositary.findById(deleteDto.getSportfieldId());
		if (findById.isPresent() && byId.isPresent()) {
			SportField field = findById.get();
			Booking booking = byId.get();
			bookingRespositary.delete(booking);
			return true;
		} else {
			throw new InvalidException("The sportfieldId is Not Available");
		}
	}

//	@Override
//	public String sendMail(DefaultUser user) {
//		try {
//			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
//			messageHelper.setTo(user.getEmail());
//			messageHelper.setSubject("Activation code is highly secured donot share anyone");
//			messageHelper.setText("Activation code is = "+user.getActivationCode());
//			javaMailSender.send(mimeMessage);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
//		
//		return "ENTER EMAIL IS INCORRECT";
//	}

	@Async
	public void sendEmail(SimpleMailMessage email) {
		javaMailSender.send(email);
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	
//		 Optional<DefaultUser> user = defaultUserRespositary.findByUserName(username);
//		 if(user.isPresent())
//		 {
//			 DefaultUser defaultUser = user.get();
//			 return new User(username,defaultUser.getPassword(),
//					 defaultUser.getRoles()
//					 .stream()
//					 .map(role -> new SimpleGrantedAuthority(role))
//					 .collect(Collectors.toList()));
//		 }else {
//			 throw new InvalidException("user not exist");
//		 }
//	}

}
