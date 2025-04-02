package joshuanelsondev.bankmanagementsystem;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BankServiceTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private InputStream originalIn;

    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        originalIn = System.in;
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    private String getOutput() {
        return outputStream.toString();
    }

    @Test
    public void testCreateSavingsAccountFromMenu() {
        provideInput("1\n12345\n1000\n1\n5\n"); // 1: Create Account, then account details, then 5 to exit
        BankManagementSystem.main(new String[]{}); // Run the main method
        assertTrue(getOutput().contains("Account created successfully."));
    }

    @Test
    public void testDepositMoneyFromMenu() {
        provideInput("1\n12345\n1000\n1\n2\n12345\n500\n5\n"); // create account, then deposit, then exit.
        BankManagementSystem.main(new String[]{});
        assertTrue(getOutput().contains("Deposited: 500.0"));
    }

    @Test
    public void testWithdrawMoneyFromMenu() {
        provideInput("1\n12345\n1000\n1\n3\n12345\n200\n5\n");
        BankManagementSystem.main(new String[]{});
        assertTrue(getOutput().contains("Withdrawing: 200.0"));
    }

    @Test
    public void testCheckBalanceFromMenu() {
        provideInput("1\n12345\n1000\n1\n4\n12345\n5\n");
        BankManagementSystem.main(new String[]{});
        assertTrue(getOutput().contains("Current balance: 1000.0"));
    }

    @Test
    public void testInvalidMenuChoice() {
        provideInput("6\n5\n");
        BankManagementSystem.main(new String[]{});
        assertTrue(getOutput().contains("Invalid choice. Please try again."));
    }


    @org.junit.After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
}
