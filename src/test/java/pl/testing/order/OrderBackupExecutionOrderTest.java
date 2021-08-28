package pl.testing.order;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExecutionOrderTest {

    @Test
    void callingBackupWithoutCreatingFileShouldThrowException() throws IOException{
        //given
        OrderBackup orderBackup = new OrderBackup();

        //when
        //then
        assertThrows(IOException.class, ()-> orderBackup.backupOrder(new Order()));
    }
}
