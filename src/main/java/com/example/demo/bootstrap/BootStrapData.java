package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // partRepository.deleteAll();
        // productRepository.deleteAll();

        // Check if database is empty before adding sample inventory

        long partCount = partRepository.count();
        long productCount = productRepository.count();

        if (partCount == 0 && productCount == 0) {
            System.out.println("Adding sample inventory...");

            // Add parts
            OutsourcedPart part1 = new OutsourcedPart();
            part1.setCompanyName("Intel");
            part1.setName("CPU Processor");
            part1.setInv(10);
            part1.setPrice(500.0);
            part1.setMinInv(5);
            part1.setMaxInv(20);
            partRepository.save(part1);

            OutsourcedPart part2 = new OutsourcedPart();
            part2.setCompanyName("NVIDIA");
            part2.setName("GPU (Graphics Card)");
            part2.setInv(15);
            part2.setPrice(200.0);
            part2.setMinInv(0);
            part2.setMaxInv(20);
            partRepository.save(part2);

            OutsourcedPart part3 = new OutsourcedPart();
            part3.setCompanyName("Corsair");
            part3.setName("Set of Two RAM Sticks");
            part3.setInv(10);
            part3.setPrice(180.0);
            part3.setMinInv(10);
            part3.setMaxInv(30);
            partRepository.save(part3);

            OutsourcedPart part4 = new OutsourcedPart();
            part4.setCompanyName("Samsung");
            part4.setName("Storage Device (SSD / HDD)");
            part4.setInv(30);
            part4.setPrice(150.0);
            part4.setMinInv(5);
            part4.setMaxInv(50);
            partRepository.save(part4);

            OutsourcedPart part5 = new OutsourcedPart();
            part5.setCompanyName("EVGA");
            part5.setName("PSU (Power Supply Unit)");
            part5.setInv(20);
            part5.setPrice(50.0);
            part5.setMinInv(0);
            part5.setMaxInv(25);
            partRepository.save(part5);

            // Add products
            Product product1 = new Product("Gaming Desktop", 1200.0, 10);
            Product product2 = new Product("Office Laptop", 800.0, 20);
            Product product3 = new Product("Student Laptop", 500.0, 10);
            Product product4 = new Product("All-in-One Desktop", 900.0, 20);
            Product product5 = new Product("Ultrabook", 700.0, 30);

            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);

            System.out.println("Sample inventory added.");
        } else {
            System.out.println("Sample inventory already exists.");
        }

        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
