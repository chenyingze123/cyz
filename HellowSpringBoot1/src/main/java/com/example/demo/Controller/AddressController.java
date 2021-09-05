package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Address;
import com.example.demo.entity.Admin;
import com.example.demo.service.AddressService;


@RestController
@RequestMapping("address")
public class AddressController {
	@Autowired
    private AddressService addressService;
    @RequestMapping("/list")
    public List<Address> list() {
        return addressService.findAll();

  }
    @RequestMapping("/add")
	@ResponseBody
	public String Add(Address address) {
		
		String addressName = address.getAddressName();
		Address address1 = new Address();
		address1 = addressService.findByaddressName(addressName);
		if (address1 == null) {

			try {
				addressService.save(address);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		} else {
			return "error1";
		}

		return "success";
	}
    @GetMapping("/delete")
	public void delete(Long addressId) {
    	
		addressService.deleteById(addressId);

	}
    @RequestMapping("/edit")

	public String update(Address address) {

		String addressName = address.getAddressName();
		Address address1 = new Address();
		address1 = addressService.findByaddressName(addressName);
		if (address1 == null) {

			try {
				addressService.save(address);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		} else {
			return "error1";
		}

		return "success";
		
    
    }
    @RequestMapping("/edits")

   	public String updates(Address address) {
    	
    	
    	
   			try {
   				addressService.save(address);
   			} catch (Exception e) {
   				e.printStackTrace();
   				return "error";
   			}
   		
   		return "success";
   		
       
       }
    @RequestMapping("/editfind")

   	public String editfind(Address address) {
    	Long s1 = address.getAddressId();
		// Long s2 = address1.getAddressId();
    	//String n1 = address.getAddressName();
		Address address1 = new Address();
		address1 = addressService.findByaddressId(s1);
		String n1 = address.getAddressName();
		String n2 = address1.getAddressName();
	   
			/* Long s1 = address.getAddressId();
			 Long s2 = address1.getAddressId();*/	 
			if( n1.equals(n2)) {
				
				return "success";		
				
			}else {
				
				return "error";
			}
					
       
       }
    
    @RequestMapping("/edittest")

   	public String edittest(Address address) {
    	
    	String n1 = address.getAddressName();
		Address address1 = new Address();
		
		address1 = addressService.findByaddressName(n1);
	     
		if (address1 == null) {

   			try {
   				addressService.save(address);
   			} catch (Exception e) {
   				e.printStackTrace();
   				return "error";
   			}
		}
		else {
			 Long s1 = address.getAddressId();
			    Long s2 = address1.getAddressId();	 
			if( s1.longValue() == s2.longValue()) {
				
				return "success";	
				
				
			}else {
				
				return "error1";
			}
			
			
			
			
		}
		return "success";
   		
       
       }
  
}
