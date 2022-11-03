package academy.digitallab.store.customer;

import academy.digitallab.store.customer.entity.Customer;
import academy.digitallab.store.customer.entity.Region;
import academy.digitallab.store.customer.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CustomerRepositoryMockTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void whenFindByRegion_thenReturnListCustomer(){
        Customer customer = Customer.builder()
                .id(5l)
                .numberID("12345678")
                .firstName("Ivan")
                .lastName("Mendoza")
                .email("ivanx_21@hotmail.com")
                .photoUrl("")
                .state("CREATED")
                .region(Region.builder().id(1l).build())
                .build();

        customerRepository.save(customer);
        List<Customer> found = customerRepository.findByRegion(customer.getRegion());

        Assertions.assertThat(found.size()).isEqualTo(2);
    }

    @Test
    public void whenFindByLastName_thenReturnListCustomer(){
        Customer customer = Customer.builder()
                .id(2l)
                .numberID("12345678")
                .firstName("Ivan")
                .lastName("xxx")
                .email("ivanx_21@hotmail.com")
                .photoUrl("")
                .state("CREATED")
                .region(Region.builder().id(1l).build())
                .build();
        customerRepository.save(customer);

        List<Customer> found = customerRepository.findByLastName(customer.getLastName());
        Assertions.assertThat(found.size()).isEqualTo(2);
    }

    @Test
    public void whenFindByNumberId_thenReturnCustomer(){
        /*
        Customer customer = Customer.builder()
                .id(5l)
                .numberID("12345678")
                .firstName("Ivan")
                .lastName("Guzmán")
                .email("ivanx_21@hotmail.com")
                .photoUrl("")
                .state("CREATED")
                .region(Region.builder().id(1l).build())
                .build();
        customerRepository.save(customer);
         */
        Customer customer1 = customerRepository.findByNumberID("32404580");
        Assertions.assertThat(customer1.getNumberID()).isEqualTo("32404580");
    }

}
