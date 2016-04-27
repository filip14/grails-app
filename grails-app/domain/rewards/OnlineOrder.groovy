package rewards

class OnlineOrder {
	Date orderDate
	Integer orderNumber
	Float orderTotal
	static belongsTo = [customer: Customer]

    static constraints = {
    }
}
