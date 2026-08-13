package dev.harrish.EcomProductService.repository;

import dev.harrish.EcomProductService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer>
{

}
