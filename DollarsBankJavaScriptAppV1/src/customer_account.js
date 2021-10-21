
function Account(id, name, pin, type, balance) { 
    this.id = id;
    this.name = name;
    this.pin = pin;
    this.type = type;
    this.balance = balance;
}
Account.prototype.deposit = function deposit(amount) {
    this.balance = this.balance + amount;
};
Account.prototype.withdraw = function withdraw(amount) { 
    this.balance = this.balance - amount;
};
Account.prototype.updatePin = function updatePin(updatedPin) {
    this.pin = updatedPin;
}

module.exports = Account;