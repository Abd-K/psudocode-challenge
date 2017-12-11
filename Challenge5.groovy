class TinyBankController {
	private static final String FROM_EMAIL = "no-reply@tinybank.se"

	// We use MailService.sendMail(String toAddress, String fromAddress, String title, String body)
	MailService mailService
	def groovySql
	//We use:
	//PersistanceService.saveTransaction(Transaction t)
	//PersistanceService.batchPersist(PersistableObject... objects)
	PersistanceService persistanceService

	Account loadAccount(string searchID) {
		final String query = '''\
            select id, balance, name, email
            from account
            where email like''' + searchID;
		final results = groovySql.rows(query);
		return new Account(results[0]);
	}

	String doPayment(Map[] params) {
		Account dr = loadAccount(params["dr"]);
		Account cr = loadAccount(params["cr"]);
		int amount = Int.parse(params["amt"]);

		if (cr.balance >= amount) {
			//Adjust all account balances
			dr.balance += amount;
			cr.balance -= amount;

			//Create a transaction object to be stored in the db, and save it
			Transaction tr = new Transaction();
			tr.credit = cr;
			tr.debit = dr;
			tr.amount = amount;

			persistanceService.saveTransaction(tr);

			//Use the batchPersist method to ensure all changes are saved in one
			//database transaction
			persistanceService.batchPersist(dr, cr, tr);

			sendDebitConfirmation(tr);
			sendCreditConfirmation(tr);
		}

		return "Transfer completed successfully";
	}


	private void sendCreditConfirmation(Transaction transaction) {
		mailService.sendMail(transaction.credit.email,
				FROM_EMAIL,
				"Credit transaction",
				transaction.value + " was transferred from your account to " + transaction.debit.name);
	}

	private void sendDebitConfirmation(Transaction transaction) {
		mailService.sendMail(transaction.debit.email,
				FROM_EMAIL,
				"Credit transaction",
				transaction.value + " was transferred to your account to " + transaction.credit.name);
	}
}