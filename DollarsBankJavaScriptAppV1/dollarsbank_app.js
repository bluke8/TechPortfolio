var MyAccount = require("./src/customer_account");
const prompt = require("prompt-sync")({sigint:true});

var database = new Array();

var idCount = 3;
// CREATING AND PUSHING HARDCODED INITIAL ACCOUNTS
var a1 = new MyAccount(0,"Brandon", 1234, "CHECKING", 100);
var a2 = new MyAccount(1,"Brandon", 1234, "SAVINGS", 500);
var a3 = new MyAccount(2,"John", 4321, "CHECKING", 50);
var a4 = new MyAccount(3,"John", 4321, "SAVINGS", 300);
database.push(a1,a2,a3,a4);

//PRINTS OUT EACH ACCOUNT IN DB
//database.forEach((account, database) => console.log(account));

let selection = OpeningPrompt();

while(selection != '4'){
    switch(selection){
        case '1': 
            let newAccount = CreateAccount();
            database.push(newAccount);
            break;
        case '2':
            console.log("you selected 2");
            selection = TransactionPrompt();
            break;
        case '3':
            database.forEach((account,database) => console.log(account));
            break;
        default: 
            console.log("\n\t~~Invalid selection!~~ ->"+selection+"\n");
            selection = OpeningPrompt();
    }
    console.log("Please select an option: \nEnter 1: open new account"
    + "\nEnter 2: transaction"
    + "\nEnter 3: view accounts"
    + "\nEnter 4: QUIT");
    selection = prompt("Make another selection: ");
}
console.log("\n\t~Goodbye!~\n");



function OpeningPrompt() { 
    console.log("Please select an option: \nEnter 1: open new account"
    + "\nEnter 2: transaction"
    + "\nEnter 3: view accounts"
    + "\nEnter 4: QUIT");
    let selection = prompt("selection: ");
    return selection;
}
function TransactionPromptOptions() {
    console.log("\nEnter 1: Account balance check\n"
    + "Enter 2: Print transactions\n"
    + "Enter 3: Update PIN\n"
    + "Enter 4: Withdraw Amount\n"
    + "Enter 5: Deposit amount\n"
    + "Enter 6: Back to home\n"
    + "Make a selection:");
}

function TransactionPrompt() { 
    database.forEach((account,database) => console.log(account));
    console.log("Please select account to access: ");
    //prints accounts in DB
    
    // ~~ONLY ALLOW EXISTING ACCOUNT TO BE SELECTED. RIGHT NOW ANY ID CAN BE SELECTED,
    // IF IT DOESN'T EXIST, UNDEFINED ACCOUNT WILL BE SELECTED~~
    let accountSelection = prompt("selection (by id): ");
    var acc;
    for(var i=0; i<database.length; i++){
        if (database[i]['id'] == parseInt(accountSelection)){
            acc = database[i];
        }
    }
    console.log("\nAccount selected: ");
    console.log(acc);

    while(transactionSelection != 6){
        TransactionPromptOptions();
        var transactionSelection = prompt();
        switch(transactionSelection){
            case '1': 
                console.log("~~Current balance: "+acc.balance+ "~~");
                break;
            case '2': 
                console.log('~2 print transactions (Need to implement. (Create a new array to store transactions?)~');
                break;
            case '3': 
                let newPin = prompt("Enter new pin: ");
                database[acc.id].updatePin(parseInt(newPin));
                //verify in its own method
                console.log("\n\tNew pin for "+database[acc.id]['name']+": "+newPin);
                break;
            case '4': 
                let withdrawAmount = prompt("Enter amount to withdraw: ");
                while(withdrawAmount > database[acc.id]['balance']){
                    console.log("Cannot withdraw that much!\n");
                    withdrawAmount = prompt("Enter different amount to withdraw: ");
                }
                database[acc.id].withdraw(parseInt(withdrawAmount));
                console.log(database[acc.id].balance);
                break;
            case '5': 
                let depositAmount = prompt("Enter amount to deposit: ");
                database[acc.id].deposit(parseInt(depositAmount));
                console.log(+database[acc.id].balance);
                break;
            case '6':
                console.log('~~Redirecting to home~~');
                break;
            default:
                console.log("~~Invalid Selection~~");
                TransactionPromptOptions();
                break;
        }
    }
    
}



function CreateAccount(){
    let name = prompt("Enter a name: ");
    let pin = prompt("Enter pin (4 digits): ");
    if(pin.length > 4 || pin.length < 4){
        console.log("Invalid pin!");
        while(pin.length > 4 || pin.length < 4){
            pin = prompt("enter another pin (4 digits) :")
        }
    }
    let verifiedPin = prompt("Verify pin: ");
    if(verifiedPin != pin){
        while(verifiedPin != pin){
            console.log("Please try again");
            verifiedPin = prompt("Verify pin: ");
        }
    }
    let type = prompt("Enter type: ");
    let upperType = type.toUpperCase();
    let balance = prompt("Enter initial balance (>= $10): ");
    if(balance <10){
        while(balance<10){
            console.log("$" + balance + " is less than $10! \nEnter amount of at least $10: ");
            balance = prompt("Enter initial balance (>= $10): ");
        }
    }
    var newAccount = new MyAccount(++idCount,name,parseInt(pin),upperType,parseInt(balance));
    console.log(newAccount);
    return newAccount;
}
