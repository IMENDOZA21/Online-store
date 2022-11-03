package academy.digitallab.store.customer;

import academy.digitallab.store.customer.entity.Customer;
import academy.digitallab.store.customer.entity.Region;
import academy.digitallab.store.customer.repository.CustomerRepository;
import academy.digitallab.store.customer.service.CustomerService;
import academy.digitallab.store.customer.service.CustomerServiceImpl;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.This;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CustomerServiceMockTest {

    @Mock
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
        Customer customer = Customer.builder()
                .id(5L)
                .numberID("12345678")
                .firstName("Ivan")
                .lastName("xxx")
                .email("ivanx_21@hotmail.com")
                .photoUrl("")
                .state("CREATED")
                .region(Region.builder().id(4l).name("Europa").build())
                .build();
        customerRepository.save(customer);
        List<Customer> found = customerRepository.findAll();
        Mockito.when(customerRepository.findAll()).thenReturn(found);
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.findByRegion(customer.getRegion())).thenReturn(found);
        Mockito.when(customerRepository.findByNumberID(customer.getNumberID())).thenReturn(customer);
    }
    @Test
    public void whenFindCustomerAll_thenReturnListCustomer(){
        List<Customer> customers = customerService.findCustomerAll();
        Assertions.assertThat(customers.size()).isEqualTo(2);
    }
    @Test
    public void whenGetCustomer_thenReturnCustomer(){
        Customer customer = customerService.getCustomer(5L);
        Assertions.assertThat(customer.getFirstName()).isEqualTo("Ivan");
    }
    /*
    public void whenFindCustomersByRegion_thenReturnListCustomer(){

        List<Customer> customers = customerService.findCustomersByRegion();
        Assertions.assertThat(customers.size()).isEqualTo(2);
    }

     */
}
