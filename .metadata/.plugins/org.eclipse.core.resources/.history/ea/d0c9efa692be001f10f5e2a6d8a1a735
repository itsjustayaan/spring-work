package com.working;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.working.dao.InvestmentAdvisorDAO;
import com.working.dao.InvestorDAO;
import com.working.dao.StockDAO;
import com.working.model.InvestmentAdvisor;
import com.working.model.Investor;
import com.working.model.Stock;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestBasketInvestingApplication {

    @Mock
    private InvestorDAO investorDAO;
    
    @Mock
    private InvestmentAdvisorDAO investorAdvisorDAO;
    
    @Mock
    private StockDAO stockDao;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private InvestmentAdvisor advisor1;

    @Mock
    private InvestmentAdvisor advisor2;

    @Mock
    private Investor investor1;

    @Mock
    private Investor investor2;

    @Mock
    private Investor investor3;
    
    @Mock
    private Stock stock1;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    @Order(1)
    void testCreateAdvisors1() {
        // Arrange
        advisor1 = new InvestmentAdvisor("Jane Advisor", "jane@example.com", "password");
        when(investorAdvisorDAO.save(any(InvestmentAdvisor.class))).thenReturn(advisor1);

        // Act
        InvestmentAdvisor createdAdvisor = investorAdvisorDAO.save(advisor1);

        // Assert
        assertNotNull(createdAdvisor);
        assertEquals("Jane Advisor", createdAdvisor.getIaName());
    }

    @Test
    @Order(2)
    void testCreateAdvisors2() {
        // Arrange
        advisor2 = new InvestmentAdvisor("Tom Advisor", "sura.mitraa@gmail.com", "password");
        when(investorAdvisorDAO.save(any(InvestmentAdvisor.class))).thenReturn(advisor2);

        // Act
        InvestmentAdvisor createdAdvisor = investorAdvisorDAO.save(advisor2);

        // Assert
        assertNotNull(createdAdvisor);
        assertEquals("Tom Advisor", createdAdvisor.getIaName());
    }

    @Test
    @Order(3)
    void testCreateInvestors1() {
        // Arrange
        investor1 = new Investor("Alice Smith", "alice@example.com", "password1", 10000);
        when(investorDAO.save(any(Investor.class))).thenReturn(investor1);

        // Act
        Investor createdInvestor = investorDAO.save(investor1);

        // Assert
        assertNotNull(createdInvestor);
        assertEquals("Alice Smith", createdInvestor.getInvestorName());
    }

    @Test
    @Order(4)
    void testCreateInvestors2() {
        // Arrange
        investor2 = new Investor("Bob Johnson", "bob@example.com", "password2", 20000);
        when(investorDAO.save(any(Investor.class))).thenReturn(investor2);

        // Act
        Investor createdInvestor = investorDAO.save(investor2);

        // Assert
        assertNotNull(createdInvestor);
        assertEquals("Bob Johnson", createdInvestor.getInvestorName());
    }

    @Test
    @Order(5)
    void testCreateInvestors3() {
        // Arrange
        investor3 = new Investor("Charlie Brown", "charlie@example.com", "password3", 30000);
        when(investorDAO.save(any(Investor.class))).thenReturn(investor3);

        // Act
        Investor createdInvestor = investorDAO.save(investor3);

        // Assert
        assertNotNull(createdInvestor);
        assertEquals("Charlie Brown", createdInvestor.getInvestorName());
    }

    @Test
    @Order(6)
    void testInsertStocks() {
        // Arrange
    	stock1 = new Stock("INE423A01024", "Adani Enterprises Ltd.", "Metals & Mining", "ADANIENT", "2450.50");
        doNothing().when(stockDao.save(any(Stock.class)));

        // Assert
//        verify(stockDao, times(1)).save(any(Stock.class));
    }

    @Test
    @Order(7)
    void testInsertBaskets() {
        // Arrange
        doNothing().when(investorDAO).insertBaskets(any(), any(), any());

        // Act
        investorDAO.insertBaskets("Diversified Portfolio", "A mix of stocks from various industries", 1);

        // Assert
        verify(investorDAO, times(1)).insertBaskets(any(), any(), any());
    }

    @Test
    @Order(8)
    void testInsertBasketAndStock() {
        // Arrange
        doNothing().when(investorDAO).insertBasketAndStock(any(), any(), any());

        // Act
        investorDAO.insertBasketAndStock(1, "INE423A01024", 100);

        // Assert
        verify(investorDAO, times(1)).insertBasketAndStock(any(), any(), any());
    }

    @Test
    @Order(9)
    void testInsertInvestorAndBasket() {
        // Arrange
        doNothing().when(investorDAO).insertInvestorAndBasket(any(), any(), any(), any());

        // Act
        investorDAO.insertInvestorAndBasket(1, 1, 100, new BigDecimal(2450.50));

        // Assert
        verify(investorDAO, times(1)).insertInvestorAndBasket(any(), any(), any(), any());
    }

    @Test
    @Order(10)
    void testCheckAbsoluteReturns() {
        // Arrange
        when(investorDAO.findByInvestorEmail("alice@example.com")).thenReturn(Optional.of(investor1));

        // Act
        BigDecimal absoluteReturn = investorDAO.calculateAbsoluteReturn(investor1);

        // Assert
        assertEquals(BigDecimal.valueOf(1469786737.5), absoluteReturn);
    }

    @Test
    @Order(11)
    void testUpdateInvestmentAdvisor() {
        // Arrange
        InvestmentAdvisor updatedAdvisor = new InvestmentAdvisor(advisor1.getIaId(), "Updated Advisor", "sura.mitraa@gmail.com", "updatedpassword");
        when(investorDAO.save(any(InvestmentAdvisor.class))).thenReturn(updatedAdvisor);

        // Act
        InvestmentAdvisor result = investorDAO.save(updatedAdvisor);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Advisor", result.getIaName());
    }

    @Test
    @Order(12)
    void testCreateBasket() {
        // Arrange
        String createBasketJson = "{ \"basketId\": 0, \"basketName\": \"New Basket\", \"basketSummary\": \"Summary\", \"iaId_ref\": 1, \"basketStockList\": [], \"investorAndBasketList\": [] }";
        when(investorDAO.createBasket(anyString(), anyString(), anyInt(), any())).thenReturn(true);

        // Act
        boolean isCreated = investorDAO.createBasket("New Basket", "Summary", 1, new BigDecimal(100));

        // Assert
        assertTrue(isCreated);
    }

    @Test
    @Order(13)
    void testForgetPassword() {
        // Arrange
        when(investorDAO.findByInvestorEmail("sura.mitraa@gmail.com")).thenReturn(Optional.of(advisor2));

        // Act
        Optional<InvestmentAdvisor> result = investorDAO.findByInvestorEmail("sura.mitraa@gmail.com");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("sura.mitraa@gmail.com", result.get().getIaEmail());
    }

    @Test
    @Order(14)
    void testUpdateInvestor() {
        // Arrange
        when(investorDAO.findByInvestorEmail(investor1.getInvestorEmail())).thenReturn(Optional.of(investor1));
        Investor updatedInvestor = new Investor(
                investor1.getInvestorId(),
                "Updated Investor Name",
                "updated.email@example.com", 
                "newpassword"
            );

        when(investorDAO.save(any(Investor.class))).thenReturn(updatedInvestor);

        // Act
        Investor result = investorDAO.save(updatedInvestor);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Investor Name", result.getInvestorName());
    }

    @Test
    @Order(15)
    void testUpdateInvestorBalance() {
        // Arrange
        Investor updatedBalanceInvestor = new Investor("Investor Name", "investor.email@example.com", "password", 20000.829);
        when(investorDAO.save(any(Investor.class))).thenReturn(updatedBalanceInvestor);

        // Act
        Investor result = investorDAO.save(updatedBalanceInvestor);

        // Assert
        assertNotNull(result);
        assertEquals(20000.829, result.getInvestorBalance());
    }

    @Test
    @Order(16)
    void testSellInvestorBasket() {
        // Arrange
        when(investorDAO.sellBasket(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(true);

        // Act
        boolean isSold = investorDAO.sellBasket(1, 1, 20, 100);

        // Assert
        assertTrue(isSold);
    }
}
