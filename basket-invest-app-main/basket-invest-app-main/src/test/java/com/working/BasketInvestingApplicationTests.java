package com.working;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.working.dao.InvestorDAO;
import com.working.model.InvestmentAdvisor;
import com.working.model.Investor;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BasketInvestingApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    InvestorDAO investorDAO;

    @Autowired
    private static JdbcTemplate jdbcTemplate;

    private static Investor investor1;
    private static Investor investor2;
    private static Investor investor3;
    private static InvestmentAdvisor advisor1;
    private static InvestmentAdvisor advisor2;

    @BeforeAll
    static void setup(@Autowired JdbcTemplate jdbcTemplate) {
        BasketInvestingApplicationTests.jdbcTemplate = jdbcTemplate;

        JdbcTestUtils.deleteFromTables(jdbcTemplate, "investor_and_basket", "basket_and_stock", "basket", "stock", "users", "authorities");

        String insertUser = "INSERT INTO users (username, password, enabled) VALUES ('admin', 'admin', true)";
        jdbcTemplate.update(insertUser);

        String insertAuthority = "INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN')";
        jdbcTemplate.update(insertAuthority);

        investor1 = new Investor("Alice Smith", "alice@example.com", "password1", 10000);
        investor2 = new Investor("Bob Johnson", "bob@example.com", "password2", 20000);
        investor3 = new Investor("Charlie Brown", "charlie@example.com", "password3", 30000);

        advisor1 = new InvestmentAdvisor("Jane Advisor", "jane@example.com", "password");
        advisor2 = new InvestmentAdvisor("Tom Advisor", "sura.mitraa@gmail.com", "password");
    }

    @Test
    @Order(1)
    void testCreateAdvisors1() throws Exception {
        createAdvisor(advisor1);
    }
    
    @Test
    @Order(2)
    void testCreateAdvisors2() throws Exception {
        createAdvisor(advisor2);
    }
    
    @Test
    @Order(3)
    void testCreateInvestors1() throws Exception {
        createInvestor(investor1);
    }
    
    @Test
    @Order(4)
    void testCreateInvestors2() throws Exception {
        createInvestor(investor2);
    }
    
    @Test
    @Order(5)
    void testCreateInvestors3() throws Exception {
        createInvestor(investor3);
    }

    @Test
    @Order(6)
    void testInsertStocks() {
        insertStocks();
    }

    @Test
    @Order(7)
    void testInsertBaskets() {
        insertBaskets();
    }

    @Test
    @Order(8)
    void testInsertBasketAndStock() {
        insertBasketAndStock();
    }

    @Test
    @Order(9)
    void testInsertInvestorAndBasket() {
        insertInvestorAndBasket();
    }

    @Test
    @Order(10)
    void testCheckAbsoluteReturns() throws Exception {
        checkAbsoluteReturn(investor1, BigDecimal.valueOf(1469786737.5));
    }

    @Test
    @Order(11)
    void testUpdateInvestmentAdvisor() throws Exception {
        InvestmentAdvisor updatedAdvisor = new InvestmentAdvisor(advisor1.getIaId(), "Updated Advisor", "sura.mitraa@gmail.com", "updatedpassword");
        mockMvc.perform(put("/ia/testUpdate")
                .with(httpBasic(advisor1.getIaEmail(), advisor1.getIaPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedAdvisor)))
                .andExpect(status().isOk());
    }
    
    @Test
    @Order(12)
    void testCreateBasket() throws Exception {
        String createBasketJson = "{ \"basketId\": 0, \"basketName\": \"New Basket\", \"basketSummary\": \"Summary\", \"iaId_ref\": " + advisor1.getIaId() + ", \"basketStockList\": [], \"investorAndBasketList\": [] }";

        mockMvc.perform(post("/ia/testCreateBasket")
                .with(httpBasic(advisor1.getIaEmail(), advisor1.getIaPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(createBasketJson))
                .andExpect(status().isOk());
    }
    
    @Test
    @Order(13)
    void testForgetPassword() throws Exception {
        String email = advisor2.getIaEmail();
        mockMvc.perform(get("/testForgetPass/ia/" + email))
                .andExpect(status().isOk());
    }
    
    @Test
    @Order(14)
    void testUpdateInvestor() throws Exception {
        Investor existingInvestor = investorDAO.findByInvestorEmail(investor1.getInvestorEmail()).get(0);
        Investor updatedInvestor = new Investor(
                existingInvestor.getInvestorId(),
                existingInvestor.getInvestorName(),
                "updated.email@example.com", 
                "newpassword"
            );
        
        mockMvc.perform(put("/investor/update")
                .with(httpBasic(investorDAO.findByInvestorEmail(investor1.getInvestorEmail()).get(0).getInvestorEmail(), investorDAO.findByInvestorEmail(investor1.getInvestorEmail()).get(0).getInvestorPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedInvestor)))
                .andExpect(status().isOk());
    }

    
    @Test
    @Order(15)
    void testUpdateInvestorBalance() throws Exception {
        // Prepare an updated balance object
        Investor updatedBalanceInvestor = new Investor("Investor Name", "investor.email@example.com", "password", 20000.829);
        investor1 = investorDAO.findByInvestorEmail("updated.email@example.com").get(0);
        mockMvc.perform(put("/investor/addBalance")
                .with(httpBasic(investor1.getInvestorEmail(), investor1.getInvestorPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBalanceInvestor)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(16)
    void testSellInvestorBasket() throws Exception {
        String sellBasketJson = "{ \"investorId\": " + investorDAO.findByInvestorEmail(investor2.getInvestorEmail()).get(0).getInvestorId() + ", \"basketId\": 3, \"quantity\": 20 }";

        mockMvc.perform(post("/investor/sell")
                .with(httpBasic(investor2.getInvestorEmail(), investor2.getInvestorPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(sellBasketJson))
                .andExpect(status().isOk());
    }

//    @Test
//    @Order(17)
//    void testBuyInvestorBasket() throws Exception {
//        String buyBasketJson = "{ \"investor\": \"Investor Name\", \"basket\": { \"basketId\": 1, \"basketName\": \"Sample Basket\", \"basketSummary\": \"Summary\", \"iaId_ref\": 1, \"basketStockList\": [], \"investorAndBasketList\": [] }, \"quantity\": 5, \"priceBought\": 500, \"id\": 0 }";
//
//        mockMvc.perform(post("/investor/buy")
//                .with(httpBasic(investor1.getInvestorEmail(), investor1.getInvestorPassword()))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(buyBasketJson))
//                .andExpect(status().isOk());
//    }


    private void createInvestor(Investor investor) throws Exception {
        mockMvc.perform(post("/admin/createInvestor")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(investor)))
                .andExpect(status().isCreated());
    }

    private void createAdvisor(InvestmentAdvisor advisor) throws Exception {
        mockMvc.perform(post("/admin/createAdvisor")
                .with(httpBasic("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(advisor)))
                .andExpect(status().isCreated());
    }

    private void insertStocks() {
        String insertStocks = "INSERT INTO stock (isin, stock_name, industry, stock_symbol, stock_price) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertStocks, "INE423A01024", "Adani Enterprises Ltd.", "Metals & Mining", "ADANIENT", "2450.50");
        jdbcTemplate.update(insertStocks, "INE742F01042", "Adani Ports and Special Economic Zone Ltd.", "Services", "ADANIPORTS", "750.25");
        jdbcTemplate.update(insertStocks, "INE437A01024", "Apollo Hospitals Enterprise Ltd.", "Healthcare", "APOLLOHOSP", "4300.75");
        jdbcTemplate.update(insertStocks, "INE021A01026", "Asian Paints Ltd.", "Consumer Durables", "ASIANPAINT", "3500.90");
        jdbcTemplate.update(insertStocks, "INE238A01034", "Axis Bank Ltd.", "Financial Services", "AXISBANK", "850.50");
    }

    private void insertBaskets() {
        String insertBaskets = "INSERT INTO basket (basket_name, basket_summary, ia_id_ref) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertBaskets, "Diversified Portfolio", "A mix of stocks from various industries", 1);
        jdbcTemplate.update(insertBaskets, "Growth Stocks", "High-growth potential stocks", 2);
        jdbcTemplate.update(insertBaskets, "Dividend Yield", "Stocks with attractive dividend yields", 3);
        jdbcTemplate.update(insertBaskets, "Index Tracker", "Mirrors the market index", 4);
        jdbcTemplate.update(insertBaskets, "Sector Rotator", "Rotates between high-performing sectors", 5);
    }

    private void insertBasketAndStock() {
        String insertBasketAndStock = "INSERT INTO basket_and_stock (basket_id, stock_isin, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertBasketAndStock, 1, "INE423A01024", 100);
        jdbcTemplate.update(insertBasketAndStock, 1, "INE742F01042", 50);
        jdbcTemplate.update(insertBasketAndStock, 2, "INE437A01024", 200);
        jdbcTemplate.update(insertBasketAndStock, 2, "INE021A01026", 150);
        jdbcTemplate.update(insertBasketAndStock, 3, "INE238A01034", 250);
        jdbcTemplate.update(insertBasketAndStock, 4, "INE423A01024", 50);
        jdbcTemplate.update(insertBasketAndStock, 4, "INE742F01042", 100);
        jdbcTemplate.update(insertBasketAndStock, 5, "INE437A01024", 100);
    }

    private void insertInvestorAndBasket() {
        String insertInvestorAndBasket = "INSERT INTO investor_and_basket (investor_id, basket_id, quantity, price_bought) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(insertInvestorAndBasket, 1, 1, 100, new BigDecimal(2450.50));
        jdbcTemplate.update(insertInvestorAndBasket, 1, 2, 50, new BigDecimal(750.25));
        jdbcTemplate.update(insertInvestorAndBasket, 2, 3, 200, new BigDecimal(4300.75));
        jdbcTemplate.update(insertInvestorAndBasket, 2, 4, 150, new BigDecimal(3500.90));
        jdbcTemplate.update(insertInvestorAndBasket, 3, 1, 250, new BigDecimal(850.50));
        jdbcTemplate.update(insertInvestorAndBasket, 3, 5, 100, new BigDecimal(3750.00));
    }

    private void checkAbsoluteReturn(Investor investor, BigDecimal expectedReturn) throws Exception {
        mockMvc.perform(get("/investor/viewAr")
                .param("investorId", String.valueOf(investor.getInvestorId()))
                .with(httpBasic(investor.getInvestorEmail(), investor.getInvestorPassword()))) // Use investor's credentials
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.absoluteReturn").value(expectedReturn));
    }


    @AfterAll
    static void dropTables() {
        // Drop tables
        jdbcTemplate.execute("DROP TABLE IF EXISTS investor_and_basket");
        jdbcTemplate.execute("DROP TABLE IF EXISTS basket_and_stock");
        jdbcTemplate.execute("DROP TABLE IF EXISTS basket");
        jdbcTemplate.execute("DROP TABLE IF EXISTS stock");
        jdbcTemplate.execute("DROP TABLE IF EXISTS investment_advisor");
        jdbcTemplate.execute("DROP TABLE IF EXISTS investor");
        jdbcTemplate.execute("DROP TABLE IF EXISTS admins");
        jdbcTemplate.execute("DROP TABLE IF EXISTS authorities");
        jdbcTemplate.execute("DROP TABLE IF EXISTS users");
    }
}
