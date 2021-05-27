package org.camunda.bpm.getstarted.loanapproval.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.getstarted.loanapproval.entitys.Drivers;
import org.camunda.bpm.getstarted.loanapproval.entitys.Notifications;
import org.camunda.bpm.getstarted.loanapproval.entitys.RequestsPassengers;
import org.camunda.bpm.getstarted.loanapproval.entitys.VehiculeRequest;
import org.camunda.bpm.getstarted.loanapproval.entitys.Vehicules;
import org.camunda.bpm.getstarted.loanapproval.message.request.RefuseModel;
import org.camunda.bpm.getstarted.loanapproval.message.request.VehiculeRequestModel;
import org.camunda.bpm.getstarted.loanapproval.message.response.RequestVehiculeItem;
import org.camunda.bpm.getstarted.loanapproval.model.ResponseJson;
import org.camunda.bpm.getstarted.loanapproval.model.User;
import org.camunda.bpm.getstarted.loanapproval.repository.DriversRepository;
import org.camunda.bpm.getstarted.loanapproval.repository.NotificationsRepository;
import org.camunda.bpm.getstarted.loanapproval.repository.RequestsPassengersRepository;
import org.camunda.bpm.getstarted.loanapproval.repository.UserRepository;
import org.camunda.bpm.getstarted.loanapproval.repository.VehiculeRequestRepository;
import org.camunda.bpm.getstarted.loanapproval.repository.VehiculesRepository;
import org.camunda.bpm.getstarted.loanapproval.security.jwt.JwtProvider;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/requests")
public class VehiculeRequestController {


	@Autowired
	private RuntimeService runtimeService;

	  
	@Autowired
	VehiculeRequestRepository vehiculeRequestRepository;
	
	@Autowired
	VehiculesRepository vehiculesRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RequestsPassengersRepository requestsPassengersRepository;

	@Autowired
	NotificationsRepository notificationsRepository;
	@Autowired
	DriversRepository driversRepository;
	
	
	// delete approve
	@GetMapping("/delete/{id}")
	public void deleteRequest(@PathVariable(value ="id") Long id){
		
		VehiculeRequest tmp =  this.vehiculeRequestRepository.findById(id).get();
		this.vehiculeRequestRepository.delete(tmp);
		
	}
	@GetMapping("/clear")
	public void deleteRequest( HttpServletRequest req ){
		List<VehiculeRequest> tmp =  this.vehiculeRequestRepository.findAll();
		
        Optional<User> current;
        String token = req.getHeader("authorization").replace("Bearer " ,"");
        System.out.println(token);
        String username=this.jwtProvider.getUserNameFromJwtToken(token);
        current=this.userRepository.findByUsername(username);
        
        
		for(VehiculeRequest vrt:tmp) {
			if( vrt.getEmployee().getId() == current.get().getId() ) {
				if( vrt.getStatus() == 2 || vrt.getStatus() == 3 ) {
					this.vehiculeRequestRepository.delete(vrt);
				}
			}
		}
		
	}
	
	
	

	// admin approve
	@GetMapping("/approve/{id}")
	public VehiculeRequest approveRequest(@PathVariable(value ="id") Long id){
		VehiculeRequest tmp =  this.vehiculeRequestRepository.findById(id).get();
		tmp.setStatus(1);
		
		// create notifications
		Notifications n = new Notifications();
		
		n.setTitle("Vehicule request");
		n.setMessage("Your véhicule request is in treatment and approved by the director, and wiating for park manager approval");
		long millis=System.currentTimeMillis();  
		n.setAdddate(   new Date(millis)  );
		n.setSeen(false);
		n.setUser(tmp.getEmployee());
		this.notificationsRepository.save(n);
		
		// create notifications for the parc manager
		Notifications parcNotif = new Notifications();
		
		parcNotif.setTitle("Vehicule request");
		parcNotif.setMessage("The director has accepted a new véhicule request and the requester is waiting for your approval.");
		 
		parcNotif.setAdddate(   new Date(millis)  );
		parcNotif.setSeen(false);
		parcNotif.setUser( this.userRepository.findByUsername("park_manager").get() ) ;
		this.notificationsRepository.save(parcNotif);
		
		
		
		return this.vehiculeRequestRepository.save(tmp);
	}
	
	@GetMapping("/approve-parc/{id}/{idD}")
	public VehiculeRequest approveParcRequest(@PathVariable(value ="id") Long id,@PathVariable(value ="idD") Long idD){
		VehiculeRequest tmp =  this.vehiculeRequestRepository.findById(id).get();
		Drivers driver =  this.driversRepository.findById(idD).get();
		
		tmp.setDriver(driver);
		


		
		
		tmp.setStatus(2);
		Vehicules v = tmp.getVehicule();
		
		//v.setIsOut(true);
		
		// create notifications
		Notifications n = new Notifications();
		
		n.setTitle("Vehicule request");
		n.setMessage("Your véhicule request is approved by the parc manager, you can have access to your vehicule.");
		long millis=System.currentTimeMillis();  
		n.setAdddate(   new Date(millis)  );
		n.setSeen(false);
		n.setUser(tmp.getEmployee());
		this.notificationsRepository.save(n);
		return this.vehiculeRequestRepository.save(tmp);
	}
	
	
	@PostMapping("/refuse")
	public VehiculeRequest refuseRequest(@RequestBody RefuseModel model){
		VehiculeRequest tmp =  this.vehiculeRequestRepository.findById(model.getId()).get();
		tmp.setStatus(3);
		tmp.setCancelReason(model.getReason());
		
		
		// create notifications
		Notifications n = new Notifications();
		
		n.setTitle("Vehicule request");
		n.setMessage("Your véhicule request is refused by the director.");
		long millis=System.currentTimeMillis();  
		n.setAdddate(   new Date(millis)  );
		n.setSeen(false);
		n.setUser(tmp.getEmployee());
		this.notificationsRepository.save(n);
		return this.vehiculeRequestRepository.save(tmp);
	}
	
