
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.Test;

public class ATMTest {

	// mocking bank
	Bank bank = mock(Bank.class);

	// creating 2 cards
	Card card1 = new Card("1111222233334444", "1111", 500);
	Card card2 = new Card("1111222233334444", "1212", 1000);

	// test authenticateUser method with valid card details
	@Test
	public void testAuthenticateUserValidInput() {

		when(bank.authenticateUser(card1)).thenReturn(true);

		assertTrue(bank.authenticateUser(card1));

		verify(bank, times(1)).authenticateUser(card1);

	}

	// test authenticateUser method with invalid card details
	@Test
	public void testAuthenticateUserInvalidInput() {

		when(bank.authenticateUser(card2)).thenReturn(false);

		assertFalse(bank.authenticateUser(card2));

		verify(bank, times(1)).authenticateUser(card2);

	}

	// test checkAmount method, test-1
	@Test
	public void testCheckAmount1() {

		when(bank.checkAmount(card1)).thenReturn(500);

		assertEquals(500, bank.checkAmount(card1));

		verify(bank, times(1)).checkAmount(card1);

	}

	// test checkAmount method, test-2
	@Test
	public void testCheckAmount2() {

		when(bank.checkAmount(card2)).thenReturn(0);

		assertEquals(0, bank.checkAmount(card2));

		verify(bank, times(1)).checkAmount(card2);

	}

	// test withdrawAmount method with valid amount
	@Test
	public void testWithdrawAmountValidInput() {

		when(bank.withdrawAmount(card1, 200)).thenReturn(true);

		assertEquals(true, bank.withdrawAmount(card1, 200));

		verify(bank, times(1)).withdrawAmount(card1, 200);
	}

	// test withdrawAmount method with invalid negative amount
	@Test
	public void testWithdrawAmountInvalidInput() {

		when(bank.withdrawAmount(card1, -100)).thenReturn(false);

		assertEquals(false, bank.withdrawAmount(card1, -100));

		verify(bank, times(1)).withdrawAmount(card1, -100);
	}

	// test depositAmount method with valid amount
	@Test
	public void testDepositAmountValidInput() {

		when(bank.depositAmount(card1, 100)).thenReturn(true);

		assertEquals(true, bank.depositAmount(card1, 100));

		verify(bank, times(1)).depositAmount(card1, 100);

	}

	// test depositAmount method with invalid negative amount
	@Test
	public void testDepositAmountInvalidInput() {

		when(bank.depositAmount(card1, -300)).thenReturn(false);

		assertEquals(false, bank.depositAmount(card1, -300));

		verify(bank, times(1)).depositAmount(card1, -300);

	}

}
