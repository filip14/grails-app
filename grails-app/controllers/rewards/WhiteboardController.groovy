package rewards

class WhiteboardController {

	def calculationService
	
    def index() { }
	
	def variables(){
		def myTotal=5
		render ("this is my total:" + myTotal)
		render("<br/>Total: " + myTotal.class)
		myTotal=myTotal + 1
		render("<br/>New Total: " + myTotal)
		
		def firstName="Mike"
		render("<br/>Name: " + firstName)
		render("<br/>Name: " + firstName.class)
		firstName=firstName + 1
		render("<br/>New Name: " + firstName)
	}
	
	def strings(){
		def first = "Filip"
		def last = "Popovic"
		def points = 4		
		render "Welcome back $first. You have $points points<br/><br/>"
		render "Today is ${new Date()}."
	}
	
	def conditions(){
		def welcomeMessage= calculationService.welcome(params)
		render welcomeMessage
	}
}
