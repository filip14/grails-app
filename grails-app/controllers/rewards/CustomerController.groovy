package rewards

class CustomerController {
	static scaffold = true
	
	def calculationService
    
	def lookup(){
		def customerInstance = Customer.findAllByFirstNameLikeAndTotalPointsGreaterThanEquals("B%",3)
		[customerInstanceList: customerInstance]
	}
	
	def index() {
		params.max=10
		def allCustomers = Customer.list(params)
		[customerInstanceList: allCustomers, customerInstanceCount: Customer.count()]
	}
	
	def create(){
		//initiate new instance of the Customer domain and passing it into view
		[customerInstance: new Customer()]
	}
	
	def save(Customer customerInstance){
		customerInstance.save()
		redirect(action: "show", id: customerInstance.id)
	}
	
	def show(Long id){
		def customerInstance = Customer.get(id)
		//sending the instance to the service - pass the object tot he service for the modification
		customerInstance = calculationService.getTotalPoints(customerInstance)
		[customerInstance: customerInstance]
	}
	
	
	def edit(Long id){
		def customerInstance = Customer.get(id)
		[customerInstance: customerInstance]
	}
	
	
	/*def update(Long id){
		def customerInstance = Customer.get(id)
		// get the properties (existing values of the fields pulled from the DB) 
		//and then get whats comming from the view PARAMS - contain changes in the field
		customerInstance.properties = params
		customerInstance.save()
		redirect(action: "show", id: customerInstance.id)
	}
	*/
	def delete(Long id){
		def customerInstance = Customer.get(id)
		customerInstance.delete()
		redirect(action: "index")
	}
	//lookupInstance - contains all elements passed from the form - for us it represents instance from Customer domain
	def customerLookup(Customer lookupInstance){
		def (customerInstance, welcomeMessage)= calculationService.processCheckin(lookupInstance)
		render(view: "checkin", model: [customerInstance: customerInstance, welcomeMessage: welcomeMessage])
	}
	
	def profile(){
		def customerInstance = Customer.findByPhone(params.id)
		[customerInstance: customerInstance]
	}
	
	def updateProfile(Customer customerInstance){
		customerInstance.save()
		render(view: "profile", model:[customerInstance: customerInstance])
	}
	
	def checkin(){}
	
}