	@PostMapping("/refuse-parc")
	public VehiculeRequest refuseParcRequest(@RequestBody RefuseModel model){
		VehiculeRequest tmp =  this.vehiculeRequestRepository.findById(model.getId()).get();
		tmp.setStatus(3);
		tmp.setCancelReason(model.getReason());
		
		
		// create notifications
		Notifications n = new Notifications();
		
		n.setTitle("Vehicule request");
		n.setMessage("Your véhicule request is refused by the parc manager.");
		long millis=System.currentTimeMillis();  
		n.setAdddate(   new Date(millis)  );
		n.setSeen(false);
		n.setUser(tmp.getEmployee());
		this.notificationsRepository.save(n);
		return this.vehiculeRequestRepository.save(tmp);
	}

	
	@GetMapping("/list")
	public List<RequestVehiculeItem> getAllRequests(){
		List<VehiculeRequest> tmp =  this.vehiculeRequestRepository.findAll();
		
        
        
        
        
        List<RequestVehiculeItem> userRequests = new ArrayList<RequestVehiculeItem>();
        
        
        List<RequestsPassengers> allPassengers = this.requestsPassengersRepository.findAll();
        
        
        
        
        for(VehiculeRequest vr:tmp) {
        	RequestVehiculeItem item = new RequestVehiculeItem();
        	List<User> passengers = new ArrayList<User>(); 
        	
        	
        	
        		item.setRequest(vr);
        		
        		
        		// now we search for passengers
        		for(RequestsPassengers rp:allPassengers) {
        			if(rp.getRequest().getId() == vr.getId()) {
        				passengers.add(rp.getPassenger());
        			}
        		}
        		item.setPassengers(passengers);
        		
        	
        	
        	userRequests.add(item);
        }
        
        return userRequests;
	}
	

    @Autowired
    JwtProvider jwtProvider;
	
	@GetMapping("/listauthenticated")
	public List<RequestVehiculeItem> getAllRequestsAutenticated( HttpServletRequest req ){
		List<VehiculeRequest> tmp =  this.vehiculeRequestRepository.findAll();
		
        Optional<User> current;
        String token = req.getHeader("authorization").replace("Bearer " ,"");
        System.out.println(token);
        String username=this.jwtProvider.getUserNameFromJwtToken(token);
        current=this.userRepository.findByUsername(username);
        
        
        
        List<RequestVehiculeItem> userRequests = new ArrayList<RequestVehiculeItem>();
        
        
        List<RequestsPassengers> allPassengers = this.requestsPassengersRepository.findAll();
        
        
        
        
        for(VehiculeRequest vr:tmp) {
        	RequestVehiculeItem item = new RequestVehiculeItem();
        	List<User> passengers = new ArrayList<User>(); 
        	
        	
        	if( vr.getEmployee().getId() == current.get().getId()) {
        		item.setRequest(vr);
        		
        		
        		// now we search for passengers
        		for(RequestsPassengers rp:allPassengers) {
        			if(rp.getRequest().getId() == vr.getId()) {
        				passengers.add(rp.getPassenger());
        			}
        		}
        		item.setPassengers(passengers);
        		
        	}
        	
        	if(item.getRequest() != null ) {
        		userRequests.add(item);
        	}
        }
        
        return userRequests;
	}
	
	


	  
	 
	 @PostMapping("/add")

