package codelinguists.codelingo.logic;

import static org.junit.Assert.*;
import org.junit.Test;

import CodeLinguists.codelingo.persistence.persistenceExceptions.AccountNotFoundException;
import CodeLinguists.codelingo.logic.logicExceptions.InputValidationException;
public class ExceptionsTest {
    @Test
    public void testAccountNotFound() {
        boolean caught = false;
        try {
            accountNotFoundException();
        } catch (AccountNotFoundException e){
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    public void testInputValidation() {
        boolean caught = false;
        try {
            invalidInput();
        } catch (InputValidationException e){
            caught = true;
        }
        assertTrue(caught);
    }

    private void accountNotFoundException() throws AccountNotFoundException {
        throw new AccountNotFoundException("Can't find account");
    }

    private void invalidInput() {
        throw new InputValidationException("Input is invalid");
    }
}
