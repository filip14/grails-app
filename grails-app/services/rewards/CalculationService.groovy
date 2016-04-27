package rewards

import grails.transaction.Transactional

@Transactional
class CalculationService {

    def welcome(params) {
		def firstName =  params.first
		def totalPoints = params.points.toInteger()
		def welcomeMessage=""
		switch(totalPoints){
			case 5:
				welcomeMessage ="Welcome back $firstName. This drink is on us."
				break
			case 4:
				welcomeMessage ="Welcome back $firstName. Your next drink is free."
				break
			case 2..3:
				welcomeMessage ="Welcome back $firstName. You have $totalPoints ."
				break
			default:
				welcomeMessage ="Welcome back $firstName. Thank you for registering."
				break
		}

    }
	
	def getTotalPoints(customerInstance){
		def totalAwards = 0
		// taking one customer, looping through each award (Customer domain hasmany awards
		// so call it by that name and if you want to run through each you just say .each
		customerInstance.awards.each{
			// it.points - get the point value for the single row of award
			totalAwards= totalAwards + it.points
		}
		customerInstance.totalPoints = totalAwards
		return customerInstance
	}
	
	def processCheckin (lookupInstance){
		// Lookup customer by phone number
		def customerInstance = Customer.findByPhone(lookupInstance.phone)
		//setup new customer
		if(customerInstance==null){
			customerInstance = lookupInstance
			customerInstance.firstName = "Customer"
		}
		
		//Calculate their awards
		def totalAwards = 0
		customerInstance.awards.each{
			totalAwards= totalAwards + it.points
		}
		customerInstance.totalPoints = totalAwards

		
		
		// Add new award
		if(totalAwards < 5){
			customerInstance.addToAwards(new Award(awardDate: new Date(), type: "Purchase", points:1))
		}
		else{
			customerInstance.addToAwards(new Award(awardDate: new Date(), type: "Reward", points:-5))
		}
		
		
		//create welcome message
		def welcomeMessage = ""
		
		switch(totalAwards){
			case 5:
				welcomeMessage ="Welcome back $customerInstance.firstName. This drink is on us."
				break
			case 4:
				welcomeMessage ="Welcome back $customerInstance.firstName. Your next drink is free."
				break
			case 1..3:
				welcomeMessage ="Welcome back $customerInstance.firstName. You have $totalAwards points."
				break
			default:
				welcomeMessage ="Welcome back $customerInstance.firstName. Thank you for registering."
				break
		}
		
		
		// save customer
		customerInstance.save()
		
		return [customerInstance, welcomeMessage]
	}
}