	 public ResponseJson addNewRequest(@RequestBody VehiculeRequestModel model ){
		 
		 VehiculeRequest v = new VehiculeRequest();
		 ResponseJson res = new ResponseJson();
		 
		 v.setArrivalDate(model.getArrival_date());
		 v.setArrivalLocation(model.getArrival_location());
		 v.setArrivalTime(model.getArrival_time());
		 v.setStartDate(model.getStart_date());
		 v.setStartLocation(model.getStart_location());
		 v.setStartTime(model.getStart_time());
		 
		 // if admin then directly to chef parc
		 if(this.userRepository.findById(model.getEmployee_id()).get().getUsername().equals("super_admin")  ) {
			 v.setStatus(1);
		 }else {
			 v.setStatus(0);
		 }
		 v.setReason(model.getResaon());
		 v.setCargo(model.getCargo());
		 v.setEmployee(this.userRepository.findById(model.getEmployee_id()).get());
		 
		 //v.setVehicule(this.vehiculesRepository.findById(model.getVehicule_id()).get());
		 
		 // now we have to look for an avaivle vehicule
		 
		 Vehicules selectedVehicule = new Vehicules();
		 
		 List<Vehicules> allVehicules = this.vehiculesRepository.findAll();
		 List<VehiculeRequest> allVehiculesRequests = this.vehiculeRequestRepository.findAll();
		 
		 
		 boolean canHaveAVehicule = true;
		 
		 for(Vehicules tmp:allVehicules  ) {
			 if( tmp.getIsOut() == false /* is available */ ) {
				 // check for reservations date
				 System.out.println("we found a vehicule");
				 LocalDateTime startAtrequest = LocalDateTime.parse( v.getStartDate()+"T"+v.getStartTime() );
				 LocalDateTime endsAtRequest = LocalDateTime.parse( v.getArrivalDate()+"T"+v.getArrivalTime() );
				 
				 
				 

				 if( allVehiculesRequests.isEmpty() ) {
					 selectedVehicule = tmp;
					 
				 }else {
					 
					 boolean isReserved = false;
					for(VehiculeRequest vr: allVehiculesRequests  ) {
					 
					 if( vr.getVehicule().getId() == tmp.getId() ) {
						 
						 // start date
						 LocalDateTime oldStartAtrequest = LocalDateTime.parse( vr.getStartDate()+"T"+vr.getStartTime() );
						 LocalDateTime oldEndsAtRequest = LocalDateTime.parse( vr.getArrivalDate()+"T"+vr.getArrivalTime() );
						 
						 
						 
						 long requestStartTime =startAtrequest.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
						 long requestEndTime =endsAtRequest.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
						 long oldRequestStartTime =oldStartAtrequest.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
						 long oldRequestEndTime =oldEndsAtRequest.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
						 
						 
						// case one
						 if( (requestStartTime <= oldRequestStartTime ) && ( requestEndTime >= requestStartTime ) && ( requestEndTime <=  oldRequestEndTime ) ) {
							 if( vr.getStatus() == 2 ) {
								 isReserved = true;
							 }
						 }
						 
						// case two
						 if( (requestStartTime == oldRequestStartTime ) && ( requestEndTime ==  oldRequestEndTime ) ) {
							 if( vr.getStatus() == 2 ) {
								 isReserved = true;
							 }
						 }
						 
						// case three
						 if( (requestStartTime >= oldRequestStartTime ) && (requestStartTime <= oldRequestEndTime ) && ( requestEndTime >=  oldRequestEndTime ) ) {
							 if( vr.getStatus() == 2 ) {
								 isReserved = true;
							 }
						 }
						 
						 
						 
						 
					 }
					} 
					
					if(isReserved == false) {
						selectedVehicule = tmp;
					}
				 }
				 

			 }
		 }
		 
		 System.out.println(selectedVehicule.getId());
		 
		 if( selectedVehicule.getId() != null ) {
			 v.setVehicule(selectedVehicule);
			 this.vehiculeRequestRepository.save(v);
			 
			 ProcessInstance  process = runtimeService.startProcessInstanceByKey("vehicule_request");
			 runtimeService.setVariable(process.getId(), "status", 1);
			 
			 
			 
			 // update passengers
			 List<Long> ids = model.getPassengers();
			 
			 for(Long id:ids) {
				 RequestsPassengers  rp = new RequestsPassengers();
				 
				 rp.setPassenger(this.userRepository.findById(id).get());
				 rp.setRequest(v);
				 
				 this.requestsPassengersRepository.save(rp);
			 }

			 
			 Notifications n = new Notifications();
				
				n.setTitle("Vehicule request");
				String fullnameEmployee = this.userRepository.findById(model.getEmployee_id()).get().getName();
				n.setMessage("You have a new vehicule request from "+fullnameEmployee+".");
				long millis=System.currentTimeMillis();  
				n.setAdddate(   new Date(millis)  );
				n.setSeen(false);
				
				
				// if admin then directly to chef parc
				 if(this.userRepository.findById(model.getEmployee_id()).get().getUsername().equals("super_admin")  ) {
					 // to the parc manager
					// create notifications for the parc manager
						Notifications parcNotif = new Notifications();
						
						parcNotif.setTitle("Vehicule request");
						parcNotif.setMessage("The director has accepted a new véhicule request and the requester is waiting for your approval.");
						 
						parcNotif.setAdddate(   new Date(millis)  );
						parcNotif.setSeen(false);
						parcNotif.setUser( this.userRepository.findByUsername("park_manager").get() ) ;
						this.notificationsRepository.save(parcNotif);
					 
						
				 }else {
					 n.setUser(this.userRepository.findByUsername("super_admin").get());
					 this.notificationsRepository.save(n);
				 }
				 
				
				res.setSuccess(true);
				res.setMessage("Your request is successfully added.");
				
			 return res;
			 
			 
			 
		 }else {
				res.setSuccess(false);
				res.setMessage("We have no véhicules availabale at the chosen dates.");
			 return res;
		 }
		 
		 

		 
		 
	 }
	 
}
